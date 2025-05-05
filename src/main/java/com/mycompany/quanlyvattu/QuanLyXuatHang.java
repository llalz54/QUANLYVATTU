package com.mycompany.quanlyvattu;

import ConDB.DBAccess;
import DAO.NHOMSP_DATA;
import DAO.PHIEUXUAT_DATA;
import DTO.PHIEUXUAT;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QuanLyXuatHang extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyXuatHang
     */
    public QuanLyXuatHang() {
        initComponents();
        loadDataTableSP();
        customControls();
    }

    private NHOMSP_DATA nhomsp_data = new NHOMSP_DATA();
    private PHIEUXUAT_DATA px_data = new PHIEUXUAT_DATA();

    private void customControls() {
        tfTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                btnTim.doClick();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                btnTim.doClick();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                btnTim.doClick();
            }
        });
        btnTim.setVisible(false);

    }

    private void loadTable_TheoTen(String tenSP) {
        NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        DefaultTableModel dtm = (DefaultTableModel) tbPX.getModel();
        dtm.setNumRows(0);
        ArrayList<PHIEUXUAT> dssp = px_data.getSPtheoTen(tenSP);
        for (PHIEUXUAT sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getTenLoai());
            vec.add(sp.getQuantity());
            vec.add(vnFormat.format(sp.getPrice()));// định dạng đơn giá
            vec.add(vnFormat.format(sp.getTongTien())); // định dạng tổng tiền
            vec.add(sp.getNgayXuat());
            vec.add(sp.getCustomer());

            dtm.addRow(vec);
        }
        tbPX.setModel(dtm);
    }

    private void loadDataTableSP() {
        NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        DefaultTableModel dtm = (DefaultTableModel) tbPX.getModel();
        dtm.setNumRows(0);
        ArrayList<PHIEUXUAT> dssp = px_data.getListPX();
        if (dssp != null) {
            for (PHIEUXUAT sp : dssp) {
                Vector vec = new Vector();
                vec.add(sp.getTenLoai());
                vec.add(sp.getQuantity());
                vec.add(vnFormat.format(sp.getPrice()));// định dạng đơn giá
                vec.add(vnFormat.format(sp.getTongTien())); // định dạng tổng tiền
                vec.add(sp.getNgayXuat());
                vec.add(sp.getCustomer());
                dtm.addRow(vec);
            }
            tbPX.setModel(dtm);
        }
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPX = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        tfTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH HÀNG XUẤT");

        tbPX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên", "Số Lượng", "Đơn Giá", "Thành Tiền", "Ngày Xuất", "Khách Hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbPX.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbPXComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(tbPX);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tìm kiếm :");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 325, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPXComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbPXComponentShown
        loadDataTableSP();
    }//GEN-LAST:event_tbPXComponentShown

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        loadTable_TheoTen(tfTim.getText().trim());
    }//GEN-LAST:event_btnTimActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyXuatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyXuatHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbPX;
    private javax.swing.JTextField tfTim;
    // End of variables declaration//GEN-END:variables
}
