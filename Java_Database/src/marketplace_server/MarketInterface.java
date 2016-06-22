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

    public boolean buy(String productName, float price, String buyerID) throws RemoteException;

    public void insertNewWish(String ClientID, String wishName, float price) throws RemoteException;

    //public ArrayList<String> updateWishList(String wishName,float price) throws RemoteException;
    public void updateWishList(HandlerInterface handler) throws RemoteException;

    //public void updateClientProductLists() throws RemoteException;

 //   public void updateAllProductList(HandlerInterface handler) throws RemoteException;

  //  public boolean transaction(String productName, float price, String sellerID, String buyerID) throws RemoteException;

  //  public ArrayList<String> getProductName() throws RemoteException;

   // public ArrayList<String> getWishsName(String clientID) throws RemoteException;

   // public  ArrayList<items> getTheWishList(String clientID)throws RemoteException;
    public String registerAccount(String username, String password) throws RemoteException;

    public String loginAccount(String username, String password) throws RemoteException;

   // public String checkAccountExist(String username) throws RemoteException;

    public void login(HandlerInterface handler) throws RemoteException;

    public void logiout(HandlerInterface handler) throws RemoteException;

    public float getBalance(String username) throws RemoteException;

    public void sale(String productName, float productPrice, String salerName) throws RemoteException;

    public int[] getRecords(String username) throws RemoteException;

}
