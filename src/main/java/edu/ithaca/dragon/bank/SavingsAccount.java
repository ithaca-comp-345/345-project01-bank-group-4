package edu.ithaca.dragon.bank;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void accrewInterest() {
        // TODO implement accrewInterest
    }
}
