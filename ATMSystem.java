import java.util.*;
 
class User { // Renamed the class to start with an uppercase letter

    private final String userId;
    private final String userPin;

    public User(String userId, String userPin) { // Renamed the constructor parameters to start with a lowercase letter
        this.userId = userId;
        this.userPin = userPin;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }
}

class Transaction {
    private final String type;
    private final double amount;
    private final String timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = generateTimestamp();
    }

    private String generateTimestamp() {
        return new Date().toString();
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount + ", Timestamp: " + timestamp;
    }
}

class BankAccount {
    private double balance;
    private final List<Transaction> transactionsHistory;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactionsHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionsHistory.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionsHistory.add(new Transaction("Withdraw", amount)); // Corrected the transaction type to "Withdraw"
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public List<Transaction> getTransactionsHistory() {
        return transactionsHistory;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        User user = new User("user123", "1234"); // Renamed the object to start with an uppercase letter
        BankAccount account = new BankAccount(1000.0);

        System.out.print("Enter user ID: ");
        String userId = sc.nextLine();

        System.out.print("Enter user pin: "); // Removed the newline character
        String userPin = sc.nextLine();

        if (userId.equals(user.getUserId()) && userPin.equals(user.getUserPin())) {
            performATMOperations(sc, account); // Call the ATM operations function
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    public static void performATMOperations(Scanner sc, BankAccount account) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume new line

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdraw amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    List<Transaction> transactions = account.getTransactionsHistory();
                    System.out.println("\nTransaction History:");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}