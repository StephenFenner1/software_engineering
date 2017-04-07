/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering;

/**
 *
 * @author stf23
 */
public class Company {

    private String companyName;
    private boolean bankrupt = false;
    private int supplyDemandRate;
    private int  stockValue;
    private int STOCK_TOTAL;
    private enum stockType {
        Food,
        Hard,
        Property,
        Hitech
    }
    
    public Company() {
    
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSupplyDemandRate() {
        return supplyDemandRate;
    }

    public void setSupplyDemandRate(int supplyDemandRate) {
        this.supplyDemandRate = supplyDemandRate;
    }

    public int getStockValue() {
        return stockValue;
    }

    public void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }

    public int getSTOCK_TOTAL() {
        return STOCK_TOTAL;
    }

    public void setSTOCK_TOTAL(int STOCK_TOTAL) {
        this.STOCK_TOTAL = STOCK_TOTAL;
    }     
        
    boolean isBankrupt() {
        return bankrupt;
    }
    
    
}
