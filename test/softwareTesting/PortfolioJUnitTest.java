package softwareTesting;

import java.util.ArrayList;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import softwareengineering.*;

/**
 * Testing class for the IntelligentTrader class.  
 */
public class PortfolioJUnitTest {

    Portfolio portfolioA;
    Portfolio portfolioB;
    ArrayList<Company> companies;
    ArrayList<Integer> stockListA;
    ArrayList<Integer> stockListB;
    Trader traderA;
    Trader traderB;
    Client clientA;
    Client clientB;

    /**
     * Used to create two portfolios used for the tests.
     */
    public PortfolioJUnitTest() {

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

        traderA = new RandomTrader();
        traderB = new RandomTrader();

        clientA = new Client("Client A Name", 1000, 20000);
        clientB = new Client("Client B Name", 200, 6000);

        portfolioA = new Portfolio(clientA, companies, stockListA, traderA);
        portfolioB = new Portfolio(clientB, companies, stockListB, traderB);

    }

    /**
     * Tests whether getClient returns the correct client for the portfolio
     */
    @Test
    public void getClientTest() {
        assertEquals(portfolioA.getClient(), clientA);
        assertEquals(portfolioB.getClient(), clientB);
    }

    /**
     * Tests getStocksOwned returns the correct stock amount for all companies
     */
    @Test
    public void getStocksOwned() {
        Map stocksA = portfolioA.getStockOwned();
        Map stocksB = portfolioB.getStockOwned();

        assertEquals(stocksA.get(companies.get(0)), stockListA.get(0));
        assertEquals(stocksA.get(companies.get(1)), stockListA.get(1));
        assertEquals(stocksA.get(companies.get(2)), stockListA.get(2));

        assertEquals(stocksB.get(companies.get(0)), stockListB.get(0));
        assertEquals(stocksB.get(companies.get(1)), stockListB.get(1));
        assertEquals(stocksB.get(companies.get(2)), stockListB.get(2));

    }

    /**
     * Tests getTrader returns the correct trader linked to the portfolio
     */
    @Test
    public void getTrader() {
        assertEquals(portfolioA.getTrader(), traderA);
        assertEquals(portfolioB.getTrader(), traderB);
    }

    /**
     * Tests getAvailableAssets returns the correct value for the clients available assets
     */
    @Test
    public void getAvailableAssets() {
        int stockValueAA = stockListA.get(0) / 100;
        int stockValueAB = stockListA.get(1) / 100;
        int stockValueAC = stockListA.get(2) / 100;

        int stockValueBA = stockListB.get(0) / 100;
        int stockValueBB = stockListB.get(1) / 100;
        int stockValueBC = stockListB.get(2) / 100;

        assertEquals(portfolioA.getAvailableAssets(companies.get(0)), stockValueAA);
        assertEquals(portfolioA.getAvailableAssets(companies.get(1)), stockValueAB);
        assertEquals(portfolioA.getAvailableAssets(companies.get(2)), stockValueAC);

        assertEquals(portfolioB.getAvailableAssets(companies.get(0)), stockValueBA);
        assertEquals(portfolioB.getAvailableAssets(companies.get(1)), stockValueBB);
        assertEquals(portfolioB.getAvailableAssets(companies.get(2)), stockValueBC);
    }

    /**
     * Tests getAvailableMoney returns the correct value for the clients avaialable money to purchase with
     */
    @Test
    public void getAvailableMoney() {
        assertEquals(clientA.getCashHolding() / 100, portfolioA.getAvailableMoney());
        assertEquals(clientB.getCashHolding() / 100, portfolioB.getAvailableMoney());
    }
    /**
     * Tests updateAvailableMoney correctly updates the available money for a client
     */

    @Test
    public void updateAvailableMoney() {
        int expectedAvailableMoneyA = clientA.getCashHolding() / 100;
        int expectedAvailableMoneyB = clientB.getCashHolding() / 100;
        portfolioA.updateAvailableMoney();
        portfolioB.updateAvailableMoney();
        assertEquals(portfolioA.getAvailableMoney(), expectedAvailableMoneyA);
        assertEquals(portfolioB.getAvailableMoney(), expectedAvailableMoneyB);

    }    
}
