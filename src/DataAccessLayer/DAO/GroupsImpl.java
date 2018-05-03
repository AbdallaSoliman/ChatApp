/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.DAO;


import DataAccessLayer.DTO.Client;
import DataAccessLayer.DTO.Groups;
import DataAccessLayer.DTO.Has;
import DataAccessLayer.DataBaseManager.DataBaseManager;
import DataAccessLayer.Interface.GroupsInterface;
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
public class GroupsImpl implements GroupsInterface {
    private DataBaseManager managerObj;
    private PreparedStatement st;
    private Statement s ;
    private ResultSet rs ;
    public GroupsImpl() {
        managerObj =new DataBaseManager();
    }
    
    @Override
    public Groups retreive(String id, Connection con) {
        Groups g=new Groups();
       try {
           Statement st = con.createStatement();
           //st = con.prepareStatement("SELECT * FROM groups WHERE ID =",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
           //st.setString(1,id);
           rs = st.executeQuery("SELECT * FROM groups WHERE ID = '"+id+"'");
           rs = st.getResultSet();
           System.out.println();
           rs.next();
           g.setId(rs.getString(1));
           g.setName(rs.getString(2));
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
            rs.close();
//            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(GroupsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return g;
    }

    @Override
    public Groups retreiveByName(String name, Connection con) {
        Groups g=new Groups();
       try {
           Statement st = con.createStatement();
           //st = con.prepareStatement("SELECT * FROM groups WHERE ID =",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
           //st.setString(1,id);
           rs = st.executeQuery("SELECT * FROM groups WHERE name ='"+name+"'");
           rs = st.getResultSet();
           rs.next();
           g.setId(rs.getString(1));
           g.setName(rs.getString(2));
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        try {
            rs.close();
//            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(GroupsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return g;
    }

    

    @Override
    public int create(Groups obj, Connection con) {
        int i=-1;
       try {
           st = con.prepareStatement("INSERT INTO Groups (ID, Name)VALUES (? , ? )",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
           st.setString(1, obj.getId());
           st.setString(2, obj.getName());
           rs = st.executeQuery();
           rs = st.getResultSet();
           i=0;
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return i;
    }

    @Override
    public int update(Groups obj, Connection con) {
        int i=-1;
       try {
           Statement st = con.createStatement();
           rs = st.executeQuery("UPDATE Groups SET Name = '"+ obj.getName() +"' WHERE ID = '" + obj.getId()+"'");
           rs = st.getResultSet();
           i=0;
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return i;
    }

    @Override
    public int delete(Groups obj, Connection con) {
        int i=-1;
       try {
           st = con.prepareStatement("Delete FROM Groups WHERE ID=?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
           st.setString(1, obj.getId());
           rs = st.executeQuery();
           rs = st.getResultSet();
           i=0;
       } catch (SQLException ex) {
           Logger.getLogger(ContactImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return i;
    }

    @Override
    public ArrayList<Groups> retreiveAll(Connection con) {
        ArrayList<Groups> g;
       g = new ArrayList<Groups>();
       try {
           Statement st = con.createStatement();
           rs = st.executeQuery(" SELECT * " + " FROM Groups");
           rs = st.getResultSet();
           rs.next();
           while(!rs.isAfterLast()){
               Groups temp = new Groups();
               temp.setId(rs.getString(1));
               temp.setName(rs.getString(2));
               g.add(temp);
               rs.next();
           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
        return g;
    }

       
    @Override
    public ArrayList<Client> reteriveClients(String id,Connection con) {
        HasImpl him = new HasImpl();
        Client obj=null;
        ArrayList<Client> cl = new ArrayList<Client>();
        ArrayList<Has> clients=him.retreiveGroupRows(id,con);
             for (int i=0; i<clients.size(); i++) {
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
            rs=s.executeQuery(" SELECT * " + " FROM Client " + " WHERE email  = '" + clients.get(i).getEmail() + "'");
            if(rs.next()){
                obj = new Client();
                obj.setEmail(rs.getString(1));
                obj.setPassword(rs.getString(2));
                obj.setName(rs.getString(3));
                obj.setGender(rs.getString(4));
                obj.setStatus(rs.getString(6));
                obj.setCity(rs.getString(7));
                obj.setPhone(rs.getString(8));
                obj.setDate(rs.getDate(9));
                
                cl.add(obj);
            }
        }
        catch(Exception x){
            x.printStackTrace();
        }
        finally
        {
            try {
                s.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
             }
             return cl;
    }

    
}
