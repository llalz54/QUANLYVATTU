<<<<<<< HEAD
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
>>>>>>> 2dedcff8aaf89c9810dbb5a323d6ac87ffa24b4b
package ConDB;

import java.sql.Connection;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.sql.SQLException;
import java.sql.Statement;

=======
import java.sql.Statement;

/**
 *
 * @author My-PC
 */
>>>>>>> 2dedcff8aaf89c9810dbb5a323d6ac87ffa24b4b
public class DBAccess {
    private Connection con;
    private Statement stmt;

    public DBAccess() {
        try {
<<<<<<< HEAD
            CONNECTION conn = new CONNECTION();
            con = conn.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
=======
            CONNECTION myCon = new CONNECTION();
            con = myCon.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
>>>>>>> 2dedcff8aaf89c9810dbb5a323d6ac87ffa24b4b

        }
    }


    public ResultSet Query(String str) {
        try {
            ResultSet rs = stmt.executeQuery(str);
            return rs;
<<<<<<< HEAD
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
=======
        } catch (Exception e) {
            return null;
        }
    }
}
>>>>>>> 2dedcff8aaf89c9810dbb5a323d6ac87ffa24b4b
