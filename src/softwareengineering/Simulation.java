package softwareengineering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class that handles the stock market simulation. The initialSetup method,
 * reads the input data, which is then used to create the Clients, Companies,
 * Traders and Portfolios. The run method runs the simulation.
 * 
 * @author Group 26
 */
public class Simulation {

    //Create Portfolio objects.
    private final ArrayList<Portfolio> portList = new ArrayList<>();

    // Create Trader objects.
    private final ArrayList<Trader> traderList = new ArrayList<>();

    // Create Client objects.
    private final ArrayList<Client> clients = new ArrayList<>();
    private final ArrayList<Company> companies = new ArrayList<>();

    // Create the Trading Exchange
    private TradingExchange tradingExchange;

    Setup setup;
    final String DELIMITER = ",";

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
        Boolean validFiles = false;
        while (!validFiles) {

            try {
                initialSetup();
                validFiles = true;
            } catch (java.lang.ArrayIndexOutOfBoundsException | java.lang.NumberFormatException e) {
                //Do nothing
                setup.displayFileError();

            }
        }
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
        int currentMarketState = 0;
        int marketStateCount = 0;
        int runningMarketState = 0;

        boolean isClosed;
        Random rand = new Random();

        //Sets up the date and time format used for display date/time
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse("01/01/2017"); //Initialise date to first day of year
        } catch (ParseException e) {
            //will never happen
        }

        Calendar calender = Calendar.getInstance();
        calender.setTime(date); // Now use today date.

        while (day <= 365) {
            //Loop through each day of the year
            
            
            //Sets and displays the date and time as appropriate
            lt = LocalTime.parse("09:00");
            setup.setDateLabel(sdf.format(calender.getTime())); 
            setup.setTimeLabel(df.format(lt.plusMinutes(15 * (time - 1))));

            isClosed = setup.isPaused(); //Whether the simulation should be paused
            while (isClosed) {
                //Check if simulation should be paused continuously.
                isClosed = setup.isPaused();
            }

            
            
            /*
            Checks whether the stock market should be closed on the current day.
            */
            closed = false; 
            if (day == sun) {
                //Sunday
                closed = true;
                sun += 7;
            } else if (day == sat) {
                //Saturday
                closed = true;
                sat += 7;
            } else if (day == goodFri || day == easterMon || day == christmas || day == boxing) {
                //Good Friday/Easter Monday/Christmas/Boxing Day
                closed = true;
            }

            /*
            Updates the each client's risk approx. every month
            */
            if (day == riskChange) {
                for (Portfolio portfolio : portList) {
                    portfolio.updateClientRisk();
                }
                riskChange += 30;
            }

            if (!closed) {
                //The market open 

                while (time <= 28) {
                    //15 minute iterations

                    setup.setTimeLabel(df.format(lt.plusMinutes(15 * (time - 1)))); //display new time on GUI

                    tradingExchange.handleTrades(setup, day, time); //Check and make valid trades
                    for (Portfolio portfolio : portList) {
                        //Update each portfolio as trades may have occurred
                        portfolio.updatePortfolio();
                    }
                    time++;
                    for (Trader trader : traderList) {
                        //Update each trader's money made. Used for GUI info
                        trader.updateMoneyMade(portList);

                    }

                    isClosed = setup.isPaused();
                    while (isClosed) {
                        //Check whether simulation is paused to allow pause after 15 mins
                        isClosed = setup.isPaused();
                    }
                    try {
                        //Sets the simulation speed using the slider speed value.
                        Thread.sleep(setup.getDelay());
                    } catch (InterruptedException e) {
                    }

                    /*

                     */
                }
                time = 1; //Reset time for the next day
                

            } else {
                //The market is closed
                //Display appropriate messages.
                String message = "\n-------------------- Day: " + day + " --------------------\n";
                switch (day) {
                    case 104:
                        message += "Trading exchange closed for Good Friday.\n";
                    case 107:
                        message += "Trading exchange closed for Easter Monday.\n";
                    case 359:
                        message += "Trading exchange closed for Christmas Day.\n";
                    case 360:
                        message += "Trading exchange closed for Boxing Day.\n";
                    default:
                        message += "Trading exchange closed.\n";
                }
                setup.printMessage(message);

            }

            for (Trader trader : traderList) {
                //Changes the mood of the traders
                if (trader instanceof RandomTrader) {
                    ((RandomTrader) trader).changeMood();
                }
            }

            for (Client client : clients) {
                //Implements a chance for client to cash out
                if (0 == rand.nextInt(4000)) {
                    client.setCashingOut(true);
                }
            }

            try {
                //Sets the simulation speed using the slider speed value.
                Thread.sleep(setup.getDelay());
            } catch (InterruptedException e) {
            }

            day++; //end of day so increment to next day

            calender.add(Calendar.DATE, 1); //Adds a day for the GUI variable

            if (!closed) {
                //Used to update the market state and display it on GUI if trading exchange is open
                currentMarketState = tradingExchange.updateMarketState();
                if (currentMarketState > 0 && runningMarketState > 0) {
                    //Both bull
                    marketStateCount++;
                    
                } else if (currentMarketState < 0 && runningMarketState < 0) {
                    //Both bear
                    marketStateCount++;
                    
                } else if (currentMarketState == 0 && runningMarketState == 0) {
                    //Both stable
                    marketStateCount++;
                    
                } else {
                    //Different types
                    marketStateCount = 0;

                }

                if (marketStateCount == 3) {
                    if (currentMarketState > 0) {
                        //If current market continues to be bull
                        tradingExchange.setMarketType(MarketType.Bull);
                    } else if (currentMarketState < 0) {
                        //If current market continues to be bear
                        tradingExchange.setMarketType(MarketType.Bear);
                    } else {
                        //Otherwise set market back to stable
                        tradingExchange.setMarketType(MarketType.Stable);
                    }
                }

                if (marketStateCount > 3) {
                    //If market has been bear/bull for 3+ days 
                    //Set to stable if the state is no longer BEAR/BULL
                    if (currentMarketState < runningMarketState && runningMarketState > 0) {
                        //Current state from BULL to STABLE/BEAR
                        tradingExchange.setMarketType(MarketType.Stable);
                        marketStateCount = 0;

                    } else if (currentMarketState > runningMarketState && runningMarketState < 0) {
                        //Current state from BEAR to STABLE/BULL
                        tradingExchange.setMarketType(MarketType.Stable);
                        marketStateCount = 0;
                    } else if (currentMarketState!=0 && runningMarketState == 0){
                        marketStateCount=0;  
                        tradingExchange.setMarketType(MarketType.Stable);
                    }

                }

                if (marketStateCount == 0) {
                    //Initialises the first day of STABLE to check for new market state.
                    runningMarketState = currentMarketState;
                }

            }
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
        makeCompanies(compFile);
        makeClients(clientFile);
        assignTraders(traderCount);
        makePortfolios(traderCount);
        tradingExchange = new TradingExchange(portList, companies);
        setup.setTradingExchange(tradingExchange);
        setup.setCompanies(companies);
        setup.setPortfolios(portList);
        setup.setTraders(traderList);
        setup.setVisible(true);

    }

    /**
     * Creates all the companies
     * @param compFile the company file chosen by user
     * @throws IOException 
     */
    private void makeCompanies(File compFile) throws IOException {
        String line;
        BufferedReader fileReader = null;
        compList = new ArrayList<>();
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
            compList.add(new Object[]{tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])});

            //compMap.put(tokens[0], new Object[]{tokens[1], Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3])});
        }

        for (Object[] c : compList) {
            String name = (String) c[0];
            StockType d = StockType.valueOf((String) c[1]);
            //StockType d = StockType.valueOf((String) tempComp[0]);
            companies.add(new Company(name, d, (int) c[2], (int) c[3]));
        }

    }
    
    /**
     * Makes the clients using client file provided.
     * @param clientFile the client file chosen by user
     * @throws IOException 
     */
    private void makeClients(File clientFile) throws IOException {
        BufferedReader fileReader = null;
        clientMap = new HashMap();
        String line;
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
            for (String b : titles) {
                //parse the numbers after the headers
                stocks = (ArrayList) ((Object[]) clientMap.get(b))[0];
                stocks.add(Integer.parseInt(tokens[i]));

                //if i == length -1 then reset i to 0,
                //otherwise, incerement i by 1.
                i = (i == length - 1) ? 0 : i + 1;
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
        Object[] tempClient;
        for (String b : titles) {
            tempClient = (Object[]) clientMap.get(b);
            clients.add(new Client(b, (int) tempClient[1], (int) tempClient[2]));
        }
        Collections.shuffle(clients);

    }

    /**
     * Randomly assigns traders to clients
     * @param traderCount the amount of traders chosen by user
     */
    private void assignTraders(int traderCount) {
        traderList.add(new IntelligentTrader()); // Create intelligentTrader object.

        //Assign random traders to clients
        for (int i = 1; i < traderCount; i++) {
            traderList.add(new RandomTrader());
        }
    }

    /**
     * Creates the portfolios for all clients
     * @param traderCount the amount of traders specified
     */
    private void makePortfolios(int traderCount) {
        Object[] clientDetails;
        int traderI = 0;
        for (Client c : clients) {
            clientDetails = (Object[]) (clientMap.get(c.getClientName()));
            Portfolio p = new Portfolio(c, companies, (ArrayList<Integer>) clientDetails[0], traderList.get(traderI));
            traderI = (traderI >= traderCount - 1) ? 1 : traderI + 1;

            portList.add(p);

        }
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
