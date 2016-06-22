/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace_server;

/**
 *
 * @author Mac
 */
public class products {
   private String productName;
   private float productPrice;
   private String seller;

   
   public products(String productName,float productPrice, String salerName){
   super();
   this.productName=productName;
   this.productPrice=productPrice;
   this.seller=salerName;
   }
    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productPrice
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the seller
     */
    public String getSalerName() {
        return seller;
    }

    /**
     * @param salerName the seller to set
     */
    public void setSalerName(String salerName) {
        this.seller = salerName;
    }
   
}
