package edu.ithaca.dragon.bank;

import java.math.BigDecimal;

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

    /**
     * Returns true if amount has 2 or fewer decimal places and is non-negative, false otherwise
     * @param amount value to check
     * @return true if amount is valid, false otherwise
     */
    public static boolean isAmountValid(double amount) {
        if (amount < 0) {
            return false;
        } else if (BigDecimal.valueOf(amount).scale() > 2) {
            return false;
        } else {
            return true;
        }
    }
}