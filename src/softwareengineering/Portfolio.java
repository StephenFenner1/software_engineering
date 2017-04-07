/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author stf23
 */
public class Portfolio {
    
    private Map stockOwned; //Map(companyName, stockValue)
    private Client client; //the client    
    
    public Portfolio(Client client, String[] companyName, Integer[] stocks) {
        this.client = client;
        List<String> companyNames = new ArrayList<String>(Arrays.asList(companyName)); //turn companyName into list
        List<Integer> stockPrice = new ArrayList<Integer>(Arrays.asList(stocks)); ////turn stocks into list
        
        //both lists have to be the same size
        if(companyNames.size() == stockPrice.size()) {
            //concat lists into map with key = companyName, value = stockValue;
            for(int i = 0; i < companyNames.size(); i++) {
                stockOwned.put(companyNames.get(i), stockPrice.get(i));
            }
        }
    }    

    public Client getClient() {
        return client;
    }

    public Map getStockOwned() {
        return stockOwned;
    }
}
