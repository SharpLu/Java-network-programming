package Server;

import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mac
 */
public class ServerStart {
   
    public static void main(String args[]) {
        boolean listening = true;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(5555);
            System.out.println("Waiting for connection");

            while (listening) {
                Socket ClientSocket = serverSocket.accept();
                new Thread(new Handler(ClientSocket)).start();
                System.out.println(ClientSocket.getInetAddress());
            }
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
