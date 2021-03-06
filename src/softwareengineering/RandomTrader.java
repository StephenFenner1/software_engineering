package softwareengineering;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to handle a random trader and it's AI functionality.
 *
 * @author Group 26
 */
public class RandomTrader extends Trader {

    private Mood mood;  // The selling mood of the Random Trader - {Balanced | AggressivePurchaser | AggressiveSeller}
    private Mood moodOverride;
    private final int id;
    private BigInteger moneyMade;
    private BigInteger avgMoneyMade;

    /**
     * Constructor method for the Random Trader. Initialises all traders to have
     * a balanced mood. Stores all the portfolios relevant to this trader.
     */
    public RandomTrader() {
        moneyMade = new BigInteger("0");
        avgMoneyMade = new BigInteger("0");
        this.mood = Mood.Balanced;
        moodOverride = Mood.None;
        id = ID++;
    }

    /**
     * Setter method for moodOverride
     *
     * @param mood the new value for moodOverride.
     */
    public void setMoodOverride(Mood mood) {
        moodOverride = mood;
    }

    /**
     * Sets moveOverride to none.
     */
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
            if ((int) portfolio.getStockOwned().get(company) > 100) {
                //If client owns more than 100 stock of this company, sell 100 at a time.
                return -100;
            } else {
                //Otherwise, sell the remaining number of stocks.
                return -(int) portfolio.getStockOwned().get(company);
            }
        } else if (compareRisk(portfolio.getRisk(), company.getRisk())) {
            Random rand = new Random();
            float var = (3 * rand.nextFloat());

            if (var < 1) {
                // Buy stocks.                
                var = rand.nextFloat();
                if (moodOverride == Mood.None) {
                    return selectBuyMood(portfolio, company, mood, var);

                } else {
                    return selectBuyMood(portfolio, company, moodOverride, var);
                }
            } else if (var < 2) {
                // Sell stocks.
                var = rand.nextFloat();
                if (moodOverride == Mood.None) {
                    return selectSellMood(portfolio, company, mood, var);
                } else {
                    return selectSellMood(portfolio, company, moodOverride, var);
                }
            }
        }
        // Do not trade.
        return 0;
    }

    /**
     * Method to select the buy mood of the trader.
     *
     * @param portfolio The portfolio the traders is controlling.
     * @param company The company that traders are trading stocks in.
     * @param mood The selected mood.
     * @param var Constant used in the amount variable logic.
     * @return The number of stocks to trade in the company.
     */
    private int selectBuyMood(Portfolio portfolio, Company company, Mood mood, float var) {
        int amount = 0;
        int stocks = 0;
        switch (mood) {
            case AggressiveSeller:
                amount = Math.round((var * (int) portfolio.getAvailableMoney()) / 2);
                stocks = (int) (amount / company.getStockValue());

                return stocks;
            case Balanced:
                amount = Math.round(var * (int) portfolio.getAvailableMoney());
                stocks = (int) (amount / company.getStockValue());

                return stocks;
            case AggressivePurchaser:
                amount = Math.round((var * (int) portfolio.getAvailableMoney()) * 2);
                stocks = (int) (amount / company.getStockValue());

                return stocks;
            default:
                return 0;
        }
    }

    /**
     * Method to select the sell mood of the trader.
     *
     * @param portfolio The portfolio the traders is controlling.
     * @param company The company that traders are trading stocks in.
     * @param mood The selected mood.
     * @param var Constant used in the amount variable logic.
     * @return The number of stocks to trade in the company.
     */
    private int selectSellMood(Portfolio portfolio, Company company, Mood mood, float var) {
        int stocks = 0;
        switch (mood) {
            case AggressiveSeller:
                stocks = Math.round((var * (int) portfolio.getAvailableAssets(company)) * 2);
                //stocks = (int)(amount / company.getStockValue());

                return -stocks;
            case Balanced:
                stocks = Math.round((var * (int) portfolio.getAvailableAssets(company)));
                //stocks = (int)(amount / company.getStockValue());

                return -stocks;
            case AggressivePurchaser:
                stocks = Math.round((var * (int) portfolio.getAvailableAssets(company)) / 2);
                //stocks = (int)(amount / company.getStockValue());

                return -stocks;
            default:
                return 0;
        }
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

    /**
     * Getter method for the traders ID.
     *
     * @return The traders ID.
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * Getter method for the traders type.
     *
     * @return "Random".
     */
    @Override
    public String getType() {
        return "Random";
    }

    /**
     * Getter method for the traders mood.
     *
     * @return The traders mood.
     */
    @Override
    public String getMood() {
        return mood.toString();
    }

    /**
     * Returns the total amount of money made by a trader for all its clients
     *
     * @return the amount of money made by this trader for all clients
     */
    @Override
    public BigInteger getMoneyMade() {
        return moneyMade;
    }

    /**
     * Returns the average money made per client by this trader
     *
     * @return the average money made
     */
    @Override
    public BigInteger getAvgMoneyMade() {
        return avgMoneyMade;
    }

    /**
     * Updates the total money made for all portfolios by this trader
     *
     * @param portfolios the list of portfolios
     */
    @Override
    public void updateMoneyMade(ArrayList<Portfolio> portfolios) {
        int clientCount = 0;
        moneyMade = new BigInteger("0");
        for (Portfolio port : portfolios) {
            if (port.getTrader().getID() == id) {
                moneyMade = moneyMade.add(BigInteger.valueOf(((port.getClient().getCurrentValue()) - (port.getClient().getInitialValue())) / 100));
                clientCount++;

            }
        }
        avgMoneyMade = moneyMade.divide(BigInteger.valueOf(clientCount));

    }
}
