package softwareengineering;

/**
 * Class to handle a random trader and it's AI functionality.
 *
 * @author Josh Hasan, Jamie Critcher
 */
import java.util.Random;

public class RandomTrader extends Trader {

    private Mood mood;  // The selling mood of the Random Trader - {Balanced | AggressivePurchaser | AggressiveSeller}
    private Mood moodOverride;
    private int id;

    /**
     * Constructor method for the Random Trader. Initialises all traders to have
     * a balanced mood. Stores all the portfolios relevant to this trader.
     */
    public RandomTrader() {
        this.mood = Mood.Balanced;
        moodOverride = Mood.None;
        id = ID++;
    }

    public void setMoodOverride(Mood mood) {
        moodOverride = mood;
    }

    public void removeMoodOverride() {
        moodOverride = Mood.None;
    }

    /**
     * Public method to request a trade decision on a specific company and
     * portfolio.
     *
     * @param company The company that traders are trading stocks in.
     * @param portfolio The portfolio the traders is controlling.
     * @return The number of stocks to trade in the company.
     */
    @Override
    public int requestTrade(Company company, Portfolio portfolio) {
        // Check whether or not the client wants to cash out.
        if (portfolio.getClient().isCashingOut()) {
            // If cashing out, try to sell all stock.
            return (int) portfolio.getStockOwned().get(company.getCompanyName());
        } else if(compareRisk(portfolio.getRisk(), company.getRisk())) {
            Random rand = new Random();
            float var = (3 * rand.nextFloat());
            
            
            
            if (var < 1) {
                // Buy stocks.
                var = rand.nextFloat();
                if (moodOverride == Mood.None) {
                    return selectBuyMood(portfolio, mood, var);
                    
                } else {
                    return selectBuyMood(portfolio, moodOverride, var);
                }
            } else if (var < 2) {
                // Sell stocks.
                var = rand.nextFloat();
                if (moodOverride == Mood.None) {
                    return selectSellMood(portfolio, mood, var);
                } else {
                    return selectSellMood(portfolio, moodOverride, var);
                }
            }
        }
        // Do not trade.
        return 0;
    }

    private int selectBuyMood(Portfolio portfolio, Mood mood, float var) {
        switch (mood) {
            case AggressiveSeller:
                return Math.round((var * (int) portfolio.getClient().getCashHolding()) / 200);
            case Balanced:
                return Math.round((var * (int) portfolio.getClient().getCashHolding()) / 100);
            case AggressivePurchaser:
                return Math.round((var * (int) portfolio.getClient().getCashHolding()) / 50);
            default:
                return 0;
        }
    }

    private int selectSellMood(Portfolio portfolio, Mood mood, float var) {
        switch (mood) {
            case AggressiveSeller:
                return Math.round((var * (int) portfolio.getClient().getCashHolding()) / 50);
            case Balanced:
                return Math.round((var * (int) portfolio.getClient().getCashHolding()) / 100);
            case AggressivePurchaser:
                return Math.round((var * (int) portfolio.getClient().getCashHolding()) / 200);
            default:
                return 0;
        }
    }

    /**
     * Getter method for the mood of the trader.
     *
     * @return The mood of the trader.
     */
    public Mood GetMood() {
        return mood;
    }

    /**
     * public method to change the mood of the trader.
     */
    public void changeMood() {
        Random rand = new Random();
        float val = rand.nextFloat();

        switch (mood) {
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
    
    @Override
    public int getID() {
        return ID;
    }
}
