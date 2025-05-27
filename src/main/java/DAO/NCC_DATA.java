package DAO;

import ConDB.CONNECTION;
import ConDB.DBAccess;
import DTO.NCC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NCC_DATA {

    private ArrayList<NCC> listNCC = null;

    public NCC_DATA() {
        docListNCC();
    }

    public void docListNCC() {
        listNCC = getListNCC();
    }

    public ArrayList<NCC> getListNCC() {
        DBAccess acc = null;
        try {
            acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT * FROM NCC");
            ArrayList<NCC> dssp = new ArrayList<>();
            while (rs.next()) {
                NCC gr = new NCC();
                gr.setSupplier_id(rs.getInt(1));
                gr.setName(rs.getString(2).trim());
                gr.setFullName(rs.getString(3).trim());
                gr.setMST(rs.getString(4).trim());
                gr.setDiaChi(rs.getString(5).trim());
                gr.setStatus(rs.getString(6).trim());
                dssp.add(gr);
            }
            return dssp;
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách nhà cung cấp!!!");
            return null;
        } finally {
            if (acc != null) {
                acc.close();
            }
        }
    }

    
    public static void create_Supplier (String name, String MST, String address){
        Connection conn = CONNECTION.getConnection();
        String sql = "insert into NCC values(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, MST);
            ps.setString(3, address);
            ps.executeUpdate();
            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm NCC!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void update_Supplier(int id, String name, String MST, String address) {
        Connection conn = CONNECTION.getConnection();
        String sql = "UPDATE NCC SET name='" + name + "', MST='" + MST + "', address='" + address + "'  where supplier_id='" + id + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Sửa nhà cung cấp thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa nhà cung cấp!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void delete_LSP(int id) {
        Connection conn = CONNECTION.getConnection();
        if (check_HD_NCC(id) == false) {
            String sql = "DELETE FROM NCC WHERE supplier_id='" + id + "'";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                ps.close();
                conn.close();
                JOptionPane.showMessageDialog(null, "Xoá nhà cung cấp thành công!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi xoá nhà cung cấp!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String sql1 = "UPDATE NCC SET status='0' WHERE supplier_id='" + id + "'";
            try {
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.executeUpdate();
                ps1.close();
                conn.close();
                JOptionPane.showMessageDialog(null, "Nhà cung cấp này đã được ghi phiếu nhập => Thay đổi trạng thái!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi cập nhật trạng thái Nhà cung cấp!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
    
    private static boolean check_HD_NCC(int id) {
        Connection conn = CONNECTION.getConnection();
        String sql = "SELECT supplier_id FROM PhieuNhap WHERE supplier_id ='" + id + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Lỗi hàm kiểm tra hoạt động Nhà cung cấp!");
        }
        return false;
    }

    public int getNCCId(String name) {
        try (Connection conn = new DBAccess().getConnection()) {
            String sql = "SELECT supplier_id FROM NCC WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("supplier_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
