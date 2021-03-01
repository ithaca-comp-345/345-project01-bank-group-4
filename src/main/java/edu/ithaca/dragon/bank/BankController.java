package edu.ithaca.dragon.bank;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
 
public class BankController {
    private Map<Integer, Account> accounts;
 
    public BankController() {
        accounts = new HashMap<>();
    }
 
    public Map<Integer, Account> getAccounts() {
        return accounts;
    }
 
    public Account retrieveAccount(int accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new NullPointerException();
        }
        return accounts.get(accountId);
    }
 
    public double checkBalance(int accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new NullPointerException();
        }
        return accounts.get(accountId).getBalance();
    }
 
    public void withdraw(int accountId, double amount) {
        if (accounts.get(accountId).isFrozen() == true) {
            throw new IllegalArgumentException();
        }
        accounts.get(accountId).withdraw(amount);
    }
 
    public void deposit(int accountId, double amount) {
        if (accounts.get(accountId).isFrozen()) {
            throw new IllegalArgumentException();
        }
        accounts.get(accountId).deposit(amount);
    }
 
    public void transfer(int accountIdFrom, int accountIdTo, double amount) {
        if (accounts.get(accountIdFrom).isFrozen() || accounts.get(accountIdTo).isFrozen() || amount < 0 || BigDecimal.valueOf(amount).scale() > 2) {
            throw new IllegalArgumentException();
        }
        accounts.get(accountIdFrom).withdraw(amount);
        accounts.get(accountIdTo).deposit(amount);
    }
 
    public String retrieveTransactionHistory(int accountId) {
        return accounts.get(accountId).getTransactionHistory();
    }
 
    public int createChecking(double balance) {
        if (balance < 0 || BigDecimal.valueOf(balance).scale() > 2){
            throw new IllegalArgumentException();
        }
        Account newAccount = new CheckingAccount(balance);
        int accountId = (int) (Math.random() * Integer.MAX_VALUE);
        accounts.put(accountId, newAccount);
        return accountId;
    }

    public int createSavings(double balance, double interest){
        if (balance < 0 || BigDecimal.valueOf(balance).scale() > 2 || interest < 0){
            throw new IllegalArgumentException();
        }
        Account newAccount = new SavingsAccount(balance, interest);
        int accountId = (int) (Math.random() * Integer.MAX_VALUE);
        accounts.put(accountId, newAccount);
        return accountId;
    }
 
    public void closeAccount(int accountId) {
        accounts.remove(accountId);
    }
 
    public double checkOverallAmount() {
        return -1;
    }
 
    public String checkSuspiciousActivity() {
        return null;
    }
 
    public void setSuspicious(int accountId, boolean suspicious) {
    }

    public boolean isSuspicious(int accountId){
        return accounts.get(accountId).suspicious;
    }
 
    public void setFrozen(int accountId, boolean frozen) {
    }
}
