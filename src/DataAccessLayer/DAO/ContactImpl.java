/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.DAO;

import DataAccessLayer.DTO.Client;
import DataAccessLayer.DTO.Contact;
import DataAccessLayer.DataBaseManager.DataBaseManager;
import DataAccessLayer.Interface.ContactInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author abdalla
 */
public class ContactImpl implements ContactInterface {
   private DataBaseManager managerObj;
   private PreparedStatement st;
   private ResultSet rs ;
   
    public ContactImpl() {
        managerObj =new DataBaseManager();
    }

    @Override
    public int create(Client obj1, Client obj2, Connection con) {
       int i=-1;
       try {
           con.setAutoCommit (false);
           st = con.prepareStatement("INSERT  INTO Contact (Email_1 , Email_2  )VALUES (? , ? ) ",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
           st.setString(1, obj1.getEmail());
           st.setString(2, obj2.getEmail());
           rs = st.executeQuery();
           i=0;
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return i;
    }

    @Override
    public Contact retreive(Client obj, Client obj2, Connection con) {
       Contact c=new Contact();
       try {
           Statement st = con.createStatement();
           rs = st.executeQuery("SELECT * FROM Contact WHERE (Email_1 = '"+obj.getEmail()+"' and Email_2 = '"+obj2.getEmail()+"') or (Email_1 = '"+obj2.getEmail()+"' and Email_2 = '"+obj.getEmail()+"')");
           rs = st.getResultSet();
           rs.next();
           c.setEmail(rs.getString(1));
           c.setContactEmail(rs.getString(2));
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return c;
    }

    @Override
    public int update (String email,Client obj,Connection con) {
        int i=-1;
       try {
           Statement st = con.createStatement();
           st.executeUpdate("UPDATE Contact SET Email_1 = '"+obj.getEmail()+"' WHERE Email_1 = '"+email+"'");
           st.executeUpdate("UPDATE Contact SET Email_2 = '"+obj.getEmail()+"' WHERE Email_2 = '"+email+"'");
           i=0;
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return i;
    }

    @Override
    public int delete(Client obj1, Client obj2, Connection con) {
         int i=-1;
       try {
           Statement st = con.createStatement();
           st.executeUpdate("DELETE FROM CONTACT WHERE ( EMAIL_1 = '"+obj1.getEmail()+"' AND EMAIL_2= '"+obj2.getEmail()+"') OR ( EMAIL_1 = '"+obj2.getEmail()+"' AND EMAIL_2= '"+obj1.getEmail()+"')");
           //st.executeUpdate("UPDATE Contact SET Email_2 = '"+obj.getEmail()+"' WHERE Email_2 = '"+obj2.getEmail()+"'");
           i=0;
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return i;
    }

    @Override
    public ArrayList<Contact> retreiveall(Client obj, Connection con) {
        ArrayList<Contact> c;
       c = new ArrayList<Contact>();
       try {
           Statement st = con.createStatement();
           rs = st.executeQuery(" SELECT * FROM CONTACT WHERE EMAIL_1 = '"+obj.getEmail()+"' OR EMAIL_2 = '"+obj.getEmail()+"'");
           rs = st.getResultSet();
           rs.next();
           while(!rs.isAfterLast()){
               Contact temp = new Contact();
               temp.setContactEmail(rs.getString(1));
               temp.setEmail(rs.getString(2));
               c.add(temp);
               rs.next();
           }
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return c;
    }

    @Override
    public int create(Client obj, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client retreive(String email, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Client obj, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Client obj, Connection con) {
        int i=-1;
       try {
           Statement st = con.createStatement();
           st.executeUpdate("DELETE FROM CONTACT WHERE EMAIL_1 = '"+obj.getEmail()+"' OR EMAIL_2 = '"+obj.getEmail()+"'");
           //st.executeUpdate("UPDATE Contact SET Email_2 = '"+obj.getEmail()+"' WHERE Email_2 = '"+obj2.getEmail()+"'");
           i=0;
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return i;
    }
}