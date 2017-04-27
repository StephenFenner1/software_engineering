package softwareengineering;

/**
 * Storage class for company data. Stores the name, stock info and supply/demand
 * rate of a company and whether or not the company is bankrupt.
 * 
 * @author Jamie Critcher
 */
public class Company {

    private final String companyName;
    private final stockType stockType;
    private final int STOCK_TOTAL;
    private int supplyDemandRate;
    private int stockValue;
    private boolean bankrupt;
    // Create new public enum stockType.
    public enum stockType {
        Food,
        Hard,
        Property,
        Hitech
    }

    /**
     * Constructor for Company class. Initialises a single company data class
     * given basic information about a company.
     * 
     * @param companyName   The name of the company.
     * @param stockType     The type of stock used by the company.
     * @param stockValue    The value of the company's individual stock.
     * @param STOCK_TOTAL   The total number of stock issued by the company.
     */
    public Company(String companyName, stockType stockType, int stockValue, int STOCK_TOTAL) {
        // Set companyName, stockType, stockValue and stockTotal using given values.
        this.companyName = companyName;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.STOCK_TOTAL = STOCK_TOTAL;
        // Initialise bankrupt as false.
        bankrupt = false;
        // Initialise supplyDemandRate as 0.
        supplyDemandRate = 0;
    }
    
    /**
     * Method to update the stock value of a company by the raw supply/demand
     * rate, supply/demand rate reset to 0 afterwards.
     */
    public void updateStockValue() {
        stockValue += supplyDemandRate;
        setSupplyDemandRate(0);
    }

    /**
     * Setter method to set the current supply/demand rate of the company.
     * 
     * @param supplyDemandRate  New supply/demand rate of the company.
     */
    public void setSupplyDemandRate(int supplyDemandRate) {
        this.supplyDemandRate = supplyDemandRate;
    }

    /**
     * Update method to update the current supply/demand rate of the company.
     * 
     * @param supplyDemandRate  Change in supply/demand rate of the company.
     */
    public void updateSupplyDemandRate(int supplyDemandRate) {
        this.supplyDemandRate += supplyDemandRate;
    }

    /**
     * Setter method to set the current stock value of the company.
     * 
     * @param stockValue    New stock value of the company.
     */
    public void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }  

    /**
     * Setter method to set whether or not the company has gone bankrupt.
     * 
     * @param bankrupt  Whether or not the company is bankrupt.
     */
    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
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
    public stockType getStockType() {
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
     * Getter method to find whether or not the company has gone bankrupt.
     * 
     * @return Whether or not the company has gone bankrupt.
     */
    public boolean isBankrupt() {
        return bankrupt;
    }
}
