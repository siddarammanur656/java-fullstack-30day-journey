package Project_Bank_Account_System;

// ── Main Demo
public class BankSystem {
    public static void main(String[] args) {

        System.out.println("🏦 BANK SYSTEM STARTED");
        System.out.println("Total accounts: " + BankAccount.getTotalAccounts()); // should be 0

        // ── Create accounts
        BankAccount alice = new BankAccount("Alice Smith", "Premium", 5000.00);
        BankAccount bob   = new BankAccount("Bob Jones",   "Savings", 1200.00);
        BankAccount carol = new BankAccount("Carol White", 0.00);

        System.out.println("Total accounts: " + BankAccount.getTotalAccounts()); // should be 3

        // ── Alice’s operations
        alice.deposit(2000.00, "Salary payment");
        alice.withdraw(500.00, "Rent");
        alice.withdraw(150.00, "Groceries");

        // ── Bob’s operations
        bob.deposit(300.00, "Freelance payment");
        bob.withdraw(200.00, "Utility bill");

        // ── Transfer
        alice.transfer(carol, 1000.00);

        // ── Print statements
        alice.printStatement();
        bob.printStatement();
        carol.printStatement();

        // ── Freeze demo
        System.out.println("\n🔒 Freezing Bob's account...");
        bob.freeze();
        try {
            bob.withdraw(100.00);
        } catch (IllegalStateException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        // ── Premium overdraft demo
        System.out.println("\n💳 Testing Premium overdraft...");
        alice.withdraw(alice.getBalance() + 300, "Emergency expense");
        System.out.printf("Alice balance after overdraft: $%.2f%n", alice.getBalance());

        // ── Insufficient funds demo
        System.out.println("\n❌ Testing insufficient funds...");
        try {
            carol.withdraw(5000.00);
        } catch (IllegalStateException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        // ── Bank summary
        System.out.println("\n📊 BANK SUMMARY");
        System.out.println("═".repeat(40));
        System.out.printf("  Total Accounts:  %d%n", BankAccount.getTotalAccounts());
        System.out.printf("  Total Deposits:  $%.2f%n", BankAccount.getTotalBankDeposits());

        // ── equals & hashCode demo
        System.out.println("\n🔍 EQUALS DEMO");
        BankAccount ref = alice; // same reference
        System.out.println("alice == ref: " + (alice == ref));       // true
        System.out.println("alice.equals(ref): " + alice.equals(ref)); // true
        System.out.println("alice.equals(bob): " + alice.equals(bob)); // false
    }
}