package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AccountTest { 
    @Test
    public void getBalanceTest() {
    }
 
    @Test
    public void getTransactionHistoryTest() {
    }
 
    @Test
    public void isSuspiciousTest() {
    }

    @Test
    public void isFrozenTest() {
    }
 
    @Test
    public void setSuspiciousTest() {
    }
 
    @Test
    public void setFrozenTest() {
    }
  
    @Test
    public void withdrawTest() {
    }
 
    @Test
    public void depositTest() {
    }
 
    @Test
    public void transferTest() {
    }


    // CheckingAccount specific tests

    @Test
    public void checkingConstructorTest() {
        double delta = .001;

        CheckingAccount checkingAccount;

        // VALID BALANCES

        checkingAccount = new CheckingAccount(200);
        assertEquals(200, checkingAccount.getBalance(), delta);

        // balance 0 -boundary case
        checkingAccount = new CheckingAccount(0);
        assertEquals(0, checkingAccount.getBalance(), delta);

        // balance .01 -boundary case
        checkingAccount = new CheckingAccount(.01);
        assertEquals(.01, checkingAccount.getBalance(), delta);


        // INVALID BALANCES

        // balance negative
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(-200));

        // balance -.01 -boundary case
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(-.01));

        // balance decimal places > 2
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(1.24816));

        // balance decimal place 3 -boundary case
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(3.141));
    }


    // SavingsAccout specific tests

    @Test
    public void savingsConstructorTest() {
    }

    @Test
    public void getInterestRateTest() {
    }

    @Test
    public void accrewInterestTest() {
    }
}
