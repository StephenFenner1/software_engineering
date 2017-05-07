package softwareengineering;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * The abstract Trader method. IntelligentTrader and RandomTrader classes both  
 * extend this class.
 * 
 * @author Group 26
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
     * @return              Returns true if client risk is bigger or equal to company risk otherwise return false.                   
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
     * Getter method for the traders ID.
     *
     * @return The traders ID.
     */
    public abstract int getID();
    
    /**
     * Getter method for the traders type.
     * 
     * @return The traders type.
     */
    public abstract String getType();
    
    /**
     * Getter method for the traders mood.
     * 
     * @return The traders mood.
     */
    public abstract String getMood();

    /**
     * Returns the total amount of money made by a trader for all its clients
     * @return the amount of money made by this trader for all clients
     */
    public abstract BigInteger getMoneyMade();
    
    /**
     * Updates the total money made for all portfolios by this trader
     * @param portfolios the list of portfolios
     */
    public abstract void updateMoneyMade(ArrayList<Portfolio> portfolios);
    
    /**
     * Returns the average money made per client by this trader
     * @return the average money made
     */
    public abstract BigInteger getAvgMoneyMade();
}
