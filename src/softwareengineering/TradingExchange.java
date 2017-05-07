package softwareengineering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to handle trading mechanism for stock market simulation.
 *
 * @author Group 26
 */
public class TradingExchange {

    private final List<Portfolio> portfolios;
    private final List<Company> companies;
    private final Map<Portfolio, Integer> buyQueue;
    private final Map<Portfolio, Integer> sellQueue;
    private MarketType marketType;
    private Setup setup;
    private int day;
    private int time;
    private int currentTime;
    private int currentDay;


    /**
     * Constructor to instantiate a new Trading Exchange. Only one Trading
     * Exchange should exist per simulation.
     *
     * @param portfolios The list of portfolios in the simulation.
     * @param companies The list of companies in the simulation.
     */
    public TradingExchange(List<Portfolio> portfolios, List<Company> companies) {
        this.portfolios = portfolios;
        this.companies = companies;
        buyQueue = new HashMap<Portfolio, Integer>();
        sellQueue = new HashMap<Portfolio, Integer>();
        marketType = MarketType.Stable;
        currentDay = 0;

    }

    /**
     * Public method used to handle all trades for a single 15-minute period of
     * the simulation.
     *
     * @param setup
     * @param day
     * @param time
     */
    public void handleTrades(Setup setup, int day, int time) {
        currentTime = 0;
        this.setup = setup;
        this.day = day;
        this.time = time;       

        // Loop through all companies.
        for (Company company : companies) {
            
            // Check that the company is not bankrupt.
            if (!company.isBankrupt()) {

                // Get and store trades in buyQueue and sellQueue.
                getTrades(company);

                // Make all available trades in buyQueue and sellQueue.
                makeTrades(company);
                
                // Clear the queues for the next company to use.
                buyQueue.clear();
                sellQueue.clear();
                
                // Update the company.
                company.updateCompany();
              
                

            }
        }
    }

    /**
     * Private method used to retrieve all trades being made by traders for an
     * individual portfolio and company for a single 15-minute period of the
     * simulation.
     *
     * @param company The current company that traders are trading stocks in.
     */
    private void getTrades(Company company) {
        // Loop through all traders.
        for (Portfolio portfolio : portfolios) {
            // Get trade request from current trader for current company.
            int trade = portfolio.getTrader().requestTrade(company, portfolio);
            // Assign trade to correct queue, ignore if trade is 0.
            if (trade < 0) {
                sellQueue.put(portfolio, -trade);
            } else if (trade > 0) {
                buyQueue.put(portfolio, trade);
            }

        }
    }

    /**
     * Private method used to make all trades for the portfolios for an
     * individual company for a single 15-minute period of the simulation.
     *
     * @param company The current company that traders are trading stocks in.
     */
    private void makeTrades(Company company) {
        // Create local variables to store the total stocks being bought/sold.
        int buyTotal = 0;
        int sellTotal = 0;

        for (Integer trade : buyQueue.values()) {
            buyTotal += trade;
        }
        for (Integer trade : sellQueue.values()) {
            sellTotal += trade;
        }
        
        /*
        Creates the message to be displayed on the trading exchange GUI
        */
        String message = ""; 
        if (currentDay != day) {
            message += "\n------------------------------------------------------------ Day: " + day + " ------------------------------------------------------------";
            currentDay = day;
        }
        if (currentTime != time) {
            message += "\n------------------------------------------------------------ Time: " + time + " ------------------------------------------------------------";
            currentTime = time;
        }

        
        message+="\n--------------------Company: " + company.getCompanyName() + "--------------------\n";
        message+="Wanting to buy: " + buyQueue.toString();
        message+="\nTotal wanting to buy: " + buyTotal;
        message+="\nWanting to sell: " + sellQueue.toString();
        message+="\nTotal wanting to sell: " + sellTotal+"\n";
        
        setup.printMessage(message);
        
        
        // Update the supply/demand rate of the company with 1% of the difference.
        int dif = (buyTotal - sellTotal);
        company.updateSupplyDemandRate(dif / 100);
        if (buyTotal == sellTotal) {
            // Make all trades.
            for (Portfolio portfolio : buyQueue.keySet()) {
                //For each portfolio, make all possible purchases
                portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company, portfolio);                
            }
            for (Portfolio portfolio : sellQueue.keySet()) {
                //For each portfolio make all possible sales
                portfolio.getTrader().makeTrade(-sellQueue.get(portfolio), company, portfolio);
            }
        } else if (buyTotal > sellTotal) {
            // All sales can be made.
            for (Portfolio portfolio : sellQueue.keySet()) {
                //For each portfolio, make all possible sales
                portfolio.getTrader().makeTrade(-sellQueue.get(portfolio), company, portfolio);


            }
            
            for (Portfolio portfolio : buyQueue.keySet()) {
                // Make as many purchases as possible.
                
                if (sellTotal != 0) {
                    if (sellTotal >= buyQueue.get(portfolio)) {
                        portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company, portfolio);
                        sellTotal -= buyQueue.get(portfolio);

                    } else {
                        //Prevents purchases for already sold stock
                        portfolio.getTrader().makeTrade(sellTotal, company, portfolio);
                        buyTotal = 0;
                        sellTotal = 0;
                    }
                }

            }
        } else {
            // All purchases can be made.
            for (Portfolio portfolio : buyQueue.keySet()) {
                //For each portfolio, make all possible purchases
                portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company, portfolio);


            }
            
            for (Portfolio portfolio : sellQueue.keySet()) {
                // Make as many sales as possible.
                if (buyTotal != 0) {
                    if (buyTotal >= sellQueue.get(portfolio)) {
                        portfolio.getTrader().makeTrade(-sellQueue.get(portfolio), company, portfolio);
                        buyTotal -= sellQueue.get(portfolio);

                    } else {
                        //Prevents sales for already purchased stock
                        portfolio.getTrader().makeTrade(-buyTotal, company, portfolio);
                        sellTotal = 0;
                        buyTotal = 0;
                    }
                }

            }
        }

    }

    /**
     * Getter method to get the value of marketType.
     *
     * @return the value of marketType.
     */
    public MarketType getMarketType() {
        return marketType;
    }
    
    /**
     * Sets the market type
     * @param marketType the market type
     */
    public void setMarketType(MarketType marketType){
        this.marketType = marketType;
    }
    
    /**
     * Updates the market state using the stock values
     * @return the specific state of the market as a value
     */
    public int updateMarketState() {
        int var = 0;
        
        // Loop through all companies.
        for (Company company : companies) {
            // Check that the company is not bankrupt.
            if (!company.isBankrupt()) {
                // Update current state of the market.
                var += (company.getMarketChange() * company.getStockValue());
            }
        }
        return var;
    }


}
