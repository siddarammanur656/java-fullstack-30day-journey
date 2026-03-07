package Project_Bank_Account_System;

import java.util.ArrayList;
import java.util.List;

//Bank Account
class BankAccount {
    // Static — shared across all accounts
    private static int    nextAccountNumber = 1000;
    private static int    totalAccounts     = 0;
    private static double totalBankDeposits = 0;

    // Instance — unique to each account
    private final String accountNumber;
    private       String ownerName;
    private       double balance;
    private final String accountType;
    private       boolean frozen;
    private       double  overdraftLimit;
    private final List<Transaction> history = new ArrayList<>();

    //constructor
    public BankAccount(String ownerName, String accountType, double initialDeposit){
        validateName(ownerName);
        validateAmount(initialDeposit);

        this.accountNumber  = "ACC" + (++nextAccountNumber);
        this.ownerName      = ownerName;
        this.accountType    = accountType;
        this.balance        = 0;
        this.frozen         = false;
        this.overdraftLimit = accountType.equals("Premium") ? 500.0 : 0.0;

        totalAccounts++;

        // Record initial deposit via deposit method
        if (initialDeposit > 0) {
            deposit(initialDeposit, "Initial deposit");
        }
    }

    // Convenience constructor — default to Savings account
    public BankAccount(String ownerName, double initialDeposit) {
        this(ownerName, "Savings", initialDeposit);
    }

    // ── Validation Helpers (private)
    //ensures owner name is valid
    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Owner name is required");
    }

    //ensures no negative amounts
    private void validateAmount(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
    }

    private void checkFrozen() {
        if (frozen)
            throw new IllegalStateException("Account " + accountNumber + " is frozen");
    }

    //Core Operations
    public void deposit(double amount, String description) {
        checkFrozen();
        validateAmount(amount);
        if (amount == 0)
            throw new IllegalArgumentException("Deposit amount must be greater than 0");

        balance += amount;
        totalBankDeposits += amount;
        history.add(new Transaction("DEPOSIT", amount, balance, description));
    }

    public void deposit(double amount) {
        deposit(amount, "Deposit");
    }

    public void withdraw(double amount, String description) {
        if (frozen) {
            throw new IllegalStateException("Account " + accountNumber + " is frozen");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
        double availableFunds = balance + overdraftLimit;

        if (amount > availableFunds) {
            throw new IllegalStateException(
                    "Insufficient funds. Available: $" + availableFunds +
                            " (including overdraft: $" + overdraftLimit + ")"
            );
        }

        balance = balance - amount;
        Transaction withdrawal = new Transaction("WITHDRAW", -amount, balance, description);
        history.add(withdrawal);
    }

    public void withdraw(double amount) {
        withdraw(amount, "Withdrawal");
    }

    public void transfer(BankAccount target, double amount) {
        if (target == null)
            throw new IllegalArgumentException("Target account cannot be null");
        if (this == target)
            throw new IllegalArgumentException("Cannot transfer to same account");

        String desc = "Transfer to " + target.accountNumber;
        withdraw(amount, desc);
        target.deposit(amount, "Transfer from " + this.accountNumber);
    }

    // ── Freeze/Unfreeze
    public void freeze()   {
        this.frozen = true;
        history.add(new Transaction("FREEZE", 0, balance, "Account frozen"));
    }

    public void unfreeze() {
        this.frozen = false;
        history.add(new Transaction("UNFREEZE", 0, balance, "Account unfrozen"));
    }

    // ── Statement
    public void printStatement() {
        String divider = "─".repeat(75);
        System.out.println("\n" + "═".repeat(75));
        System.out.printf("  ACCOUNT STATEMENT: %s (%s)%n", accountNumber, accountType);
        System.out.println("═".repeat(75));
        System.out.printf("  Owner:    %s%n", ownerName);
        System.out.printf("  Balance:  $%.2f%s%n", balance, balance < 0 ? " ⚠ OVERDRAWN" : "");
        System.out.printf("  Status:   %s%n", frozen ? "🔒 FROZEN" : "✅ Active");
        System.out.println(divider);
        System.out.println("  TRANSACTION HISTORY:");
        System.out.println(divider);
        if (history.isEmpty()) {
            System.out.println("  No transactions yet.");
        } else {
            for (Transaction t : history) System.out.println(t);
        }
        System.out.println("═".repeat(75));
    }
    // ── Static Methods
    public static int    getTotalAccounts(){
        return totalAccounts;
    }
    public static double getTotalBankDeposits(){
        return totalBankDeposits;
    }
    // ── Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getOwnerName(){
        return ownerName;
    }
    public double getBalance(){
        return balance;
    }
    public String getAccountType(){
        return accountType;
    }
    public boolean isFrozen(){
        return frozen;
    }

    // ── Setter (only name is changeable)
    public void setOwnerName(String name) {
        validateName(name);
        this.ownerName = name;
    }


    // ── equals, hashCode, toString
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankAccount other = (BankAccount) obj;
        return this.accountNumber.equals(other.accountNumber);
    }

    public int hashCode() {
        return java.util.Objects.hash(accountNumber);
    }

    public String toString() {
        return "BankAccount Details:\n"
                + "  Account Number: " + accountNumber + "\n"
                + "  Owner:          " + ownerName + "\n"
                + "  Type:           " + accountType + "\n"
                + "  Balance:        $" + String.format("%.2f", balance) + "\n"
                + "  Frozen:         " + frozen;
    }
}