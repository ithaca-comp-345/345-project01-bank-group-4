package edu.ithaca.dragon.bank;

import java.util.HashMap;
import java.util.Map;

public class BankController {
    private Map<Integer, Account> accounts;

    public BankController() {
        accounts = new HashMap<>();
        // Adds default Accounts
        accounts.put(-3, new CheckingAccount(200));
        accounts.put(-2, new SavingsAccount(200, .1));
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public Account retrieveAccount(int accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            return account;
        }
    }

    public double checkBalance(int accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            return account.getBalance();
        }
    }

    public void withdraw(int accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            account.withdraw(amount);
        }
    }

    public void deposit(int accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            account.deposit(amount);
        }
    }

    public void transfer(int accountIdFrom, int accountIdTo, double amount) {
        Account accountFrom = accounts.get(accountIdFrom);
        Account accountTo = accounts.get(accountIdTo);
        if (accountFrom == null || accountTo == null) {
            throw new NullPointerException();
        } else {
            accountFrom.transfer(accountTo, amount);;
        }
    }

    public String retrieveTransactionHistory(int accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            return account.getTransactionHistory();
        }
    }

    public int createAccount(char accountType) {
        Account account;
        if (accountType == 'c') {
            account = new CheckingAccount(0);
        } else if (accountType == 's') {
            account = new SavingsAccount(0, 0);
        } else {
            throw new IllegalArgumentException();
        }
        int accountId = (int) (Math.random() * Integer.MAX_VALUE);
        accounts.put(accountId, account);
        return accountId;
    }

    public void closeAccount(int accountId) {
        accounts.remove(accountId);
    }

    public double checkOverallAmount() {
        double sum = 0;
        for (Account account : accounts.values()) {
            sum += account.getBalance();
        }
        return sum;
    }

    public String checkSuspiciousActivity() {
        String report = "";
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            Account account = entry.getValue();
            if (account.isSuspicious()) {
                report += entry.getKey() + " : " + account.getTransactionHistory() + "\n";
            }
        }
        return report;
    }

    public void setSuspicious(int accountId, boolean suspicious) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            account.setSuspicious(suspicious);
        }
    }

    public void setFrozen(int accountId, boolean frozen) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            account.setFrozen(frozen);
        }
    }
}
