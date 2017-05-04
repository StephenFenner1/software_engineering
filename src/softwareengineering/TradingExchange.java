package softwareengineering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to handle trading mechanism for stock market simulation.
 * 
 * @author Jamie Critcher
 */
public class TradingExchange {

    private final List<Portfolio> portfolios;
    private final List<Company> companies;
    private final Map<Portfolio, Integer> buyQueue;
    private final Map<Portfolio, Integer> sellQueue;
    private MarketType marketType;

    /**
     * Constructor to instantiate a new Trading Exchange.
     * Only one Trading Exchange should exist per simulation.
     * 
     * @param portfolios    The list of portfolios in the simulation.
     * @param companies     The list of companies in the simulation.
     */
    public TradingExchange(List<Portfolio> portfolios, List<Company> companies) {
        this.portfolios = portfolios;
        this.companies = companies;
        buyQueue = new HashMap<Portfolio, Integer>();
        sellQueue = new HashMap<Portfolio, Integer>();
        marketType = MarketType.Stable;
    }

    /**
     * Public method used to handle all trades for a single 15-minute period
     * of the simulation.
     */
    public void handleTrades() {
        int var = 0;
        // Loop through all companies.
        for (Company company : companies) {
            // Check that the company is not bankrupt.
            if (!company.isBankrupt()) {
                
                // Get and store trades in buyQueue and sellQueue.
                getTrades(company);
                System.out.println("------------------------------");
                System.out.println(buyQueue.toString());
                System.out.println(sellQueue.toString());
                System.out.println("------------------------------");
                // Make all available trades in buyQueue and sellQueue.
                makeTrades(company);
                // Clear the queues for the next company to use.
                buyQueue.clear();
                sellQueue.clear();
                // Update the company.
                company.updateCompany();
                // Update current state of the market.
                var += (company.getMarketChange() * company.getStockValue());
                
            }
        }
        // Update current state of the market.
        if (var < 0) {
            marketType = MarketType.Bear;
        } else if (var > 0) {
            marketType = MarketType.Bull;
        } else {
            marketType = MarketType.Stable;
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
            } else {
                System.out.println("nothonl");
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
        
        System.out.println("Buy Queue: " + buyQueue.toString());
        System.out.println(buyTotal);
        System.out.println("Sell Queue: " + sellQueue.toString());
        System.out.println(sellTotal);
        
        // Update the supply/demand rate of the company with 1% of the difference.
        int dif = (buyTotal - sellTotal);
        System.out.println("dif       " + dif);
        company.updateSupplyDemandRate(dif / 100);

        if (buyTotal == sellTotal) {
            // Make all trades.
            for (Portfolio portfolio : buyQueue.keySet()) {
                portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company, portfolio);
            }
            for (Portfolio portfolio : sellQueue.keySet()) {
                portfolio.getTrader().makeTrade(-sellQueue.get(portfolio), company, portfolio);
            }
        } else if (buyTotal > sellTotal) {
            // All sales can be made.
            for (Portfolio portfolio : sellQueue.keySet()) {
                portfolio.getTrader().makeTrade(-sellQueue.get(portfolio), company, portfolio);
            }
            // Make as many purchases as possible.
            for (Portfolio portfolio : buyQueue.keySet()) {
                if (sellTotal != 0) {
                    if (sellTotal >= buyQueue.get(portfolio)) {
                        portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company, portfolio);
                        sellTotal -= buyQueue.get(portfolio);
                    } else {
                        portfolio.getTrader().makeTrade(sellTotal, company, portfolio);
                        buyTotal = 0;
                    }
                }
            }
        } else {
            // All purchases can be made.
            for (Portfolio portfolio : buyQueue.keySet()) {
                portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company, portfolio);
            }
            // Make as many sales as possible.
            for (Portfolio portfolio : sellQueue.keySet()) {
                if (buyTotal != 0) {
                    if (buyTotal >= sellQueue.get(portfolio)) {
                        portfolio.getTrader().makeTrade(-sellQueue.get(portfolio), company, portfolio);
                        buyTotal -= sellQueue.get(portfolio);
                    } else {
                        portfolio.getTrader().makeTrade(-buyTotal, company, portfolio);
                        sellTotal = 0;
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
}
