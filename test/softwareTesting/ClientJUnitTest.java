package softwareTesting;

import org.junit.Test;
import static org.junit.Assert.*;
import softwareengineering.Client;

/**
 * Testing class for our Client storage class.
 * 
 * @author Group 26
 */
public class ClientJUnitTest {
    
    Client testClient;
    
    /**
     * Constructor, used to initialise the testClient used throughout the tests.
     */
    public ClientJUnitTest() {
        testClient = new Client("bob dylan", 10000, 200);
    }
    
    /**
     * Tests the getter methods in client.java 
     */
    @Test
    public void testClientGetters() {
        assertEquals(testClient.getClientName(), "bob dylan");
        assertEquals(testClient.getCashHolding(), 10000);
        assertEquals(testClient.getInitialValue(), 200);
        assertEquals(testClient.getExpectedValue(), (int)(testClient.getInitialValue() * 1.1));        
    }
    
    /**
     * Tests the cashingOut setter method.
     */
    @Test
    public void checkCashingOut() {
        assertFalse(testClient.isCashingOut());
        testClient.setCashingOut(true);
        assertTrue(testClient.isCashingOut());
    }

    /**
     * Tests the client toString method.
     */
    @Test
    public void checkToString() {
        assertEquals(testClient.toString(), testClient.getClientName());
    }
    
    /**
     * Tests the currentValue setter method.
     */
    @Test
    public void checkCurrentValue() {
        assertEquals(testClient.getInitialValue(), testClient.getCurrentValue());
        testClient.setCurrentValue(300);
        assertEquals(testClient.getCurrentValue(), 300);
    }

    /**
     * Tests the cashHolding setter method.
     */
    @Test
    public void checkCashHolding() {
        assertEquals(testClient.getCashHolding(), 10000);
        testClient.setCashHolding(12000);
        assertEquals(testClient.getCashHolding(), 12000);
    }
}
