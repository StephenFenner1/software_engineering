package softwareengineering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that handles the stock market simulation. The initialSetup method, reads the
 * input data, which is then used to create the Clients, Companies, Traders and 
 * Portfolios. The run method runs the simulation.
 */
public class Simulation {

    //Map compMap; 
    ArrayList<Object[]> compList; //The list that stores the company information, read in from the initialization data
    Map clientMap; //The map that stores the client information, read in from the initialization data

    /*
    * Constructor method for the Simulation class. Calls the relevant methods.
    */
    Simulation() throws FileNotFoundException, IOException {
        initialSetup();
    }

    /*
    * Method to setup the simulation. Reads two separate CSV files, one Client
    * and one Company, storing them in a Map and ArrayList respectively. The data
    * is then used to create and the client, company, portfolio and trader objects
    * needed to run the simulation.
    */
    private void initialSetup() throws IOException {
        Setup setup = new Setup();
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
        
        /*****************************
        ******************************
        *** Read the Company file. ***
        ******************************
        ******************************/
        
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
            compList.add(new Object[]{tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])});

            //compMap.put(tokens[0], new Object[]{tokens[1], Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3])});
        }
        /*System.out.println(((Object[])(compMap.get("WazooIt")))[0]);

            for (String[] b : compList) {
        
                compMap.put(b[0], new Object[]{b[1], Integer.parseInt(b[2]),Integer.parseInt(b[3])});
        
             }*/
        
        /****************************
        *****************************
        *** Read the Client file. ***
        *****************************
        *****************************/
        
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
            /*b is the name of the clients,the object
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
            ((Object[]) clientMap.get(b))[1] = stocks.remove(stocks.size() - 2);
            ((Object[]) clientMap.get(b))[2] = stocks.remove(stocks.size() - 1);

        }

        /*
        * Create Client objects.
        */
        ArrayList<Client> clients = new ArrayList<>();
        Object[] tempClient;
        for (String b : titles) {
            tempClient = (Object[]) clientMap.get(b);
            clients.add(new Client(b, (int) tempClient[1], (int) tempClient[2]));
        }

        /*
        * Create Company objects.
        */
        ArrayList<Company> companies = new ArrayList<>();
        Object[] tempComp;

        for (Object[] c : compList) {
            String name = (String) c[0];
            StockType d = StockType.valueOf((String) c[1]);
            //StockType d = StockType.valueOf((String) tempComp[0]);

            companies.add(new Company(name, d, (int) c[2], (int) c[3]));
        }

        ArrayList<String> compNames = new ArrayList<>();
        for (i = 0 ; i < companies.size(); i++) {
            compNames.add(companies.get(i).getCompanyName());
        }
        

        /*
        * Create Trader objects.
        */
        ArrayList<Trader> traderList = new ArrayList<>();
        for (i = 0; i < traderCount; i++) {
            traderList.add(new RandomTrader());
        }
        
        
        /*
        * Create Portfolio objects.
        */
        ArrayList<Portfolio> portList = new ArrayList<>();

        Object[] tempar;
        for (Client c : clients) {
            tempar = (Object[])(clientMap.get(c.getClientName()));
            int randyTrad = ThreadLocalRandom.current().nextInt(1, traderCount + 1)-1;
            Portfolio p = new Portfolio(c,compNames,(ArrayList<Integer>) tempar[0],traderList.get(randyTrad));
            p.printPortfolio();
            System.out.println("-----------------------------------------------------------------");
            portList.add(p);
                                
        }
    }
}
