package Project_Bank_Account_System;

//transaction record
class Transaction{
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final String description;
    private final java.time.LocalDateTime timestamp;

    //constructor
    public Transaction(String type, double amount, double balanceAfter, String description){
        this.type         = type;
        this.amount       = amount;
        this.balanceAfter = balanceAfter;
        this.description  = description;
        this.timestamp    = java.time.LocalDateTime.now();
    }
    public String toString() {
        return "Transaction Details:\n"
                + "  Time:        " + timestamp + "\n"
                + "  Type:        " + type + "\n"
                + "  Amount:      " + amount + "\n"
                + "  Balance:     " + balanceAfter + "\n"
                + "  Description: " + description;
    }
}