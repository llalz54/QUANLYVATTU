
package DAO;

import ConDB.DBAccess;
import DTO.SANPHAM;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SANPHAM_DATA {
    private ArrayList<SANPHAM> listSP = null;
    
    public SANPHAM_DATA() {
        docListSP();
    }
    
    public void docListSP() {
        listSP = getListSP();
    }
    
    public ArrayList<SANPHAM> getListSP() {
        try {
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT * FROM dbo.SanPham");
            ArrayList<SANPHAM> dssp = new ArrayList<>();
            while (rs.next()) {
              SANPHAM sp = new SANPHAM();
               sp.setProductID(rs.getInt("product_id"));
               sp.setCategoryID(rs.getInt("category_id"));
               sp.setSerial(rs.getString("serial").trim());
               sp.setStatus(rs.getString("status"));
               sp.setStartDate(rs.getString("start_date"));
               sp.setEndDate(rs.getString("end_day"));
               dssp.add(sp);
               
            }
            acc.close();
            return dssp;
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách sản phẩm!!!");
        }
        return null;
    }
}
