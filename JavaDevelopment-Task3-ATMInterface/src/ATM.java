import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;
    private Account currentAccount;

    public ATM(Bank bank) {

        this.bank = bank;
        scanner = new Scanner(System.in);
    }

    public void start() {

        displayWelcome();

        if (!login()) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       ACCESS DENIED");
            System.out.println("Too many incorrect login attempts.");
            System.out.println("Please try again later.");
            System.out.println("==========================================");

            return;
        }

        showMenu();
    }

    private void displayWelcome() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("           JAVA ATM MACHINE");
        System.out.println("==========================================");
        System.out.println("        Secure Banking System");
        System.out.println("==========================================");
    }

    private boolean login() {

        final int MAX_ATTEMPTS = 3;

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {

            System.out.println();
            System.out.println("---------- LOGIN ----------");

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine().trim();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            Account account = bank.findAccount(userId);

            if (account != null && account.verifyPin(pin)) {

                currentAccount = account;

                System.out.println();
                System.out.println("Login successful!");
                System.out.println("Welcome, " + currentAccount.getHolderName() + "!");

                return true;
            }

            int remaining = MAX_ATTEMPTS - attempt;

            System.out.println();
            System.out.println("Invalid User ID or PIN.");

            if (remaining > 0) {
                System.out.println("Attempts remaining: " + remaining);
            }
        }

        return false;
    }

    private void showMenu() {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("              MAIN MENU");
            System.out.println("==========================================");
            System.out.println("Account Holder : " + currentAccount.getHolderName());
            System.out.println("Account Number : " + currentAccount.getAccountNumber());
            System.out.printf("Balance        : Rs.%.2f%n",
                    currentAccount.getBalance());
            System.out.println("------------------------------------------");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("==========================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    quit();
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }
    }

    private void showTransactionHistory() {

        ArrayList<Transaction> transactions =
                currentAccount.getTransactions();

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                  TRANSACTION HISTORY");
        System.out.println("==============================================================");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.printf(
                "%-12s | %-12s | %-35s | %s%n",
                "TYPE",
                "AMOUNT",
                "DESCRIPTION",
                "DATE & TIME"
        );

        System.out.println("--------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("==============================================================");
    }

    private void withdraw() {

        System.out.println();
        System.out.println("---------- WITHDRAW ----------");

        double amount = readAmount("Enter withdrawal amount: Rs.");

        if (amount > currentAccount.getBalance()) {

            System.out.println();
            System.out.println("Insufficient Funds.");
            System.out.printf(
                    "Available Balance: Rs.%.2f%n",
                    currentAccount.getBalance()
            );

            return;
        }

        boolean success = currentAccount.withdraw(amount);

        if (success) {

            System.out.println();
            System.out.println("Withdrawal successful!");
            System.out.printf("Amount Withdrawn: Rs.%.2f%n", amount);
            System.out.printf(
                    "Remaining Balance: Rs.%.2f%n",
                    currentAccount.getBalance()
            );
        }
    }

    private void deposit() {

        System.out.println();
        System.out.println("---------- DEPOSIT ----------");

        double amount = readAmount("Enter deposit amount: Rs.");

        currentAccount.deposit(amount);

        System.out.println();
        System.out.println("Deposit successful!");
        System.out.printf("Amount Deposited: Rs.%.2f%n", amount);
        System.out.printf(
                "New Balance: Rs.%.2f%n",
                currentAccount.getBalance()
        );
    }

    private void transfer() {

        System.out.println();
        System.out.println("---------- TRANSFER ----------");

        System.out.print("Enter recipient account number: ");
        String recipientNumber = scanner.nextLine().trim();

        Account recipient =
                bank.findAccountByNumber(recipientNumber);

        if (recipient == null) {

            System.out.println();
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipient == currentAccount) {

            System.out.println();
            System.out.println("You cannot transfer money to your own account.");
            return;
        }

        double amount = readAmount("Enter transfer amount: Rs.");

        if (amount > currentAccount.getBalance()) {

            System.out.println();
            System.out.println("Insufficient Funds.");
            System.out.printf(
                    "Available Balance: Rs.%.2f%n",
                    currentAccount.getBalance()
            );

            return;
        }

        boolean success =
                currentAccount.transferTo(recipient, amount);

        if (success) {

            System.out.println();
            System.out.println("Transfer successful!");
            System.out.printf("Amount Transferred: Rs.%.2f%n", amount);
            System.out.println(
                    "Recipient: " + recipient.getHolderName()
            );
            System.out.println(
                    "Recipient Account: " + recipient.getAccountNumber()
            );

            System.out.printf(
                    "Remaining Balance: Rs.%.2f%n",
                    currentAccount.getBalance()
            );
        }
    }

    private double readAmount(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                double amount = Double.parseDouble(input);

                if (amount <= 0) {
                    System.out.println(
                            "Amount must be greater than zero."
                    );
                    continue;
                }

                return amount;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid amount. Please enter a valid number."
                );
            }
        }
    }

    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private void quit() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       THANK YOU FOR USING JAVA ATM");
        System.out.println("==========================================");
        System.out.println(
                "Goodbye, " + currentAccount.getHolderName() + "!"
        );
        System.out.println("Have a great day!");
        System.out.println("==========================================");
    }
}