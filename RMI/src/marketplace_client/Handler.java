/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace_client;

import bank.Account;
import bank.Bank;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import marketplace_server.MarketInterface;

/**
 *
 * @author Mac
 */
public class Handler extends UnicastRemoteObject implements HandlerInterface {

    private String id;
    private Bank thebank;
    MarketInterface marketInterface;
    Client client;

    Handler(Client client, String id, String bank, float money) throws RemoteException {
        this.client = client;
        this.id = id;

        try {
            thebank = (Bank) Naming.lookup(bank);
            marketInterface = (MarketInterface) Naming.lookup("rmi://localhost:1099/market");
            marketInterface.registerClient(this);
            //marketInterface.unRegisterClient(this);
        } catch (Exception e) {
            System.out.println("Exception Handler class " + e);
        }
        try {
            thebank.newAccount(id);
            thebank.getAccount(id).deposit(money);
            client.updateMoney(id,money);
        } catch (Exception e) {

        }

    }

    public void sellProduct(String productName, float price) throws RemoteException {
        System.out.println("Get sale product from GUI"+productName+price);
        marketInterface.sal(productName, price, id);
        
    }

    public void bayProduct(String productName, float price) throws RemoteException {
         System.out.println("Get buy product from GUI"+productName+price);
        marketInterface.buy(productName, price, id);
    }
    
    
    public void insertNewWish(String id,String wishname,float price) throws RemoteException{
        System.out.println("get the wish price is "+price);
        marketInterface.insertNewWish(id,wishname,price);
   
    }


    public synchronized void updateMoney() throws RemoteException {
        float money = ((Account) thebank.getAccount(String.valueOf(id))).getBalance();
        client.updateMoney(id,money); 
        
    }

    public synchronized String getID() {
        return id;
    }

    public synchronized void notifyTransactions(String username,String productName, float price) throws RemoteException {
        client.showMessage("Dear " + username + "transaction  " + productName + " money " + price);
        updateMoney();
    }

    @Override
    public synchronized void updateMarketPlaceList(ArrayList<String> itemNames) {
       
        DefaultListModel ProductList = new DefaultListModel();
        for (String ite : itemNames) {
            ProductList.addElement(ite);
        }
        System.out.println(ProductList.size()+"Client return");
        client.updateMarketPlaceList(ProductList);
    }
    
    
    
    @Override
    public synchronized void updateWishList(ArrayList<String> wishlist) {
        
        System.out.println("update wish list called back "+wishlist);
        DefaultListModel wishList = new DefaultListModel();
        
        for (String ite : wishlist) {
            wishList.addElement(ite);
        }
        System.out.println(wishList.size()+"Client return");
        client.updateWishsList(wishList);

    }

    @Override
    public void unregister() throws RemoteException {
        try{
        
         thebank.deleteAccount(id);
        
        }catch(Exception e){
            System.out.println("error of deleteaccount");
        }
       try{
            marketInterface.unRegisterClient(this);
       }catch(Exception e){
       System.out.println("error of unregister marketInterface");
       }
       
    }

}
