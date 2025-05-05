package DAO;

import ConDB.DBAccess;
import DTO.LOAISP;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LOAISP_DATA {

    private ArrayList<LOAISP> listLoaiSP = null;

    public LOAISP_DATA() {

    }

    public void docListLoaiSP() {
        listLoaiSP = getListLoaiSP();
    }

    public ArrayList<LOAISP> getListLoaiSP() {
        try {
            String query = "SELECT lsp.category_id, lsp.group_id, n.name AS gr_name,"
                    + "lsp.name, lsp.status, lsp.brand, COUNT(sp.product_id) AS soluong\n"
                    + "FROM LoaiSP lsp\n"
                    + "JOIN NhomSP n ON lsp.group_id = n.group_id\n"
                    + "LEFT JOIN SanPham sp ON lsp.category_id = sp.category_id\n"
                    + "GROUP BY lsp.category_id,lsp.group_id,n.name,lsp.name,lsp.status,lsp.brand;";
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query(query);
            ArrayList<LOAISP> list = new ArrayList<>();
            while (rs.next()) {
                LOAISP loaiSP = new LOAISP();
                loaiSP.setCategoryID(rs.getInt("category_id"));
                loaiSP.setGroupID(rs.getInt("group_id"));
                loaiSP.setGrName(rs.getString("gr_name"));
                loaiSP.setName(rs.getString("name"));
                loaiSP.setStatus(rs.getString("status"));
                loaiSP.setBrand(rs.getString("brand"));
                loaiSP.setSoLuong(rs.getInt("soluong"));

                list.add(loaiSP);
            }
            acc.close();
            return list;
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách loại sản phẩm!!!");
            return null;
        }
    }

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
            if (dBAccess != null) {
                dBAccess.close();
            }
        }
    }
    
    public ArrayList<LOAISP> getDS_LoaiSP(String groupName, String brand) {
        
        ArrayList<LOAISP> dssp = new ArrayList<>();
        for (LOAISP sp : listLoaiSP) {
            if (groupName.contains(sp.getGrName()) && brand.contains(sp.getBrand())) {
                dssp.add(sp);
            }
        }
        return dssp;
    }
}
