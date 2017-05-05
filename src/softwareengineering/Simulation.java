package softwareengineering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that handles the stock market simulation. The initialSetup method,
 * reads the input data, which is then used to create the Clients, Companies,
 * Traders and Portfolios. The run method runs the simulation.
 */
public class Simulation {

    //Create Portfolio objects.
    private ArrayList<Portfolio> portList = new ArrayList<>();

    // Create Trader objects.
    private ArrayList<Trader> traderList = new ArrayList<>();

    // Create Client objects.
    private ArrayList<Client> clients = new ArrayList<>();

    // Create the Trading Exchange
    private TradingExchange tradingExchange;

    Setup setup;

    //Map compMap; 
    private ArrayList<Object[]> compList; //The list that stores the company information, read in from the initialization data
    private Map clientMap; //The map that stores the client information, read in from the initialization data

    /**
     * Constructor method for the Simulation class. Calls the initialSetup()
     * method.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public Simulation() throws FileNotFoundException, IOException {
        initialSetup();
    }

    /**
     * Method that runs the simulation.
     *
     * @throws java.lang.InterruptedException
     */
    public void simulate() throws InterruptedException {
        boolean closed;
        int sun = 1;
        int sat = 7;
        final int goodFri = 104;
        final int easterMon = 107;
        final int christmas = 359;
        final int boxing = 360;
        int riskChange = 30;
        int day = 1;
        int time = 1;
        boolean isClosed;
        Random rand = new Random();

        while (day <= 365) {
            System.out.println("Day: " + day);

            isClosed = setup.isPaused();
            while (isClosed) {
                isClosed = setup.isPaused();
            }

            closed = false;
            if (day == sun) {
                closed = true;
                sun += 7;
            } else if (day == sat) {
                closed = true;
                sat += 7;
            } else if (day == goodFri || day == easterMon || day == christmas || day == boxing) {
                closed = true;
            }

            if (day == riskChange) {
                for (Portfolio portfolio : portList) {
                    portfolio.updateClientRisk();
                }
                riskChange += 30;
            }

            if (!closed) {
                //market open 

                while (time <= 28) {
                    //15 minute iterations

                    tradingExchange.handleTrades();
                    for (Portfolio portfolio : portList) {
                        portfolio.updatePortfolio();
                    }
                    time++;

                    /*
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                     */
                }
                time = 1;
            } else {
                System.out.println("Trading exchange closed.");
            }

            for (Trader trader : traderList) {
                if (trader instanceof RandomTrader) {
                    ((RandomTrader) trader).changeMood();
                }
            }

            for (Client client : clients) {
                if (0 == rand.nextInt(4000)) {
                    client.setCashingOut(true);
                }
            }

            day++;
        }
    }

    /**
     * Method to setup the simulation. Reads two separate CSV files, one Client
     * and one Company, storing them in a Map and ArrayList respectively. The
     * data is then used to create and the client, company, portfolio and trader
     * objects needed to run the simulation.
     *
     * @throws java.io.IOException
     */
    private void initialSetup() throws IOException {
        setup = new Setup();
        File[] files = setup.getFile();
        int traderCount = setup.getTraderCount();

        File compFile = files[0];
        File clientFile = files[1];
        //compMap = new HashMap();
        clientMap = new HashMap();
        Object[] tempArray = new Object[3];
        compList = new ArrayList<>();

        BufferedReader fileReader = null;
        //Delimiter used in CSV file
        final String DELIMITER = ",";
        String line;

        /*
         ******************************
         ******************************
         *** Read the Company file. *** 
         ******************************
         ******************************       
         */
        //Create the file reader
        try {
            fileReader = new BufferedReader(new FileReader(compFile));
        } catch (FileNotFoundException e) {
            System.err.println("compFile not found...");
        }

        //Read the file line by line
        while ((line = fileReader.readLine()) != null) {
            //Get all tokens available in line
            String[] tokens = line.split(DELIMITER);
            //parse the tokens, and add them to the company ArrayList.
            try {
                compList.add(new Object[]{tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])});
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                System.out.println("Incorrect file format.");
                System.exit(0);
            }

            //compMap.put(tokens[0], new Object[]{tokens[1], Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3])});
        }

        /*
         *****************************
         *****************************
         *** Read the Client file. *** 
         *****************************
         *****************************        
         */
        //Create the file reader
        try {
            fileReader = new BufferedReader(new FileReader(clientFile));
        } catch (FileNotFoundException e) {
            System.err.println("clientFile not found...");
        }

        //Read the file line by line
        line = fileReader.readLine();

        //Read only the first line (The client names)
        String[] titles = line.split(DELIMITER);
        //length of the line
        int length = titles.length;
        //ArrayList of the stock prices
        ArrayList<Integer> stocks = null;

        for (String b : titles) {
            /*
            * b is the name of the clients,the object
            * array contains the ArrayList of stock values
            * the other two are the cash holdings and total.
             */
            clientMap.put(b, new Object[]{new ArrayList<>(), 0, 0});
        }
        int i = 0;

        while ((line = fileReader.readLine()) != null) {
            //Get all tokens available in line

            String[] tokens = line.split(DELIMITER);

            //compList.add(tokens);
            try {
                for (String b : titles) {
                    //parse the numbers after the headers
                    stocks = (ArrayList) ((Object[]) clientMap.get(b))[0];

                    stocks.add(Integer.parseInt(tokens[i]));

                    //if i == length -1 then reset i to 0,
                    //otherwise, incerement i by 1.
                    i = (i == length - 1) ? 0 : i + 1;

                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect file format.");
                System.exit(0);
            }

        }

        /*
        * Remove the last two entries into the ArrayList
        * These are the cash holdings and the stock total.
        * (Had to use this loop as there was an error when
        * trying to do this within the main while loop).
         */
        for (String b : titles) {
            stocks = (ArrayList) ((Object[]) clientMap.get(b))[0];
            ((Object[]) clientMap.get(b))[1] = stocks.remove(stocks.size() - 2) * 100;
            ((Object[]) clientMap.get(b))[2] = stocks.remove(stocks.size() - 1) * 100;

        }

        /*
        * Create Client objects.
         */
        Object[] tempClient;
        for (String b : titles) {
            tempClient = (Object[]) clientMap.get(b);
            clients.add(new Client(b, (int) tempClient[1], (int) tempClient[2]));
        }

        /*
        * Create Company objects.
         */
        ArrayList<Company> companies = new ArrayList<>();

        for (Object[] c : compList) {
            String name = (String) c[0];
            StockType d = StockType.valueOf((String) c[1]);
            //StockType d = StockType.valueOf((String) tempComp[0]);
            companies.add(new Company(name, d, (int) c[2], (int) c[3]));
        }

        /*
        * Create intelligentTrader object.
         */
        traderList.add(new IntelligentTrader());

        /*
        * Create randomTrader objects.
         */
        for (i = 1; i < traderCount; i++) {
            traderList.add(new RandomTrader());
        }

        /*
        * Create Portfolio objects.
         */
        Object[] tempClientDetails;
        int traderI = 0;
        for (Client c : clients) {
            tempClientDetails = (Object[]) (clientMap.get(c.getClientName()));

            //int randyTrad = ThreadLocalRandom.current().nextInt(1, traderCount + 1) - 1;
            Portfolio p = new Portfolio(c, companies, (ArrayList<Integer>) tempClientDetails[0], traderList.get(traderI));
            traderI = (traderI >= traderCount - 1) ? 1 : traderI + 1;
            portList.add(p);
        }

        /*
        * Create the Trading Exchange
         */
        tradingExchange = new TradingExchange(portList, companies);
        setup.setTradingExchange(tradingExchange);

        /*
        * UI Setup.
         */
        setup.setCompanies(companies);
        setup.setPortfolios(portList);

        setup.setVisible(true);
    }

    /**
     * Main method that runs the simulation.
     *
     * @param args The command line arguments.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Simulation s = new Simulation();

        s.simulate();
    }
}
