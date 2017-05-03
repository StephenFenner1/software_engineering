package softwareengineering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class for the portfolio of the client. Takes the client and creates a
 * portfolio for that client. The constructor creates the portfolio, the other
 * methods allow to get information on the client and the contents of the
 * portfolio.
 */
public class Portfolio {

    private final Map<Company, Integer> stockOwned;         // Map(companyName, stockValue).
    private final Client client;    // The client.
    private final Trader trader;   // The trader.
    private Risk risk;              // Risk decided by the client.
    private int availableMoney;

    /**
     * Constructor method for the Portfolio class. Creates a new portfolio for
     * the specified Client. Initialises and fills a HashMap with the contents
     * of two arrays (read from the initialisation data), also initialises the
     * client that the portfolio belongs too.
     *
     * @param client The client who owns the portfolio.
     * @param company Array that contains the company respective to the client
     * from the initialisation data.
     * @param stocks Array that contains the stock prices respective to the
     * client from the initialisation data.
     */
    public Portfolio(Client client, ArrayList<Company> companies, ArrayList<Integer> stocks, Trader trader) {
        this.client = client;
        this.trader = trader;
        stockOwned = new HashMap<Company, Integer>();
        //both arrays have to be the same size
        if (companies.size() == stocks.size()) {
            //concat lists into map with key = companyName, value = stockValue;
            for (int i = 0; i < companies.size(); i++) {
                stockOwned.put(companies.get(i), stocks.get(i));
            }
        }
        // Randomly assign a risk to the Portfolio.
        Random rand = new Random();
        float var = (5 * rand.nextFloat());
        if (var < 1) {
            risk = Risk.Low;
        } else if (var < 4) {
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
    @Override
    public String toString() {
        return client.getClientName();
    }

    public Trader getTrader() {
        return trader;
    }

    public void updateClientRisk() {
        // Randomly assign a risk to the Portfolio.
        Random rand = new Random();
        float var = (5 * rand.nextFloat());
        if (var < 1) {
            risk = Risk.Low;
        } else if (var < 4) {
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
        for (Company company : stockOwned.keySet()) {
            var += getPrice(company);
        }
        // Set the clients current value to their stock value + cash holding.
        client.setCurrentValue(client.getCashHolding() + var);
        updateAvailableMoney();
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
     * @param company The company name.
     * @return The price of the companies stock.
     */
    public int getPrice(Company company) {
        return stockOwned.get(company) * company.getStockValue();
    }

    public Risk getRisk() {
        return risk;
    }

    /**
     * Method to print the contents of the portfolio in the format: ("Company
     * name" : "Stock price").
     */
    public void printPortfolio() {
        for (Object company : stockOwned.keySet()) {
            String key = company.toString();
            String value = stockOwned.get(company).toString();
            System.out.println(key + " : " + value);
        }
    }

    public void updateAvailableMoney() {
        int noOfCompanies = 0;
        for (Company company : stockOwned.keySet()) {
            if (!company.isBankrupt()) {
                noOfCompanies++;
            }
        }
        if (noOfCompanies == 0) {
            availableMoney = 0;
        } else {
            availableMoney = (getClient().getCashHolding() / 100) / noOfCompanies;
            System.out.println("avalab in port: " + availableMoney);
        }
    }

    public int getAvailableMoney() {
        return availableMoney;
    }
}
