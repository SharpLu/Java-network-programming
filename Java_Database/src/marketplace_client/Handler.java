/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace_client;

import bank.Account;
import bank.Bank;
import bank.RejectedException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import marketplace_server.MarketInterface;

/**
 *
 * @author Mac
 */
public class Handler extends UnicastRemoteObject implements HandlerInterface {

    String username;
    String password;
    float money;
    MarketInterface marketInterface;
    Client client;
    int[] values = new int[2];

    Handler(Client client, String username, String password) throws RemoteException {
        this.client = client;
        this.username = username;
        this.password = password;

        try {
            marketInterface = (MarketInterface) Naming.lookup("rmi://localhost:1099/market");

            marketInterface.login(this);
            money = marketInterface.getBalance(username);
            updateMoney(money);
            values = marketInterface.getRecords(username);

        } catch (Exception e) {
            System.out.println("Exception Handler class " + e);
        }

        updateRecords(values);

    }

    public void sellProduct(String productName, float price) throws RemoteException {
        marketInterface.sale(productName, price, username);

    }

    public void bayProduct(String productName, float price) throws RemoteException {
        marketInterface.buy(productName, price, username);
    }

    public void insertNewWish(String id, String wishname, float price) throws RemoteException {
        System.out.println("get the wish price is " + price);
        marketInterface.insertNewWish(id, wishname, price);
    }

    public synchronized void updateMoney(float money) throws RemoteException {
        // float money;

        System.out.println("here we get the money" + money);
        client.updateMoney(username, money);
    }

    public synchronized void updateRecords(int[] value) throws RemoteException {

        client.setSold(value[0]);
        client.setBought(value[1]);

    }

    public synchronized String getID() {
        return username;
    }

    public synchronized void notifyBuyer(String username, String productName, float price) throws RemoteException {

        client.showMessage("Dear " + username + "transaction  " + productName + " money " + price);
        money -= price;
        updateMoney(money);
        client.getBought();

    }

    public synchronized void notifySeller(String username, String productName, float price) throws RemoteException {

        client.showMessage("Dear " + username + "transaction  " + productName + " money " + price);
        money += price;
        updateMoney(money);
        client.getSold();

    }

    @Override
    public synchronized void updateMarketPlaceList(ArrayList<String> products) throws RemoteException {
        System.out.println("this method is callbacked by server");
        DefaultListModel ProductList = new DefaultListModel();
        for (String ite : products) {
            ProductList.addElement(ite);
        }
        System.out.println(ProductList.size() + "Client return");
        client.updateMarketPlaceList(ProductList);

    }

    @Override
    public synchronized void updateWishList(ArrayList<String> wishlist) throws RemoteException {

        System.out.println("update wish list called back " + wishlist);
        DefaultListModel wishList = new DefaultListModel();

        for (String ite : wishlist) {
            wishList.addElement(ite);
        }
        System.out.println(wishList.size() + "Client return");
        client.updateWishsList(wishList);
    }

    @Override
    public void unregister() throws RemoteException {
        try {
            marketInterface.logiout(this);
        } catch (Exception e) {
            System.out.println("error of unregister marketInterface");
        }

    }

}
