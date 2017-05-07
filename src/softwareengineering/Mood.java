package softwareengineering;

/**
 * An enumerator initialisation used to define the traders mood.
 * 
 * @author Group 26
 */
public enum Mood {
    
    /**
     * The trader has no specified mood.
     */
    None,
    
    /**
     * The trader will have a balanced mood. Will sell/buy equally.
     */
    Balanced,
    
    /**
     * The trader is more likely to sell than buy.
     */
    AggressiveSeller,
    
    /**
     * The trader is more likely to buy than sell.
     */
    AggressivePurchaser;
}
