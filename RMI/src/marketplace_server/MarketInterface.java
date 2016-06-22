package marketplace_server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import marketplace_client.HandlerInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mac
 */
public interface MarketInterface extends Remote {

    public void registerClient(HandlerInterface handler) throws RemoteException;

    public void unRegisterClient(HandlerInterface handler) throws RemoteException;

    public void sal(String productName, float price, String ownID) throws RemoteException;

    public boolean buy(String productName, float price, String buyerID) throws RemoteException;
    
     public  void insertNewWish(String ClientID,String wishName,float price) throws RemoteException;

    //public ArrayList<String> updateWishList(String wishName,float price) throws RemoteException;
    
      public  void updateWishList(HandlerInterface handler) throws RemoteException;

    public void updateClientProductLists() throws RemoteException;

    public void updateAllProductList(HandlerInterface handler) throws RemoteException;

    public boolean transaction(items ite, String buyerID, String sellerID) throws RemoteException;

    public ArrayList<String> getProductName() throws RemoteException;
    
    public  ArrayList<String> getWishsName(String clientID) throws RemoteException;
    
    public  ArrayList<items> getTheWishList(String clientID)throws RemoteException;
}
