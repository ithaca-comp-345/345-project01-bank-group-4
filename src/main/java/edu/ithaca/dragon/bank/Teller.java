package edu.ithaca.dragon.bank;
 
public abstract class Teller {
    protected BankController bankController;
    protected int currAccountId;
 
    public Teller(BankController bankController) {
    }
 
    public BankController getBankController() {
        return null;
    }
 
    public int getCurrAccountId() {
        return -1;
    }
 
    public void confirmCredentials(int accountId) {
    }
 
    public double checkBalance() {
        return -1;
    }
 
    public void withdraw(double amount) {
    }
 
    public void deposit(double amount) {
    }
 
    public void transfer(int accountIdTo, double amount) {
    }
 
    public String retrieveTransactionHistory() {
        return null;
    }
}
