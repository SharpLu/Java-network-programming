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
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    int[] values = new int[2];
    String _username = "root";
    String _password = "";
    Connection connection = null;
    Statement statement = null;

    public MarketImplementation(String bankName) throws RemoteException {
        super();
        try {
            bank = (Bank) Naming.lookup(bankName);
            Naming.rebind(URL, this);
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/marketplace", _username, _password);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    @Override
    public synchronized void login(HandlerInterface handler) throws RemoteException {
        try {
            client.put(handler.getID(), handler);
            System.out.println("login the client " + handler.getID());
        } catch (Exception e) {

        }
        updateAllProductList(handler);
        updateClientProductLists();
        updateClientWishList();
        try {
            wishHandler();
        } catch (SQLException ex) {
            Logger.getLogger(MarketImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void logiout(HandlerInterface handler) {
        try {
            System.out.println("login out " + handler.getID());
            client.remove(handler.getID());
        } catch (RemoteException ex) {
            Logger.getLogger(MarketImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public synchronized String registerAccount(String username, String password) throws RemoteException {
        String checkAccount = checkAccountExist(username);
        if (!checkAccount.equals("exist")) {
            try {
                String sql = "INSERT INTO marketplace.users (username,password)VALUES ('" + username + "', '" + password + "')";
                System.out.println("server get the registeration arguments" + username + password);
                statement = (Statement) connection.createStatement();
                int result = statement.executeUpdate(sql);
                if (result == 1) {
                    bank.newAccount(username).deposit(10000);
                    statement.close();
                    return "success";
                } else {
                    return "faild";
                }
            } catch (Exception e) {
            }
        }
        return "faild";

    }

    public synchronized String checkAccountExist(String username) {
        String check = "select * from marketplace.users where username = '" + username + "'";
        try {
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(check);
            while (rs.next()) {
                String u = rs.getString("username");
                if (u.equals(username)) {
                    rs.close();
                    return "exist";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "not exist";
    }

    @Override
    public synchronized String loginAccount(String username, String password) throws RemoteException {
        try {
            String sql = "select username,password from marketplace.users";
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String u = rs.getString("username");
                String p = rs.getString("password");
                if (u.equals(username) && p.equals(password)) {
                    //System.out.println("Login successs");
                    rs.close();
                    return "success";
                }
            }
        } catch (Exception e) {
        }
        return "faild";
    }

    public synchronized float getBalance(String username) {
        try {

            float money = bank.getAccount(username).getBalance();
            System.out.println("get client balance " + money);
            return money;
        } catch (Exception e) {

            return -1;
        }
    }

    public synchronized int[] getRecords(String username) {
        String check = "select username,sold,bought from marketplace.users";
        try {
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(check);
            while (rs.next()) {
                String u = rs.getString("username");
                int sold = rs.getInt("sold");
                int bought = rs.getInt("bought");
                if (u.equals(username)) {
                    values[0] = sold;
                    values[1] = bought;
                    return values;
                }
            }
        } catch (Exception e) {

        }
        return values;
    }

    @Override
    public synchronized void sale(String productName, float productPrice, String salerName) throws RemoteException {
        try {

            String insetsale = "INSERT INTO marketplace.products (productName,ProductPrice,salerName)" + "VALUES ('" + productName + "', '" + productPrice + "', '" + salerName + "')";

            statement = (Statement) connection.createStatement();
            int result = statement.executeUpdate(insetsale);
            if (result == 1) {
                System.out.println("execute success");
                updateClientProductLists();
                statement.close();
            } else {
                System.out.println("execute faild");
            }
           
        } catch (Exception e) {
        }
        try {
            wishHandler();
        } catch (SQLException ex) {
            Logger.getLogger(MarketImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized boolean buy(String productName, float price, String buyerID) throws RemoteException {
        try {

            String checkProducts = "select productName,productPrice,salerName from marketplace.products";
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(checkProducts);
            while (rs.next()) {
                String productname = rs.getString("productName");
                String productprice = rs.getString("productPrice");
                float p = Float.parseFloat(productprice);
                String salername = rs.getString("salerName");
                System.out.println("productname " + productname);
                System.out.println("productprice  " + productprice);
                if (productname.equals(productName) && p == price) {
                    //salerID=salername;
                    //  System.out.println("products exist the salerID is"+salerID);
                    boolean bankStatus = transaction(productName, price, salername, buyerID);
                    if (bankStatus == false) {
                        System.out.println("Transaction faild");
                    }
                    if (bankStatus == true) {
                        
                        System.out.println("Transaction success");
                    }
                    System.out.println(bankStatus);

                    rs.close();
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public synchronized void insertNewWish(String ClientID, String wishName, float price) throws RemoteException {
        try {
            String insertWish = "INSERT INTO marketplace.wishs (productName,ProductPrice,owner)" + "VALUES ('" + wishName + "', '" + price + "', '" + ClientID + "')";
            statement = (Statement) connection.createStatement();
            int result = statement.executeUpdate(insertWish);
            System.out.println("213 line $$$ " + result);
            for (HandlerInterface clien : client.values()) {
                if (ClientID.equals(clien.getID())) {
                    System.out.println("Here we get client iD " + clien);
                    updateWishList(clien);

                }

            }
        } catch (Exception e) {

        }
        try {
            wishHandler();
        } catch (SQLException ex) {
            Logger.getLogger(MarketImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void wishHandler() throws SQLException, RemoteException {
        Statement statementCheckProducts;
        Statement statementCheckwishs;

        ArrayList<products> productList = new ArrayList<products>();
        ArrayList<wish> wishList = new ArrayList<wish>();
        
        String checkWish = "select * from marketplace.wishs";
        String checkProducts = "select * from marketplace.products";
        statementCheckProducts = (Statement) connection.createStatement();
        ResultSet productsResult = statementCheckProducts.executeQuery(checkProducts);
        while (productsResult.next()) {
            String productname = productsResult.getString("productName");
            float productPrice = productsResult.getFloat("productPrice");
            String saler = productsResult.getString("salerName");
            products p = new products(productname, productPrice, saler);
            productList.add(p);
        }
        
        statementCheckProducts.close();
      //  connection.clo
        statementCheckwishs = (Statement) connection.createStatement();
        ResultSet wishsResult = statementCheckwishs.executeQuery(checkWish);
        while (wishsResult.next()) {
            String wishname = wishsResult.getString("productName");
            float wishPrice = wishsResult.getFloat("productPrice");
            String buyer = wishsResult.getString("owner");
            wish w = new wish(wishname, wishPrice, buyer);
            wishList.add(w);
        }

        System.out.println("productList $$$$$"+productList.size());
        System.out.println("wishList $$$$$"+wishList.size());
        for (int i = 0; i < wishList.size(); i++) {
            wish wi=wishList.get(i);
            //newwishList.add(wi);
            
            for(int j=0;j<productList.size();j++){
                products pro=productList.get(j);
               // newproductList.add(pro);
                
            if (pro.getProductName().equals(wi.getProductName()) && pro.getProductPrice()<=wi.getProductPrice()) {
            System.out.println("the wish product exits $$$$$$$$$$$$$$$$$$");
                System.out.println(pro.getProductName());
                  System.out.println(pro.getProductPrice());
                  System.out.println(wi.getProductName());
                  System.out.println(wi.getProductPrice());
                String delete = "delete from marketplace.wishs where productName='" + wishList.get(i).getProductName() + "' and owner='" + wishList.get(i).getOwner() + "'";
                String a = pro.getProductName();
                float b = pro.getProductPrice();
               // String se = productList.get(i).getSalerName();
                //String bu = wishList.get(i).getOwner();
                boolean bankStatus = transaction(a, b, pro.getSalerName(),wi.getOwner());
                if (bankStatus == false) {
                    System.out.println("Transaction faild");
                }
                if (bankStatus == true) {
                    statement = (Statement) connection.createStatement();
                    statement.executeUpdate(delete);
                    statement.close();
                    updateClientWishList();
                    System.out.println("Transaction success");
                }
            }
        }
}
    }

    @Override
    public synchronized void updateWishList(HandlerInterface handler) throws RemoteException {
        String clientID = handler.getID();
        handler.updateWishList(getWishsName(clientID));
    }

    public synchronized void updateClientWishList() throws RemoteException {
        for (HandlerInterface clien : client.values()) {
            System.out.println("Here we get client iD " + clien);
            updateWishList(clien);
        }
    }

    //after client add new items need update product list
    // for example if client just added new product need update this
  //  @Override
    public synchronized void updateClientProductLists() throws RemoteException {
        System.out.println("execute here updateClientProductLists()");
        for (HandlerInterface clien : client.values()) {
            System.out.println("Here we get client iD " + clien);
            updateAllProductList(clien);
        }
    }

    // update the all  products information
   // @Override
    public synchronized void updateAllProductList(HandlerInterface handler) throws RemoteException {

        handler.updateMarketPlaceList(getProductName());
        System.out.println(getProductName() + "product names");
    }


    public synchronized boolean transaction(String productName, float price, String sellerID, String buyerID) {
        String DelteRow = "delete from marketplace.products where productName='" + productName + "' and salerName='" + sellerID + "'";
        HandlerInterface buyer = client.get(buyerID);
        HandlerInterface seller = client.get(sellerID);
        System.out.println("$$$ buyer " + buyer);
        System.out.println("$$$ saler " + seller);
        if (buyer.equals(seller)) {
            return false;
        }
        if (buyer == null) {
            return false;
        }

        try {
        
          //  bank.transfer(account1, account2, price)
            bank.getAccount(buyerID).withdraw(price);
            bank.getAccount(sellerID).deposit(price);
            sold(sellerID);
            bought(buyerID);
            buyer.notifyBuyer(buyerID, productName, price);
            if (seller != null) {
                seller.notifySeller(sellerID, productName, price);
            }
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(DelteRow);
            updateClientProductLists();
            statement.close();
            return true;
        } catch (Exception e) {

        }
        return true;
    }

  //  @Override
    public synchronized ArrayList<String> getProductName() {
        ArrayList<String> re = new ArrayList<String>();
        try {
            String sql = "select * from marketplace.products";
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String productname = rs.getString("productName");
                float productPrice = rs.getFloat("productPrice");
                String saler = rs.getString("salerName");
                String result = productname + "," + productPrice + "," + saler;
                System.out.println(result);
                re.add(result);
            }
        } catch (Exception e) {
        }
        return re;
    }

  //  @Override
    public synchronized ArrayList<String> getWishsName(String clientID) {
        ArrayList<String> wishs = new ArrayList<String>();
        try {
            String sql = "select * from marketplace.wishs";
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String productname = rs.getString("productName");
                float productPrice = rs.getFloat("productPrice");
                String owner = rs.getString("owner");
                String result = productname + "," + productPrice;
                System.out.println(result);
                if (clientID.equals(owner)) {
                    wishs.add(result);
                }
            }

        } catch (Exception e) {

        }

        return wishs;
    }

    public synchronized void sold(String username) throws RemoteException {
        String check = "select username,sold from marketplace.users";
        try {
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(check);
            while (rs.next()) {
                String u = rs.getString("username");
                int sold = rs.getInt("sold");
                if (u.equals(username)) {
                    sold++;
                    String updatesold = "update marketplace.users set sold = '" + sold + "' where username = '" + username + "' ";
                    statement.executeUpdate(updatesold);

                }
            }
        } catch (Exception e) {

        }

    }

    public synchronized void bought(String username) throws RemoteException {
        String check = "select username,bought from marketplace.users";
        try {
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(check);
            while (rs.next()) {
                String u = rs.getString("username");
                int bought = rs.getInt("bought");
                if (u.equals(username)) {
                    bought++;
                    String updatebought = "update marketplace.users set bought = '" + bought + "' where username = '" + username + "' ";
                    statement.executeUpdate(updatebought);

                }
            }
        } catch (Exception e) {

        }

    }
}
