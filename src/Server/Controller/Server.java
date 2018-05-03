/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Controller;

import DataAccessLayer.DAO.ClientImpl;
import DataAccessLayer.DataBaseManager.DataBaseManager;
import ViewLayer.fxml.ChartController;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author abdalla
 */
public class Server implements Runnable {
   public static ChartController chartcontroller;
    public Server (){
        
    }
    
DataBaseManager dataBaseManager=new DataBaseManager();
Connection con=dataBaseManager.getCon();
        private int A,B,C,D,E,A2,B2;
        private int AO=0,BO=0,CO=0,DO=0,EO=0,AO2=0,BO2=0;
ClientImpl CImp =new ClientImpl();
    @Override
    public void run() {
        while(true){
        A=CImp.getA(con);
        B=CImp.getB(con);
        C=CImp.getC(con);
        D=CImp.getD(con);
        E=CImp.getE(con);
        A2=CImp.getA2(con);
        B2=CImp.getB2(con);
//        if(A!=AO||B!=BO||C!=CO||D!=DO||E!=EO||A2!=AO2||B2!=BO2){
//            AO=A;
//            BO=B;
//            CO=C;
//            DO=D;
//            EO=E;
//            A2=AO2;
//            B2=BO2;
       if (Platform.isFxApplicationThread()) {
        chartcontroller.pie1(A,B,C,D,E);
        chartcontroller.pie2(A2,B2);
        chartcontroller.ChangeTable();
       }else
       {
             Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                    chartcontroller.pie1(A,B,C,D,E);
                    chartcontroller.pie2(A2,B2);
                    chartcontroller.ChangeTable();
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
       }
 
       try {
           sleep(4000);
       } catch (InterruptedException ex) {
           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
//        }
    }
   public static void setServerController(ChartController chartcontroller){
       Server.chartcontroller=chartcontroller;
   }
  public static class Client {
 
     
     private final SimpleStringProperty  Email;
     
     private final SimpleStringProperty  Password;
     
     private final SimpleStringProperty  Name;
     
     private final SimpleStringProperty  Gender;
     
  //   private final SimpleImageViewProperty Image;
     
     private final SimpleStringProperty  Status;
     
     private final SimpleStringProperty  City;
     
     private final SimpleStringProperty  Phone;
     
  //   private final SimpleDateProperty  BDate;
 
private  Client(String Email, String Password, String Name,String Gender,Image Image,String Status,String City,String Phone,Date BDate) {
                this.Email= new SimpleStringProperty(Email);
            this.Password= new SimpleStringProperty(Password);
            this.Name= new SimpleStringProperty(Name);
            this.Gender= new SimpleStringProperty(Gender);
         //   this.Image= new SimpleStringProperty(Image);
            this.Status= new SimpleStringProperty(Status);
            this.City= new SimpleStringProperty(City);
            this.Phone= new SimpleStringProperty(Phone);
       //     this.BDate= new SimpleStringProperty(BDate);
}

    public SimpleStringProperty  getEmail() {
        return Email;
    }

    public SimpleStringProperty  getPassword() {
        return Password;
    }

    public SimpleStringProperty  getName() {
        return Name;
    }

    public SimpleStringProperty  getGender() {
        return Gender;
    }

//    public Image getImage() {
//        return Image;
//    }

    public SimpleStringProperty  getStatus() {
        return Status;
    }

    public SimpleStringProperty  getCity() {
        return City;
    }

    public SimpleStringProperty  getPhone() {
        return Phone;
    }
public void setEmail(String fName) {
            Email.set(fName);
        }
public void setPassword(String fName) {
            Password.set(fName);
        }
public void setGender(String fName) {
            Gender.set(fName);
        }
public void setStatus(String fName) {
            Status.set(fName);
        }
public void setCity(String fName) {
            City.set(fName);
        }
public void setPhone(String fName) {
            Phone.set(fName);
        }





//    public Date getBDate() {
//        return BDate;
//    }
 

    
}
 
}

