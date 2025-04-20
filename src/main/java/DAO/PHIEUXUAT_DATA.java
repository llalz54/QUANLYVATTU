
package DAO;

import ConDB.DBAccess;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Connection;
import ConDB.CONNECTION;

/**
 *
 * @author Admin
 */
public class PHIEUXUAT_DATA {
  
  
    public boolean xuatHang(int userId, int categoryId, int quantity, int price, String customer, String ngayXuat, List<String> listSerial) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           conn = CONNECTION.getConnection();
           conn.setAutoCommit(false); // Bắt đầu transaction

        // 1. Kiểm tra serial hợp lệ
            for (String serial : listSerial) {
             String sqlCheck = "SELECT * FROM SanPham WHERE serial = ? AND status = 0";
             ps = conn.prepareStatement(sqlCheck);
             ps.setString(1, serial);
             rs = ps.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Serial không hợp lệ hoặc đã được xuất: " + serial);
                conn.rollback();
                return false;
            }
            ps.close();
        }

        // 2. Insert vào bảng PhieuXuat
        String sqlInsertPX = "INSERT INTO PhieuXuat(user_id, category_id, quantity, price, ngayXuat, customer) VALUES (?, ?, ?, ?, ?, ?)";
        ps = conn.prepareStatement(sqlInsertPX, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, categoryId);
        ps.setInt(3, quantity);
        ps.setDouble(4, price);
        ps.setString(5, ngayXuat);
        ps.setString(6, customer);
        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        int idpx = -1;
        if (rs.next()) {
            idpx = rs.getInt(1);
        } else {
            conn.rollback();
            return false;
        }
        ps.close();

        // 3. Thêm chi tiết serial và cập nhật trạng thái sản phẩm
        String sqlInsertCTPX = "INSERT INTO CTPX(idpx, serial) VALUES (?, ?)";
        String sqlUpdateStatus = "UPDATE SanPham SET status = 1 WHERE serial = ?";
        for (String serial : listSerial) {
            // Insert CTPX
            ps = conn.prepareStatement(sqlInsertCTPX);
            ps.setInt(1, idpx);
            ps.setString(2, serial);
            ps.executeUpdate();
            ps.close();

            // Update status
            ps = conn.prepareStatement(sqlUpdateStatus);
            ps.setString(1, serial);
            ps.executeUpdate();
            ps.close();
        }

        conn.commit(); // Thành công
        return true;

    } catch (Exception e) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (conn != null) conn.setAutoCommit(true); conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

}
