/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering;

/**
 *
 * @author stf23
 */
public class RandomTrader extends Trader{
    private String mood;
    
    public RandomTrader(String mood) {
        this.mood = mood;
    }
    
    public String getMood() {
        return mood;
    }
    
    public void setMood(String mood) {
        this.mood = mood;
    }
}
