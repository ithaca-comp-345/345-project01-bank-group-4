package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AccountTest {    
    @Test
    public void withdrawTest() {
        double delta = .001;

        Account account;

        // VALID
        // withdraw integer
        account = new CheckingAccount(200);
        account.withdraw(100);
        assertEquals(100, account.getBalance(), delta);
        assertEquals("w 100.0 | ", account.getTransactionHistory());

        // withdraw integer leaving 0 -boundary case
        account = new CheckingAccount(200);
        account.withdraw(200);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("w 200.0 | ", account.getTransactionHistory());

        // withdraw float
        account = new CheckingAccount(15.75);
        account.withdraw(13);
        assertEquals(2.75, account.getBalance(), delta);
        assertEquals("w 13.0 | ", account.getTransactionHistory());

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
        // withdraw negative integer amount
        final CheckingAccount a = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> a.withdraw(-100));
        assertEquals(200, a.getBalance(), delta);

        // withdraw negative float amount
        final CheckingAccount b = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> b.withdraw(-10.20));
        assertEquals(200, b.getBalance(), delta);

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

        // withdraw float component decimal place > 2
        final CheckingAccount f = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f.withdraw(.248));
        assertEquals(200, f.getBalance(), delta);

        // withdraw integer and float component decimal place > 2
        final CheckingAccount g = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> g.withdraw(20.091));
        assertEquals(200, g.getBalance(), delta);

        // withdraw negative float component decimal place > 2
        final CheckingAccount h = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> h.withdraw(-.009));
        assertEquals(200, h.getBalance(), delta);

        // withdraw negative integer and float component decimal place > 2
        final CheckingAccount i = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> i.withdraw(-20.1234));
        assertEquals(200, i.getBalance(), delta);
    }
 
    @Test
    public void depositTest() {
        double delta = .001;

        Account account;

        // VALID
        // positive integer component
        account = new CheckingAccount(200);
        account.deposit(20);
        assertEquals(220, account.getBalance(), delta);

        // positive 1 decimal place float component
        account = new CheckingAccount(200);
        account.deposit(.5);
        assertEquals(200.5, account.getBalance(), delta);

        // positive 1 decimal place integer and float component
        account = new CheckingAccount(200);
        account.deposit(125.2);
        assertEquals(325.2, account.getBalance(), delta);

        // positive 2 decimal place float component
        account = new CheckingAccount(200);
        account.deposit(.25);
        assertEquals(200.25, account.getBalance(), delta);

        // positive 2 decimal place integer and float component
        account = new CheckingAccount(200);
        account.deposit(88.32);
        assertEquals(288.32, account.getBalance(), delta);

        // 0 amount -boundary case
        account = new CheckingAccount(200);
        account.deposit(0);
        assertEquals(200, account.getBalance(), delta);

        // .01 amount -boundary case
        account = new CheckingAccount(200);
        account.deposit(.01);
        assertEquals(200.01, account.getBalance(), delta);


        // INVALID

        // NEGATIVE
        // negative integer component
        final CheckingAccount a = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> a.deposit(-10));
        assertEquals(200, a.getBalance(), delta);

        // negative 1 place float component
        final CheckingAccount b = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> b.deposit(-.5));
        assertEquals(200, b.getBalance(), delta);

        // negative 1 place integer and float component
        final CheckingAccount c = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> c.deposit(-1.1));
        assertEquals(200, c.getBalance(), delta);

        // negative 2 place float component
        final CheckingAccount d = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> d.deposit(-.75));
        assertEquals(200, d.getBalance(), delta);

        // negative 2 place integer and float component
        final CheckingAccount e = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> e.deposit(-6.23));
        assertEquals(200, e.getBalance(), delta);

        // -.01 amount -boundary case
        final CheckingAccount f = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f.deposit(-.01));
        assertEquals(200, f.getBalance(), delta);


        // DECIMAL PLACES > 2
        // decimal place > 2 float component amount
        final CheckingAccount g = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> g.deposit(.121212121212));

        // 3 decimal place float component amount -boundary case
        final CheckingAccount h = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> h.deposit(.789));

        // .009 amount -boundary case
        final CheckingAccount i = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> i.deposit(.009));

        // integer and 3 decimal place float component amount
        final CheckingAccount j = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> j.deposit(793.539));


        // NEGATIVE + DECIMAL PLACES > 2
        // float component amount
        final CheckingAccount k = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> k.deposit(-.71828));

        // integer and float component amount
        final CheckingAccount l = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> l.deposit(-123.4556));

        // -.001 amount -boundary case
        final CheckingAccount m = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> m.deposit(-.001));

        // -.009 amount -boundary case
        final CheckingAccount n = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> n.deposit(-.009));
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
