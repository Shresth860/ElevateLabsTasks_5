import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

// Transaction Class
class Transaction {
    private String transactionId;
    private String type; // Deposit or Withdraw
    private double amount;
    private Date date;
    private double balanceAfterTransaction;

    public Transaction(String type, double amount, double balanceAfterTransaction) {
        this.transactionId = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.date = new Date();
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                "\nType: " + type +
                "\nAmount: " + amount +
                "\nDate: " + date +
                "\nBalance After Transaction: " + balanceAfterTransaction +
                "\n----------------------------";
    }
}


// Bank Account Class
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    private List<Transaction> transactionHistory;

    public BankAccount(String accountHolderName, double initialBalance) {
        this.accountNumber = UUID.randomUUID().toString();
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    // Deposit Money
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Amount must be greater than 0");
            return;
        }
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount, balance));
        System.out.println("✅ Successfully deposited ₹" + amount);
    }

    // Withdraw Money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount");
            return;
        }
        if (amount > balance) {
            System.out.println("❌ Insufficient balance!");
            return;
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Withdraw", amount, balance));
        System.out.println("✅ Successfully withdrawn ₹" + amount);
    }

    // Show Balance
    public double getBalance() {
        return balance;
    }

    // Show Transaction History
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("📭 No transactions found.");
            return;
        }
        System.out.println("\n📜 Transaction History:");
        for (Transaction t : transactionHistory) {
            System.out.println(t);
        }
    }

    // Show Account Details
    public void showAccountDetails() {
        System.out.println("\n🏦 Account Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: ₹" + balance);
    }
}


// Main Simulation Class
public class BankSimulation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        BankAccount account = new BankAccount(name, balance);

        int choice;
        do {
            System.out.println("\n====== BANK MENU ======");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double d = sc.nextDouble();
                    account.deposit(d);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    account.withdraw(w);
                    break;

                case 3:
                    System.out.println("💰 Current Balance: ₹" + account.getBalance());
                    break;

                case 4:
                    account.showTransactionHistory();
                    break;

                case 5:
                    account.showAccountDetails();
                    break;

                case 6:
                    System.out.println("👋 Exiting... Thank you for using the Bank System.");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again!");
            }
        } while (choice != 6);
    }
}
