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
        if (isEmailValid(email)){
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }

        if (isAmountValid(startingBalance)) {
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
     * Subracts amount from balance if amount is non-negative and greater than or equal to balance
     * @param amount value to withdraw from balance
     * @throws InsufficientFundsException
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount must be non-negative and have 2 or fewer decimal places");
        } else if (amount <= balance) {
            balance -= amount;
        } else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * Adds amount to balance if amount is valid
     * @param amount amount to deposit
     */
    public void deposit(double amount) {
        if (isAmountValid(amount)) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("amount must be non-negative and have 2 or fewer decimal places");
        }
    }

    /**
     * Subtracts amount from balance and deposits it into to if to and amount are valid
     * @param to BankAccount to transfer to
     * @param amount amount to transfer
     * @throws InsufficientFundsException
     */
    public void transfer(BankAccount to, double amount) throws InsufficientFundsException {
        if (to == null) {
            throw new IllegalArgumentException("receiving BankAccount must not be null");
        } else if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount must be non-negative and have 2 or fewer decimal places");
        } else {
            withdraw(amount);  // throws InsufficientFundsException if amount > balance
            to.deposit(amount);
        }
    }
}
