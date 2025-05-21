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
import javax.swing.JFrame;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null); // căn giữa màn hình
        setVisible(true);
    }

    Color defaultColor = new Color(89, 168, 105);
    Color clickColor = new Color(26, 188, 156);

    public void showPanel(JPanel panel) {
        panelShow.removeAll();              // Xóa panel cũ
        panelShow.setLayout(new BorderLayout());
        panelShow.add(panel); // Thêm panel mới
        panelShow.revalidate();             // Làm mới hiển thị
        panelShow.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        pn_OpenSP = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        pn_OpenSupplier = new javax.swing.JPanel();
        lb_Supplier = new javax.swing.JLabel();
        pn_OpenNhap = new javax.swing.JPanel();
        lblNhapHang = new javax.swing.JLabel();
        pn_OpenPhieuNhap = new javax.swing.JPanel();
        lb_PhieuNhap = new javax.swing.JLabel();
        pn_OpenXuat = new javax.swing.JPanel();
        lblXuatHang = new javax.swing.JLabel();
        pn_OpenPhieuXuat = new javax.swing.JPanel();
        lb_PhieuXuat = new javax.swing.JLabel();
        pn_Logout = new javax.swing.JPanel();
        lb_Logout = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelShow = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1300, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMenu.setBackground(new java.awt.Color(89, 168, 105));
        panelMenu.setPreferredSize(new java.awt.Dimension(300, 700));

        pn_OpenSP.setBackground(new java.awt.Color(89, 168, 105));
        pn_OpenSP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pn_OpenSPMouseMoved(evt);
            }
        });
        pn_OpenSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_OpenSPMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_OpenSPMouseExited(evt);
            }
        });

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\products.png")); // NOI18N
        lblSanPham.setText("SẢN PHẨM");

        javax.swing.GroupLayout pn_OpenSPLayout = new javax.swing.GroupLayout(pn_OpenSP);
        pn_OpenSP.setLayout(pn_OpenSPLayout);
        pn_OpenSPLayout.setHorizontalGroup(
            pn_OpenSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_OpenSPLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pn_OpenSPLayout.setVerticalGroup(
            pn_OpenSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_OpenSPLayout.createSequentialGroup()
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pn_OpenSupplier.setBackground(new java.awt.Color(89, 168, 105));
        pn_OpenSupplier.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pn_OpenSupplierMouseMoved(evt);
            }
        });
        pn_OpenSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_OpenSupplierMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_OpenSupplierMouseExited(evt);
            }
        });

        lb_Supplier.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb_Supplier.setForeground(new java.awt.Color(255, 255, 255));
        lb_Supplier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Supplier.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\supplier.png")); // NOI18N
        lb_Supplier.setText("NHÀ CUNG CẤP");

        javax.swing.GroupLayout pn_OpenSupplierLayout = new javax.swing.GroupLayout(pn_OpenSupplier);
        pn_OpenSupplier.setLayout(pn_OpenSupplierLayout);
        pn_OpenSupplierLayout.setHorizontalGroup(
            pn_OpenSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_OpenSupplierLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(lb_Supplier)
                .addGap(53, 53, 53))
        );
        pn_OpenSupplierLayout.setVerticalGroup(
            pn_OpenSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Supplier, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        pn_OpenNhap.setBackground(new java.awt.Color(89, 168, 105));
        pn_OpenNhap.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pn_OpenNhapMouseMoved(evt);
            }
        });
        pn_OpenNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_OpenNhapMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_OpenNhapMouseExited(evt);
            }
        });

        lblNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        lblNhapHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNhapHang.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\import.png")); // NOI18N
        lblNhapHang.setText("NHẬP HÀNG");

        javax.swing.GroupLayout pn_OpenNhapLayout = new javax.swing.GroupLayout(pn_OpenNhap);
        pn_OpenNhap.setLayout(pn_OpenNhapLayout);
        pn_OpenNhapLayout.setHorizontalGroup(
            pn_OpenNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_OpenNhapLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(lblNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pn_OpenNhapLayout.setVerticalGroup(
            pn_OpenNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_OpenNhapLayout.createSequentialGroup()
                .addComponent(lblNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pn_OpenPhieuNhap.setBackground(new java.awt.Color(89, 168, 105));
        pn_OpenPhieuNhap.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pn_OpenPhieuNhapMouseMoved(evt);
            }
        });
        pn_OpenPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_OpenPhieuNhapMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_OpenPhieuNhapMouseExited(evt);
            }
        });

        lb_PhieuNhap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb_PhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        lb_PhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_PhieuNhap.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\requirement.png")); // NOI18N
        lb_PhieuNhap.setText("PHIẾU NHẬP");

        javax.swing.GroupLayout pn_OpenPhieuNhapLayout = new javax.swing.GroupLayout(pn_OpenPhieuNhap);
        pn_OpenPhieuNhap.setLayout(pn_OpenPhieuNhapLayout);
        pn_OpenPhieuNhapLayout.setHorizontalGroup(
            pn_OpenPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_OpenPhieuNhapLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lb_PhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        pn_OpenPhieuNhapLayout.setVerticalGroup(
            pn_OpenPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_PhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        pn_OpenXuat.setBackground(new java.awt.Color(89, 168, 105));
        pn_OpenXuat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pn_OpenXuatMouseMoved(evt);
            }
        });
        pn_OpenXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_OpenXuatMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_OpenXuatMouseExited(evt);
            }
        });

        lblXuatHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblXuatHang.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatHang.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\tracking.png")); // NOI18N
        lblXuatHang.setText("XUẤT HÀNG");

        javax.swing.GroupLayout pn_OpenXuatLayout = new javax.swing.GroupLayout(pn_OpenXuat);
        pn_OpenXuat.setLayout(pn_OpenXuatLayout);
        pn_OpenXuatLayout.setHorizontalGroup(
            pn_OpenXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_OpenXuatLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(lblXuatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_OpenXuatLayout.setVerticalGroup(
            pn_OpenXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_OpenXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXuatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pn_OpenPhieuXuat.setBackground(new java.awt.Color(89, 168, 105));
        pn_OpenPhieuXuat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pn_OpenPhieuXuatMouseMoved(evt);
            }
        });
        pn_OpenPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_OpenPhieuXuatMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_OpenPhieuXuatMouseExited(evt);
            }
        });

        lb_PhieuXuat.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb_PhieuXuat.setForeground(new java.awt.Color(255, 255, 255));
        lb_PhieuXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_PhieuXuat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\checklist (1).png")); // NOI18N
        lb_PhieuXuat.setText("PHIẾU XUẤT");

        javax.swing.GroupLayout pn_OpenPhieuXuatLayout = new javax.swing.GroupLayout(pn_OpenPhieuXuat);
        pn_OpenPhieuXuat.setLayout(pn_OpenPhieuXuatLayout);
        pn_OpenPhieuXuatLayout.setHorizontalGroup(
            pn_OpenPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_OpenPhieuXuatLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lb_PhieuXuat)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pn_OpenPhieuXuatLayout.setVerticalGroup(
            pn_OpenPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_PhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        pn_Logout.setBackground(new java.awt.Color(89, 168, 105));
        pn_Logout.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pn_LogoutMouseMoved(evt);
            }
        });
        pn_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_LogoutMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_LogoutMouseExited(evt);
            }
        });

        lb_Logout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_Logout.setForeground(new java.awt.Color(255, 255, 255));
        lb_Logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Logout.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\logout.png")); // NOI18N
        lb_Logout.setText("ĐĂNG XUẤT");

        javax.swing.GroupLayout pn_LogoutLayout = new javax.swing.GroupLayout(pn_Logout);
        pn_Logout.setLayout(pn_LogoutLayout);
        pn_LogoutLayout.setHorizontalGroup(
            pn_LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_LogoutLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(lb_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        pn_LogoutLayout.setVerticalGroup(
            pn_LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CÔNG TY CP KTTM NAM TRUNG");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\ntlogo2022.png")); // NOI18N

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pn_OpenPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pn_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pn_OpenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pn_OpenSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pn_OpenNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pn_OpenXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pn_OpenPhieuNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addComponent(pn_OpenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_OpenSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_OpenNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_OpenPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_OpenXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_OpenPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(pn_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        getContentPane().add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 800));

        panelShow.setBackground(new java.awt.Color(255, 255, 255));
        panelShow.setPreferredSize(new java.awt.Dimension(1000, 700));

        javax.swing.GroupLayout panelShowLayout = new javax.swing.GroupLayout(panelShow);
        panelShow.setLayout(panelShowLayout);
        panelShowLayout.setHorizontalGroup(
            panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        panelShowLayout.setVerticalGroup(
            panelShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        getContentPane().add(panelShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 0, 1200, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void pn_OpenSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenSPMouseClicked
        // TODO add your handling code here:
        showPanel(new QuanLyKho());
    }//GEN-LAST:event_pn_OpenSPMouseClicked

    private void lblXuatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHangMouseClicked

    }//GEN-LAST:event_lblXuatHangMouseClicked


    private void pn_OpenSPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenSPMouseExited
        // TODO add your handling code here:
        pn_OpenSP.setBackground(defaultColor);
    }//GEN-LAST:event_pn_OpenSPMouseExited

    private void pn_OpenSPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenSPMouseMoved
        // TODO add your handling code here:
        pn_OpenSP.setBackground(clickColor);
    }//GEN-LAST:event_pn_OpenSPMouseMoved

    private void pn_OpenSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenSupplierMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_pn_OpenSupplierMouseClicked

    private void pn_OpenSupplierMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenSupplierMouseExited
        // TODO add your handling code here:
        pn_OpenSupplier.setBackground(defaultColor);
    }//GEN-LAST:event_pn_OpenSupplierMouseExited

    private void pn_OpenSupplierMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenSupplierMouseMoved
        // TODO add your handling code here:
        pn_OpenSupplier.setBackground(clickColor);
    }//GEN-LAST:event_pn_OpenSupplierMouseMoved

    private void pn_OpenNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenNhapMouseClicked
        // TODO add your handling code here:
        showPanel(new ChiTietNhapHang());
    }//GEN-LAST:event_pn_OpenNhapMouseClicked

    private void pn_OpenNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenNhapMouseExited
        // TODO add your handling code here:
        pn_OpenNhap.setBackground(defaultColor);
    }//GEN-LAST:event_pn_OpenNhapMouseExited

    private void pn_OpenNhapMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenNhapMouseMoved
        // TODO add your handling code here:
        pn_OpenPhieuNhap.setBackground(clickColor);
    }//GEN-LAST:event_pn_OpenNhapMouseMoved

    private void pn_OpenPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenPhieuNhapMouseClicked
        showPanel(new ChiTietNhapHang());
    }//GEN-LAST:event_pn_OpenPhieuNhapMouseClicked

    private void pn_OpenPhieuNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenPhieuNhapMouseExited
        // TODO add your handling code here:
        pn_OpenPhieuNhap.setBackground(defaultColor);
    }//GEN-LAST:event_pn_OpenPhieuNhapMouseExited

    private void pn_OpenPhieuNhapMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenPhieuNhapMouseMoved
        // TODO add your handling code here:
        pn_OpenPhieuNhap.setBackground(clickColor);
    }//GEN-LAST:event_pn_OpenPhieuNhapMouseMoved

    private void pn_OpenXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenXuatMouseClicked
        // TODO add your handling code here:
        showPanel(new XuatHang());
    }//GEN-LAST:event_pn_OpenXuatMouseClicked

    private void pn_OpenXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenXuatMouseExited
        // TODO add your handling code here:
        pn_OpenXuat.setBackground(defaultColor);
    }//GEN-LAST:event_pn_OpenXuatMouseExited

    private void pn_OpenXuatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenXuatMouseMoved
        // TODO add your handling code here:
        pn_OpenXuat.setBackground(clickColor);
    }//GEN-LAST:event_pn_OpenXuatMouseMoved

    private void pn_OpenPhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenPhieuXuatMouseClicked
        // TODO add your handling code here:
        showPanel(new QuanLyXuatHang());
    }//GEN-LAST:event_pn_OpenPhieuXuatMouseClicked

    private void pn_OpenPhieuXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenPhieuXuatMouseExited
        // TODO add your handling code here:
        pn_OpenPhieuXuat.setBackground(defaultColor);
    }//GEN-LAST:event_pn_OpenPhieuXuatMouseExited

    private void pn_OpenPhieuXuatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_OpenPhieuXuatMouseMoved
        // TODO add your handling code here:
        pn_OpenPhieuXuat.setBackground(clickColor);
    }//GEN-LAST:event_pn_OpenPhieuXuatMouseMoved

    private void pn_LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_LogoutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pn_LogoutMouseClicked

    private void pn_LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_LogoutMouseExited
        // TODO add your handling code here:
        pn_Logout.setBackground(defaultColor);
    }//GEN-LAST:event_pn_LogoutMouseExited

    private void pn_LogoutMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_LogoutMouseMoved
        // TODO add your handling code here:
        pn_Logout.setBackground(clickColor);
    }//GEN-LAST:event_pn_LogoutMouseMoved

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lb_Logout;
    private javax.swing.JLabel lb_PhieuNhap;
    private javax.swing.JLabel lb_PhieuXuat;
    private javax.swing.JLabel lb_Supplier;
    private javax.swing.JLabel lblNhapHang;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblXuatHang;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelShow;
    private javax.swing.JPanel pn_Logout;
    private javax.swing.JPanel pn_OpenNhap;
    private javax.swing.JPanel pn_OpenPhieuNhap;
    private javax.swing.JPanel pn_OpenPhieuXuat;
    private javax.swing.JPanel pn_OpenSP;
    private javax.swing.JPanel pn_OpenSupplier;
    private javax.swing.JPanel pn_OpenXuat;
    // End of variables declaration//GEN-END:variables
}
