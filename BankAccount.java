package SimpleBankingApplicationBackend;
import java.util.ArrayList;
import java.util.List;


class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    // Deposit money
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(TransactionType.DEPOSIT.name(), amount, "Deposit to " + accountNumber));
            return true;
        }
        return false;
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction(TransactionType.WITHDRAW.name(), amount, "Withdrawal from " + accountNumber));
            return true;
        }
        return false;
    }

    // Transfer money to another account
    public boolean transfer(BankAccount toAccount, double amount) {
        if (withdraw(amount)) {
            toAccount.deposit(amount);
            transactions.add(new Transaction(TransactionType.TRANSFER.name(), amount, "Transfer to " + toAccount.getAccountNumber()));
            return true;
        }
        return false;
    }

    // Get transaction history
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactions); // Return a copy
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return String.format("Account: %s (%s) - Balance: $%.2f", accountNumber, ownerName, balance);
    }
}
