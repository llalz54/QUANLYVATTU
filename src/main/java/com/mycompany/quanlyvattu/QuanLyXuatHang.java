package com.mycompany.quanlyvattu;

import ConDB.DBAccess;
import DAO.CTPX_DATA;
import DAO.LOAISP_DATA;
import DAO.NHOMSP_DATA;
import DAO.NumberToWords;
import DAO.PHIEUXUAT_DATA;
import DAO.Session;
import DAO.UpperCase;

import DTO.CTPX;
import DTO.LOAISP;
import DTO.PHIEUXUAT;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Admin
 */
public class QuanLyXuatHang extends JPanel {

    private ArrayList<LOAISP> loaisps;
    private NHOMSP_DATA nhomsp_data = new NHOMSP_DATA();
    private LOAISP_DATA loaisp_data = new LOAISP_DATA();
    private PHIEUXUAT_DATA px_data = new PHIEUXUAT_DATA();
    private CTPX_DATA ctpx_data = new CTPX_DATA();


    public QuanLyXuatHang() {

        initComponents();
        loadDataTableSP();
        customControls();

    }

    private void capNhatBangSerialTheoSoLuong() {
        DefaultTableModel model = (DefaultTableModel) tbSerial.getModel();
        int soLuongHienTai = model.getRowCount();

        int soLuongMoi;
        try {
            soLuongMoi = Integer.parseInt(tf_soLuong.getText().trim());
            if (soLuongMoi < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên dương hợp lệ!");
            tf_soLuong.requestFocus();
            return;
        }

        // Nếu số lượng mới > số hàng hiện có → thêm dòng trống
        if (soLuongMoi > soLuongHienTai) {
            for (int i = soLuongHienTai; i < soLuongMoi; i++) {
                model.addRow(new Object[]{i + 1, ""}); // Thêm STT và Serial
            }
        } // Nếu số lượng mới < số hàng hiện có → xóa dòng dư
        else if (soLuongMoi < soLuongHienTai) {
            for (int i = soLuongHienTai - 1; i >= soLuongMoi; i--) {
                model.removeRow(i);
            }
        }

        // Cập nhật lại STT cho các dòng còn lại
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0); // Cập nhật cột STT (cột 0)
        }
    }

//    public void loadCBGroup() {
//        nhomsp_data.getListnhomSP().forEach(nhomSP -> {
//            cb_Product2.addItem(nhomSP.getName());
//        });
//    }
//
//    public void loadCBTenSP() {
//        cb_LoaiSP.removeAllItems();
//        if (cb_Product2.getSelectedItem() != null) {
//            String groupName = String.valueOf(cb_Product2.getSelectedItem());
//            this.loaisps = loaisp_data.getLoaiSP(groupName);
//            this.loaisps.forEach(loaisp -> {
//                cb_LoaiSP.addItem(loaisp.getName());
//            });
//        }
//    }
    private void loadChiTietPhieuXuat(int idpx) {
        try {
            System.out.println("idpx: " + idpx);
            DBAccess acc = new DBAccess();

            // Lấy thông tin phiếu xuất, nhóm SP, loại SP
            String sqlPhieu = "SELECT px.*, n.name AS group_name, l.name AS category_name "
                    + "FROM PhieuXuat px "
                    + "JOIN LoaiSP l ON px.category_id = l.category_id "
                    + "JOIN NhomSP n ON l.group_id = n.group_id "
                    + "WHERE px.idpx = ?";
            PreparedStatement ps = acc.getConnection().prepareStatement(sqlPhieu);
            ps.setInt(1, idpx);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                tf_NYCau.setText(rs.getString("NYCau"));
                tf_ghiChu.setText(rs.getString("ghiChu"));
                tf_soLuong.setText(rs.getString("quantity"));
                tf_giaXuat.setText(rs.getString("price"));
                tf_khachHang.setText(rs.getString("customer"));
                tf_diaChi.setText(rs.getString("address"));
            }
            ps.close();

            // Lấy 1 serial đầu tiên để xem start_date, end_date
            String sqlFirstSerial = "SELECT TOP 1 sp.start_date, sp.end_date "
                    + "FROM CTPX ct JOIN SanPham sp ON ct.serial = sp.serial "
                    + "WHERE ct.idpx = ?";
            ps = acc.getConnection().prepareStatement(sqlFirstSerial);
            ps.setInt(1, idpx);
            rs = ps.executeQuery();

            if (rs.next()) {
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                tf_ngayXuat.setText(startDate);  // Ngày bảo hành bắt đầu

                // Tính thời gian bảo hành từ start -> end (tính theo tháng)
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = LocalDate.parse(endDate);
                long months = ChronoUnit.MONTHS.between(start, end);
                cb_Time.setSelectedItem(months + " tháng");
            }
            ps.close();

            // Load danh sách serial theo dòng (2 cột: STT và Serial)
            String sqlCTPX = "SELECT serial FROM CTPX WHERE idpx = ? ORDER BY serial";
            ps = acc.getConnection().prepareStatement(sqlCTPX);
            ps.setInt(1, idpx);
            rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel(new Object[]{"STT", "Serial"}, 0);
            int stt = 1;
            while (rs.next()) {
                model.addRow(new Object[]{stt++, rs.getString("serial")});
            }
            tbSerial.setModel(model);

            ps.close();
            acc.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tải chi tiết phiếu xuất!");
            e.printStackTrace();
        }
    }

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
            vec.add(sp.getIdpx());
            vec.add(sp.getTenLoai());
            vec.add(sp.getQuantity());
            vec.add(vnFormat.format(sp.getPrice()));// định dạng đơn giá
            vec.add(vnFormat.format(sp.getTongTien())); // định dạng tổng tiền
            vec.add(sp.getNgayXuat());
            vec.add(sp.getCustomer());
            dtm.addRow(vec);
        }
        tbPX.setModel(dtm);
        // Ẩn cột idpx (giả sử là cột đầu tiên - index 0)
        tbPX.getColumnModel().getColumn(0).setMinWidth(0);
        tbPX.getColumnModel().getColumn(0).setMaxWidth(0);
        tbPX.getColumnModel().getColumn(0).setWidth(0);
    }

    private void loadDataTableSP() {
        NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        DefaultTableModel dtm = (DefaultTableModel) tbPX.getModel();
        dtm.setNumRows(0);
        ArrayList<PHIEUXUAT> dssp = px_data.getListPX();
        if (dssp != null) {
            for (PHIEUXUAT sp : dssp) {
                Vector vec = new Vector();
                vec.add(sp.getIdpx());
                vec.add(sp.getTenLoai());
                vec.add(sp.getQuantity());
                vec.add(vnFormat.format(sp.getPrice()));// định dạng đơn giá
                vec.add(vnFormat.format(sp.getTongTien())); // định dạng tổng tiền
                vec.add(sp.getNgayXuat());
                vec.add(sp.getCustomer());
                dtm.addRow(vec);
            }
            tbPX.setModel(dtm);
            // Ẩn cột idpx (giả sử là cột đầu tiên - index 0)
            tbPX.getColumnModel().getColumn(0).setMinWidth(0);
            tbPX.getColumnModel().getColumn(0).setMaxWidth(0);
            tbPX.getColumnModel().getColumn(0).setWidth(0);
        }
    }
//XOÁ XUẤT HÀNG

    public boolean xoaPhieuXuat(int idpx) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBAccess().getConnection();
            conn.setAutoCommit(false);

            // 1. Cập nhật status = 1 cho tất cả serial trong phiếu xuất
            String updateSerial = "UPDATE SanPham SET status = 1, start_date = NULL, end_date = NULL "
                    + "WHERE serial IN (SELECT serial FROM CTPX WHERE idpx = ?)";
            ps = conn.prepareStatement(updateSerial);
            ps.setInt(1, idpx);
            int updatedRows = ps.executeUpdate();
            ps.close();

            // 2. Xóa chi tiết phiếu xuất
            String deleteCTPX = "DELETE FROM CTPX WHERE idpx = ?";
            ps = conn.prepareStatement(deleteCTPX);
            ps.setInt(1, idpx);
            ps.executeUpdate();
            ps.close();

            // 3. Xóa phiếu xuất
            String deletePX = "DELETE FROM PhieuXuat WHERE idpx = ?";
            ps = conn.prepareStatement(deletePX);
            ps.setInt(1, idpx);
            int deletedRows = ps.executeUpdate();
            ps.close();

            conn.commit();

            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(this, "Đã xóa phiếu xuất và cập nhật " + updatedRows + " serial về trạng thái chưa xuất!");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu xuất để xóa!");
                return false;
            }
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa phiếu xuất: " + e.getMessage());
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
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //SỬA XUẤT HÀNG
    public boolean updateXuatHang(int idpx, int userId, int categoryId, int quantity, int price, String NYC, String ghiChu,
            String customer, String diaChi, String ngayXuat,
            String startDate, String endDate,
            List<String> listSerial) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBAccess().getConnection();
            conn.setAutoCommit(false);

            // 1. Lấy danh sách serial cũ trong CTPX
            Set<String> oldSerialSet = new HashSet<>();
            String sqlOldSerials = "SELECT serial FROM CTPX WHERE idpx = ?";
            ps = conn.prepareStatement(sqlOldSerials);
            ps.setInt(1, idpx);
            rs = ps.executeQuery();
            while (rs.next()) {
                oldSerialSet.add(rs.getString("serial").trim());
            }
            ps.close();
            rs.close();
            // 2. Kiểm tra serial mới có thuộc category_id đúng không
            String sqlCheckSerial = "SELECT category_id FROM SanPham WHERE serial = ?";
            ps = conn.prepareStatement(sqlCheckSerial);

            for (String serial : listSerial) {
                if (!oldSerialSet.contains(serial)) {
                    ps.setString(1, serial);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        int serialCategoryId = rs.getInt("category_id");
                        if (serialCategoryId != categoryId) {
                            JOptionPane.showMessageDialog(null,
                                    "Serial " + serial + " không thuộc loại sản phẩm này!\n"
                                    + "Vui lòng nhập serial đúng cho ");
                            conn.rollback();
                            return false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Serial " + serial + " không tồn tại!");
                        conn.rollback();
                        return false;
                    }
                    rs.close();
                }
            }
            ps.close();

            // 3. Tìm các serial bị xóa (có trong oldSerialSet nhưng không có trong listSerial mới)
            Set<String> deletedSerials = new HashSet<>(oldSerialSet);
            deletedSerials.removeAll(listSerial);

            // 4. Kiểm tra serial mới
            String sqlCheck = "SELECT * FROM SanPham WHERE serial = ? AND status = 1 ";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            for (String serial : listSerial) {
                if (!oldSerialSet.contains(serial)) {
                    psCheck.setString(1, serial);
                    rs = psCheck.executeQuery();
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Serial không hợp lệ hoặc đã được xuất: " + serial);
                        conn.rollback();
                        psCheck.close();
                        return false;
                    }
                    rs.close();
                }
            }
            psCheck.close();

            // 5. Cập nhật phiếu xuất
            String updatePX = "UPDATE PhieuXuat SET user_id=?, category_id=?, quantity=?, price=?,NYCau=?, ghiChu=?, customer=?,address=?, ngayXuat=? WHERE idpx=?";
            ps = conn.prepareStatement(updatePX);
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ps.setInt(3, quantity);
            ps.setInt(4, price);
            ps.setString(5, NYC);
            ps.setString(6, ghiChu);
            ps.setString(7, customer);
            ps.setString(8, diaChi);
            ps.setString(9, ngayXuat);
            ps.setInt(10, idpx);
            ps.executeUpdate();
            ps.close();

            // 6. Xóa CTPX cũ
            String deleteCTPX = "DELETE FROM CTPX WHERE idpx=?";
            ps = conn.prepareStatement(deleteCTPX);
            ps.setInt(1, idpx);
            ps.executeUpdate();
            ps.close();

            // 7. Chèn CTPX mới và update Sản phẩm
            String insertCTPX = "INSERT INTO CTPX(idpx, serial) VALUES (?, ?)";
            String updateSP = "UPDATE SanPham SET status = 0, start_date = ?, end_date = ? WHERE serial = ?";
            for (String serial : listSerial) {
                ps = conn.prepareStatement(insertCTPX);
                ps.setInt(1, idpx);
                ps.setString(2, serial);
                ps.executeUpdate();
                ps.close();

                ps = conn.prepareStatement(updateSP);
                ps.setString(1, startDate);
                ps.setString(2, endDate);
                ps.setString(3, serial);
                ps.executeUpdate();
                ps.close();
            }

            // 8. Cập nhật status = 1 cho các serial bị xóa
            if (!deletedSerials.isEmpty()) {
                String updateDeletedSP = "UPDATE SanPham SET status = 1, start_date = NULL, end_date = NULL WHERE serial = ?";
                ps = conn.prepareStatement(updateDeletedSP);
                for (String serial : deletedSerials) {
                    ps.setString(1, serial);
                    ps.addBatch();
                }
                ps.executeBatch();
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
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void suaXuatHang() {
        try {

            int userId = 1;
            int selectedRow = tbPX.getSelectedRow();
            String name = tbPX.getValueAt(selectedRow, 1).toString();
            int categoryId = loaisp_data.getCategoryIdByName(name);
         
            int price = Integer.parseInt(tf_giaXuat.getText().trim());
            String NYC = tf_NYCau.getText().trim();
            String ghiChu = tf_ghiChu.getText().trim();
            String customer = tf_khachHang.getText().trim();
            String address = tf_diaChi.getText().trim();
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

            DefaultTableModel model = (DefaultTableModel) tbSerial.getModel();
            int rowCount = model.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                Object value = model.getValueAt(i, 1); // Giờ Serial ở cột 1 (cột 2)
                if (value != null) {
                    String serial = value.toString().trim();
                    if (!serial.isEmpty()) {
                        listSerial.add(serial);
                    }
                }
            }

            // Gọi xử lý
            int idpx = Integer.parseInt(tbPX.getValueAt(selectedRow, 0).toString()); // Cột 0 là idpx
            boolean ok = updateXuatHang(idpx, userId, categoryId, soLuong, price, NYC, ghiChu, customer, address, ngayXuatStr, startDate.toString(), endDateStr, listSerial);
            if (ok) {
                JOptionPane.showMessageDialog(null, "Sửa thành công!");
                model.setRowCount(0); // clear table
            } else {
                JOptionPane.showMessageDialog(null, "Xuất hàng thất bại! Kiểm tra lại serial hoặc dữ liệu.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Xuất
    public void xuatPhieuBaoGiaPDF() {
        try {
            int selectedRow = tbPX.getSelectedRow();
            int idpx = Integer.parseInt(tbPX.getValueAt(selectedRow, 0).toString());
            String name = tbPX.getValueAt(selectedRow, 1).toString();
            int price = Integer.parseInt(tf_giaXuat.getText().trim());
            String NYC = tf_NYCau.getText().trim();
            String ghiChu = tf_ghiChu.getText().trim();
            String customer = tf_khachHang.getText().trim();
            String address = tf_diaChi.getText().trim();
            int thanhTien = Integer.parseInt(tbPX.getValueAt(selectedRow, 4).toString().trim().replaceAll("[^\\d]", ""));
            int soLuong = Integer.parseInt(tf_soLuong.getText().trim());
            // Lấy danh sách serial từ bảng
            List<String> listSerial = new ArrayList<>();
            //ngayXuat

            String ngayXuatStr = tf_ngayXuat.getText().trim();

            // 1. Nhập VAT
            String inputVat = JOptionPane.showInputDialog(null, "Nhập VAT (%):", "10");
            if (inputVat == null || inputVat.isEmpty()) {
                return;
            }
            int vatPercent = Integer.parseInt(inputVat.trim());

// === Mở hộp thoại chọn nơi lưu file ===
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Chọn thư mục để lưu file PDF");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            int result = chooser.showSaveDialog(null);
            if (result != JFileChooser.APPROVE_OPTION) {
                return; // Người dùng bấm Cancel
            }

            File selectedDir = chooser.getSelectedFile();
            String fileName = name + " cho " + customer + ".pdf";
            String fullPath = selectedDir.getAbsolutePath() + File.separator + fileName;
            // 2. Tạo Document PDF
            Document document = new Document(PageSize.A4) {
            };

            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            // 3. Fonts
            //BaseFont bf = BaseFont.createFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            BaseFont bf = BaseFont.createFont(
                    getClass().getResource("/fonts/arial.ttf").toString(),
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );
            Font fontTitle = new Font(bf, 14, Font.BOLD);
            Font fontNormal = new Font(bf, 12);
            Font fontBold = new Font(bf, 12, Font.BOLD);
            Font ctyBold = new Font(bf, 10, Font.BOLD);
            Font fontcty = new Font(bf, 10);
            // 4. Header bảng 2 cột
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{75f, 25f}); // chia đôi trái phải     

// 1. Thông tin công ty góc trái
            PdfPTable companyInfo = new PdfPTable(2);
            companyInfo.setWidthPercentage(100);
            companyInfo.setWidths(new float[]{70f, 30f});

            PdfPCell leftCompany = new PdfPCell();
            leftCompany.setBorder(Rectangle.NO_BORDER);
            leftCompany.addElement(new Paragraph("CÔNG TY CỔ PHẦN ĐẦU TƯ KT TM NAM TRUNG", ctyBold));
            leftCompany.addElement(new Paragraph("45 Thạch Thị Thanh, Phường Tân Định, Quận 1", fontcty));
            companyInfo.addCell(leftCompany);

// Cột phải trống hoặc thêm nội dung
            PdfPCell rightEmpty = new PdfPCell(new Phrase(""));
            rightEmpty.setBorder(Rectangle.NO_BORDER);
            companyInfo.addCell(rightEmpty);

            document.add(companyInfo);

// Phần trái
            PdfPCell leftCell = new PdfPCell();
            leftCell.setBorder(Rectangle.NO_BORDER);
            leftCell.addElement(new Paragraph("Tên khách hàng: " + customer, fontNormal));
            leftCell.addElement(new Paragraph("Địa chỉ: " + address, fontNormal));
            leftCell.addElement(new Paragraph("Tên Sản Phẩm: " + name, fontNormal));
            leftCell.addElement(new Paragraph("Số Lượng: " + soLuong, fontNormal));
            leftCell.addElement(new Paragraph("Tổng tiền: " + String.format("%,d", thanhTien), fontNormal));
            leftCell.addElement(new Paragraph("Nội dung:", fontNormal));

// Phần phải
            PdfPCell rightCell = new PdfPCell();
            rightCell.setBorder(Rectangle.NO_BORDER);
            rightCell.addElement(new Paragraph("Ngày: " + ngayXuatStr, fontNormal));
            rightCell.addElement(new Paragraph("Số phiếu: BG" + String.format("%05d", idpx), fontNormal));
            rightCell.addElement(new Paragraph(" ", fontNormal));
            rightCell.addElement(new Paragraph("Đơn giá: " + String.format("%,d", price), fontNormal));
            rightCell.addElement(new Paragraph("Đơn vị tiền tệ: VND", fontNormal));

// Thêm vào bảng
            headerTable.addCell(leftCell);
            headerTable.addCell(rightCell);

// Tiêu đề "BÁO GIÁ" ở giữa
            Paragraph title = new Paragraph("HOÁ ĐƠN", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Dòng trắng

// Thêm bảng header 2 cột
            document.add(headerTable);
            document.add(new Paragraph(" ")); // Dòng trắng nếu cần
            // 5. Bảng chi tiết
            PdfPTable table = new PdfPTable(1);
            table.setWidths(new float[]{100f});
            table.setWidthPercentage(30);

            // Header bảng
            String[] headers = {"Serial"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, fontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Dữ liệu
            DefaultTableModel model = (DefaultTableModel) tbSerial.getModel();
            int rowCount = model.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                Object value = model.getValueAt(i, 1); // Giờ Serial ở cột 1 (cột 2)
                if (value != null) {
                    String serial = value.toString().trim();
                    if (!serial.isEmpty()) {
                        listSerial.add(serial);
                    }
                }
            }
            for (String s : listSerial) {

                PdfPCell serialCell = new PdfPCell(new Phrase(s, fontNormal));
                serialCell.setHorizontalAlignment(Element.ALIGN_CENTER); // căn giữa ngang
                serialCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // căn giữa dọc (nếu cần)
                table.addCell(serialCell);

            }

            document.add(table);

            // 6. Tổng kết
            long tienVAT = thanhTien * vatPercent / 100;
            long tongThanhToan = thanhTien + tienVAT;

            document.add(new Paragraph("\nCộng tiền hàng: " + String.format("%,d", thanhTien), fontNormal));
            document.add(new Paragraph("Thuế VAT (" + vatPercent + "%): " + String.format("%,d", tienVAT), fontNormal));
            document.add(new Paragraph("Tổng thanh toán: " + String.format("%,d", tongThanhToan), fontBold));
            document.add(new Paragraph("Số tiền bằng chữ: " + NumberToWords.convert(tongThanhToan), fontNormal));

            // 7. Ký tên
            // Bảng chữ ký 2 cột
            PdfPTable signTable = new PdfPTable(2);
            signTable.setWidthPercentage(100);
            signTable.setWidths(new float[]{75f, 25f});

// Ô bên trái – Người lập phiếu
            PdfPCell nguoiLap = new PdfPCell();
            nguoiLap.setBorder(Rectangle.NO_BORDER);
            nguoiLap.setHorizontalAlignment(Element.ALIGN_CENTER);
            nguoiLap.addElement(new Paragraph("Người lập phiếu", fontNormal));
            nguoiLap.addElement(new Paragraph("(Ký, họ tên)", fontcty));
            signTable.addCell(nguoiLap);

// Ô bên phải – Khách hàng
            PdfPCell khachHang = new PdfPCell();
            khachHang.setBorder(Rectangle.NO_BORDER);
            khachHang.setHorizontalAlignment(Element.ALIGN_CENTER);
            khachHang.addElement(new Paragraph("Khách hàng", fontNormal));
            khachHang.addElement(new Paragraph("(Ký, họ tên)", fontcty));
            signTable.addCell(khachHang);

// Thêm bảng chữ ký vào document
            document.add(new Paragraph("\n\n")); // Dòng trắng phía trên chữ ký
            document.add(signTable);
            document.close();

            JOptionPane.showMessageDialog(null, "Xuất file PDF thành công:\n" + fileName);
            Desktop.getDesktop().open(new File(fullPath));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + e.getMessage());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSerial = new javax.swing.JTable();
        btn_Luu = new javax.swing.JButton();
        tf_soLuong = new javax.swing.JTextField();
        tf_ngayXuat = new javax.swing.JTextField();
        tf_giaXuat = new javax.swing.JTextField();
        tf_khachHang = new javax.swing.JTextField();
        cb_Time = new javax.swing.JComboBox<>();
        btnXoa = new javax.swing.JButton();
        tf_diaChi = new javax.swing.JTextField();
        tf_ghiChu = new javax.swing.JTextField();
        tf_NYCau = new javax.swing.JTextField();
        btnXuat = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH PHIẾU XUẤT");

        tbPX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbPX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Số Lượng", "Đơn Giá", "Thành Tiền", "Ngày Xuất", "Khách Hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPXMouseClicked(evt);
            }
        });
        tbPX.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbPXComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(tbPX);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Tìm kiếm :");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        tbSerial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbSerial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        jScrollPane2.setViewportView(tbSerial);
        if (tbSerial.getColumnModel().getColumnCount() > 0) {
            tbSerial.getColumnModel().getColumn(0).setResizable(false);
            tbSerial.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbSerial.getColumnModel().getColumn(1).setResizable(false);
            tbSerial.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        btn_Luu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        tf_soLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_soLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số Lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        tf_soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_soLuongActionPerformed(evt);
            }
        });

        tf_ngayXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_ngayXuat.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Ngày Bảo Hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tf_giaXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_giaXuat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá Xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tf_khachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_khachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        cb_Time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12 tháng", "24 tháng", "36 tháng" }));
        cb_Time.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")), "Thời gian bảo hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tf_diaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_diaChi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Địa Chỉ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tf_ghiChu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_ghiChu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ghi Chú", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        tf_ghiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ghiChuActionPerformed(evt);
            }
        });

        tf_NYCau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_NYCau.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Người Yêu Cầu Xuất", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        tf_NYCau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_NYCauActionPerformed(evt);
            }
        });

        btnXuat.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXuat.setText("Xuất PDF");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXuat)
                        .addGap(9, 9, 9)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_ngayXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tf_giaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_NYCau, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_ghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTim)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuat))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_NYCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_giaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ngayXuat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        loadTable_TheoTen(tfTim.getText().trim());
    }//GEN-LAST:event_btnTimActionPerformed

    private void tbPXComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbPXComponentShown
        loadDataTableSP();
    }//GEN-LAST:event_tbPXComponentShown

    private void tf_ghiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ghiChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_ghiChuActionPerformed

    private void tf_NYCauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_NYCauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NYCauActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        int selectedRow = tbPX.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu xuất để xuất báo giá.");
            return;
        }

        try {
            int idpx = Integer.parseInt(tbPX.getValueAt(selectedRow, 0).toString());
            xuatPhieuBaoGiaPDF();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất báo giá: " + e.getMessage());
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {
         if (tbSerial.isEditing()) {
            tbSerial.getCellEditor().stopCellEditing();
        }
        int selectedRow = tbPX.getSelectedRow();
        if (selectedRow >= 0) {
            int idpx = Integer.parseInt(tbPX.getValueAt(selectedRow, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn sửa phiếu xuất này?",
                    "Xác nhận sửa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                suaXuatHang();
                loadDataTableSP(); // Tải lại danh sách phiếu xuất
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu xuất cần sửa!");
        }
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tbPX.getSelectedRow();
        if (selectedRow >= 0) {
            int idpx = Integer.parseInt(tbPX.getValueAt(selectedRow, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa phiếu xuất này?\nTất cả serial sẽ được chuyển về trạng thái chưa xuất.",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = xoaPhieuXuat(idpx);
                if (success) {
                    loadDataTableSP(); // Tải lại danh sách phiếu xuất
                    DefaultTableModel model = (DefaultTableModel) tbSerial.getModel();
                    model.setRowCount(0); // Xóa dữ liệu bảng serial
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu xuất cần xóa!");
        }
    }

    private void tf_soLuongActionPerformed(java.awt.event.ActionEvent evt) {
        capNhatBangSerialTheoSoLuong();
    }

    private void tbPXMouseClicked(java.awt.event.MouseEvent evt) {

        int selectedRow = tbPX.getSelectedRow();
        if (selectedRow >= 0) {
            int idpx = Integer.parseInt(tbPX.getValueAt(selectedRow, 0).toString()); // Cột 0 là idpx
            // Gọi form sửa hoặc load dữ liệu
            loadChiTietPhieuXuat(idpx); // hoặc gọi form mới: new SuaXuatHang(idpx)
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
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuat;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JComboBox<String> cb_Time;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbPX;
    private javax.swing.JTable tbSerial;
    private javax.swing.JTextField tfTim;
    private javax.swing.JTextField tf_NYCau;
    private javax.swing.JTextField tf_diaChi;
    private javax.swing.JTextField tf_ghiChu;
    private javax.swing.JTextField tf_giaXuat;
    private javax.swing.JTextField tf_khachHang;
    private javax.swing.JTextField tf_ngayXuat;
    private javax.swing.JTextField tf_soLuong;
    // End of variables declaration//GEN-END:variables
}
