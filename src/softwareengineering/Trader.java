/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering;

import java.util.List;

/**
 *
 * @author Josh Hasan
 */
public abstract class Trader {
    
    public List<Portfolio> portfolios;
    
    public int requestTrade() {
        return -1;
    }
    
    public void makeTrade(int stocks) {
        
    }
}
