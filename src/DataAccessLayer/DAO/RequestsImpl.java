/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.DAO;

import DataAccessLayer.DTO.Requests;
import DataAccessLayer.DataBaseManager.DataBaseManager;
import DataAccessLayer.Interface.RequestsInterface;
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
public class RequestsImpl implements RequestsInterface {

    private Statement s;
    private PreparedStatement st;
    private ResultSet rs;
    private DataBaseManager managerObj;

    public RequestsImpl() {
        managerObj = new DataBaseManager();
    }

    {
        /*
    @Override
    public int create(Requests obj) {
        con = managerObj.getCon();

        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("INSERT  INTO Admin (EmailSender  , EmailReciever   )VALUES (? , ? ) ",
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, obj.getEmailSender());
            stmt.setString(2, obj.getEmailReciever());
            result = stmt.executeQuery();
            result = stmt.getResultSet();
            con.commit();
            con.close();
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int create(Requests obj, Connection con) {
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("INSERT  INTO Admin (EmailSender  , EmailReciever   )VALUES (? , ? ) ",
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, obj.getEmailSender());
            stmt.setString(2, obj.getEmailReciever());
            result = stmt.executeQuery();
            result = stmt.getResultSet();
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Requests retreive(String senderEmail, String recevierEmail) {
        Requests valueObject = new Requests();
        valueObject.setEmailSender(senderEmail);
        valueObject.setEmailReciever(recevierEmail);
        con = managerObj.getCon();
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("SELECT * FROM Requests WHERE (EmailSender = ? AND EmailReciever = ? ) ",
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, valueObject.getEmailSender());
            stmt.setString(2, valueObject.getEmailReciever());
            result = stmt.executeQuery();
            if (result.next()) {

                valueObject.setEmailSender(result.getString("EmailSender"));
                valueObject.setEmailReciever(result.getString("EmailReciever"));

            } else {
                System.out.println("RequestsImpl Object Not Found!");
            }
            result = stmt.getResultSet();
            con.commit();
            con.close();
            return valueObject;
        } catch (SQLException ex) {
            Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestsImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
public RequestsImpl createValueObject() {
          return new RequestsImpl();
    }
    @Override
    public Requests retreive(String senderEmail, String recevierEmail, Connection con) {
        RequestsImpl valueObject = createValueObject();
          valueObject.setEmailSender(senderEmail);
          valueObject.setEmailReciever(recevierEmail);
          load(con, valueObject);
          return Requests;
    }

    @Override
    public Requests retreive(String email, Connection con) {
        return null;
    }

    @Override
    public int update(Requests obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Requests obj, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Requests obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Requests obj, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Requests retreive(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         */
    }

    @Override
    public synchronized int create(Requests obj, Connection con) {
        try {
            //    Requests obj1=new Requests();

            //     obj1=retreive(obj.getEmailReciever(), obj.getEmailSender(),  con) ;
         //   System.out.println(obj.getEmailSender());
            st = con.prepareStatement("INSERT  INTO Requests ( EmailSender , EmailReciever )VALUES (? , ? ) ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setString(1, obj.getEmailSender());
            st.setString(2, obj.getEmailReciever());
            st.executeQuery();
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(RequestsImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestsImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Requests retreive(String EmailSender, String EmailReciever, Connection con) {
        Requests req = new Requests();
        try {
            
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           // st.setString(1, EmailSender);
           // st.setString(2, EmailReciever); //EmailReceiver
           
            rs = s.executeQuery(" SELECT *  FROM Requests  where EmailSender = '"+EmailSender+"' AND EmailReciever = '"+EmailReciever+"'");

            while  (rs.next()) {
                
               // System.out.println("lol");
                req.setEmailSender(rs.getString("EmailSender"));
                req.setEmailReciever(rs.getString("EmailReciever"));

            }
        } catch (SQLException x) {
            x.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestsImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return req;
    }

    
       @Override
    public ArrayList<Requests> retreiveAll(String email,Connection con) {
       ArrayList<Requests> c;
       c = new ArrayList<Requests>();
       try {
           Statement st = con.createStatement();
           rs = st.executeQuery(" SELECT * " + " FROM Requests " + " WHERE EmailReciever  = '" + email + "'");
           rs = st.getResultSet();
           rs.next();
           while(!rs.isAfterLast()){
               Requests temp = new Requests();
               temp.setEmailSender(rs.getString(1));
               temp.setEmailReciever(rs.getString(2));
               c.add(temp);
               rs.next();
           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
        return c;      
    } 
    
    
    
    @Override
    public Requests retreive(String email, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Requests obj, Connection con) {
        try {
            st = con.prepareStatement(" update Requests set EmailSender  = ? AND EmailReciever = ? where EmailSender = ? AND EmailReciever = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setString(1, obj.getEmailSender());
            st.setString(2, obj.getEmailReciever());
            st.setString(3, obj.getEmailSender());
            st.setString(4, obj.getEmailReciever());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            st.executeUpdate();

            return 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestsImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int delete(Requests obj, Connection con) {
        try {
            st = con.prepareStatement(" DELETE FROM Requests where EmailSender = ? AND EmailReciever = ? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setString(1, obj.getEmailSender());
            st.setString(2, obj.getEmailReciever());
            // System.out.println("EmailSender: " + obj.getEmailSender() + " " + "EmailReciever: " + obj.getEmailReciever());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if(obj.getEmailSender()!=null)
            {
            st.executeUpdate();
            }else
             System.out.println(" No Requests to delete");
            return 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestsImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
