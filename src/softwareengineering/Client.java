package softwareengineering;

/**
 * Storage class for client data. Stores the name, value and cash holding of a
 * client and whether or not the client is cashing out.
 * 
 * @author Jamie Critcher
 */
public class Client {

    private final String clientName;
    private final int initialValue;
    private final int expectedValue;
    private int cashHolding;
    private int currentValue;
    private boolean cashingOut;

    /**
     * Constructor for Client class. Initialises a single client data class
     * given basic information about a client.
     * 
     * @param clientName    The name of the client.
     * @param cashHolding   The initials cash holding of the client.
     * @param initialValue  The initial total value of the client.
     */
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

    /**
     * Setter method to set the current cash holding of a client to a new value.
     * 
     * @param cashHolding   New cash holding value.
     */
    public void setCashHolding(int cashHolding) {
        this.cashHolding = cashHolding;
    }

    /**
     * Setter method to set the current total value of a client to a new value.
     * 
     * @param currentValue  New current total value.
     */
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Setter method to set whether or not the client is cashing out.
     * 
     * @param cashingOut    Whether or not the client is cashing out.
     */
    public void setCashingOut(boolean cashingOut) {
        this.cashingOut = cashingOut;
    }

    /**
     * Getter method to retrieve the stored name of the client.
     * 
     * @return The name of the client.
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Getter method to retrieve the current cash holding of the client.
     * 
     * @return The cash holding of the client.
     */
    public int getCashHolding() {
        return cashHolding;
    }

    /**
     * Getter method to retrieve the initial value of the client.
     * 
     * @return The initial value of the client.
     */
    public int getInitialValue() {
        return initialValue;
    }

    /**
     * Getter method to return the current value of the client.
     * 
     * @return The current value of the client.
     */
    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Getter method to retrieve the value the client expects in returns.
     * 
     * @return The value the client expects in returns.
     */
    public int getExpectedValue() {
        return expectedValue;
    }

    /**
     * Getter method to retrieve whether or not the client wants to cash out.
     * 
     * @return Whether or not the client wants to cash out.
     */
    public boolean isCashingOut() {
        return cashingOut;
    }
}
