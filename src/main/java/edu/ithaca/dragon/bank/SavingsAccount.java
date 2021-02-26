package edu.ithaca.dragon.bank;
 
public class SavingsAccount extends Account {
    private double interestRate;
 
    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        //TODO initialize interestRate
    }
 
    public double getInterestRate() {
        return -1;
    }
 
    public void accrewInterest() {
    }
}
