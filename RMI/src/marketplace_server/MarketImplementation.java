package marketplace_server;

import marketplace_client.HandlerInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import bank.Bank;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mac
 */
public class MarketImplementation extends UnicastRemoteObject implements MarketInterface {

    static final String URL = "rmi://localhost:1099/market";
    Bank bank;
    Map<String, HandlerInterface> client = new HashMap<String, HandlerInterface>();
    ArrayList<items> item = new ArrayList<items>();
    ArrayList<items> wishs = new ArrayList<items>();

    public MarketImplementation(String bankName) throws RemoteException {
        super();
        try {
            bank = (Bank) Naming.lookup(bankName);
            Naming.rebind(URL, this);
            System.out.println("Success bind bank");
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
//put registered client to a hashmap  each unique ID

    @Override
    public synchronized void registerClient(HandlerInterface handler) throws RemoteException {
        try {
            client.put(handler.getID(), handler);
            System.out.println("Register client " + handler);
        } catch (Exception e) {

        }
        updateAllProductList(handler);
        updateWishList(handler);
    }
// remove client from hashtable if client need unregister

    @Override
    public synchronized void unRegisterClient(HandlerInterface handler) {
        try {
            System.out.println("Unregistered account " + handler.getID());
            client.remove(handler.getID());
        } catch (RemoteException ex) {
            Logger.getLogger(MarketImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//if client need sale own product  the product should contain name,price,and ownID

    @Override
    public synchronized void sal(String productName, float price, String ownID) throws RemoteException {
        System.out.println(" sale function server get information" + productName + price + ownID);
        try {
            items newproduct = new items(productName, price, ownID);
            item.add(newproduct);
            System.out.println(newproduct + "from client");
        } catch (Exception e) {

        }
        System.out.println(getProductName());
        HandlerInterface handler = client.get(ownID);
        updateClientProductLists();
        updateWishList(handler);
        wishHandler();
    }

// in the market place who bought this need to get id ID 
    // transaction need it
    @Override
    public synchronized boolean buy(String productName, float price, String buyerID) throws RemoteException {
        items selectedProduct = null;
        for (items ite : item) {

            if (ite.getProductName().equals(productName) && ite.getPrice() == price) {
                selectedProduct = ite;
            }
        }
        System.out.println("selectedProduct  $" + selectedProduct);
        if (selectedProduct != null) {
            String seller = selectedProduct.getOwnID();
            System.out.println("get own id " + selectedProduct.getOwnID());
            System.out.println("Product you selected " + selectedProduct);
            System.out.println("Buyer ID" + buyerID);
            System.out.println("seller ID" + seller);

            boolean result = transaction(selectedProduct, buyerID, seller);
            System.out.println("result " + result);
            if (result == true) {
                wishHandler();
                return result;
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public synchronized void insertNewWish(String ClientID, String wishName, float price) throws RemoteException {
        items wish = new items(wishName, price, ClientID);
        wishs.add(wish);

        for (HandlerInterface clien : client.values()) {
            if (ClientID.equals(clien.getID())) {
                System.out.println("Here we get client iD " + clien);
                updateWishList(clien);
                wishHandler();
            }

        }
    }

    @Override
    public synchronized void updateWishList(HandlerInterface handler) throws RemoteException {
        String clientID = handler.getID();
        handler.updateWishList(getWishsName(clientID));
    }

    public synchronized void wishHandler() throws RemoteException {
        HandlerInterface buyer = null;
        boolean status = false;
        ArrayList<items> handlewishes = new ArrayList<items>();
        for (int i = 0; i < wishs.size(); i++) {
            items tempWish = wishs.get(i);
            handlewishes.add(tempWish);

            ArrayList<items> products = item;
            for (int j = 0; j < products.size(); ++j) {
                buyer = null;
                items ite = products.get(j);
                if ((ite.getProductName().equals(tempWish.getProductName()) && (ite.getPrice() <= tempWish.getPrice()))) {
                    boolean flag = transaction(ite, tempWish.getOwnID(), ite.getOwnID());
                    if (!flag) {
                        System.out.println("Error of your transaction ");
                    } else {
                        buyer = client.get(tempWish.getOwnID());
                        if (buyer == null) {
                            System.out.println("Error of you buyer");
                        } else {
                            handlewishes.remove(tempWish);
                            wishs.remove(tempWish);
                            status = true;
                            break;
                        }
                    }
                }

            }
        }
        if (status) {
            wishs = handlewishes;
            updateWishList(buyer);
        }
    }

    //after client add new items need update product list
    // for example if client just added new product need update this
    @Override
    public synchronized void updateClientProductLists() throws RemoteException {
        for (HandlerInterface clien : client.values()) {
            System.out.println("Here we get client iD " + clien);
            updateAllProductList(clien);
        }
    }

    // update the all  products information
    @Override
    public synchronized void updateAllProductList(HandlerInterface handler) throws RemoteException {

        handler.updateMarketPlaceList(getProductName());
        System.out.println(getProductName() + "product names");
    }

//connect to bank account and withdra money pay to seller account
    @Override
    public synchronized boolean transaction(items ite, String buyerID, String sellerID) {
        HandlerInterface buyer = client.get(buyerID);
        HandlerInterface seller = client.get(sellerID);
        if (buyer.equals(seller)) {
            return false;
        }

        if (buyer != null && seller != null) {
            try {
                System.out.println("Start transaction ");
                float price = ite.getPrice();
                System.out.println("get the price from ite " + price);
                bank.getAccount(buyerID).withdraw(price);
                buyer.notifyTransactions(buyerID, ite.getProductName(), ite.getPrice());

                bank.getAccount(sellerID).deposit(price);
                seller.notifyTransactions(sellerID, ite.getProductName(), ite.getPrice());
                item.remove(ite);
                updateClientProductLists();

                System.out.println("after transaction " + item);
                return true;
            } catch (Exception e) {

            }
        }
        return true;
    }

    @Override
    public synchronized ArrayList<String> getProductName() {
        ArrayList<String> re = new ArrayList<String>();
        for (items j : item) {
            re.add(j.toString());
            System.out.println("getProductName() " + j.toString());
        }
        return re;
    }

    @Override
    public synchronized ArrayList<String> getWishsName(String clientID) {
        ArrayList<items> wi = getTheWishList(clientID);
        ArrayList<String> kkk = new ArrayList<String>();
        for (items j : wi) {
            kkk.add(j.toString());
        }
            //System.out.println("getWishsName() " + j.toString());

        return kkk;
    }

    @Override
    public synchronized ArrayList<items> getTheWishList(String clientID) {
        ArrayList<items> wiwi = new ArrayList<items>();
        for (items s : wishs) {
            if (s.getOwnID().equals(clientID)) {
                wiwi.add(s);
            }

        }

        return wiwi;
    }

}
