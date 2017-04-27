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
     *  Used to randomly change the modd of the trader.
     *  Probabilities of the new mood depends on the current mood of the trader.
     */
    public void changeMood() {
        Random rand = new Random();
        float val = rand.nextFloat();
        
        switch(mood) {
            case AggressiveSeller:
                if (val > 0.4) {
                    mood = Mood.Balanced;
                }
                break;
                
            case Balanced:
                if (val < 0.1) {
                    mood = Mood.AggressiveSeller;
                } else if (val > 0.9) {
                    mood = Mood.AggressivePurchaser;
                }
                break;
                
            case AggressivePurchaser:
                if (val < 0.7) {
                    mood = Mood.Balanced;
                }
                break;
        }
    }
}
