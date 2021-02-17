package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String email, double startingBalance) {
        super(email, startingBalance);
        interestRate = 0.5;
    }

    public void accrewInterest(double amount) {
        balance+=(balance*interestRate);
    }

    public double getInterest() {
        return interestRate;
    }



}
