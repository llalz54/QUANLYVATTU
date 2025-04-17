package DAO;

import ConDB.DBAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class OTHER_DATA {

    public static void loadCBDM(JComboBox cbox) {
        cbox.removeAllItems();
        try {
            DBAccess acc = new DBAccess();
            ResultSet rs = acc.Query("SELECT name FROM NhomSP");
            while (rs.next()) {
                cbox.addItem(rs.getString("name").trim());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi loadCBDM!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
