package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mac
 */
public class Handler implements Runnable {

    public static int WordsLength;
    public static String RandomWords;
    public static char FirstLetter;
    private String clientCharactor;
    public static ArrayList<String> wordsList = new ArrayList<String>();
    private Socket clientSocket;
    static String score = "1000";
    DataInputStream reader = null;
    DataOutputStream writer = null;
    BufferedReader br = null;

    Handler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {

        try {

            System.out.println("Connected");
            this.reader = new DataInputStream(clientSocket.getInputStream());
            this.writer = new DataOutputStream(clientSocket.getOutputStream());
            while (true) {
                String receiver = this.receiveMessage();
                System.out.println("first receive from client " + receiver);
                if (receiver != null) {
                    processesStartMessage(receiver);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void processesStartMessage(String message) throws IOException {
        int k = 0;
        if (message.equals("StartGame")) {
            wordsList = (ArrayList<String>) Handler.loadDictionary();
            ArrayList<Character> firstCharacter = new ArrayList<Character>();
            // random get a word
            clientCharactor = wordsList.get(new Random().nextInt(wordsList.size()));

            for (int i = 0; i < clientCharactor.length(); i++) {
                firstCharacter.add(clientCharactor.charAt(i));
            }
            // send length
            System.out.println(clientCharactor + "|clientCharactor.length=>" + clientCharactor.length());
            this.sendMessage("" + clientCharactor.length());
            // send first character
            this.sendMessage("" + firstCharacter.get(0));

        } else if (message.startsWith("compare:")) {
            String[] chars = message.substring(message.indexOf(":") + 1).split("\\|");// 正则表达式中|为特殊字符需要转义
            int index = Integer.valueOf(chars[0]);
            char wordChar = chars[1].charAt(0);
            sendMessage(clientCharactor.charAt(index) == wordChar ? "1" : "0");
            System.out.println(message);
            System.out.println(clientCharactor.charAt(index) + "|" + wordChar + "|" + (clientCharactor.charAt(index) == wordChar));
            k++;
        }
        if (k == clientCharactor.length()) {
            System.out.println("test1");
            this.sendMessage("WIN");
        }

        wordsList.clear();

    }

    public void sendMessage(String message) throws IOException {
        try {
            writer.writeUTF(message);
            writer.flush();
        } catch (Exception e) {
        }

    }

    public String receiveMessage() throws IOException {
        String message = this.reader.readUTF();
        return message;
    }

    public static List<String> loadDictionary() {
        List<String> DictionaryList = new ArrayList<String>();
        try {
            FileInputStream fileinput = new FileInputStream("/Users/Mac/Desktop/words.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileinput));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                DictionaryList.add(strLine);
            }
            br.close();
            fileinput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DictionaryList;
    }

}
