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
        if (!accounts.containsKey(accountId)) {
            throw new NullPointerException();
        }
        accounts.remove(accountId);
    }
 
    public double checkOverallAmount() {
        double total = 0;
        for ( Map.Entry<Integer, Account> entry : accounts.entrySet()){
            total += entry.getValue().getBalance();
        }
        return total;
    }
 
    public String checkSuspiciousActivity() {
        String susString = "Suspicious Accounts: ";
        for ( Map.Entry<Integer, Account> entry : accounts.entrySet()){
            if (entry.getValue().isSuspicious()) {
                susString += entry.getKey() + ", ";
            }
        }
        return susString;
    }
 
    public void setSuspicious(int accountId, boolean suspicious) {
        accounts.get(accountId).setSuspicious(suspicious);
    }

    public boolean isSuspicious(int accountId){
        return accounts.get(accountId).suspicious;
    }
 
    public void setFrozen(int accountId, boolean frozen) {
    }
}
