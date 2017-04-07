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
public class Client {
    
    private String clientName;
    private int cashHolding;
    private int initialValue;
    private int currentValue;
    private int expectedValue;
    private int ID;
    boolean cashingOut;
    
    public Client(String fileName) {
    
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getCashHolding() {
        return cashHolding;
    }

    public void setCashHolding(int cashHolding) {
        this.cashHolding = cashHolding;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(int expectedValue) {
        this.expectedValue = expectedValue;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isCashingOut() {
        return cashingOut;
    }

    public void setCashingOut(boolean cashingOut) {
        this.cashingOut = cashingOut;
    }
    
    
}
