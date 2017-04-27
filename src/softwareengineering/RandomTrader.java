package softwareengineering;

/**
 * Class to handle a random trader and it's AI functionality.
 * 
 * @author Josh Hasan, Jamie Critcher
 */

import java.util.Random;

public class RandomTrader extends Trader {
    
    private Mood mood;  // The selling mood of the Random Trader - {Balanced | AggressivePurchaser | AggressiveSeller}
    
    /**
     *  Constructor method for the Random Trader.
     *  Initialises all traders to have a balanced mood.
     *  Stores all the portfolios relevant to this trader.
     */
    public RandomTrader() {
        this.mood = Mood.Balanced;
    }

    /**
     * Public method to request a trade decision on a specific company and
     * portfolio.
     * 
     * @param company   The company that traders are trading stocks in.
     * @param portfolio The portfolio the traders is controlling.
     * @return          The number of stocks to trade in the company.
     */
    @Override
    public int requestTrade(Company company, Portfolio portfolio) {
        Random rand = new Random();
        float var = (3 * rand.nextFloat());
        
        if (var < 1) {
            // Buy stocks.
            var = rand.nextFloat();
            switch (mood) {
                case AggressiveSeller:
                    return Math.round((var * (int)portfolio.getStockOwned().get(company)) / 200);
                case Balanced:
                    return Math.round((var * (int)portfolio.getStockOwned().get(company)) / 100);
                case AggressivePurchaser:
                    return Math.round((var * (int)portfolio.getStockOwned().get(company)) / 50);
            }
        } else if (var < 2) {
            // Sell stocks.
            var = rand.nextFloat();
            switch (mood) {
                case AggressiveSeller:
                    return -(Math.round((var * (int)portfolio.getStockOwned().get(company)) / 50));
                case Balanced:
                    return -(Math.round((var * (int)portfolio.getStockOwned().get(company)) / 100));
                case AggressivePurchaser:
                    return -(Math.round((var * (int)portfolio.getStockOwned().get(company)) / 200));
            }
        }
        // Do not trade.
        return 0;
    }
    
    /**
     *  Getter method for the mood of the trader.
     *  @return The mood of the trader.
     */
    public Mood GetMood() {
        return mood;
    }
    
    /**
     *  public method to change the mood of the trader.
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
