import java.util.HashMap;

public class Bank {

    private HashMap<String, Account> accounts;

    public Bank() {

        accounts = new HashMap<>();

        // Demo Account 1
        Account account1 = new Account(
                "user123",
                "1234",
                "ACC1001",
                "Chirag",
                25000.00
        );

        // Demo Account 2
        Account account2 = new Account(
                "user456",
                "5678",
                "ACC1002",
                "Rahul",
                15000.00
        );

        accounts.put(account1.getUserId(), account1);
        accounts.put(account2.getUserId(), account2);
    }

    public Account findAccount(String userId) {
        return accounts.get(userId);
    }

    public Account findAccountByNumber(String accountNumber) {

        for (Account account : accounts.values()) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    public boolean authenticate(String userId, String pin) {

        Account account = findAccount(userId);

        if (account == null) {
            return false;
        }

        return account.verifyPin(pin);
    }
}