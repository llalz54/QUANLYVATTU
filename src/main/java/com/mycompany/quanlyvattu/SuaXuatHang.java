package com.mycompany.quanlyvattu;

/**
 *
 * @author Admin
 */
import ConDB.DBAccess;
import DAO.GroupItem;
import DAO.UpperCase;
import DAO.LOAISP_DATA;
import DAO.PHIEUXUAT_DATA;
import DAO.NHOMSP_DATA;
import DTO.LOAISP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SuaXuatHang extends javax.swing.JPanel {

    private NHOMSP_DATA nhomsp_data = new NHOMSP_DATA();
    private LOAISP_DATA loaisp_data = new LOAISP_DATA();
    private ArrayList<LOAISP> loaisps;
    private boolean isEditMode;
    private int idPhieuXuatDangChinhSua;

    /**
     * Creates new form ChiTietXuatHang
     */
    public SuaXuatHang() {
        initComponents();
    }
    

    public void setFormData(int idpx, String tenSP, int soLuong, int donGia, String khachHang, String ngayBH, List<String> serials) {
    cb_Brand.setSelectedItem(tenSP);
    tf_soLuong.setText(String.valueOf(soLuong));
    tf_giaXuat.setText(String.valueOf(donGia));
    tf_khachHang.setText(khachHang);
    tf_ngayXuat.setText(ngayBH);

    // Fill serial vào bảng
    create_TB_CTPX(soLuong);
    DefaultTableModel model = (DefaultTableModel) tb_CTPX.getModel();
    int row = 0, col = 0;
    for (String serial : serials) {
        model.setValueAt(serial, row, col++);
        if (col == model.getColumnCount()) {
            col = 0;
            row++;
        }
    }

    // Đặt chế độ sửa
    this.isEditMode = true;
    this.idPhieuXuatDangChinhSua = idpx;
}
    private void loadSerialByPhieuXuatID(int idpx) {
    try {
        List<String> serials = new PHIEUXUAT_DATA().getSerialByPhieuXuatID(idpx);
        int quantity = serials.size();
        create_TB_CTPX(quantity);
        DefaultTableModel model = (DefaultTableModel) tb_CTPX.getModel();

        for (int i = 0; i < serials.size(); i++) {
            int row = i / 10;
            int col = i % 10;
            model.setValueAt(serials.get(i), row, col);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
     public boolean updateXuatHang(int idpx, int userId, int categoryId, int quantity, int price,
            String customer, String ngayXuat,
            String startDate, String endDate,
            List<String> listSerial) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBAccess().getConnection();
            conn.setAutoCommit(false); // bắt đầu transaction

            // 1. Cập nhật thông tin phiếu xuất
            String updatePX = "UPDATE PhieuXuat SET user_id=?, category_id=?, quantity=?, price=?, customer=?, ngayXuat=? WHERE idpx=?";
            ps = conn.prepareStatement(updatePX);
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ps.setInt(3, quantity);
            ps.setInt(4, price);
            ps.setString(5, customer);
            ps.setString(6, ngayXuat);
            ps.setInt(7, idpx);
            ps.executeUpdate();
            ps.close();

            // 2. Xóa CTPX cũ
            String deleteCTPX = "DELETE FROM CTPX WHERE idpx=?";
            ps = conn.prepareStatement(deleteCTPX);
            ps.setInt(1, idpx);
            ps.executeUpdate();
            ps.close();

            // 3. Chèn lại CTPX mới và cập nhật sản phẩm
            String insertCTPX = "INSERT INTO CTPX(idpx, serial) VALUES (?, ?)";
            String updateSP = "UPDATE SanPham SET status = 1, start_date = ?, end_date = ? WHERE serial = ?";
            for (String serial : listSerial) {
                // insert CTPX
                ps = conn.prepareStatement(insertCTPX);
                ps.setInt(1, idpx);
                ps.setString(2, serial);
                ps.executeUpdate();
                ps.close();

                // update SanPham
                ps = conn.prepareStatement(updateSP);
                ps.setString(1, startDate);
                ps.setString(2, endDate);
                ps.setString(3, serial);
                ps.executeUpdate();
                ps.close();
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadSerialTheoIDPhieuXuat(int idpx) throws SQLException {
    DefaultTableModel model = (DefaultTableModel) tb_CTPX.getModel();
    model.setRowCount(0); // clear
    PHIEUXUAT_DATA data = new PHIEUXUAT_DATA();
    List<String> serials = data.getSerialByPhieuXuatID(idpx); // bạn cần viết hàm này trong DAO
    int column = 10;
    int row = (int) Math.ceil((double) serials.size() / 10);

    for (int i = 0; i < row; i++) {
        Object[] rowData = new Object[column];
        for (int j = 0; j < column; j++) {
            int index = i * column + j;
            if (index < serials.size()) {
                rowData[j] = serials.get(index);
            }
        }
        model.addRow(rowData);
    }
}

    private void create_TB_CTPX(int quantity) {
        int column = 10;
        int row = (int) Math.ceil((double) quantity / 10);

        String[] columnNames = new String[column];
        for (int i = 0; i < column; i++) {
            columnNames[i] = "" + (i + 1);
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                int cellIndex = rowIndex * column + columnIndex;
                return cellIndex < quantity; // chỉ các ô trong số lượng cho phép mới được chỉnh
            }
        };

        for (int i = 0; i < row; i++) {
            model.addRow(new Object[column]);
        }

        tb_CTPX.setModel(model);
        // Gán editor in hoa cho mỗi ô
        UpperCase upperEditor = new UpperCase();
        for (int i = 0; i < tb_CTPX.getColumnCount(); i++) {
            tb_CTPX.getColumnModel().getColumn(i).setCellEditor(upperEditor);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelXuatHang = new javax.swing.JPanel();
        cb_Brand = new javax.swing.JComboBox<>();
        tf_soLuong = new javax.swing.JTextField();
        tf_ngayXuat = new javax.swing.JTextField();
        tf_giaXuat = new javax.swing.JTextField();
        tf_khachHang = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_Luu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_CTPX = new javax.swing.JTable();
        cb_GrProduct1 = new javax.swing.JComboBox<>();
        cb_Time = new javax.swing.JComboBox<>();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelXuatHang.setPreferredSize(new java.awt.Dimension(1000, 700));

        cb_Brand.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên SP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        cb_Brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BrandActionPerformed(evt);
            }
        });

        tf_soLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số Lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tf_ngayXuat.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Ngày Bảo Hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tf_giaXuat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá Xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tf_khachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("SỬA XUẤT VẬT TƯ");

        btn_Luu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        tb_CTPX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_CTPX.setRowHeight(40);
        tb_CTPX.setRowSelectionAllowed(false);
        tb_CTPX.setShowGrid(true);
        jScrollPane1.setViewportView(tb_CTPX);

        cb_GrProduct1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhóm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        cb_GrProduct1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_GrProduct1ItemStateChanged(evt);
            }
        });

        cb_Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12 tháng", "24 tháng", "36 tháng" }));
        cb_Time.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")), "Thời gian bảo hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        javax.swing.GroupLayout panelXuatHangLayout = new javax.swing.GroupLayout(panelXuatHang);
        panelXuatHang.setLayout(panelXuatHangLayout);
        panelXuatHangLayout.setHorizontalGroup(
            panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXuatHangLayout.createSequentialGroup()
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXuatHangLayout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelXuatHangLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_soLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(tf_ngayXuat)
                            .addComponent(cb_GrProduct1, 0, 159, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_Brand, 0, 164, Short.MAX_VALUE)
                            .addComponent(tf_giaXuat)
                            .addComponent(tf_khachHang))
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelXuatHangLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(cb_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelXuatHangLayout.createSequentialGroup()
                                .addGap(245, 245, 245)
                                .addComponent(btn_Luu)))))
                .addContainerGap(287, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        panelXuatHangLayout.setVerticalGroup(
            panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXuatHangLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_Time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_GrProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_giaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_ngayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelXuatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelXuatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
       
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void cb_BrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BrandActionPerformed

    }//GEN-LAST:event_cb_BrandActionPerformed

    private void cb_GrProduct1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_GrProduct1ItemStateChanged
         
    }//GEN-LAST:event_cb_GrProduct1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuaXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SuaXuatHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Luu;
    private javax.swing.JComboBox<String> cb_Brand;
    private javax.swing.JComboBox<String> cb_GrProduct1;
    private javax.swing.JComboBox<String> cb_Time;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelXuatHang;
    private javax.swing.JTable tb_CTPX;
    private javax.swing.JTextField tf_giaXuat;
    private javax.swing.JTextField tf_khachHang;
    private javax.swing.JTextField tf_ngayXuat;
    private javax.swing.JTextField tf_soLuong;
    // End of variables declaration//GEN-END:variables
}
