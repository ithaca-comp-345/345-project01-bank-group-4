package edu.ithaca.dragon.bank;

import java.util.HashMap;
import java.util.Map;

public class BankController {
    private Map<Integer, Account> accounts;

    public BankController() {
        accounts = new HashMap<>();
        // TODO add default accounts
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public Account retrieveAccount(int accountId) {
        // TODO implement retrieveAccount
        return null;
    }

    public double checkBalance(int accountId) {
        // TODO implement checkBalance
        return 0;
    }

    public void withdraw(int accountId, double amount) {
        // TODO implement withdraw
    }

    public void deposit(int accountId, double amount) {
        // TODO implement deposit
    }

    public void transfer(int accountIdFrom, int accountIdTo, double amount) {
        // TODO implement transfer
    }

    public String retrieveTransactionHistory(int accountId) {
        // TODO implement retrieveTransactionHistory
        return "";
    }

    public int createAccount(char accountType) {
        // TODO implement createAccount
        return 0;
    }

    public void closeAccount(int accountId) {
        // TODO implement closeAccount
    }

    public double checkOverallAmount() {
        // TODO implement checkOverallAmount
        return 0;
    }

    public String checkSuspiciousActivity() {
        // TODO implement checkSuspicioiusActivity
        return "";
    }

    public void freeze(int accountId) {
        // TODO implement freeze
    }

    public void unfreeze(int accountId) {
        // TODO implement unfreeze
    }
}
