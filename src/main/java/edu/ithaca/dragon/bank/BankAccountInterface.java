package edu.ithaca.dragon.bank;

public interface BankAccountInterface {

    public double getBalance();
    public String getEmail();
    public void withdraw (double amount) throws InsufficientFundsException;

    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}