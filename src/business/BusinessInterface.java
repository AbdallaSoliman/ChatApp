/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import DataAccessLayer.DTO.Client;
import DataAccessLayer.DTO.Groups;
import DataAccessLayer.DTO.Has;
import DataAccessLayer.DTO.Requests;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Ali Alzantot
 */
public interface BusinessInterface extends Remote{
    void tellOthers(String msg,String senderEmail,ArrayList<String> receiversEmails) throws RemoteException;
    void register(ClientBusinessInterface clientRef,String email) throws RemoteException;
    void unRegister(ClientBusinessInterface clientRef) throws RemoteException;
    
    public int createClient(Client obj)throws RemoteException; 
    public Client retreiveByEmailAndPass(String email,String pass)throws RemoteException;
    public ArrayList<Client> retreiveFriends (Client obj)throws RemoteException;
    public ArrayList<Groups> retreiveGroups (Client obj)throws RemoteException;
    public ArrayList<String> retreiveRequests (Client obj)throws RemoteException;
    public int acceptRequest (String email1,String email2)throws RemoteException;
    public int declineRequest (String senderMail,String receiverMail)throws RemoteException;
    public int addFriend (String senderMail,String receiverMail)throws RemoteException;
    public int addToGroup (String email,String id)throws RemoteException;
    public int createGroup (String name,Client c)throws RemoteException;
    public int unfriend (String email1,String email2)throws RemoteException;
    public int leaveGroup (String email,String id)throws RemoteException;
    public ArrayList<Client> reteriveClients(String id) throws RemoteException;
    ///////////////////////////////////////////////////////////////
    public Client retreiveClient(String email) throws RemoteException;
    public Groups reteriveDeleteGroups(String id)throws RemoteException;
    public int DeleteGroups(Groups g)throws RemoteException;
    public int create(Has obj)throws RemoteException;
    public Has retreive(String email) throws RemoteException;
    public int delete(Has obj)throws RemoteException;
    public int update(Client obj)throws RemoteException;
    public ArrayList<Has> retreiveGroupRows(String id)throws RemoteException;
    
    
    
    
    

    
}
