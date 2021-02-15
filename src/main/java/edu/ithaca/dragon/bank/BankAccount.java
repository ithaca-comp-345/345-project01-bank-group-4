package edu.ithaca.dragon.bank;

public class BankAccount implements BankAccountInterface{

    private String email;
    private double balance;

    /**
     * Creates a BankAccount object if email and amount are valid
     * @param email account email address
     * @param startingBalance account starting balance
     */
    public BankAccount(String email, double startingBalance) {
        if (BankAccountInterface.isEmailValid(email)){
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }

        if (BankAccountInterface.isAmountValid(startingBalance)) {
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
}
