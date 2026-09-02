import java.util.ArrayList;

public class Account {

    private String userId;
    private String pin;
    private String accountNumber;
    private String holderName;
    private double balance;

    private ArrayList<Transaction> transactions;

    public Account(String userId, String pin, String accountNumber,
                   String holderName, double initialBalance) {

        this.userId = userId;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;

        transactions = new ArrayList<>();

        transactions.add(
                new Transaction(
                        "OPENING",
                        initialBalance,
                        "Opening balance"
                )
        );
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            return;
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposited"
                )
        );
    }

    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(
                new Transaction(
                        "WITHDRAW",
                        amount,
                        "Cash withdrawn"
                )
        );

        return true;
    }

    public boolean transferTo(Account recipient, double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        recipient.balance += amount;

        transactions.add(
                new Transaction(
                        "TRANSFER",
                        amount,
                        "Transfer to " + recipient.getAccountNumber()
                )
        );

        recipient.transactions.add(
                new Transaction(
                        "RECEIVED",
                        amount,
                        "Transfer from " + this.accountNumber
                )
        );

        return true;
    }

    public void displayBalance() {
        System.out.printf("Current Balance: Rs.%.2f%n", balance);
    }
}