package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(String email, double startingBalance, double interestRate) {
        super(email, startingBalance);
        this.interestRate = interestRate;
    }

    public void accrewInterest() {
        // TODO
    }
}
