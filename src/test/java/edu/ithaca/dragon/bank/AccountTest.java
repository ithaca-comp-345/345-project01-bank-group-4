package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AccountTest {    
    @Test
    public void withdrawTest() {
        double delta = .001;

        // VALID

        Account account = new CheckingAccount(200);
        account.withdraw(100);
        assertEquals(100, account.getBalance(), delta);
        assertEquals("w 100.0 | ", account.getTransactionHistory());

        // withdraw integer leaving 0 -boundary case
        account = new CheckingAccount(200);
        account.withdraw(200);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("w 200.0 | ", account.getTransactionHistory());

        // withdraw float leaving 0 -boundary case
        account = new CheckingAccount(115.42);
        account.withdraw(115.42);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("w 115.42 | ", account.getTransactionHistory());

        // withdraw 0 -boundary case
        account = new CheckingAccount(200);
        account.withdraw(0);
        assertEquals(200, account.getBalance(), delta);
        assertEquals("w 0.0 | ", account.getTransactionHistory());

        // withdraw 0 leaving 0 -boundary case
        account = new CheckingAccount(0);
        account.withdraw(0);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("w 0.0 | ", account.getTransactionHistory());

        // withdraw .01 -boundary case
        account = new CheckingAccount(200);
        account.withdraw(.01);
        assertEquals(199.99, account.getBalance(), delta);
        assertEquals("w 0.01 | ", account.getTransactionHistory());

        // withdraw .01 leaving 0 -boundary case
        account = new CheckingAccount(.01);
        account.withdraw(.01);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("w 0.01 | ", account.getTransactionHistory());


        // INVALID

        // withdraw negative amount
        final CheckingAccount a = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> a.withdraw(-100));
        assertEquals(200, a.getBalance(), delta);

        // withdraw -.01 -boundary case
        final CheckingAccount c = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> c.withdraw(-.01));
        assertEquals(200, c.getBalance(), delta);

        // withdraw amount greater than balance
        final CheckingAccount d = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> d.withdraw(300));
        assertEquals(200, d.getBalance(), delta);

        // withdraw amount .01 greater than balance -boundary case
        final CheckingAccount e = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> e.withdraw(200.01));
        assertEquals(200, e.getBalance(), delta);

        // withdraw amount decimal place > 2
        final CheckingAccount g = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> g.withdraw(20.091));
        assertEquals(200, g.getBalance(), delta);

        // withdraw negative amount decimal place > 2
        final CheckingAccount h = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> h.withdraw(-.009));
        assertEquals(200, h.getBalance(), delta);
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
        double delta = .001;

        SavingsAccount savingsAccount;

        savingsAccount = new SavingsAccount(100, .05);
        savingsAccount.accrewInterest();
        assertEquals(105, savingsAccount.getBalance(), delta);
        savingsAccount.accrewInterest();
        assertEquals(110.25, savingsAccount.getBalance(), delta);

        // balance .01 -boundary case
        savingsAccount = new SavingsAccount(.01, .03);
        savingsAccount.accrewInterest();
        assertEquals(.01, savingsAccount.getBalance(), delta);

        // balance 0 -boundary case
        savingsAccount = new SavingsAccount(0, .03);
        savingsAccount.accrewInterest();
        assertEquals(0, savingsAccount.getBalance(), delta);

        // interestRate .01 -boundary case
        savingsAccount = new SavingsAccount(100, .01);
        savingsAccount.accrewInterest();
        assertEquals(101, savingsAccount.getBalance(), delta);
        savingsAccount.accrewInterest();
        assertEquals(102.01, savingsAccount.getBalance(), delta);

        // interestRate 0 -boundary case
        savingsAccount = new SavingsAccount(200, 0);
        savingsAccount.accrewInterest();
        assertEquals(200, savingsAccount.getBalance(), delta);
    }
}
