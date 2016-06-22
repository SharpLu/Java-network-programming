package marketplace_server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mac
 */
public class Server {

    private static final String USAGE = "java server.MarketServer [<bank_url>]";
    private static final String BANK = "rmi://localhost/bank";

    public static void main(String args[]) {
        String bankURL;
           // new Server();
        // System.out.println("RMI Server started");
        switch (args.length) {
            case 0:
                bankURL = BANK;
                createMarketPlace(bankURL);
                break;
            case 1:
                bankURL = args[0];
                createMarketPlace(bankURL);
                break;
            default:
                System.err.println(USAGE);
                System.exit(1);
        }
    }

    public static void createMarketPlace(String bankURL) {
      try{
        //  UserInterface users = new UserImplementation();
        MarketInterface inf=new MarketImplementation(bankURL);
        System.out.println("RMI MarketServer is start");
        }catch(Exception e){
        System.out.println("Here is error  $$$$"+e);
        }
    }
}
