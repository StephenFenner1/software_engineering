package softwareengineering;

/**
 *
 * @author Josh Hasan, Jamie Critcher
 */
public abstract class Trader {
    
    protected static int ID = 0;
    
    public abstract int requestTrade(Company company, Portfolio portfolio);
    
    public void makeTrade(Integer stocks, Company company, Portfolio portfolio) {
        // Update Client's cash holding.
        portfolio.getClient().setCashHolding(portfolio.getClient().getCashHolding() - (stocks * company.getStockValue()));
        
        // Update number of stocks held.
        int var = (int)portfolio.getStockOwned().get(company);
        portfolio.getStockOwned().replace(company, var + stocks);
    }
    
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
    
    public abstract int getID();
}
