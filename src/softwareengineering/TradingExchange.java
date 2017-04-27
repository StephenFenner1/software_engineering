package softwareengineering;

import java.util.List;
import java.util.Map;

/**
 * Class to handle trading mechanism for stock market simulation.
 */
public class TradingExchange {

    List<Portfolio> portfolios;
    List<Company> companies;
    Map<Portfolio, Integer> buyQueue;
    Map<Portfolio, Integer> sellQueue;

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
    }

    /**
     * Public method used to handle all trades for a single 15-minute period
     * of the simulation.
     */
    public void handleTrades() {
        // Loop through all companies.
        for (Company company : companies) {

            getTrades(company);
            
            makeTrades(company);

            buyQueue.clear();
            sellQueue.clear();
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
            int trade = portfolio.getTrader().requestTrade(company);
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
     * @param company 
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
        
        int dif = (buyTotal - sellTotal);
        
        company.updateSupplyDemandRate(dif);

        if (buyTotal == sellTotal) {
            // Make all trades.

            for (Portfolio portfolio : buyQueue.keySet()) {
                portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company);
            }

            for (Portfolio portfolio : sellQueue.keySet()) {
                portfolio.getTrader().makeTrade(sellQueue.get(portfolio), company);
            }

        } else if (buyTotal > sellTotal) {

            // All sales can be made.
            for (Portfolio portfolio : sellQueue.keySet()) {
                portfolio.getTrader().makeTrade(sellQueue.get(portfolio), company);
            }

            // Make as many purchases as possible.
            for (Portfolio portfolio : buyQueue.keySet()) {
                if (buyTotal != 0) {
                    if (buyTotal >= buyQueue.get(portfolio)) {
                        portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company);
                        buyTotal -= buyQueue.get(portfolio);
                    } else {
                        portfolio.getTrader().makeTrade(buyTotal, company);
                        buyTotal = 0;
                    }
                }/* else {
                        trader.makeTrade(0);
                    }*/
            }

        } else {
            // All purchases can be made.

            for (Portfolio portfolio : buyQueue.keySet()) {
                portfolio.getTrader().makeTrade(buyQueue.get(portfolio), company);
            }

            // Make as many sales as possible.
            for (Portfolio portfolio : sellQueue.keySet()) {
                if (sellTotal != 0) {
                    if (sellTotal >= sellQueue.get(portfolio)) {
                        portfolio.getTrader().makeTrade(sellQueue.get(portfolio), company);
                        sellTotal -= sellQueue.get(portfolio);
                    } else {
                        portfolio.getTrader().makeTrade(sellTotal, company);
                        sellTotal = 0;
                    }
                }
            }
        }
    }
}
