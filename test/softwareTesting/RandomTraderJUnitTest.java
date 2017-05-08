package softwareTesting;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import softwareengineering.*;

/**
 * Testing class for the IntelligentTrader class. 
 * 
 * @author Group 26
 */
public class RandomTraderJUnitTest {

    private static boolean initialTest = true;
    Portfolio portfolioA;
    Portfolio portfolioB;
    Portfolio portfolioC;
    Portfolio portfolioD;
    ArrayList<Company> companies;
    ArrayList<Integer> stockListA;
    ArrayList<Integer> stockListB;
    ArrayList<Integer> stockListC;
    ArrayList<Integer> stockListD;
    ArrayList<Portfolio> portfolios;
    Trader traderA;
    Trader traderB;
    Client clientA;
    Client clientB;
    Client clientC;
    Client clientD;

    /**
     * Constructor method, used to initialise all Portfolios/Traders/Clients/ArrayLists
     * needed to create a new instance of RandomTrader.
     */
    public RandomTraderJUnitTest() {

        companies = new ArrayList<>();
        companies.add(new Company("Company A", StockType.Property, 150, 200));
        companies.add(new Company("Company B", StockType.Food, 20, 800));
        companies.add(new Company("Company C", StockType.Hitech, 50, 600));

        stockListA = new ArrayList<>();
        stockListA.add(20);
        stockListA.add(150);
        stockListA.add(100);

        stockListB = new ArrayList<>();
        stockListB.add(5);
        stockListB.add(400);
        stockListB.add(25);

        stockListC = new ArrayList<>();
        stockListC.add(50);
        stockListC.add(10);
        stockListC.add(300);

        stockListD = new ArrayList<>();
        stockListD.add(25);
        stockListD.add(40);
        stockListD.add(50);

        traderA = new RandomTrader();
        traderB = new RandomTrader();

        clientA = new Client("Client A Name", 1000, 20000);
        clientB = new Client("Client B Name", 200, 6000);
        clientC = new Client("Client C Name", 100, 2500);
        clientD = new Client("Client D Name", 250, 4500);

        portfolioA = new Portfolio(clientA, companies, stockListA, traderA);
        portfolioB = new Portfolio(clientB, companies, stockListB, traderB);
        portfolioC = new Portfolio(clientC, companies, stockListC, traderA);
        portfolioD = new Portfolio(clientD, companies, stockListD, traderB);
        portfolios = new ArrayList<>();
        portfolios.add(portfolioA);
        portfolios.add(portfolioB);
        portfolios.add(portfolioC);
        portfolios.add(portfolioD);

    }

    /**
     * Tests the getter method for ID.
     */
    @Test
    public void testGetID() {
        testGetIDFirst();
    }

    /**
     * Tests that ID auto-increments.
     */
    public void testGetIDFirst() {
        if (initialTest) {
            assertEquals(0, traderA.getID());
            assertEquals(1, traderB.getID());
        }
        initialTest = false;
    }

    /**
     * Tests the getter method for the type of the Trader. In this instance it
     * should produce "Random"
     */
    @Test
    public void getType() {
        testGetIDFirst();
        assertEquals(traderA.getType(), "Random");
        assertEquals(traderB.getType(), "Random");
    }

    /**
     * Tests the getter method for the mood of the Trader.
     */
    @Test
    public void getMood() {
        testGetIDFirst();
        assertEquals(traderA.getMood(), "Balanced");
        assertEquals(traderB.getMood(), "Balanced");
    }

    /**
     * Tests the money made method. 
     */
    @Test
    public void moneyMade() {
        testGetIDFirst();
        clientA.setCurrentValue(10000);
        clientB.setCurrentValue(10000);
        clientC.setCurrentValue(500);
        clientD.setCurrentValue(3000);
        //client A made lost 10,000
        //client B made 400
        //client C lost 2,000
        //client D lost 1,500
        //((client A loss)+(client C loss))/2 = average money made for traderA
        //((client B loss)+(client C loss))/2 = average money made for traderB

        int traderAMoneyMade = (((-10000) + (-2000)) / 2) / 100;
        int traderBMoneyMade = (((4000) + (-1500)) / 2) / 100;

        traderA.updateMoneyMade(portfolios);
        traderB.updateMoneyMade(portfolios);

        assertEquals(traderA.getAvgMoneyMade().intValue(), traderAMoneyMade);
        assertEquals(traderB.getAvgMoneyMade().intValue(), traderBMoneyMade);

    }    
   
}
