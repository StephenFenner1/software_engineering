package softwareengineering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *  Class for the portfolio of the client. Takes the client and creates a portfolio for that client.
 *  The constructor creates the portfolio, the other methods allow to get information on the client
 *  and the contents of the portfolio.
 */
public class Portfolio {
    
    private Map stockOwned;         // Map(companyName, stockValue).
    private final Client client;    // The client.
    private final  Trader trader;   // The trader.
    private Risk risk;              // Risk decided by the client.
    
    /**
     * Constructor method for the Portfolio class. Creates a new portfolio for the 
     * specified Client. Initialises and fills a HashMap with the contents
     * of two arrays (read from the initialisation data), also initialises the client
     * that the portfolio belongs too.
     *
     * @param client         The client who owns the portfolio.
     * @param companyName    Array that contains the company names respective to the client from the initialisation data.
     * @param stocks         Array that contains the stock prices respective to the client from the initialisation data.
     */
    public Portfolio(Client client, ArrayList<String> companyName, ArrayList<Integer>stocks, Trader trader) {
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
        // Randomly assign a risk to the Portfolio.
        Random rand = new Random();
        float var = (5 * rand.nextFloat());
        if (var < 1) {
            risk = Risk.Low;
        } else if ( var < 4) {
            risk = Risk.Moderate;
        } else {
            risk = Risk.High;
        }
    } 
    /**
     * Getter method for the trader that the portfolio belongs to
     *
     * @return The trader object.
     */
    public Trader getTrader() {
        return trader;
    }
    
    public void updateClientRisk() {
        // Randomly assign a risk to the Portfolio.
        Random rand = new Random();
        float var = (5 * rand.nextFloat());
        if (var < 1) {
            risk = Risk.Low;
        } else if ( var < 4) {
            risk = Risk.Moderate;
        } else {
            risk = Risk.High;
        }
    }
    
    /**
     * Public method to update the current value of a portfolios client.
     */
    public void updatePortfolio() {
        // Get the total value of the clients owned stocks.
        int var = 0;
        for (Object companyName : stockOwned.keySet()) {
            var += getPrice((String)companyName);
        }
        // Set the clients current value to their stock value + cash holding.
        client.setCurrentValue(client.getCashHolding() + var);
    }

    /**
     * Getter method for the client that the portfolio belongs to.
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
    
    /**
     * Gets the price of the stock of a company given in the clients portfolio
     *
     * @param  name   The company name.
     * @return        The price of the companies stock.      
     */
    public int getPrice(String name) {
        return (int) stockOwned.get(name);
    }
    
    public Risk getRisk() {
        return risk;
    }
    
    /**
     * Method to print the contents of the portfolio in the format:
     * ("Company name" : "Stock price").
     */
    public void printPortfolio() {
        for(Object company : stockOwned.keySet()) {
            String key = company.toString();
            String value = stockOwned.get(company).toString();
            System.out.println(key + " : " + value);
        }
    }
}
