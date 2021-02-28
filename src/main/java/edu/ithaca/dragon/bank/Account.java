package edu.ithaca.dragon.bank;

import java.math.BigDecimal;

public abstract class Account {
    protected double balance;
    protected String transactionHistory;
    protected boolean suspicious;
    protected boolean frozen;
 
    public Account(double balance) {
        if (!isAmountValid(balance)) {
            throw new IllegalArgumentException();
        } else {
            this.balance = balance;
            transactionHistory = "";
            suspicious = false;
            frozen = false;
        }
    }
 
    public double getBalance() {
        return balance;
    }
 
    public String getTransactionHistory() {
        return transactionHistory;
    }
 
    public boolean isSuspicious() {
        return suspicious;
    }
     
    public boolean isFrozen() {
        return frozen;
    }
 
    public void setSuspicious(boolean suspicious) {
    }
 
    public void setFrozen(boolean frozen) {
    }
 
    private boolean isAmountValid(double amount) {
        return amount >= 0 && BigDecimal.valueOf(amount).scale() <= 2;
    }
 
    public void withdraw(double amount) {
    }
 
    public void deposit(double amount) {
    }
 
    public void transfer(Account account, double amount) {
    }
}
