
package DAO;

import ConDB.DBAccess;
import DTO.NHOMSP;
import DTO.SANPHAM;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NHOMSP_DATA {
     private ArrayList<NHOMSP> listnhomSP = null;
    
    public NHOMSP_DATA() {
        docListnhomSP();
    }
    
    public void docListnhomSP() {
        listnhomSP = getListnhomSP();
    }
    
    public ArrayList<NHOMSP> getListnhomSP() {
        try {
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT name FROM NhomSP");
            ArrayList<NHOMSP> dssp = new ArrayList<>();
            while (rs.next()) {
              NHOMSP sp = new NHOMSP();
               sp.setName(rs.getString("name"));
               dssp.add(sp);
               
            }
            acc.close();
            return dssp;
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách nhóm sản phẩm!!!");
            return null;
        }
        
    }
}
