package softwareengineering;

import java.util.HashMap;
import java.util.Map;

/**
 *  Class for the portfolio of the client. Takes the client and creates a portfolio for that client.
 *  The constructor creates the portfolio, the other methods allow to get information on the client
 *  and the contents of the portfolio.
 */
public class Portfolio {
    
    private Map stockOwned; //Map(companyName, stockValue)
    private Client client; //the client    
    private Trader trader; //the trader
    /*
    * Constructor method for the Portfolio class. Creates a new portfolio for the 
    * specified Client. Initialises and fills a HashMap with the contents
    * of two ArrayLists (read from the initialisation data), also adds the client
    * and trader that the portfolio belongs to and handles respectively.
    *
    * @param client         The client who owns the portfolio.
    * @param companyName    ArrayList that contains the company names respective to the client from the initialisation data.
    * @param stocks         ArrayList that contains the stock prices respective to the client from the initialisation data.
    * @param trader         The trader handling the portfolio
    */
    public Portfolio(Client client, ArrayList<String> companyName, ArrayList<Integer> stocks, Trader trader) {
        this.client = client;     
        this.trader = trader;
        stockOwned = new HashMap<String, Integer>();
        //both arrays have to be the same size
        if(companyName.size() == stocks.size()) {
            //concat lists into map with key = companyName, value = stockValue;
            for(int i= 0; i < companyName.size(); i++){
                stockOwned.put(companyName.get(i), stocks.get(i));
            }
        }
    } 
    /*
    * Getter method for the trader that the portfolio belongs to
    *
    * @return The trader object.
    */
    public Trader getTrader() {
        return trader;
    }

    /*
    * Getter method for the client that the portfolio belongs to
    *
    * @return The client object.
    */
    public Client getClient() {
        return client;
    }
    
    /*
    * Getter method for the stockOwned Map
    *
    * @return The stockOwned Map.
    */
    public Map getStockOwned() {
        return stockOwned;
    }
    
    /*
    * Gets the price of the stock of a company given in the clients portfolio
    *
    * @param  name   The company name.
    * @return        The price of the companies stock.      
    */
    public int getPrice(String name) {
        return (int) stockOwned.get(name);
    }
    
    /*
    * Method to print the contents of the portfolio in the format: ("Company name" : "Stock price")
    */
    public void printPortfolio() {
        for(Object company : stockOwned.keySet()) {
            String key = company.toString();
            String value = stockOwned.get(company).toString();
            System.out.println(key + " : " + value);
        }
    }
}
