package softwareengineering;

/**
 *
 */
public class Client {
    
    private String clientName;
    private int cashHolding;
    private int initialValue;
    private int currentValue;
    private int expectedValue;
    private boolean cashingOut;
    
    public Client(String clientName, int cashHolding, int initialValue) {
        // Initialise clientName, cashHolding and initialValue to given values.
        this.clientName = clientName;
        this.cashHolding = cashHolding;
        this.initialValue = initialValue;
        // Initialise currentValue to match initialValue.
        this.currentValue = initialValue;
        // Set expectedValue to a 10% return.
        this.expectedValue = (int)(initialValue * 1.1);
        // Initialise cashingOut as false.
        this.cashingOut = false;
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

    public boolean isCashingOut() {
        return cashingOut;
    }

    public void setCashingOut(boolean cashingOut) {
        this.cashingOut = cashingOut;
    }
}
