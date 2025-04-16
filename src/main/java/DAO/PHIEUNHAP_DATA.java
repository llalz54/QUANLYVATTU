package DAO;

import ConDB.CONNECTION;
import ConDB.DBAccess;
import DTO.PHIEUNHAP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PHIEUNHAP_DATA {
    private ArrayList<PHIEUNHAP> listPN = null;
    
    public PHIEUNHAP_DATA() {
        docListPN();
    }
    
    public void docListPN() {
        listPN = getListPN();
    }
    
    public ArrayList<PHIEUNHAP> getListPN() {
        try {
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT * FROM PHIEUNHAP");
            ArrayList<PHIEUNHAP> dspn = new ArrayList<>();
            while (rs.next()) {
                PHIEUNHAP pn = new PHIEUNHAP();

                pn.setIdpn(rs.getInt(1));
                pn.setUserID(rs.getInt(2));
                pn.setCategoryID(rs.getInt(3));
                pn.setQuantity(rs.getInt(4));
                pn.setPrice(rs.getInt(5));
                pn.setNgayNhap(rs.getString(6).trim());
                pn.setSupplier(rs.getString(7).trim());
                dspn.add(pn);
            }
            acc.close();
            return dspn;
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách phiếu nhập!!!");
        }
        return null;
    }

    public static boolean createPN(int userID, int categoryID, int quantity, int price, String ngayNhap, String supplier) {
        Connection conn = CONNECTION.getConnection();
        try {
            String sql = "INSERT INTO PHIEUNHAP values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, userID);
            ps.setInt(2, categoryID);
            ps.setInt(3, quantity);
            ps.setInt(4, price);
            ps.setString(5, ngayNhap);
            ps.setString(6, supplier);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm PN!!!");
        }
        return false;
    }
    
    public static void updatePN(){
        
    }
}
