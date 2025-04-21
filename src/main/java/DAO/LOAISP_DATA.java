
package DAO;

import ConDB.DBAccess;
import DTO.LOAISP;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LOAISP_DATA {
    
    public ArrayList<LOAISP> getLoaiSP(String nhomSP) {
        DBAccess dBAccess = null;
        String sqString = "SELECT l.category_id, l.name FROM LoaiSP l JOIN NhomSP n ON l.group_id = n.group_id WHERE n.name = ?";
        try {
            dBAccess = new DBAccess();
            
            PreparedStatement preparedStatement = dBAccess.getConnection().prepareStatement(sqString);
            preparedStatement.setString(1, nhomSP);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<LOAISP> result = new ArrayList<>();
            while (rs.next()) {
                LOAISP category = new LOAISP(rs);
                result.add(category);
            }
            return result;
        } catch (Exception e) {
            System.out.println("Loi load loaiSP");
            e.printStackTrace();
            return null;
        } finally {
            if(dBAccess != null) {
                dBAccess.close();
            }
        }
    }
}
