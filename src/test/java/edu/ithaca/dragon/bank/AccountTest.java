package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AccountTest {    
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

        // VALID

        checkingAccount = new CheckingAccount(200);
        assertEquals(200, checkingAccount.getBalance(), delta);
        assertEquals("", checkingAccount.getTransactionHistory());
        assertEquals(false, checkingAccount.isSuspicious());
        assertEquals(false, checkingAccount.isFrozen());

        // balance 0 -boundary case
        checkingAccount = new CheckingAccount(0);
        assertEquals(0, checkingAccount.getBalance(), delta);
        assertEquals("", checkingAccount.getTransactionHistory());
        assertEquals(false, checkingAccount.isSuspicious());
        assertEquals(false, checkingAccount.isFrozen());

        // balance .01 -boundary case
        checkingAccount = new CheckingAccount(.01);
        assertEquals(.01, checkingAccount.getBalance(), delta);
        assertEquals("", checkingAccount.getTransactionHistory());
        assertEquals(false, checkingAccount.isSuspicious());
        assertEquals(false, checkingAccount.isFrozen());


        // INVALID

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
        double delta = .001;

        SavingsAccount savingsAccount;

        // VALID

        savingsAccount = new SavingsAccount(200, .02);
        assertEquals(200, savingsAccount.getBalance(), delta);
        assertEquals(.02, savingsAccount.getInterestRate(), delta);
        assertEquals("", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // balance .01 -boundary case
        savingsAccount = new SavingsAccount(.01, .04);
        assertEquals(.01, savingsAccount.getBalance(), delta);
        assertEquals(.04, savingsAccount.getInterestRate(), delta);
        assertEquals("", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // balance 0 -boundary case
        savingsAccount = new SavingsAccount(0, .03);
        assertEquals(0, savingsAccount.getBalance(), delta);
        assertEquals(.03, savingsAccount.getInterestRate(), delta);
        assertEquals("", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // interestRate .01 -boundary case
        savingsAccount = new SavingsAccount(200, .01);
        assertEquals(200, savingsAccount.getBalance(), delta);
        assertEquals(.01, savingsAccount.getInterestRate(), delta);
        assertEquals("", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // interestRate 0 -boundary case
        savingsAccount = new SavingsAccount(200, 0);
        assertEquals(200, savingsAccount.getBalance(), delta);
        assertEquals(0, savingsAccount.getInterestRate(), delta);
        assertEquals("", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());


        // INVALID

        // balance negative
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(-200, .02));

        // balance -.01 -boundary case
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(-.01, .03));

        // balance decimal places > 2
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(1.24816, .04));

        // balance decimal place 3 -boundary case
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(3.141, .05));

        // interestRate negative
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(200, -.02));

        // interestRate -.01 -boundary case
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(200, -.01));
    }

    @Test
    public void accrewInterestTest() {
    }
}
