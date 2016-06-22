/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace_client;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.rmi.RemoteException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mac
 */
public class sqltest {

    static String _username = "root";
    static String _password = "";
    static Connection connection = null;
    static Statement statement = null;

    public static void main(String args[]) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/marketplace", _username, _password);

       // String result=   registerAccount("worinima","worinima");
          //  String result=delete("iphone100",2000,"","hellojava");
            String result=bought("hellojava");
        System.out.println(result);
        } catch (Exception e) {

        }
//       ArrayList<String> result=getProductName();
//       for(int i=0;i<result.size();i++){
//       System.out.println(result.get(i));
//       }
    }


    
   //    @Override
    public static synchronized String registerAccount(String username, String password) throws RemoteException {
     //  String checkAccount= checkAccountExist(username);
       // if(!checkAccount.equals("exist")){
        try {
            String sql = "INSERT INTO marketplace.users (username,password)VALUES ('" + username + "', '" + password + "')";
            System.out.println("server get arguments"+username+password);
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
        }     
        return "faild";
        
        }
    
    
    
    public static synchronized String delete(String productname,float price,String buyerID,String salerID) throws SQLException{
 String DelteRow="delete from marketplace.products where productName='"+productname+"' and salerName='"+salerID+"'" ;
    //  System.out.println("server get arguments"+username+password);
         statement = (Statement) connection.createStatement();
         int result=   statement.executeUpdate(DelteRow);   
         System.out.println(result);
        return "success";
    }
    
    
    public static synchronized String sold(String username) {
        String check = "select username,sold from marketplace.users";
        try {
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(check);
            while(rs.next()){
            String u=rs.getString("username");
            int sold=rs.getInt("sold");
            if(u.equals(username)){
            sold++;
            String updatesold = "update marketplace.users set sold = '" + sold + "' where username = '" + username + "' ";
            statement.executeUpdate(updatesold);
            }
            }
        } catch (Exception e) {

        }
    return "success";
    }
    
    
      public static synchronized String bought(String username) {
        String check = "select username,bought from marketplace.users";
        try {
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(check);
            while(rs.next()){
            String u=rs.getString("username");
            int bought=rs.getInt("bought");
            if(u.equals(username)){
            bought++;
            String updatebought = "update marketplace.users set bought = '" + bought + "' where username = '" + username + "' ";
            statement.executeUpdate(updatebought);
            }
            }
        } catch (Exception e) {

        }
    return "success";
    }  
    
    
}
