package softwareengineering;

import java.util.List;

/**
 *
 * @author Josh Hasan, Jamie Critcher
 */
public abstract class Trader {
    
    public List<Portfolio> portfolios;
    
    public abstract int requestTrade(Company company, Portfolio portfolio);
    
    public void makeTrade(Integer stocks, Company company, Portfolio portfolio) {
        int var = (int)portfolio.getStockOwned().get(company);
        portfolio.getStockOwned().replace(company, var + stocks);
    }
}
