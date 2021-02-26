package edu.ithaca.dragon.bank;
  
public abstract class Account {
    protected double balance;
    protected String transactionHistory;
    protected boolean suspicious;
    protected boolean frozen;
 
    public Account(double balance) {
    }
 
    public double getBalance() {
        return -1;
    }
 
    public String getTransactionHistory() {
        return null;
    }
 
    public boolean isSuspicious() {
        return false;
    }
     
    public boolean isFrozen() {
        return false;
    }
 
    public void setSuspicious(boolean suspicious) {
    }
 
    public void setFrozen(boolean frozen) {
    }
 
    private boolean isAmountValid(double amount) {
        return false;
    }
 
    public void withdraw(double amount) {
    }
 
    public void deposit(double amount) {
    }
 
    public void transfer(Account account, double amount) {
    }
}
