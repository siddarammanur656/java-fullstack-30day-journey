//Without encapsulation — dangerous:
//class BankAccount {
//    double balance; // public by default — anyone can change it!
//}
//
//BankAccount acc = new BankAccount();
//acc.balance = -999999; // NOTHING stops this!
//acc.balance = Double.NaN; // or this!


//With encapsulation — safe:
class BankAccountEncapsulation {
    private double balance; // hidden from outside

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Must be positive");
        balance += amount; // only valid change allowed
    }
}