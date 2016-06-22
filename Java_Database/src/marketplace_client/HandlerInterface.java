/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace_client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Mac
 */
public interface HandlerInterface extends Remote {

    public String getID() throws RemoteException;

    public void notifySeller(String username, String productName, float price) throws RemoteException;

    public void notifyBuyer(String username, String productName, float price) throws RemoteException;

    public void updateMoney(float money) throws RemoteException;

    public void updateMarketPlaceList(ArrayList<String> itemNames) throws RemoteException;

    public void updateWishList(ArrayList<String> wishlist) throws RemoteException;

    public void updateRecords(int[] value) throws RemoteException;

    public void unregister() throws RemoteException;

}
