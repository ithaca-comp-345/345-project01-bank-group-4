package edu.ithaca.dragon.bank;
 
import java.util.Map;
 
public class BankController {
    private Map<Integer, Account> accounts;
 
    public BankController() {
    }
 
    public Map<Integer, Account> getAccounts() {
        return null;
    }
 
    public Account retrieveAccount(int accountId) {
        return null;
    }
 
    public double checkBalance(int accountId) {
        return -1;
    }
 
    public void withdraw(int accountId, double amount) {
    }
 
    public void deposit(int accountId, double amount) {
    }
 
    public void transfer(int accountIdFrom, int accountIdTo, double amount) {
    }
 
    public String retrieveTransactionHistory(int accountId) {
        return null;
    }
 
    public int createChecking(double balance) {
        return -1;
    }

    public int createSavings(double balance, double interest){
        return -1;
    }
 
    public void closeAccount(int accountId) {
    }
 
    public double checkOverallAmount() {
        return -1;
    }
 
    public String checkSuspiciousActivity() {
        return null;
    }
 
    public void setSuspicious(int accountId, boolean suspicious) {
    }
 
    public void setFrozen(int accountId, boolean frozen) {
    }
}
