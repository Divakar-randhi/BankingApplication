package SimpleBankingApplicationBackend;

import java.util.ArrayList;
import java.util.List;

class User {
    private String username;
    private String password;
    private List<BankAccount> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    // Add an account
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    // Get accounts
    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }

    // Getters
    public String getUsername() { return username; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
}
