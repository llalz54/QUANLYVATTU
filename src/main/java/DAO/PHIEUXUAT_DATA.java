
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
  
    public static boolean createPX(int userID, int categoryID, int quantity, int price, String ngayXuat, String customer) {
        Connection conn = CONNECTION.getConnection();
        try {
            String sql = "INSERT INTO PHIEUXUAT values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, userID);
            ps.setInt(2, categoryID);
            ps.setInt(3, quantity);
            ps.setInt(4, price);
            ps.setString(5, ngayXuat);
            ps.setString(6, customer);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm PN!!!");
        }
        return false;
    }
}
