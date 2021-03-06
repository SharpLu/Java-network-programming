/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace_client;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Mac
 */
public class Client extends javax.swing.JFrame {

    private Handler handler;
    String username;
    String password;

    /**
     * Creates new form GUI
     */
    public Client(String username, String password) {
        this.username = username;
        this.password = password;
        initComponents();
        try {

            handler = new Handler(this, username, password);

        } catch (Exception e) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        productname = new javax.swing.JTextField();
        productPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        saleProductButton = new javax.swing.JButton();
        JMessage = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SaleProducts = new javax.swing.JList();
        JMoney = new javax.swing.JLabel();
        buybutton = new javax.swing.JButton();
        wishPriceJtextField = new javax.swing.JTextField();
        wishButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        wishNameJtextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        wishsList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jRecords = new javax.swing.JLabel();
        sold = new javax.swing.JLabel();
        bought = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Products for sale");

        jLabel3.setText("Product Name");

        jLabel4.setText("Product Price");

        saleProductButton.setText("sale");
        saleProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleProductButtonActionPerformed(evt);
            }
        });

        JMessage.setText("Server status");

        SaleProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(SaleProducts);

        JMoney.setText("Money you have");

        buybutton.setText("buy");
        buybutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buybuttonActionPerformed(evt);
            }
        });

        wishButton.setText("Wish");
        wishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wishButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Enter wish price");

        jLabel5.setText("wish name");

        wishsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(wishsList);

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRecords.setText("User records");

        sold.setText("sold");

        bought.setText("bought");

        jLabel6.setText("You sold ");

        jLabel7.setText("You bought");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(77, 104, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(JMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(productname)
                                        .addComponent(productPrice)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(saleProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(wishPriceJtextField)
                                    .addComponent(wishNameJtextField)
                                    .addComponent(wishButton, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel5)))
                                .addGap(6, 6, 6)))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buybutton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRecords)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sold)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(12, 12, 12)
                                .addComponent(bought)))))
                .addGap(141, 141, 141))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRecords)
                    .addComponent(sold)
                    .addComponent(bought)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JMoney))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buybutton)
                        .addGap(155, 155, 155))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(1, 1, 1)
                                .addComponent(productname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saleProductButton)
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JMessage)
                        .addGap(9, 9, 9)))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wishNameJtextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wishPriceJtextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wishButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saleProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleProductButtonActionPerformed

        String name = productname.getText().toString();
        String price = productPrice.getText().toString();
        float p = Float.parseFloat(price);
        try {
            handler.sellProduct(name, p);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        productname.setText("");
        productPrice.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_saleProductButtonActionPerformed

//    private void SaleProductsValueChanged(javax.swing.event.ListSelectionEvent evt) {
//        System.out.println("tetes");
//        if (SaleProducts.getSelectedIndex() != -1) {
//            buybutton.setEnabled(true);
//        } else {
//            buybutton.setEnabled(false);
//
//        }
//
//    }

    private void buybuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buybuttonActionPerformed
        Object selected = SaleProducts.getModel().getElementAt(SaleProducts.getSelectedIndex());
        String[] product = ((String) selected).split(",");
        String productName = product[0];
        String ProductPrice = (product[1].replaceAll("USD", "")).trim();
        // String ProductPrice = product[1];
        System.out.println("[0]" + productName);
        System.out.println("[1]" + ProductPrice);

        if (productName.length() != 0 && ProductPrice.length() != 0) {
            try {

                float ppp = Float.parseFloat(ProductPrice);
                System.out.println(productName + ppp);
                handler.bayProduct(productName, ppp);
            } catch (RemoteException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            SaleProducts.setSelectedIndex(-1);
        }

// TODO add your handling code here:
    }//GEN-LAST:event_buybuttonActionPerformed

    private void wishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wishButtonActionPerformed
        if (wishNameJtextField.getText() != null && wishPriceJtextField.getText() != null) {
            String getwishprice = wishPriceJtextField.getText().toString();
            String getwishname = wishNameJtextField.getText().toString();
            float ppp = Float.parseFloat(getwishprice);
            try {
                handler.insertNewWish(username, getwishname, ppp);
            } catch (RemoteException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            wishPriceJtextField.setText("");
            wishNameJtextField.setText("");
        }

        //  handler.
// TODO add your handling code here:
    }//GEN-LAST:event_wishButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            handler.unregister();
            System.exit(0);

// TODO add your handling code here:
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void updateMarketPlaceList(DefaultListModel list) {
        SaleProducts.setModel(list);
        SaleProducts.repaint();
    }

    public void updateWishsList(DefaultListModel list) {
        wishsList.setModel(list);
        wishsList.repaint();
    }

    public void showMessage(String message) {

        System.out.println(message);
        JMessage.setText(message);
        JMessage.repaint();
    }

    public void updateMoney(String username, float money) {
        String m = Float.toString(money);
        JMoney.setText(username + " Your Bank balance is   " + m);
        JMoney.repaint();
    }

//    public void updateRecords(String username,int sold,int bought){
//    jRecords.setText(username+"You sold "+sold+" bought "+bought);
//    jRecords.repaint();
//    }
    public void setSold(int amount) {
        String s = Integer.toString(amount);
        sold.setText(s);
        sold.repaint();
    }

    public void getSold() {
        int s = Integer.parseInt(sold.getText());
        s++;
        sold.setText(Integer.toString(s));
        sold.repaint();
    }

    public void setBought(int amount) {
        String b = Integer.toString(amount);
        bought.setText(b);
        bought.repaint();
    }

    public void getBought() {
        int b = Integer.parseInt(bought.getText());
        b++;
        bought.setText(Integer.toString(b));
        bought.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JMessage;
    private javax.swing.JLabel JMoney;
    private javax.swing.JList SaleProducts;
    private javax.swing.JLabel bought;
    private javax.swing.JButton buybutton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jRecords;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField productPrice;
    private javax.swing.JTextField productname;
    private javax.swing.JButton saleProductButton;
    private javax.swing.JLabel sold;
    private javax.swing.JButton wishButton;
    private javax.swing.JTextField wishNameJtextField;
    private javax.swing.JTextField wishPriceJtextField;
    private javax.swing.JList wishsList;
    // End of variables declaration//GEN-END:variables
}
