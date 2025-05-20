
package DAO;

import ConDB.DBAccess;
import DTO.NCC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                gr.setMST(rs.getString(3));
                gr.setDiaChi(rs.getString(4).trim());
                dssp.add(gr);
            }
            return dssp;
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách nhóm sản phẩm!!!");
            return null;
        } finally {
            if(acc != null) {
                acc.close();
            }
        }
    }
}
