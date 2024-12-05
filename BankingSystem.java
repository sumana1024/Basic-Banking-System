import java.util.ArrayList;
import java.util.Scanner;

// Base class representing a Bank Account
class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactions;

    // Constructor
    public BankAccount(String name, String accountNumber) {
        this.accountHolderName = name;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: $0.0");
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
        } else {
            balance += amount;
            transactions.add("Deposited: $" + amount);
            System.out.println("Deposited $" + amount + " successfully!");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            transactions.add("Withdrew: $" + amount);
            System.out.println("Withdrew $" + amount + " successfully!");
        }
    }

    // Check balance
    public double getBalance() {
        return balance;
    }

    // View transaction history
    public void printTransactions() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Get account information
    public void printAccountInfo() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

// Main class
public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        System.out.println("Welcome to the Banking System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Create account
                    System.out.print("Enter Account Holder Name: ");
                    scanner.nextLine(); // Consume the newline
                    String name = scanner.nextLine();
                    String accountNumber = "ACC" + (accounts.size() + 1);
                    BankAccount newAccount = new BankAccount(name, accountNumber);
                    accounts.add(newAccount);
                    System.out.println("Account created successfully! Your account number is: " + accountNumber);
                    break;

                case 2: // Deposit
                    System.out.print("Enter Account Number: ");
                    String depositAccountNumber = scanner.next();
                    BankAccount depositAccount = findAccount(accounts, depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = scanner.nextDouble();
                        depositAccount.deposit(amount);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3: // Withdraw
                    System.out.print("Enter Account Number: ");
                    String withdrawAccountNumber = scanner.next();
                    BankAccount withdrawAccount = findAccount(accounts, withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        withdrawAccount.withdraw(amount);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4: // Check balance
                    System.out.print("Enter Account Number: ");
                    String balanceAccountNumber = scanner.next();
                    BankAccount balanceAccount = findAccount(accounts, balanceAccountNumber);
                    if (balanceAccount != null) {
                        System.out.println("Your balance is: $" + balanceAccount.getBalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 5: // View transactions
                    System.out.print("Enter Account Number: ");
                    String transactionAccountNumber = scanner.next();
                    BankAccount transactionAccount = findAccount(accounts, transactionAccountNumber);
                    if (transactionAccount != null) {
                        transactionAccount.printTransactions();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 6: // Exit
                    System.out.println("Thank you for using the Banking System!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper method to find an account by account number
    private static BankAccount findAccount(ArrayList<BankAccount> accounts, String accountNumber) {
        for (BankAccount account : accounts) {
            if (accountNumber.equals(account.getAccountNumber())) {
                return account;
            }
        }
        return null;
    }
}