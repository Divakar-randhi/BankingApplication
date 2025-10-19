package SimpleBankingApplicationBackend;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingApp {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize with a demo user
        User demoUser = new User("Divakar", "password123");
        demoUser.addAccount(new BankAccount("ACC001", "John Doe"));
        users.put("Divakar", demoUser);

        System.out.println("=== Welcome to Simple Banking App ===");
        boolean running = true;

        while (running) {
            System.out.println("\n1. Login\n2. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    running = false;
                    System.out.println("Thank you for using the app!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static void handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
            handleBankingMenu(user);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void handleBankingMenu(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\nBanking Menu:");
            System.out.println("1. View Accounts");
            System.out.println("2. Create New Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. View Transaction History");
            System.out.println("7. Logout");

            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    viewAccounts(user);
                    break;
                case 2:
                    createAccount(user);
                    break;
                case 3:
                    handleDeposit(user);
                    break;
                case 4:
                    handleWithdraw(user);
                    break;
                case 5:
                    handleTransfer(user);
                    break;
                case 6:
                    viewTransactionHistory(user);
                    break;
                case 7:
                    loggedIn = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void viewAccounts(User user) {
        System.out.println("\nYour Accounts:");
        for (BankAccount acc : user.getAccounts()) {
            System.out.println(acc);
        }
    }

    /**
     * Creates a new bank account for a user
     * @param user The user for whom the account is being created
     */
    private static void createAccount(User user) {
        // Prompt user to enter account number
        System.out.print("Enter account number: ");
        // Read account number from user input
        String accNum = scanner.nextLine();
        // Prompt user to enter owner name
        System.out.print("Enter owner name: ");
        // Read owner name from user input
        String name = scanner.nextLine();

        // Create a new BankAccount object with the provided details
        BankAccount newAcc = new BankAccount(accNum, name);
        // Add the newly created account to the user's account list
        user.addAccount(newAcc);
        // Display confirmation message with the created account details
        System.out.println("Account created: " + newAcc);
    }

    private static void handleDeposit(User user) {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount acc = findAccount(user, accNum);
        if (acc != null) {
            System.out.print("Enter deposit amount: ");
            double amount = getDoubleInput();
            if (acc.deposit(amount)) {
                System.out.println("Deposit successful. New balance: $" + acc.getBalance());
            } else {
                System.out.println("Invalid amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void handleWithdraw(User user) {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount acc = findAccount(user, accNum);
        if (acc != null) {
            System.out.print("Enter withdraw amount: ");
            double amount = getDoubleInput();
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: $" + acc.getBalance());
            } else {
                System.out.println("Insufficient funds or invalid amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void handleTransfer(User user) {
        System.out.print("From account number: ");
        String fromAccNum = scanner.nextLine();
        BankAccount fromAcc = findAccount(user, fromAccNum);
        if (fromAcc == null) {
            System.out.println("From account not found.");
            return;
        }

        System.out.print("To account number: ");
        String toAccNum = scanner.nextLine();
        BankAccount toAcc = findAccount(user, toAccNum);
        if (toAcc == null) {
            System.out.println("To account not found.");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = getDoubleInput();
        if (fromAcc.transfer(toAcc, amount)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Check balance.");
        }
    }

    private static void viewTransactionHistory(User user) {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        BankAccount acc = findAccount(user, accNum);
        if (acc != null) {
            System.out.println("\nTransaction History for " + accNum + ":");
            for (Transaction t : acc.getTransactionHistory()) {
                System.out.println(t);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static BankAccount findAccount(User user, String accNum) {
        for (BankAccount acc : user.getAccounts()) {
            if (acc.getAccountNumber().equals(accNum)) {
                return acc;
            }
        }
        return null;
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
}