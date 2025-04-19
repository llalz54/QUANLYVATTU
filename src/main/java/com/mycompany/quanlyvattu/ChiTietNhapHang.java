package com.mycompany.quanlyvattu;

import ConDB.CONNECTION;
import ConDB.DBAccess;
import DAO.CTPN_DATA;
import DAO.NHOMSP_DATA;
import javax.swing.table.DefaultTableModel;
import DAO.OTHER_DATA;
import DAO.PHIEUNHAP_DATA;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ChiTietNhapHang extends javax.swing.JFrame {

    private CTPN_DATA ctpn_Data = new CTPN_DATA();
    private PHIEUNHAP_DATA pn_Data = new PHIEUNHAP_DATA();
    private NHOMSP_DATA gr_Data = new NHOMSP_DATA();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public ChiTietNhapHang() {
        initComponents();
        OTHER_DATA.loadCBDM(cb_GrProduct);
        OTHER_DATA.load_Cb_Brand(cb_Brand);
    }

    private void create_TB_CTPN(int quantity) {
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

        tb_CTPN.setModel(model);
        String notification = "Hãy nhập Serial cho các thiết bị vừa nhập";

        JOptionPane.showMessageDialog(null, notification, "", JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadCB_ListSP(String gr, String brand) {
        cb_Product.removeAllItems();
        int group_ID = gr_Data.name_to_ID(gr);
        try {
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT name FROM LoaiSP WHERE group_id = '" + group_ID + "' AND BRAND ='" + brand + "'");
            while (rs.next()) {
                cb_Product.addItem(rs.getString("name").trim());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi loadCB_ListSP!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_CTPN = new javax.swing.JTable();
        cb_GrProduct = new javax.swing.JComboBox<>();
        cb_Brand = new javax.swing.JComboBox<>();
        cb_Supplier = new javax.swing.JComboBox<>();
        txt_Quantity = new javax.swing.JTextField();
        txt_ngayNhap = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        btn_Confirm = new javax.swing.JButton();
        cb_Product = new javax.swing.JComboBox<>();
        btn_GhiPhieu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 600));

        tb_CTPN.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_CTPN.setOpaque(false);
        tb_CTPN.setPreferredSize(new java.awt.Dimension(790, 400));
        tb_CTPN.setRowHeight(40);
        tb_CTPN.setRowSelectionAllowed(false);
        tb_CTPN.setShowGrid(true);
        jScrollPane1.setViewportView(tb_CTPN);
        if (tb_CTPN.getColumnModel().getColumnCount() > 0) {
            tb_CTPN.getColumnModel().getColumn(0).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(1).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(2).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(3).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(4).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(5).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(6).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(7).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(8).setResizable(false);
            tb_CTPN.getColumnModel().getColumn(9).setResizable(false);
        }

        cb_GrProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Máy in" }));
        cb_GrProduct.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhóm sản phẩm"));
        cb_GrProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_GrProductMouseClicked(evt);
            }
        });
        cb_GrProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_GrProductActionPerformed(evt);
            }
        });

        cb_Brand.setBorder(javax.swing.BorderFactory.createTitledBorder("Hãng"));
        cb_Brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BrandActionPerformed(evt);
            }
        });

        cb_Supplier.setEditable(true);
        cb_Supplier.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhà cung cấp"));

        txt_Quantity.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));

        txt_ngayNhap.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày nhập"));

        txt_price.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá nhập"));

        btn_Confirm.setText("XÁC NHẬN");
        btn_Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConfirmActionPerformed(evt);
            }
        });

        cb_Product.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        btn_GhiPhieu.setText("GHI PHIẾU");
        btn_GhiPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GhiPhieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_Quantity)
                            .addComponent(cb_GrProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Supplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_Brand, 0, 225, Short.MAX_VALUE)
                            .addComponent(txt_ngayNhap)
                            .addComponent(txt_price))))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Confirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_GhiPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(207, 207, 207))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_GrProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cb_Product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btn_Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_GhiPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConfirmActionPerformed
        // TODO add your handling code here:
        int quantity = Integer.parseInt(txt_Quantity.getText());
        create_TB_CTPN(quantity);
    }//GEN-LAST:event_btn_ConfirmActionPerformed

    private void btn_GhiPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GhiPhieuActionPerformed
        // TODO add your handling code here:
        if (tb_CTPN.isEditing()) {
            tb_CTPN.getCellEditor().stopCellEditing();
        }
        String grName = cb_GrProduct.getSelectedItem().toString();
        String supplier = cb_Supplier.getSelectedItem().toString();
        String ngayNhap = txt_ngayNhap.getText().trim();
        int quantity = Integer.parseInt(txt_Quantity.getText());
        int price = Integer.parseInt(txt_price.getText());

        int user_id = 1;
        int categoryID = gr_Data.name_to_ID(grName);

        Connection conn = CONNECTION.getConnection();
        try {
            conn.setAutoCommit(false);
            String sql_insertPN = "INSERT INTO PhieuNhap values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql_insertPN, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user_id);
            ps.setInt(2, categoryID);
            ps.setInt(3, quantity);
            ps.setInt(4, price);
            ps.setString(5, ngayNhap);
            ps.setString(6, supplier);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            int idpn = 0;
            while (rs.next()) {
                idpn = rs.getInt(1);
            }
            rs.close();
            ps.close();

            String sql = "EXEC dbo.SP_INSERT_CTPN @CT=?";
            SQLServerDataTable dt = new SQLServerDataTable();
            dt.addColumnMetadata("idpn", java.sql.Types.INTEGER);
            dt.addColumnMetadata("serial", java.sql.Types.NVARCHAR);

            DefaultTableModel model1 = (DefaultTableModel) tb_CTPN.getModel();
            for (int row = 0; row < model1.getRowCount(); row++) {
                for (int col = 0; col < model1.getColumnCount(); col++) {
                    Object value = model1.getValueAt(row, col);
                    if (value != null) {
                        String serial = value.toString().trim();
                        if (!serial.isEmpty()) {
                            dt.addRow(idpn, serial);
                        }
                    }
                }
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ((SQLServerPreparedStatement) pstmt).setStructured(1, "dbo.TYPE_CTPN", dt);
            pstmt.execute();
            pstmt.close();

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            JOptionPane.showMessageDialog(null, "Ghi phiếu nhập thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi GHI phiếu nhập!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_GhiPhieuActionPerformed

    private void cb_GrProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_GrProductActionPerformed
        // TODO add your handling code here:
        String group = cb_GrProduct.getSelectedItem() != null ? cb_GrProduct.getSelectedItem().toString().trim() : "";
        String brand = cb_Brand.getSelectedItem() != null ? cb_Brand.getSelectedItem().toString().trim() : "";
        loadCB_ListSP(group, brand);
    }//GEN-LAST:event_cb_GrProductActionPerformed

    private void cb_GrProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_GrProductMouseClicked
      
    }//GEN-LAST:event_cb_GrProductMouseClicked

    private void cb_BrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BrandActionPerformed
        // TODO add your handling code here:
        String group = cb_GrProduct.getSelectedItem() != null ? cb_GrProduct.getSelectedItem().toString().trim() : "";
        String brand = cb_Brand.getSelectedItem() != null ? cb_Brand.getSelectedItem().toString().trim() : "";

        loadCB_ListSP(group, brand);
    }//GEN-LAST:event_cb_BrandActionPerformed

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
            java.util.logging.Logger.getLogger(ChiTietNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietNhapHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Confirm;
    private javax.swing.JButton btn_GhiPhieu;
    private javax.swing.JComboBox<String> cb_Brand;
    private javax.swing.JComboBox<String> cb_GrProduct;
    private javax.swing.JComboBox<String> cb_Product;
    private javax.swing.JComboBox<String> cb_Supplier;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_CTPN;
    private javax.swing.JTextField txt_Quantity;
    private javax.swing.JTextField txt_ngayNhap;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
