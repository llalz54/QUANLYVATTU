/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlyvattu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * phương thức mở menu
     */
    int width = 1000;
    int height = 700;

    public void showPanel(JPanel panel) {
        panelShow.removeAll();              // Xóa panel cũ
        panelShow.setLayout(new BorderLayout());
        panelShow.add(panel, BorderLayout.CENTER); // Thêm panel mới
        panelShow.revalidate();             // Làm mới hiển thị
        panelShow.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        lblNhapHang = new javax.swing.JLabel();
        lblXuatHang = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        lblTonKho = new javax.swing.JLabel();
        lblDSXuat = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelShow = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenu.setPreferredSize(new java.awt.Dimension(300, 700));

        lblNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNhapHang.setText("NHẬP HÀNG");
        lblNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhapHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNhapHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNhapHangMouseExited(evt);
            }
        });

        lblXuatHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblXuatHang.setText("XUẤT HÀNG");
        lblXuatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXuatHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXuatHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXuatHangMouseExited(evt);
            }
        });

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSanPham.setText("SẢN PHẨM");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseExited(evt);
            }
        });

        lblTonKho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTonKho.setText("TỒN KHO");
        lblTonKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTonKhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTonKhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTonKhoMouseExited(evt);
            }
        });

        lblDSXuat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDSXuat.setText("DS HÀNG XUẤT");
        lblDSXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDSXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDSXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDSXuatMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("CÔNG TY CP KTTM NAM TRUNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblXuatHang, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(lblNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTonKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDSXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(lblXuatHang)
                .addGap(35, 35, 35)
                .addComponent(lblSanPham)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblTonKho))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDSXuat)
                .addGap(63, 63, 63)
                .addComponent(lblNhapHang)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        panelShow.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panelShow.setForeground(new java.awt.Color(204, 204, 204));
        panelShow.setPreferredSize(new java.awt.Dimension(1000, 700));

        javax.swing.GroupLayout panelShowLayout = new javax.swing.GroupLayout(panelShow);
        panelShow.setLayout(panelShowLayout);
        panelShowLayout.setHorizontalGroup(
            panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 992, Short.MAX_VALUE)
        );
        panelShowLayout.setVerticalGroup(
            panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShow, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhapHangMouseClicked
	lblNhapHang.setOpaque(true); // Để màu nền có hiệu lực  
	showPanel(new ChiTietNhapHang());
      
    }//GEN-LAST:event_lblNhapHangMouseClicked

    private void lblXuatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHangMouseClicked
        lblXuatHang.setOpaque(true); // Để màu nền có hiệu lực  
        showPanel(new XuatHang());
    }//GEN-LAST:event_lblXuatHangMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        lblSanPham.setOpaque(true); // Để màu nền có hiệu lực  
        try {
            showPanel(new QuanLySanPham());
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblTonKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTonKhoMouseClicked
         lblTonKho.setOpaque(true); // Để màu nền có hiệu lực  
         showPanel(new QuanLyTonKho());
    }//GEN-LAST:event_lblTonKhoMouseClicked

    private void lblDSXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDSXuatMouseClicked
        lblDSXuat.setOpaque(true); // Để màu nền có hiệu lực  
        showPanel(new QuanLyXuatHang());
    }//GEN-LAST:event_lblDSXuatMouseClicked

    private void lblNhapHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhapHangMouseEntered
        lblNhapHang.setBackground(Color.GRAY);
    }//GEN-LAST:event_lblNhapHangMouseEntered

    private void lblNhapHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhapHangMouseExited
        lblNhapHang.setBackground(Color.LIGHT_GRAY);
        lblNhapHang.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblNhapHangMouseExited

    private void lblXuatHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHangMouseEntered
        lblXuatHang.setBackground(Color.GRAY);
    }//GEN-LAST:event_lblXuatHangMouseEntered

    private void lblXuatHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHangMouseExited
        lblXuatHang.setBackground(Color.LIGHT_GRAY);
        lblXuatHang.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblXuatHangMouseExited

    private void lblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseEntered
        lblSanPham.setBackground(Color.GRAY);
    }//GEN-LAST:event_lblSanPhamMouseEntered

    private void lblSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseExited
        lblSanPham.setBackground(Color.LIGHT_GRAY);
        lblSanPham.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblSanPhamMouseExited

    private void lblTonKhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTonKhoMouseEntered
        lblTonKho.setBackground(Color.GRAY);
    }//GEN-LAST:event_lblTonKhoMouseEntered

    private void lblTonKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTonKhoMouseExited
        lblTonKho.setBackground(Color.LIGHT_GRAY);
        lblTonKho.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblTonKhoMouseExited

    private void lblDSXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDSXuatMouseEntered
        lblDSXuat.setBackground(Color.GRAY);
    }//GEN-LAST:event_lblDSXuatMouseEntered

    private void lblDSXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDSXuatMouseExited
        lblDSXuat.setBackground(Color.LIGHT_GRAY);
        lblDSXuat.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblDSXuatMouseExited

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDSXuat;
    private javax.swing.JLabel lblNhapHang;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTonKho;
    private javax.swing.JLabel lblXuatHang;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelShow;
    // End of variables declaration//GEN-END:variables
}
