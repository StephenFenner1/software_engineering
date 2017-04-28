package softwareengineering;

/**
 * Class to handle an intelligent trader and it's AI functionality.
 * 
 * @author Josh Hasan, Jamie Critcher
 */

import java.util.Random;

public class IntelligentTrader extends Trader {
    
    /**
     *  Constructor method for the Intelligent Trader.
     *  Initialises all traders to have a balanced mood.
     *  Stores all the portfolios relevant to this trader.
     */
    public IntelligentTrader() {}

    /**
     * Public method to request a trade decision on a specific company and
     * portfolio.
     * 
     * @param company   The company that traders are trading stocks in.
     * @param portfolio The portfolio the traders is controlling.
     * @return          The number of stocks to trade in the company.
     */
    @Override
    public int requestTrade(Company company, Portfolio portfolio) {
        Random rand = new Random();
        float var = (3 * rand.nextFloat());
        
        if (var < 1) {
            // Buy stocks.
            var = rand.nextFloat();
            return Math.round((var * (int)portfolio.getStockOwned().get(company)) / 100);
        } else if (var < 2) {
            // Sell stocks.
            var = rand.nextFloat();
            return -(Math.round((var * (int)portfolio.getStockOwned().get(company)) / 100));
        }
        // Do not trade.
        return 0;
    }
}
