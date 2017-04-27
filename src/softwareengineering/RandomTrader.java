/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering;

/**
 *
 * @author Josh Hasan
 */
public class RandomTrader extends Trader {
    
    /*
     *  An enum initialisation used to define the traders mood.
     */
    public enum Mood {
        Balanced, AggressiveSeller, AggressivePurchaser;
    };
    
    /*
     *  The selling mood of the Random Trader - {Balanced | AggressivePurchaser | AggressiveSeller}
     */
    private Mood mood;
    
    /**
     *  Constructor method for the Random Trader. Initialises all traders to have a balanced mood.
     */
    public RandomTrader() {
        this.mood = Mood.Balanced;
    }
    
    /**
     *  Getter method for the mood of the trader.
     *  @return The mood of the trader.
     */
    public Mood GetMood() {
        return mood;
    }
    
    /*
     *  Setter method for the mood for the trader.
     *  @param mood  The new mood for the trader.
     */
    public void setMood(Mood mood) {
        this.mood = mood;
    }
}
