package softwareengineering;

import java.util.Random;

/**
 * Storage class for company data. Stores the name, stock info and supply/demand
 * rate of a company and whether or not the company is bankrupt.
 *
 * @author Group 26
 */
public class Company {

    private final String companyName;
    private final StockType stockType;
    private final int STOCK_TOTAL;
    private int supplyDemandRate;
    private int stockValue;
    private boolean bankrupt;
    private MarketType marketType;
    private int marketChange;
    private Risk risk;
    private Risk riskOverride;

    /**
     * Constructor for Company class. Initialises a single company data class
     * given basic information about a company.
     *
     * @param companyName The name of the company.
     * @param stockType The type of stock used by the company.
     * @param stockValue The value of the company's individual stock.
     * @param STOCK_TOTAL The total number of stock issued by the company.
     */
    public Company(String companyName, StockType stockType, int stockValue, int STOCK_TOTAL) {
        // Set companyName, stockType, stockValue and stockTotal using given values.
        this.companyName = companyName;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.STOCK_TOTAL = STOCK_TOTAL;
        // Randomly assign a stock type to the company.
        Random rand = new Random();
        float var = (2 * rand.nextFloat());
        if (var < 1) {
            marketType = MarketType.Bear;
        } else {
            marketType = MarketType.Bull;
        }
        // Initialise risk based on stockType.
        if (stockType == StockType.Food || stockType == StockType.Hard) {
            risk = Risk.Low;
        } else if (stockType == StockType.Hitech) {
            risk = Risk.Moderate;
        } else {
            risk = Risk.High;
        }
        // Initialise bankrupt as false.
        bankrupt = false;
        // Initialise supplyDemandRate as 0.
        supplyDemandRate = 0;
        // Initialise marketChange to 0.
        marketChange = 0;
        // Set risk override to none.
        riskOverride = Risk.None;
    }

    /**
     * Method to update the stock value and market type of a company by the raw
     * supply/demand rate, supply/demand rate reset to 0 afterwards.
     */
    public void updateCompany() {
        updateMarketType();
        stockValue += supplyDemandRate;
        if (stockValue < 0) {
            stockValue = 0;
        }
        checkBankrupt();
        marketChange = supplyDemandRate;
        setSupplyDemandRate(0);
    }

    /**
     * Private method to set the current supply/demand rate of the company.
     *
     * @param supplyDemandRate New supply/demand rate of the company.
     */
    private void setSupplyDemandRate(int supplyDemandRate) {
        this.supplyDemandRate = supplyDemandRate;
    }

    /**
     * Update method to update the current supply/demand rate of the company.
     *
     * @param supplyDemandRate Change in supply/demand rate of the company.
     */
    public void updateSupplyDemandRate(int supplyDemandRate) {
        this.supplyDemandRate += supplyDemandRate;
    }

    /**
     * Setter method to set the current stock value of the company.
     *
     * @param stockValue New stock value of the company.
     */
    public void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }

    /**
     * Checks whether the company is bankrupt. If it is, sets
     * bankrupt boolean to be true.     * 
     */
    private void checkBankrupt() {
        if (stockValue == 0) {
            setBankrupt();
        }
    }

    /**
     * Private method to set the company to bankrupt.
     *
     * @param bankrupt Whether or not the company is bankrupt.
     */
    private void setBankrupt() {
        bankrupt = true;
    }

    /**
     * Getter method to retrieve the stored name of the company.
     *
     * @return The name of the company.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Getter method to retrieve the type of stock handled by the company.
     *
     * @return The type of stock used by the company.
     */
    public StockType getStockType() {
        return stockType;
    }

    /**
     * Getter method to retrieve the current supply/demand rate of the company.
     *
     * @return The current supply/demand rate of the company.
     */
    public int getSupplyDemandRate() {
        return supplyDemandRate;
    }

    /**
     * Getter method to retrieve the current value of the company's stocks.
     *
     * @return The current value of the company's individual stocks.
     */
    public int getStockValue() {
        return stockValue;
    }

    /**
     * Getter method to get the total number of stocks issued by the company.
     *
     * @return The total number of stocks issued by the company.
     */
    public int getSTOCK_TOTAL() {
        return STOCK_TOTAL;
    }

    /**
     * Getter method to get the recent change in the company stock price.
     *
     * @return The recent change in the company stock price.
     */
    public int getMarketChange() {
        return marketChange;
    }

    /**
     * Getter method to get the current market type of the company.
     *
     * @return The market type of the company (Bull/Bear).
     */
    public MarketType getMarketType() {
        return marketType;
    }

    /**
     * Getter method to find whether or not the company has gone bankrupt.
     *
     * @return Whether or not the company has gone bankrupt.
     */
    public boolean isBankrupt() {
        return bankrupt;
    }

    /**
     * Updates the market type between bear and bull.
     */
    private void updateMarketType() {
        if (supplyDemandRate < 0) {
            if (marketType == MarketType.Bear) {
                decreaseRisk();
            } else {
                increaseRisk();
                marketType = MarketType.Bear;
            }
        } else if (supplyDemandRate > 0) {
            if (marketType == MarketType.Bull) {
                decreaseRisk();
            } else {
                increaseRisk();
                marketType = MarketType.Bull;
            }
        }
    }

    /**
    * Increases the risk value.
    * Low -> Moderate, Moderate -> High.
    */
    private void increaseRisk() {
        if (riskOverride == Risk.None) {
            if (risk == Risk.Low) {
                risk = Risk.Moderate;
            } else if (risk == Risk.Moderate && (stockType != StockType.Food || stockType != StockType.Hard)) {
                risk = Risk.High;
            }
        }
    }

    /**
    * Decreases the risk value.
    * High -> Moderate, Moderate -> Low.
    */
    private void decreaseRisk() {
        if (riskOverride == Risk.None) {
            if (risk == Risk.High) {
                risk = Risk.Moderate;
            } else if (risk == Risk.Moderate && stockType != StockType.Property) {
                risk = Risk.Low;
            }
        }
    }    
    
    /**
     * Returns the risk.
     * @return returns risk if riskOverride is none, otherwise returns riskOverride.
     */
    public Risk getRisk() {
        if (riskOverride == Risk.None) {
            return risk;
        } else {
            return riskOverride;
        }
    }

    /**
     * Sets riskOverride to risk
     * 
     * @param risk  The new value of riskOverride. 
     */
    public void setRiskOverride(Risk risk) {
        riskOverride = risk;
    }

    /**
     *  Sets riskOverride to none.
     */
    public void removeRiskOverride() {
        riskOverride = Risk.None;
    }
}
