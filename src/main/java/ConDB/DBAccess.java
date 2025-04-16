package ConDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {
    private Connection con;
    private Statement stmt;

    public DBAccess() {
        try {
            CONNECTION conn = new CONNECTION();
            con = conn.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {

        }
    }


    public ResultSet Query(String str) {
        try {
            ResultSet rs = stmt.executeQuery(str);
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public void close() {
        try {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            
        }
    }
}
