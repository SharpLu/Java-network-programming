package marketplace_server;



/**
 *
 * @author Mac
 */
public class items {
   
    private String productName;
    private float price;
    private String ownID;

    public items(String productName,float price,String ownID) {
        super();
        this.productName=productName;
        this.price=price;
        this.ownID=ownID;
        
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
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    /**
     * @return the ownID
     */
    public String getOwnID() {
        return ownID;
    }

    /**
     * @param ownID the ownID to set
     */
    public void setOwnID(String ownID) {
        this.ownID = ownID;
    }
    
    public String toString(){
    return(productName+ ","+ price+"USD");
    }

 
  
}
