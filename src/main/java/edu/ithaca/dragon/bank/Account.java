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
            transactionHistory = balance + " | ";
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
        this.suspicious = suspicious;
    }
 
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
 
    /**
     * returns whether an amount is a valid balance, or withdraw, deposit or transfer amount
     * @param amount value to check
     * @return true if amount is non-negative and has 2 or fewer decimal places
     */
    private boolean isAmountValid(double amount) {
        return amount >= 0 && BigDecimal.valueOf(amount).scale() <= 2;
    }
 
    /**
     * withdraws amount from balance if valid, and less or equal to balance
     * @param amount value to withdraw
     */
    public void withdraw(double amount) {
        if (!isAmountValid(amount) || amount > balance) {
            throw new IllegalArgumentException();
        } else {
            balance -= amount;
            transactionHistory += "w " + amount + " | ";
        }
    }
 
    /**
     * deposits amount into balance if valid
     * @param amount value to deposit
     */
    public void deposit(double amount) {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException();
        } else {
            balance += amount;
            transactionHistory += "d " + amount + " | ";
        }
    }
 
    /**
     * transfers amount from balance into account's balance if account is non-null, and amount is valid and less or equal to balance
     * @param account account to transfer to
     * @param amount value to transfer
     */
    public void transfer(Account account, double amount) {
        if (account == null) {
            throw new NullPointerException();
        } else if (!isAmountValid(amount) || amount > balance) {
            throw new IllegalArgumentException();
        } else {
            withdraw(amount);
            account.deposit(amount);
        }
    }
}
