/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.DataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author abdalla
 */
public class DataBaseManager {
    private Connection con;
      public  Connection getCon() {
         try {
             DriverManager.registerDriver(new OracleDriver());
             con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
            con.setAutoCommit (false);
             return con;
         } catch (SQLException ex) {
             Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
    }
}
