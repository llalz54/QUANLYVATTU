
package DAO;
import ConDB.DBAccess;
import DTO.PHIEUNHAP;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 *
 * @author Admin
 */
public class NhapHang {
    private ArrayList<PHIEUNHAP> listPN = null;
     

    public NhapHang() {
        docListPN();
    }

    public void docListPN() {
        listPN = sp_getListPN();
    }

    public ArrayList<PHIEUNHAP> sp_getListPN() {
        try {
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT * FROM PhieuNhap");
            ArrayList<PHIEUNHAP> dspn = new ArrayList<>();
            while (rs.next()) {
                PHIEUNHAP pn = new PHIEUNHAP();
                
                pn.setIdpn(1);
                pn.setNgayNhap(rs.getString(2).trim());
                pn.setGroup_id(rs.getInt(3));
                pn.setCategoryID(rs.getInt(4));
                pn.setQuantity(rs.getInt(5));
                pn.setPrice(rs.getInt(6));
                pn.setTongTien(rs.getInt(7));
                pn.setSupplier(rs.getString(8).trim());
                dspn.add(pn);
            }
            return dspn;
        } catch (SQLException e) {
        }
        return null;
    }
    
   
            
           
    
}
