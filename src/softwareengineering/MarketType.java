package softwareengineering;

/**
 * An enumerator used to define a companies market type (Bull/Bear).
 * If a stock price falls, it is a Bear market.
 * If a stock price rises, it is a Bull market.
 * If a stock price remains unchanged, it maintains it's previous market type.
 * 
 * All companies start with a randomly generated market type.
 * 
 * @author Jamie Critcher
 */
public enum MarketType {
    Bull, Bear, Stable;
}
