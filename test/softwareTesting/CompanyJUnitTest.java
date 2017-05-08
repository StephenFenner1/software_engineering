package softwareTesting;

import org.junit.Test;
import static org.junit.Assert.*;
import softwareengineering.*;

/**
 * JUnit testing for the Company class.
 * 
 * @author Group 26
 */
public class CompanyJUnitTest {
    
    Company testCompany1;
    Company testCompany2;
    Company testCompany3;
   
    
    /**
     * Constructor for the company JUnit test, initialises three different test
     * companies so that different types of stock and risk can be tested.
     */
    public CompanyJUnitTest() {
        testCompany1 = new Company("jim", StockType.Food, 10000, 10000);
        testCompany2 = new Company("jim", StockType.Hitech, 10000, 10000);
        testCompany3 = new Company("jim", StockType.Property, 10000, 10000);
    }

    /**
     * Test method for the getter methods in the company class.
     */
    @Test
    public void testCompanyGetters() {
        assertEquals(testCompany1.getCompanyName(), "jim");
        assertEquals(testCompany1.getSTOCK_TOTAL(), 10000);
        assertEquals(testCompany1.getStockType(), StockType.Food);
        assertEquals(testCompany1.getStockValue(), 10000);
        assertEquals(testCompany1.getSupplyDemandRate(), 0);
        assertEquals(testCompany1.getMarketChange(), 0);            
    }
   
    /**
     * Tests that a company is initially bankrupt.
     */
    @Test
    public void checkBankrupt() {
        assertFalse(testCompany1.isBankrupt());
    }
    
    /**
     * Tests that risk is assigned properly, based on the type of stock.
     */
    @Test
    public void checkCompanyRiskAssigned() {
        assertEquals(testCompany1.getRisk(), Risk.Low);
        assertEquals(testCompany2.getRisk(), Risk.Moderate);
        assertEquals(testCompany3.getRisk(), Risk.High);
    }
    
    /**
     * Tests both the setter and update method for supplyDemandRate.
     */
    @Test
    public void checkSupplyDemandRate() {
        assertEquals(testCompany1.getSupplyDemandRate(), 0); 
        testCompany1.setSupplyDemandRate(1);
        assertEquals(testCompany1.getSupplyDemandRate(), 1);
        testCompany1.updateSupplyDemandRate(1);
        assertEquals(testCompany1.getSupplyDemandRate(), 2);
    }
    
    /**
     * Tests that both increase and decrease risk methods increase and decrease
     * risk respectively, by one level. Also ensures that property stock types
     * cannot go below a "Moderate" risk.
     */
    @Test
    public void checkRiskChange() {
        testCompany1.removeRiskOverride();
        testCompany1.increaseRisk();
        assertEquals(testCompany1.getRisk(), Risk.Moderate);
        testCompany1.decreaseRisk();
        assertEquals(testCompany1.getRisk(), Risk.Low);
        
        //Make sure property stock types cant go below Moderate risk.
        testCompany3.removeRiskOverride();
        testCompany3.decreaseRisk();
        assertEquals(testCompany3.getRisk(), Risk.Moderate);
        testCompany3.decreaseRisk();
        assertEquals(testCompany3.getRisk(), Risk.Moderate);
    }
    
    /**
     * Tests that the setter and remove riskOverride methods work correctly,
     * calling getRisk when riskOverride is set should return the value of the
     * override as opposed to the risk the company is initialised with.
     */
    @Test
    public void checkRiskOverride() {
        testCompany1.setRiskOverride(Risk.Moderate);
        assertEquals(testCompany1.getRisk(), Risk.Moderate);
        testCompany1.removeRiskOverride();
        assertEquals(testCompany1.getRisk(), Risk.Low);
    }
   
}
