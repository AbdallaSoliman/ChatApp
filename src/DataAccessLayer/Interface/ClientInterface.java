/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.Interface;



import DataAccessLayer.DTO.Client;
import DataAccessLayer.GenericInterface.GInterface;
import java.sql.Connection;

/**
 *
 * @author abdalla
 */
public interface ClientInterface extends GInterface<Client>{

      
    @Override
       public int create(Client obj ,Connection con );
    @Override
            public Client retreive (String email,Connection con);
    @Override
      public int update (Client obj,Connection con);
    @Override
      public int delete (Client obj,Connection con);

}
