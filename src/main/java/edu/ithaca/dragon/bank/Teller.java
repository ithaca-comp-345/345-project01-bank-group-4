package edu.ithaca.dragon.bank;

public abstract class Teller {
    protected BankController bankController;
    protected int currAccountId;

    public Teller(BankController bankController) {
        if (bankController == null) {
            throw new NullPointerException();
        } else {
            this.bankController = bankController;
            currAccountId = -1;
        }
    }

    public BankController getBankController() {
        return bankController;
    }

    public int getCurrAccountId() {
        return currAccountId;
    }

    public void confirmCredentials(int accountId) {
        Account account = bankController.retrieveAccount(accountId);
        if (account == null) {
            throw new NullPointerException();
        } else {
            currAccountId = accountId;
        }
    }

    public double checkBalance() {
        return bankController.checkBalance(currAccountId);
    }

    public void withdraw(double amount) {
        bankController.withdraw(currAccountId, amount);
    }

    public void deposit(double amount) {
        bankController.deposit(currAccountId, amount);
    }

    public void transfer(int accountIdTo, double amount) {
        bankController.transfer(currAccountId, accountIdTo, amount);
    }

    public String retrieveTransactionHistory() {
        return bankController.retrieveTransactionHistory(currAccountId);
    }
}
