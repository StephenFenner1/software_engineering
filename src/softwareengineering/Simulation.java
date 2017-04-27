
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketui;

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
 *
 * @author jh480
 */
public class Simulation {

    Map compMap;
    Map clientMap;

    Simulation() throws FileNotFoundException, IOException {
        initialSetup();

    }

    private void initialSetup() throws IOException {
        Setup setup = new Setup();
        File[] files = setup.getFile();
        int traderCount = setup.getTraderCount();
        File compFile = files[0];
        File clientFile = files[1];
        compMap = new HashMap();
        clientMap = new HashMap();
        Object[] tempArray = new Object[3];
        ArrayList<Object[]> compList = new ArrayList<>();

        BufferedReader fileReader = null;
        //Delimiter used in CSV file
        final String DELIMITER = ",";
        String line;
        //Create the file reader

        try {
            fileReader = new BufferedReader(new FileReader(compFile));
        } catch (FileNotFoundException e) {

        }
        //Read the file line by line
        while ((line = fileReader.readLine()) != null) {
            //Get all tokens available in line

            String[] tokens = line.split(DELIMITER);
            compList.add(new Object[]{tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])});

            //compMap.put(tokens[0], new Object[]{tokens[1], Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3])});
        }
        //System.out.println(((Object[])(compMap.get("WazooIt")))[0]);

//        for (String[] b : compList) {
//
//            compMap.put(b[0], new Object[]{b[1], Integer.parseInt(b[2]),Integer.parseInt(b[3])});
//
//        }
        try {
            fileReader = new BufferedReader(new FileReader(clientFile));
        } catch (FileNotFoundException e) {

        }

        line = fileReader.readLine();

        String[] titles = line.split(DELIMITER);
        int length = titles.length;
        ArrayList<Integer> fuckt = null;
        for (String b : titles) {

            clientMap.put(b, new Object[]{new ArrayList<>(), 0, 0});
        }
        int i = 0;

        while ((line = fileReader.readLine()) != null) {
            //Get all tokens available in line

            String[] tokens = line.split(DELIMITER);

            //compList.add(tokens);
            for (String b : titles) {

                fuckt = (ArrayList) ((Object[]) clientMap.get(b))[0];
                fuckt.add(Integer.parseInt(tokens[i]));

                i = (i == length - 1) ? 0 : i + 1;

            }

        }

        for (String b : titles) {
            fuckt = (ArrayList) ((Object[]) clientMap.get(b))[0];
            ((Object[]) clientMap.get(b))[1] = fuckt.remove(fuckt.size() - 2);
            ((Object[]) clientMap.get(b))[2] = fuckt.remove(fuckt.size() - 1);

        }

        ArrayList<Client> clients = new ArrayList<>();
        Object[] tempClient;
        for (String b : titles) {
            tempClient = (Object[]) clientMap.get(b);
            clients.add(new Client(b, (int) tempClient[1], (int) tempClient[2]));
        }

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
        

  
        ArrayList<Trader> traderList = new ArrayList<>();
        for (i = 0; i < traderCount; i++) {
            traderList.add(new RandomTrader());
        }
        
        

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
