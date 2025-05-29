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
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.Desktop;
import java.io.File;
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
        document.add(new Paragraph("Tên khách hàng: " + px.getCustomer(), fontNormal));
        document.add(new Paragraph("Địa chỉ: " + px.getDiachi(), fontNormal));
        document.add(new Paragraph("Ngày: " + px.getNgayXuat(), fontNormal));
        document.add(new Paragraph("Số phiếu: BG" + String.format("%05d", px.getIdpx()), fontNormal));
        document.add(new Paragraph("Tên Sản Phẩm: " + px.getTenLoai(), fontNormal));
        document.add(new Paragraph("Số Lượng: " + px.getQuantity(), fontNormal));
        document.add(new Paragraph("Đơn giá: " + px.getPrice(), fontNormal));
        document.add(new Paragraph("Tổng tiền: " + px.getTongTien(), fontNormal));
        document.add(new Paragraph(" ", fontNormal));
        
        

        // 5. Bảng chi tiết
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{1.2f, 4f});
        table.setWidthPercentage(100);

        // Header bảng
        String[] headers = {"Mã hàng", "Tên hàng"};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h, fontBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
 
 

        // Dữ liệu
        long tongTien = px.getTongTien();
        for (CTPX ct : ctpxList) {
           
            

            table.addCell(new Phrase(ct.getSerial(), fontNormal));
            
            table.addCell(new Phrase("Cái", fontNormal));
          
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
