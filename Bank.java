import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public boolean createAccount(String username, String password, double initialBalance) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists. Please choose another one.");
            return false;
        }
        accounts.put(username, new Account(username, password, initialBalance));
        System.out.println("Account created successfully.");
        return true;
    }

    public boolean login(String username, String password) {
        if (accounts.containsKey(username) && accounts.get(username).getPassword().equals(password)) {
            System.out.println("Login successful.");
            return true;
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void checkBalance(String username) {
        if (accounts.containsKey(username)) {
            System.out.println("Balance for " + username + ": $" + accounts.get(username).getBalance());
        } else {
            System.out.println("User not found.");
        }
    }

    public void deposit(String username, double amount) {
        if (accounts.containsKey(username)) {
            accounts.get(username).deposit(amount);
            System.out.println("Deposit of $" + amount + " successful.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void withdraw(String username, double amount) {
        if (accounts.containsKey(username)) {
            if (accounts.get(username).getBalance() >= amount) {
                accounts.get(username).withdraw(amount);
                System.out.println("Withdrawal of $" + amount + " successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}

class Account {
    private String username;
    private String password;
    private double balance;

    public Account(String username, String password, double initialBalance) {
        this.username = username;
        this.password = password;
        this.balance = initialBalance;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createAccount("user1", "password123", 100);
        bank.createAccount("user2", "abc123", 0);

        bank.login("user1", "password123");
        bank.checkBalance("user1");
        bank.deposit("user1", 50);
        bank.checkBalance("user1");
        bank.withdraw("user1", 30);
        bank.checkBalance("user1");

        bank.login("user3", "password123"); // Invalid login
    }
}