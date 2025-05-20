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
import DAO.Session;
import DTO.LOAISP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChiTietXuatHang extends javax.swing.JPanel {

    private NHOMSP_DATA nhomsp_data = new NHOMSP_DATA();
    private LOAISP_DATA loaisp_data = new LOAISP_DATA();
    private ArrayList<LOAISP> loaisps;

    /**
     * Creates new form ChiTietXuatHang
     */
    public ChiTietXuatHang() {
        initComponents();
        loadCBGroup();
        loadCBTenSP();

        tf_ngayXuat.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    private void clearForm() {
        tf_soLuong.setText("");
        tf_giaXuat.setText("");
        tf_khachHang.setText("");
        tf_ngayXuat.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        cb_GrProduct1.setSelectedIndex(0);
        cb_Brand.setSelectedIndex(0);
        cb_Time.setSelectedIndex(0);

        // Xóa bảng serial
        DefaultTableModel model = (DefaultTableModel) tb_CTPX.getModel();
        model.setRowCount(0);
    }

    public void loadCBGroup() {
        nhomsp_data.getListnhomSP().forEach(nhomSP -> {
            cb_GrProduct1.addItem(nhomSP.getName());
        });
    }

    public void loadCBTenSP() {
        cb_Brand.removeAllItems();
        if (cb_GrProduct1.getSelectedItem() != null) {
            String groupName = String.valueOf(cb_GrProduct1.getSelectedItem());
            this.loaisps = loaisp_data.getLoaiSP(groupName);
            this.loaisps.forEach(loaisp -> {
                cb_Brand.addItem(loaisp.getName());
            });
        }
    }

    public void thucHienXuatHang() {
        try {

            int userId = 1;
            System.out.println("id user = " + userId);
            int categoryId = 0;
            for (LOAISP loaisp : loaisps) {
                if (loaisp.getName().equals(cb_Brand.getSelectedItem())) {
                    categoryId = loaisp.getCategoryID();
                    break;
                }
            }
            int price = Integer.parseInt(tf_giaXuat.getText().trim());
            String customer = tf_khachHang.getText().trim();
            String address = tf_khachHang.getText().trim();
            String NYC = tf_khachHang.getText().trim();
            String ghiChu = tf_khachHang.getText().trim();
            int soLuong = Integer.parseInt(tf_soLuong.getText().trim());
            // Lấy danh sách serial từ bảng
            List<String> listSerial = new ArrayList<>();
            //ngayXuat
            LocalDate ngayXuat = LocalDate.now();
            String ngayXuatStr = ngayXuat.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Ngày bắt đầu bảo hành (start_date)
            LocalDate startDate = LocalDate.parse(tf_ngayXuat.getText().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Lấy thời gian bảo hành từ comboBox
            String selectedWarranty = (String) cb_Time.getSelectedItem(); // "12 tháng"
            int months = Integer.parseInt(selectedWarranty.split(" ")[0]);

            // Tính end_date
            LocalDate endDate = startDate.plusMonths(months);
            String endDateStr = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // Lặp serial
            DefaultTableModel model = (DefaultTableModel) tb_CTPX.getModel();

            for (int i = 0; i < model.getRowCount(); i++) {
                Object checked = model.getValueAt(i, 2); // cột "Chọn"
                if (checked instanceof Boolean && (Boolean) checked) {
                    String serial = model.getValueAt(i, 1).toString(); // cột Serial
                    listSerial.add(serial);
                }
            }

            if (listSerial.size() != soLuong) {
                JOptionPane.showMessageDialog(null, "Số lượng serial được chọn không khớp với số lượng cần xuất.");
                return;
            }

            // Gọi xử lý
            PHIEUXUAT_DATA data = new PHIEUXUAT_DATA();
            boolean ok = data.xuatHang(userId, categoryId, soLuong, price, customer,address, NYC,ghiChu, ngayXuatStr, startDate.toString(), endDateStr, listSerial);
            if (ok) {
                JOptionPane.showMessageDialog(null, "Xuất hàng thành công!");
                clearForm(); // clear table
            } else {
                JOptionPane.showMessageDialog(null, "Xuất hàng thất bại! Kiểm tra lại serial hoặc dữ liệu.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadTableSerialTheoCategory(int categoryId) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"STT", "Serial", "Chọn"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // chỉ cho chọn checkbox
            }
        };

        try {

            Connection conn = new DBAccess().getConnection();
            String sql = "SELECT serial FROM SanPham WHERE category_id = ? AND status = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            int stt = 1;
            while (rs.next()) {
                model.addRow(new Object[]{stt++, rs.getString("serial"), false});
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tb_CTPX.setModel(model);
        tb_CTPX.getColumnModel().getColumn(0).setPreferredWidth(50);
        tb_CTPX.getColumnModel().getColumn(1).setPreferredWidth(300);
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
        btn_XacNhan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_Luu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_CTPX = new javax.swing.JTable();
        cb_NCC = new javax.swing.JComboBox<>();
        cb_Time = new javax.swing.JComboBox<>();
        cb_GrProduct1 = new javax.swing.JComboBox<>();
        tf_DiaChi = new javax.swing.JTextField();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelXuatHang.setBackground(new java.awt.Color(245, 245, 245));
        panelXuatHang.setForeground(new java.awt.Color(204, 204, 204));
        panelXuatHang.setPreferredSize(new java.awt.Dimension(1000, 700));

        cb_Brand.setBackground(new java.awt.Color(204, 204, 204));
        cb_Brand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_Brand.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên SP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cb_Brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BrandActionPerformed(evt);
            }
        });

        tf_soLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_soLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số Lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        tf_soLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_soLuongKeyTyped(evt);
            }
        });

        tf_ngayXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_ngayXuat.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Ngày Bảo Hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        tf_ngayXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ngayXuatActionPerformed(evt);
            }
        });

        tf_giaXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_giaXuat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá Xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        tf_giaXuat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_giaXuatKeyTyped(evt);
            }
        });

        tf_khachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_khachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btn_XacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XacNhan.setText("Xác Nhận");
        btn_XacNhan.setBorder(null);
        btn_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("XUẤT VẬT TƯ");

        btn_Luu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.setBorder(null);
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        tb_CTPX.setBackground(new java.awt.Color(245, 245, 245));
        tb_CTPX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tb_CTPX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tb_CTPX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        tb_CTPX.setRowHeight(40);
        tb_CTPX.setRowSelectionAllowed(false);
        tb_CTPX.setShowGrid(true);
        jScrollPane1.setViewportView(tb_CTPX);

        cb_NCC.setBackground(new java.awt.Color(204, 204, 204));
        cb_NCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_NCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nguyên Kim" }));
        cb_NCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NCC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cb_NCC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_NCCItemStateChanged(evt);
            }
        });

        cb_Time.setBackground(new java.awt.Color(204, 204, 204));
        cb_Time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12 tháng", "24 tháng", "36 tháng" }));
        cb_Time.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")), "Thời gian bảo hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        cb_GrProduct1.setBackground(new java.awt.Color(204, 204, 204));
        cb_GrProduct1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_GrProduct1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhóm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cb_GrProduct1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_GrProduct1ItemStateChanged(evt);
            }
        });

        tf_DiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_DiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Địa chỉ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        javax.swing.GroupLayout panelXuatHangLayout = new javax.swing.GroupLayout(panelXuatHang);
        panelXuatHang.setLayout(panelXuatHangLayout);
        panelXuatHangLayout.setHorizontalGroup(
            panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXuatHangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_NCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_soLuong)
                    .addComponent(tf_ngayXuat)
                    .addComponent(tf_DiaChi)
                    .addComponent(cb_GrProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXuatHangLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelXuatHangLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_Brand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_Time, 0, 291, Short.MAX_VALUE)
                            .addComponent(tf_giaXuat)
                            .addComponent(tf_khachHang))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXuatHangLayout.createSequentialGroup()
                        .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelXuatHangLayout.setVerticalGroup(
            panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXuatHangLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXuatHangLayout.createSequentialGroup()
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_GrProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_giaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_ngayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addComponent(tf_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelXuatHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(panelXuatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_XacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(btn_Luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(33, 40, Short.MAX_VALUE))
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
            .addComponent(panelXuatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {
        String soLuongStr = tf_soLuong.getText().trim();

        if (soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng cần xuất.");
            return;
        }

        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ. Vui lòng nhập số nguyên.");
            return;
        }

        String tenLoaiSP = (String) cb_Brand.getSelectedItem();
        for (LOAISP loaisp : loaisps) {
            if (loaisp.getName().equals(tenLoaiSP)) {
                loadTableSerialTheoCategory(loaisp.getCategoryID());
                break;
            }
        }
    }

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed

        if (tb_CTPX.isEditing()) {
            tb_CTPX.getCellEditor().stopCellEditing();
        }
        thucHienXuatHang();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void cb_BrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BrandActionPerformed

    }//GEN-LAST:event_cb_BrandActionPerformed

    private void cb_NCCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_NCCItemStateChanged

    }//GEN-LAST:event_cb_NCCItemStateChanged

    private void tf_giaXuatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_giaXuatKeyTyped
        char c = evt.getKeyChar();
        // Nếu không phải số và không phải phím xóa (backspace), thì hủy ký tự nhập
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); // chặn không cho nhập
            java.awt.Toolkit.getDefaultToolkit().beep(); // kêu beep để báo
        }
    }//GEN-LAST:event_tf_giaXuatKeyTyped

    private void tf_soLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_soLuongKeyTyped
        char c = evt.getKeyChar();
        // Nếu không phải số và không phải phím xóa (backspace), thì hủy ký tự nhập
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); // chặn không cho nhập
            java.awt.Toolkit.getDefaultToolkit().beep(); // kêu beep để báo
        }
    }//GEN-LAST:event_tf_soLuongKeyTyped

    private void cb_GrProduct1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_GrProduct1ItemStateChanged
        loadCBTenSP();
    }//GEN-LAST:event_cb_GrProduct1ItemStateChanged

    private void tf_ngayXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ngayXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_ngayXuatActionPerformed

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
            java.util.logging.Logger.getLogger(ChiTietXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietXuatHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_XacNhan;
    private javax.swing.JComboBox<String> cb_Brand;
    private javax.swing.JComboBox<String> cb_GrProduct1;
    private javax.swing.JComboBox<String> cb_NCC;
    private javax.swing.JComboBox<String> cb_Time;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelXuatHang;
    private javax.swing.JTable tb_CTPX;
    private javax.swing.JTextField tf_DiaChi;
    private javax.swing.JTextField tf_giaXuat;
    private javax.swing.JTextField tf_khachHang;
    private javax.swing.JTextField tf_ngayXuat;
    private javax.swing.JTextField tf_soLuong;
    // End of variables declaration//GEN-END:variables
}
