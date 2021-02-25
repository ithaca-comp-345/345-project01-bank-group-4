package edu.ithaca.dragon.bank;

public abstract class Teller {
    private BankController bankController;
    private Account currAccount;

    public Teller(BankController bankController) {
        this.bankController = bankController;
        currAccount = null;
    }

    public BankController getBankController() {
        return bankController;
    }

    public Account getCurrAccount() {
        return currAccount;
    }

    public void confirmCredentials(int accountId) {
        // TODO implement confirmCredentials
    }

    public double checkBalance() {
        // TODO implement checkBalance
        return 0;
    }

    public void withdraw(double amount) {
        // TODO implement withdraw
    }

    public void deposit(double amount) {
        // TODO implement deposit
    }

    public void transfer(int accountId, double amount) {
        // TODO implement transfer
    }

    public String retrieveTransactionHistory() {
        // TODO implement retrieveTransactionHistory
        return "";
    }
}
