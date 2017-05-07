package softwareengineering;

/**
 * An enumerator used to define a companies market type (Bull/Bear). 
 * All companies start with a randomly generated market type.
 * 
 * @author Group 26
 */
public enum MarketType {
    
    /**
     * If a stock price falls, it is a Bear market
     */
    Bull,
    
    /**
     * If a stock price rises, it is a Bull market.
     */
    Bear, 
    
    /**
     * If a stock price remains unchanged, it maintains it's previous market type.
     */
    Stable;
}
