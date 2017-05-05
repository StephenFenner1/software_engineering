package softwareengineering;

/**
 * Class to handle an intelligent trader and it's AI functionality.
 *
 * @author Josh Hasan, Jamie Critcher
 */
public class IntelligentTrader extends Trader {

    private int id;

    /**
     * Constructor method for the Intelligent Trader. Initialises the trader a
     * new ID.
     */
    public IntelligentTrader() {
        id = ID++;
    }

    /**
     * Public method to request a trade decision on a specific company and
     * portfolio.
     *
     * @param company The company that traders are trading stocks in.
     * @param portfolio The portfolio the traders is controlling.
     * @return The number of stocks to trade in the company.
     */
    @Override
    public int requestTrade(Company company, Portfolio portfolio) {
        // Check whether or not the client wants to cash out.
        if (portfolio.getClient().isCashingOut()) {
            // If cashing out, try to sell all stock.
            if ((int) portfolio.getStockOwned().get(company) > 100) {
                //If client owns more than 100 stock of this company, sell 100 at a time.
                return -100;
            } else {
                //Otherwise, sell the remaining number of stocks.
                return -(int) portfolio.getStockOwned().get(company);
            }
        } else if (compareRisk(portfolio.getRisk(), company.getRisk())) {
            int amount = 0;
            int stocks = 0;
            // Check market type of company.
            if (company.getMarketType() == MarketType.Bear) {
                // Buy stocks.
                amount = Math.round((int) portfolio.getAvailableMoney());
                stocks = (int) (amount / company.getStockValue());
                return stocks;
            } else {
                // Sell stocks.
                amount = Math.round((int) portfolio.getAvailableAssets(company));
                stocks = (int) (amount / company.getStockValue());
                return -stocks;
            }
        }
        // Do not trade.
        return 0;
    }

    /**
     * Getter method for the traders ID.
     *
     * @return The traders ID.
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * Getter method for the traders type.
     * 
     * @return "Intelligent".
     */
    @Override
    public String getType() {
        return "Intelligent";
    }

    /**
     * Getter method for the traders mood.
     * 
     * @return "N/A".
     */
    @Override
    public String getMood() {
        return "N/A";
    }
}
