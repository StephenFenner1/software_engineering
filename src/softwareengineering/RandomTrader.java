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

import java.util.List;

public class RandomTrader extends Trader {
    
    /*
     *  The selling mood of the Random Trader - {Balanced | AggressivePurchaser | AggressiveSeller}
     */
    private Mood mood;
    
    /**
     *  Constructor method for the Random Trader.
     *  Initialises all traders to have a balanced mood.
     *  Stores all the portfolios relevant to this trader.
     *  @param portfolios
     */
    public RandomTrader(List<Portfolio> portfolios) {
        this.mood = Mood.Balanced;
        this.portfolios = portfolios;
    }
    
    @Override
    public int requestTrade(Company company, Portfolio portfolio) {
        
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
