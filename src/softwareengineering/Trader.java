package softwareengineering;

/**
 * The abstract Trader method. IntelligentTrader and RandomTrader classes both  
 * extend this class.
 * 
 * @author Josh Hasan, Jamie Critcher
 */
public abstract class Trader {
    
    protected static int ID = 0;
    
    /**
     * Public method to request a trade decision on a specific company and
     * portfolio.
     *
     * @param company The company that traders are trading stocks in.
     * @param portfolio The portfolio the traders is controlling.
     * @return The number of stocks to trade in the company.
     */
    public abstract int requestTrade(Company company, Portfolio portfolio);
    
    /**
     * Method to make a trade given an amount on a specific company and
     * portfolio.
     * 
     * @param stocks        The amount of stocks to trade.
     * @param company       The company the traders are trading stocks in.    
     * @param portfolio     The portfolio the trader controls.
     */
    public void makeTrade(Integer stocks, Company company, Portfolio portfolio) {
        // Update Client's cash holding.
        portfolio.getClient().setCashHolding(portfolio.getClient().getCashHolding() - (stocks * company.getStockValue()));
        
        // Update number of stocks held.
        int var = (int)portfolio.getStockOwned().get(company);
        portfolio.getStockOwned().replace(company, var + stocks);
    }
    
    /**
     * Compares risk between company and client.
     * 
     * @param clientRisk    The client risk.
     * @param companyRisk   The company risk.
     * @return              Returns true if client risk is >= to company risk otherwise return false.                   
     */
    protected boolean compareRisk(Risk clientRisk, Risk companyRisk) {
        if (clientRisk == Risk.High) {
            return true;
        } else if (clientRisk == Risk.Moderate && (companyRisk == Risk.Moderate || companyRisk == Risk.Low)) {
            return true;
        } else if (clientRisk == Risk.Low && companyRisk == Risk.Low) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Getter method for the ID of the trader
     * 
     * @return the traders ID.
     */
    public abstract int getID();
}
