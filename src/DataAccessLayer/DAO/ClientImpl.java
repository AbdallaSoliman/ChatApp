/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.DAO;

import DataAccessLayer.DTO.Client;

import DataAccessLayer.DataBaseManager.DataBaseManager;
import DataAccessLayer.Interface.ClientInterface;
import Property.ClientProp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
//import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author abdalla
 */
//implements Serializable
public class ClientImpl implements ClientInterface {

    // private Statement s;
    private PreparedStatement st;
    private ResultSet rs;
    private DataBaseManager managerObj;
    private final Connection con;
    private Statement s;

    public ClientImpl() {
        managerObj = new DataBaseManager();
        this.con=managerObj.getCon();
    }

    public ClientImpl(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        con = DriverManager.getConnection(dbURL, user, password);
    }
    
    public void shutdown() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public List<ClientProp> getPersonList() throws SQLException {

        try  {
                 s = con.createStatement();
                
                rs = s.executeQuery("select * from Client");
                rs = s.getResultSet();
            List<ClientProp> clientList = new ArrayList<>();
            while (rs.next()) {
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String Name = rs.getString("Name");
                String Gender = rs.getString("Gender");
                String Status = rs.getString("Status");
                String City = rs.getString("City");
                String Phone = rs.getString("Phone");
                ClientProp client = new ClientProp(Email, Password, Name, Gender, Status, City, Phone);
                clientList.add(client);
            }
            rs.close();
            s.close();
            return clientList;
        } catch (SQLException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public int create(Client obj, Connection con) {
       

        try {           

            st = con.prepareStatement("INSERT  INTO Client (Email,Password,Name,Gender,Status,CIty,Phone,Bdate)VALUES (?,?,?,?,?,?,?,?) ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            st.setString(1, obj.getEmail());
            st.setString(2, obj.getPassword());
            st.setString(3, obj.getName());
            st.setString(4, obj.getGender());
            st.setString(5, obj.getStatus());
            st.setString(6, obj.getCity());
            st.setString(7, obj.getPhone());
            st.setDate(8, (Date) obj.getDate());
            //st.setString(9, obj.getImage());

            rs = st.executeQuery();
            rs = st.getResultSet();
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Client retreive(String email, Connection con) {      
        Client obj=null;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
            rs=s.executeQuery(" SELECT * " + " FROM Client " + " WHERE email  = '" + email + "'");
            if(rs.next()){
                obj = new Client();
                obj.setEmail(email);
                obj.setPassword(rs.getString(2));
                obj.setName(rs.getString(3));
                obj.setGender(rs.getString(4));
                obj.setStatus(rs.getString(6));
                obj.setCity(rs.getString(7));
                obj.setPhone(rs.getString(8));
                obj.setDate(rs.getDate(9));
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
             
        return obj;
    }

     public Client retreiveByEmailAndPass(String email,String pass,Connection con) {      
        Client obj=null;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
            rs=s.executeQuery(" SELECT * " + " FROM Client " + " WHERE email  = '" + email + "' and password = '" + pass + "'");
            if(rs.next()){
                obj=new Client();
                obj.setEmail(email);
                obj.setPassword(rs.getString(2));
                obj.setName(rs.getString(3));
                obj.setGender(rs.getString(4));
                obj.setStatus(rs.getString(6));
                obj.setCity(rs.getString(7));
                obj.setPhone(rs.getString(8));
                obj.setDate(rs.getDate(9));
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
             
        return obj;
    }
     
    @Override
    public int update(Client obj, Connection con) {

        try {
            s = con.createStatement();
            s.executeUpdate("UPDATE Client set Password='" + obj.getPassword() + "',Name='" + obj.getName()
                    + "',Gender='" + obj.getGender() + "',Status='" + obj.getStatus() + "',CIty='" + obj.getCity() + "',Phone='" + obj.getPhone()
                    + "' where Email='" + obj.getEmail() + "'");
            //  rs = st.getResultSet();     
            //  st = con.prepareStatement("UPDATE Client set Password=?,Name=?,Gender=?,Status=?,CIty=?,Phone=?,Bdate=?,Image=? where Email=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            /*            st.setString(1, obj.getPassword());
            st.setString(2, obj.getName());
            st.setString(3, obj.getGender());
            st.setString(4, obj.getStatus());
            st.setString(5, obj.getCity());
            st.setString(6, obj.getPhone());
            st.setDate(7, (Date) obj.getDate());
            st.setString(8, obj.getImage());
            st.setString(9, obj.getEmail());*/
        } catch (Exception ex) {
            ex.printStackTrace();

            return -1;
        }
        //  s.execute(sql);
        //System.out.println(st.g);
        return 0;

    }

    @Override
    public int delete(Client obj, Connection con) {
        try {
            s = con.createStatement();
            s.executeUpdate(" DELETE FROM Client where Email = '" + obj.getEmail() + "'");
            //st.setString(1, obj.getEmail());
            // System.out.println("EmailSender: " + obj.getEmailSender() + " " + "EmailReciever: " + obj.getEmailReciever());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if (obj.getEmail() != null) {
                // st.executeUpdate();
            } else {
                System.out.println(" No Client to delete");
            }
            return 0;

            // } catch (SQLException ex) {
            //     ex.printStackTrace();
            //     return -1;
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

        /*try {

            st = con.prepareStatement("DELETE from Client where Email = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setString(1, obj.getEmail());
        } catch (Exception ex) {
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
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
 /*         int ind=-1;

        try {
            st = con.prepareStatement(" DELETE FROM client where Email = ? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.setString(1, obj.getEmail());
            
             st.executeUpdate();
         //  rs = st.getResultSet();
            ind=0;
            
       // } catch (SQLException ex) {
         //   ex.printStackTrace();
        //}
        //try {
          //  st.executeUpdate();
            //return 0;

        } catch (SQLException ex) {
            //ex.printStackTrace();
           Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
 /* finally {
            try {
              if (rs != null)
                  rs.close();
              if (st != null)
                  st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }       
    
 /*return ind;*/
    }

    public int getAge(Calendar dob) {
        long currentTime = System.currentTimeMillis();
        TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
        Calendar today = Calendar.getInstance(tz);
        today.setTimeInMillis(currentTime);

        int curYear = today.get(Calendar.YEAR);
        int dobYear = dob.get(Calendar.YEAR);

        int age = curYear - dobYear;

        // if dob is month or day is behind today's month or day
        // reduce age by 1
        int curMonth = today.get(Calendar.MONTH);
        int dobMonth = dob.get(Calendar.MONTH);
        if (dobMonth > curMonth) { // this year can't be counted!
            age--;
        } else if (dobMonth == curMonth) { // same month? check for day
            int curDay = today.get(Calendar.DAY_OF_MONTH);
            int dobDay = dob.get(Calendar.DAY_OF_MONTH);
            if (dobDay > curDay) { // this year can't be counted!
                age--;
            }
        }
        System.out.println(age);
        return age;
    }

    public int Gget(ArrayList<Date> c, int start, int End) {
        int n = 1;
        for (int i = 0; i < c.size(); i++) {
            //  c.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                 //  TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles"); 
           Calendar dob = Calendar.getInstance();
           if(c.get(i)!=null)
            dob.setTime(c.get(i));
            int date = getAge(dob);
            if (date > start && date < End) {
                n += 1;
            }
        }
        return n;
    }

    public int getA(Connection con) {

        int counter = 0;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery("SELECT months_between (sysdate, Bdate)/12 FROM Client ");
            List<Integer> myCoords = new ArrayList<Integer>();
            while (rs.next()) {
                myCoords.add( rs.getInt(1));
                 System.out.println(" age:"+rs.getInt(1));
            }
         
           Iterator <Integer> myListIterator = myCoords.iterator(); 
 while (myListIterator.hasNext()) {
    Integer coord = myListIterator.next();     
if(coord>=5&&coord<10)counter++;
}
        } catch (SQLException x) {
            
        } finally {
            try {
                if(s!=null)
                s.close();
                 if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return counter;
    }

    public int getB(Connection con) {
           int counter = 0;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery("SELECT months_between (sysdate, Bdate)/12 FROM Client ");
            List<Integer> myCoords = new ArrayList<Integer>();
            while (rs.next()) {
                myCoords.add( rs.getInt(1));
            }
         
           Iterator <Integer> myListIterator = myCoords.iterator(); 
 while (myListIterator.hasNext()) {
    Integer coord = myListIterator.next();     
if(coord>=10&&coord<20)counter++;
}
        } catch (SQLException x) {
            
        } finally {
            try {
                if(s!=null)
                s.close();
                 if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return counter;
    }

    public int getC(Connection con) {
           int counter = 0;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery("SELECT months_between (sysdate, Bdate)/12 FROM Client ");
            List<Integer> myCoords = new ArrayList<Integer>();
            while (rs.next()) {
                myCoords.add( rs.getInt(1));
                 System.out.println(" age:"+rs.getInt(1));
            }
         
           Iterator <Integer> myListIterator = myCoords.iterator(); 
 while (myListIterator.hasNext()) {
    Integer coord = myListIterator.next();     
if(coord>=20&&coord<30)counter++;
}
        } catch (SQLException x) {
            
        } finally {
            try {
                if(s!=null)
                s.close();
                 if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return counter;
    }

    public int getD(Connection con) {
        int counter = 0;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery("SELECT months_between (sysdate, Bdate)/12 FROM Client ");
            List<Integer> myCoords = new ArrayList<Integer>();
            while (rs.next()) {
                myCoords.add( rs.getInt(1));
            }
         
           Iterator <Integer> myListIterator = myCoords.iterator(); 
 while (myListIterator.hasNext()) {
    Integer coord = myListIterator.next();     
if(coord>=30&&coord<40)counter++;
}
        } catch (SQLException x) {
            
        } finally {
            try {
                if(s!=null)
                s.close();
                 if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return counter;
    }

    public int getE(Connection con) {
      int counter = 0;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery("SELECT months_between (sysdate, Bdate)/12 FROM Client ");
            List<Integer> myCoords = new ArrayList<Integer>();
            while (rs.next()) {
                myCoords.add( rs.getInt(1));
            }
         
           Iterator <Integer> myListIterator = myCoords.iterator(); 
 while (myListIterator.hasNext()) {
    Integer coord = myListIterator.next();     
if(coord>=40&&coord<550)counter++;
}
        } catch (SQLException x) {
            
        } finally {
            try {
                if(s!=null)
                s.close();
                 if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return counter;
    }
    

    public int getA2(Connection con) {
        int counter = 1;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery(" SELECT count(GENDER) " + " FROM Client  where GENDER='Male'");
            if (rs.next()) {
                counter = Integer.parseInt(rs.getString(1));
                System.out.println(counter);
            }
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                s.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return counter;
    }

    public int getB2(Connection con) {
        int counter = 1;
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = s.executeQuery(" SELECT count(GENDER) " + " FROM Client  where GENDER='Female'");
            if (rs.next()) {
                counter = Integer.parseInt(rs.getString(1));
                System.out.println(counter);
            }
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                s.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return counter;
    }

}
