
package com.mycompany.quanlyvattu;

import ConDB.DBAccess;
import DAO.NHOMSP_DATA;
import DAO.NhapHang;
import DTO.PHIEUNHAP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FormNhapHang extends javax.swing.JFrame {

    /**
     * Creates new form FormNhapHang
     */
    public FormNhapHang() {
        initComponents();
        loadDataTablePN();
        loadCBDM(cbNhom);
    }
 
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    private NhapHang pn_data = new NhapHang();
    private NHOMSP_DATA nhomsp_data = new NHOMSP_DATA();
    
    private void loadCBDM(JComboBox cbox) {
        cbox.removeAllItems();
        try {
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT name FROM NhomSP");
            while (rs.next()) {
                cbox.addItem(rs.getString("name").trim());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi loadCBDM!", "ERROR!", JOptionPane.ERROR_MESSAGE);
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

        panelNhapHang = new javax.swing.JPanel();
        lbNhapHang = new javax.swing.JLabel();
        lbNgayNhap = new javax.swing.JLabel();
        lbNhom = new javax.swing.JLabel();
        lbLoai = new javax.swing.JLabel();
        lbSoLuong = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ftxngayNhap = new javax.swing.JFormattedTextField();
        cbNhom = new javax.swing.JComboBox<>();
        tfsoLuong = new javax.swing.JTextField();
        tfNCC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txgiaNhap = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCTNhapHang = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPN = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tfTimKiem = new javax.swing.JTextField();
        tfTen = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1300, 600));

        lbNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNhapHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNhapHang.setText("QUẢN LÍ NHẬP HÀNG");

        lbNgayNhap.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lbNgayNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNgayNhap.setText("Ngày Nhập :");

        lbNhom.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lbNhom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNhom.setText("Nhóm :");

        lbLoai.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lbLoai.setText("Loại :");

        lbSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lbSoLuong.setText("Số Lượng :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel5.setText("NCC :");

        ftxngayNhap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ftxngayNhap.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        ftxngayNhap.setText("12/04/2025");
        ftxngayNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxngayNhapActionPerformed(evt);
            }
        });

        cbNhom.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cbNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tfsoLuong.setText("100");

        tfNCC.setText("Nguyên Kim");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setText("Đơn Giá :");

        tbCTNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "STT", "Serial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbCTNhapHang);
        if (tbCTNhapHang.getColumnModel().getColumnCount() > 0) {
            tbCTNhapHang.getColumnModel().getColumn(0).setPreferredWidth(2);
            tbCTNhapHang.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        tbPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Ngày Nhập", "Nhóm", "Tên", "Số Lượng", "Đơn Giá", "Thành Tiền", "Nhà Cung Cấp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPN);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tìm kiếm :");

        javax.swing.GroupLayout panelNhapHangLayout = new javax.swing.GroupLayout(panelNhapHang);
        panelNhapHang.setLayout(panelNhapHangLayout);
        panelNhapHangLayout.setHorizontalGroup(
            panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNhapHangLayout.createSequentialGroup()
                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNhapHangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNhapHangLayout.createSequentialGroup()
                                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNgayNhap)
                                    .addComponent(lbSoLuong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ftxngayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfsoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNhom, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelNhapHangLayout.createSequentialGroup()
                                .addComponent(lbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfTen, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNhapHangLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbNhom, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelNhapHangLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txgiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelNhapHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)))
                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNhapHangLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNhapHangLayout.createSequentialGroup()
                                .addComponent(lbNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(497, 497, 497))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNhapHangLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(302, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNhapHangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        panelNhapHangLayout.setVerticalGroup(
            panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhapHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNhapHangLayout.createSequentialGroup()
                        .addComponent(lbNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftxngayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNhom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbNhom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNhapHangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txgiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfsoLuong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(panelNhapHangLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");

        jButton1.setText("Sửa");

        jButton2.setText("Reload");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNhapHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnLuu)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(btnThoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnThoat)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 100, Short.MAX_VALUE))
        );

        panelNhapHang.getAccessibleContext().setAccessibleName("lblNhapHang");
        panelNhapHang.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void ftxngayNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxngayNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxngayNhapActionPerformed

     private void loadDataTablePN() {
        Date date = new Date();
        String ngay = format1.format(date);
        DefaultTableModel dtm = (DefaultTableModel) tbPN.getModel();
        dtm.setNumRows(0);
        ArrayList<PHIEUNHAP> dspn = pn_data.sp_getListPN();
        if (dspn != null) {
            for (PHIEUNHAP pn : dspn) {
                Vector vec = new Vector();
                vec.add(pn.getIdpn());
                vec.add(pn.getNgayNhap());
                vec.add(pn.getCategoryID());
                vec.add(pn.getGroup_id());
                vec.add(pn.getQuantity());
                vec.add(pn.getPrice());
                vec.add(pn.getTongTien());
                vec.add(pn.getSupplier());
               
                dtm.addRow(vec);
            }
            tbPN.setModel(dtm);
        }
    }
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
            java.util.logging.Logger.getLogger(FormNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormNhapHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThoat;
    private javax.swing.JComboBox<String> cbNhom;
    private javax.swing.JFormattedTextField ftxngayNhap;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbLoai;
    private javax.swing.JLabel lbNgayNhap;
    private javax.swing.JLabel lbNhapHang;
    private javax.swing.JLabel lbNhom;
    private javax.swing.JLabel lbSoLuong;
    private javax.swing.JPanel panelNhapHang;
    private javax.swing.JTable tbCTNhapHang;
    private javax.swing.JTable tbPN;
    private javax.swing.JTextField tfNCC;
    private javax.swing.JTextField tfTen;
    private javax.swing.JTextField tfTimKiem;
    private javax.swing.JTextField tfsoLuong;
    private javax.swing.JTextField txgiaNhap;
    // End of variables declaration//GEN-END:variables
}
