
package ConDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.sql.Statement;

/**
 *
 * @author My-PC
 */
public class DBAccess {
    private Connection con;
    private Statement stmt;

    public DBAccess() throws SQLException {
        try {

            CONNECTION conn = new CONNECTION();
            con = conn.getConnection();
            stmt = con.createStatement();
      
        } catch (Exception e) {

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
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
    } catch (SQLException e) {
        System.err.println("Error closing Statement: " + e.getMessage());
    }

    try {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    } catch (SQLException e) {
        System.err.println("Error closing Connection: " + e.getMessage());
    }
}

    public Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

      
}


