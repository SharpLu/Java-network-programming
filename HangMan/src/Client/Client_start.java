package Client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client_start extends Thread {

    Socket socket = null;
    DataOutputStream writer = null;
    DataInputStream reader = null;
    String msg = null;
    JFrame frame = new JFrame("Hangman");
    JPanel wordContainer = new JPanel();
    JPanel submitContainer = new JPanel();
    // JPanel ButtonPanel = new JPanel();
    JLabel Score = new JLabel("Score");
    // JLabel clientwords;
    JLabel showmessage = new JLabel();
    JTextField WordsTextField = new JTextField(20);
    static JTextField tf = new JTextField(50);
    JButton submit = new JButton("Submit");
    JButton newgame = new JButton("New Game");
    String firctCharacter;
    int length;
    int currentscore = 0;
    int attempts = 0;
    List<JTextField> fields = new ArrayList<JTextField>();

    public Client_start() {

        frame.setLayout(new BorderLayout());
        WordsTextField.setEditable(true);
        submit.setEnabled(false);
        Score.setFont(new Font("Serif", Font.PLAIN, 20));
        submitContainer.add(showmessage);
        submitContainer.add(newgame);
        frame.getContentPane().add(wordContainer, BorderLayout.NORTH);
        frame.getContentPane().add(Score, BorderLayout.CENTER);
        frame.getContentPane().add(WordsTextField, BorderLayout.EAST);
        frame.getContentPane().add(submit, BorderLayout.WEST);
        frame.getContentPane().add(submitContainer, BorderLayout.SOUTH);
        frame.pack();

        submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("You enter words length is " + WordsTextField.getText().length());
                try {
                    if (attempts >= 1 && WordsTextField.getText().length() == length) {
                        String word = WordsTextField.getText();
                        int k = 0;
                        for (int i = 0; i < word.length() && i < length; i++) {
                            sentMessage("compare:" + i + "|" + word.charAt(i));
                            System.out.println("compare:" + i + "|" + word.charAt(i));
                            String result = receiveMessage();
                            System.out.println(result);
                            if ("1".equals(result)) {
                                fields.get(i).setText("" + word.charAt(i));
                                fields.get(i).repaint();
                                k++;
                                currentscore = k;
                            }
                        }
                        if (k == length) {
                            submit.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "Win", "System Prompt", JOptionPane.INFORMATION_MESSAGE);
                        }
                        attempts--;
                        currentscore = currentscore + 100;
                        Score.setText("Score " + k + " Attempts  " + attempts);
                        Score.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "empty input or dont have change", "System Prompt", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception c) {

                }

            }
        });

        newgame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new Thread() {

                    @Override
                    public void run() {
                        currentscore = 0;
                        try {
                            sentMessage("StartGame");
                            wordContainer.removeAll();
                            length = Integer.valueOf(receiveMessage());
                            firctCharacter = receiveMessage();
                            submit.setEnabled(true);
                            int i = 0;
                            attempts = length;
                            showmessage.setText("The first character is " + firctCharacter + " and length is " + length);
                            Score.setText("Score " + currentscore + " Attempts  " + attempts);
                            fields.clear();
                            while (i < length) {
                                tf = new JTextField();
                                tf.setPreferredSize(new Dimension(30, 30));
                                wordContainer.add(tf);
                                fields.add(tf);
                                i++;
                            }
                            wordContainer.validate();
                            wordContainer.repaint();
                        } catch (IOException ex) {
                            Logger.getLogger(Client_start.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }.start();

            }
        });

    }

    private String getServerAddress() {
        return JOptionPane.showInputDialog(frame, "Enter IP Address of the Server:", "127.0.0.1", JOptionPane.QUESTION_MESSAGE);
    }

    private String getPort() {
        return JOptionPane.showInputDialog(frame, "Enter server Port of the Server:", "5555", JOptionPane.PLAIN_MESSAGE);
    }

    public void run() {
        Gui();
        String serverAddress = getServerAddress();
        String port = getPort();
        int p = Integer.parseInt(port);
        try {
            socket = new Socket(serverAddress, p);

            writer = new DataOutputStream(socket.getOutputStream());

            reader = new DataInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(Client_start.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) throws Exception {
        Client_start client = new Client_start();
        client.run();
    }

    public void Gui() {
        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void sentMessage(String message) throws IOException {
        writer.writeUTF(message);
        writer.flush();
        // return null;
    }

    public String receiveMessage() throws IOException {
        return reader.readUTF();
    }

}
