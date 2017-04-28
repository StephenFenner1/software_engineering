package softwareengineering;

/**
 * Class to handle an intelligent trader and it's AI functionality.
 * 
 * @author Josh Hasan, Jamie Critcher
 */

public class IntelligentTrader extends Trader {
    private int id;
    
    /**
     *  Constructor method for the Random Trader.
     *  Initialises all traders to have a balanced mood.
     *  Stores all the portfolios relevant to this trader.
     */
    public IntelligentTrader() {
        id = ID++;
    }

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
        if (portfolio.getClient().isCashingOut()) {
            // If cashing out, try to sell all stock.
            return (int)portfolio.getStockOwned().get(company);
        } else if(compareRisk(portfolio.getRisk(), company.getRisk())) {
            // Check market type of company.
            if (company.getMarketType() == MarketType.Bear) {
                // Buy stocks.
                return Math.round((int)portfolio.getStockOwned().get(company) / 100);
            } else {
                // Sell stocks.
                return -Math.round((int)portfolio.getStockOwned().get(company) / 100);
            }
        }
        // Do not trade.
        return 0;
    }
    
    @Override
    public int getID() {
        return ID;
    }
}
