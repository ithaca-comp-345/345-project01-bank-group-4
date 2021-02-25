package edu.ithaca.dragon.bank;

public abstract class Account {
    private double balance;
    private boolean suspicious;
    private boolean frozen;

    public Account(double balance) {
        this.balance = balance;
        suspicious = false;
        frozen = false;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void withdraw(double amount) {
        // TODO implement withdraw
    }

    public void deposit(double amount) {
        // TODO implement deposit
    }

    public void transfer(Account account, double amount) {
        // TODO implement transfer
    }
}
