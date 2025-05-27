/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CTPX;
import DTO.PHIEUXUAT;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

/**
 *
 * @author Admin
 */
public class xuatPhieuBaoGiaPDF {
  public void xuatPhieuBaoGiaPDF(PHIEUXUAT px, List<CTPX> ctpxList) {
    try {
        // 1. Nhập VAT
        String inputVat = JOptionPane.showInputDialog(null, "Nhập VAT (%):", "10");
        if (inputVat == null || inputVat.isEmpty()) return;
        int vatPercent = Integer.parseInt(inputVat.trim());

        // 2. Tạo Document PDF
        Document document = new Document(PageSize.A4) {};
        String fileName = "BaoGia_" + px.getIdpx() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        // 3. Fonts
        BaseFont bf = BaseFont.createFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontTitle = new Font(bf, 14, Font.BOLD);
        Font fontNormal = new Font(bf, 12);
        Font fontBold = new Font(bf, 12, Font.BOLD);

        // 4. Header
        Paragraph header = new Paragraph("BÁO GIÁ", fontTitle);
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        document.add(new Paragraph("Tên khách hàng: " + px.getKhachHang(), fontNormal));
        document.add(new Paragraph("Địa chỉ: " + px.getDiaChi(), fontNormal));
        document.add(new Paragraph("Ngày: " + px.getNgayXuat(), fontNormal));
        document.add(new Paragraph("Số phiếu: BG" + String.format("%05d", px.getIdpx()), fontNormal));
        document.add(new Paragraph(" ", fontNormal));

        // 5. Bảng chi tiết
        PdfPTable table = new PdfPTable(6);
        table.setWidths(new float[]{1.2f, 3f, 2f, 1.2f, 2f, 2f});
        table.setWidthPercentage(100);

        // Header bảng
        String[] headers = {"Mã hàng", "Tên hàng", "ĐVT", "SL", "Đơn giá", "Thành tiền"};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h, fontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        // Dữ liệu
        long tongTien = 0;
        for (CTPX ct : ctpxList) {
            long thanhTien = ct.getDonGia() * ct.getSoLuong();
            tongTien += thanhTien;

            table.addCell(new Phrase(ct.getMaSP(), fontNormal));
            table.addCell(new Phrase(ct.getTenSP(), fontNormal));
            table.addCell(new Phrase("Cái", fontNormal));
            table.addCell(new Phrase(String.valueOf(ct.getSoLuong()), fontNormal));
            table.addCell(new Phrase(String.format("%,d", ct.getDonGia()), fontNormal));
            table.addCell(new Phrase(String.format("%,d", thanhTien), fontNormal));
        }

        document.add(table);

        // 6. Tổng kết
        long tienVAT = tongTien * vatPercent / 100;
        long tongThanhToan = tongTien + tienVAT;

        document.add(new Paragraph("\nCộng tiền hàng: " + String.format("%,d", tongTien), fontNormal));
        document.add(new Paragraph("Thuế VAT (" + vatPercent + "%): " + String.format("%,d", tienVAT), fontNormal));
        document.add(new Paragraph("Tổng thanh toán: " + String.format("%,d", tongThanhToan), fontBold));
        document.add(new Paragraph("Số tiền bằng chữ: " + NumberToWords.convert(tongThanhToan), fontNormal));

        // 7. Ký tên
        document.add(new Paragraph("\n\nNgười lập phiếu", fontNormal));
        document.add(new Paragraph(" (Ký, họ tên)", fontNormal));
        document.close();

        JOptionPane.showMessageDialog(null, "Xuất file PDF thành công:\n" + fileName);
        Desktop.getDesktop().open(new File(fileName));
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + e.getMessage());
    }
}
}
