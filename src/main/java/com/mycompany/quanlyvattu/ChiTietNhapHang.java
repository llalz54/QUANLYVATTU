
package com.mycompany.quanlyvattu;

import javax.swing.table.DefaultTableModel;
import DAO.OTHER_DATA;
public class ChiTietNhapHang extends javax.swing.JFrame {

    /**
     * Creates new form ChiTietNhapHang
     */
    public ChiTietNhapHang() {
        initComponents();
        OTHER_DATA.loadCBDM(cb_GrProduct);
    }

    private void create_TB_CTPN(int quantity) {
        int column = 10;
        int row = (int) Math.ceil((double) quantity / 10);

        String[] columnNames = new String[column + 1];
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
    }
    
    private void load_Cb_Brand(){
        
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
        txt_Quantity1 = new javax.swing.JTextField();
        txt_Quantity2 = new javax.swing.JTextField();
        btn_Confirm = new javax.swing.JButton();

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

        cb_Brand.setBorder(javax.swing.BorderFactory.createTitledBorder("Hãng"));

        cb_Supplier.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhà cung cấp"));

        txt_Quantity.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));

        txt_Quantity1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày nhập"));

        txt_Quantity2.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá nhập"));

        btn_Confirm.setText("XÁC NHẬN");
        btn_Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_GrProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_Quantity, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cb_Supplier, javax.swing.GroupLayout.Alignment.LEADING, 0, 159, Short.MAX_VALUE)))
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Quantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Quantity2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Confirm)
                .addGap(207, 207, 207))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_GrProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Confirm)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Quantity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Quantity2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConfirmActionPerformed
        // TODO add your handling code here:
        int quantity = Integer.parseInt(txt_Quantity.getText());
        create_TB_CTPN(quantity);
    }//GEN-LAST:event_btn_ConfirmActionPerformed

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
    private javax.swing.JComboBox<String> cb_Brand;
    private javax.swing.JComboBox<String> cb_GrProduct;
    private javax.swing.JComboBox<String> cb_Supplier;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_CTPN;
    private javax.swing.JTextField txt_Quantity;
    private javax.swing.JTextField txt_Quantity1;
    private javax.swing.JTextField txt_Quantity2;
    // End of variables declaration//GEN-END:variables
}
