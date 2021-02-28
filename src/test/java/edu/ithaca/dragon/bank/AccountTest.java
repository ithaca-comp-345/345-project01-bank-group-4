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
        assertEquals("200.0 | w 100.0 | ", account.getTransactionHistory());

        // withdraw integer leaving 0 -boundary case
        account = new CheckingAccount(200);
        account.withdraw(200);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("200.0 | w 200.0 | ", account.getTransactionHistory());

        // withdraw float
        account = new CheckingAccount(15.75);
        account.withdraw(13);
        assertEquals(2.75, account.getBalance(), delta);
        assertEquals("15.75 | w 13.0 | ", account.getTransactionHistory());

        // withdraw float leaving 0 -boundary case
        account = new CheckingAccount(115.42);
        account.withdraw(115.42);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("115.42 | w 115.42 | ", account.getTransactionHistory());

        // withdraw 0 -boundary case
        account = new CheckingAccount(200);
        account.withdraw(0);
        assertEquals(200, account.getBalance(), delta);
        assertEquals("200.0 | w 0.0 | ", account.getTransactionHistory());

        // withdraw 0 leaving 0 -boundary case
        account = new CheckingAccount(0);
        account.withdraw(0);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("0.0 | w 0.0 | ", account.getTransactionHistory());

        // withdraw .01 -boundary case
        account = new CheckingAccount(200);
        account.withdraw(.01);
        assertEquals(199.99, account.getBalance(), delta);
        assertEquals("200.0 | 200.0 | w 0.01 | ", account.getTransactionHistory());

        // withdraw .01 leaving 0 -boundary case
        account = new CheckingAccount(.01);
        account.withdraw(.01);
        assertEquals(0, account.getBalance(), delta);
        assertEquals("0.01 | 0.01 | w 0.01 | ", account.getTransactionHistory());

        // INVALID
        // withdraw negative integer amount
        final CheckingAccount a = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> a.withdraw(-100));
        assertEquals(200, a.getBalance(), delta);
        assertEquals("200.0 | ", a.getTransactionHistory());

        // withdraw negative float amount
        final CheckingAccount b = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> b.withdraw(-10.20));
        assertEquals(200, b.getBalance(), delta);
        assertEquals("200.0 | ", b.getTransactionHistory());

        // withdraw -.01 -boundary case
        final CheckingAccount c = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> c.withdraw(-.01));
        assertEquals(200, c.getBalance(), delta);
        assertEquals("200.0 | ", c.getTransactionHistory());

        // withdraw amount greater than balance
        final CheckingAccount d = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> d.withdraw(300));
        assertEquals(200, d.getBalance(), delta);
        assertEquals("200.0 | ", d.getTransactionHistory());

        // withdraw amount .01 greater than balance -boundary case
        final CheckingAccount e = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> e.withdraw(200.01));
        assertEquals(200, e.getBalance(), delta);
        assertEquals("200.0 | ", e.getTransactionHistory());

        // withdraw float component decimal place > 2
        final CheckingAccount f = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f.withdraw(.248));
        assertEquals(200, f.getBalance(), delta);
        assertEquals("200.0 | ", f.getTransactionHistory());

        // withdraw integer and float component decimal place > 2
        final CheckingAccount g = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> g.withdraw(20.091));
        assertEquals(200, g.getBalance(), delta);
        assertEquals("200.0 | ", g.getTransactionHistory());

        // withdraw negative float component decimal place > 2
        final CheckingAccount h = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> h.withdraw(-.009));
        assertEquals(200, h.getBalance(), delta);
        assertEquals("200.0 | ", h.getTransactionHistory());

        // withdraw negative integer and float component decimal place > 2
        final CheckingAccount i = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> i.withdraw(-20.1234));
        assertEquals(200, i.getBalance(), delta);
        assertEquals("200.0 | ", i.getTransactionHistory());
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
        assertEquals("200.0 | d 20.0 | ", account.getTransactionHistory());

        // positive 1 decimal place float component
        account = new CheckingAccount(200);
        account.deposit(.5);
        assertEquals(200.5, account.getBalance(), delta);
        assertEquals("200.0 | d 0.5 | ", account.getTransactionHistory());

        // positive 1 decimal place integer and float component
        account = new CheckingAccount(200);
        account.deposit(125.2);
        assertEquals(325.2, account.getBalance(), delta);
        assertEquals("125.2 | d 125.2 | ", account.getTransactionHistory());

        // positive 2 decimal place float component
        account = new CheckingAccount(200);
        account.deposit(.25);
        assertEquals(200.25, account.getBalance(), delta);
        assertEquals("200.0 | d 0.25 | ", account.getTransactionHistory());

        // positive 2 decimal place integer and float component
        account = new CheckingAccount(200);
        account.deposit(88.32);
        assertEquals(288.32, account.getBalance(), delta);
        assertEquals("200.0 | d 88.32 | ", account.getTransactionHistory());

        // 0 amount -boundary case
        account = new CheckingAccount(200);
        account.deposit(0);
        assertEquals(200, account.getBalance(), delta);
        assertEquals("200.0 | d 0.0 | ", account.getTransactionHistory());

        // .01 amount -boundary case
        account = new CheckingAccount(200);
        account.deposit(.01);
        assertEquals(200.01, account.getBalance(), delta);
        assertEquals("200.0 | d 0.01 | ", account.getTransactionHistory());


        // INVALID

        // NEGATIVE
        // negative integer component
        final CheckingAccount a = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> a.deposit(-10));
        assertEquals(200, a.getBalance(), delta);
        assertEquals("200.0 | ", a.getTransactionHistory());

        // negative 1 place float component
        final CheckingAccount b = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> b.deposit(-.5));
        assertEquals(200, b.getBalance(), delta);
        assertEquals("200.0 | ", b.getTransactionHistory());

        // negative 1 place integer and float component
        final CheckingAccount c = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> c.deposit(-1.1));
        assertEquals(200, c.getBalance(), delta);
        assertEquals("200.0 | ", c.getTransactionHistory());

        // negative 2 place float component
        final CheckingAccount d = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> d.deposit(-.75));
        assertEquals(200, d.getBalance(), delta);
        assertEquals("200.0 | ", d.getTransactionHistory());

        // negative 2 place integer and float component
        final CheckingAccount e = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> e.deposit(-6.23));
        assertEquals(200, e.getBalance(), delta);
        assertEquals("200.0 | ", e.getTransactionHistory());

        // -.01 amount -boundary case
        final CheckingAccount f = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f.deposit(-.01));
        assertEquals(200, f.getBalance(), delta);
        assertEquals("200.0 | ", f.getTransactionHistory());


        // DECIMAL PLACES > 2
        // decimal place > 2 float component amount
        final CheckingAccount g = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> g.deposit(.121212121212));
        assertEquals(200, g.getBalance(), delta);
        assertEquals("200.0 | ", g.getTransactionHistory());

        // 3 decimal place float component amount -boundary case
        final CheckingAccount h = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> h.deposit(.789));
        assertEquals(200, h.getBalance(), delta);
        assertEquals("200.0 | ", h.getTransactionHistory());

        // .009 amount -boundary case
        final CheckingAccount i = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> i.deposit(.009));
        assertEquals(200, i.getBalance(), delta);
        assertEquals("200.0 | ", i.getTransactionHistory());

        // integer and 3 decimal place float component amount
        final CheckingAccount j = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> j.deposit(793.539));
        assertEquals(200, j.getBalance(), delta);
        assertEquals("200.0 | ", j.getTransactionHistory());


        // NEGATIVE + DECIMAL PLACES > 2
        // float component amount
        final CheckingAccount k = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> k.deposit(-.71828));
        assertEquals(200, k.getBalance(), delta);
        assertEquals("200.0 | ", k.getTransactionHistory());

        // integer and float component amount
        final CheckingAccount l = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> l.deposit(-123.4556));
        assertEquals(200, l.getBalance(), delta);
        assertEquals("200.0 | ", l.getTransactionHistory());

        // -.001 amount -boundary case
        final CheckingAccount m = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> m.deposit(-.001));
        assertEquals(200, m.getBalance(), delta);
        assertEquals("200.0 | ", m.getTransactionHistory());

        // -.009 amount -boundary case
        final CheckingAccount n = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> n.deposit(-.009));
        assertEquals(200, n.getBalance(), delta);
        assertEquals("200.0 | ", n.getTransactionHistory());
    }
 
    @Test
    public void transferTest() {
        double delta = .001;

        Account from, to;

        // VALID
        // normal case
        from = new CheckingAccount(200);
        to = new CheckingAccount(200);
        from.transfer(to, 100);
        assertEquals(100, from.getBalance(), delta);
        assertEquals("200.0 | w 100.0 | ", from.getTransactionHistory());
        assertEquals(300, to.getBalance(), delta);
        assertEquals("200.0 | d 100.0 | ", to.getTransactionHistory());

        // trasfer entire balance -boundary case
        from = new CheckingAccount(200);
        to = new CheckingAccount(200);
        from.transfer(to, 200);
        assertEquals(0, from.getBalance(), delta);
        assertEquals("200.0 | w 200.0 | ", from.getTransactionHistory());
        assertEquals(400, to.getBalance(), delta);
        assertEquals("200.0 | d 200.0 | ", to.getTransactionHistory());

        // transfer 0 -boundary case
        from = new CheckingAccount(200);
        to = new CheckingAccount(200);
        from.transfer(to, 0);
        assertEquals(200, from.getBalance(), delta);
        assertEquals("200.0 | w 0.0 | ", from.getTransactionHistory());
        assertEquals(200, to.getBalance(), delta);
        assertEquals("200.0 | d 0.0 | ", to.getTransactionHistory());

        // transfer .01 -boundary case
        from = new CheckingAccount(200);
        to = new CheckingAccount(200);
        from.transfer(to, .01);
        assertEquals(199.99, from.getBalance(), delta);
        assertEquals("200.0 | w 0.01 | ", from.getTransactionHistory());
        assertEquals(200.01, to.getBalance(), delta);
        assertEquals("200.0 | d 0.01 | ", to.getTransactionHistory());

        // transfer leaving .01 -boundary case
        from = new CheckingAccount(200);
        to = new CheckingAccount(200);
        from.transfer(to, 199.99);
        assertEquals(.01, from.getBalance(), delta);
        assertEquals("200.0 | w 199.99 | ", from.getTransactionHistory());
        assertEquals(399.99, to.getBalance(), delta);
        assertEquals("200.0 | d 199.99 | ", to.getTransactionHistory());


        // INVALID
        // transfer amount larger than balance
        CheckingAccount f1 = new CheckingAccount(200);
        CheckingAccount t1 = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f1.transfer(t1, 300));
        assertEquals(200, f1.getBalance(), delta);
        assertEquals("200.0 | ", f1.getTransactionHistory());
        assertEquals(200, t1.getBalance(), delta);
        assertEquals("200.0 | ", t1.getTransactionHistory());

        // transfer .01 more than balance -boundary case
        CheckingAccount f2 = new CheckingAccount(200);
        CheckingAccount t2 = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f2.transfer(t2, 200.01));
        assertEquals(200, f2.getBalance(), delta);
        assertEquals("200.0 | ", f2.getTransactionHistory());
        assertEquals(200, t2.getBalance(), delta);
        assertEquals("200.0 | ", t2.getTransactionHistory());

        // transfer negative amount
        CheckingAccount f3 = new CheckingAccount(200);
        CheckingAccount t3 = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f3.transfer(t3, -20));
        assertEquals(200, f3.getBalance(), delta);
        assertEquals("200.0 | ", f3.getTransactionHistory());
        assertEquals(200, t3.getBalance(), delta);
        assertEquals("200.0 | ", t3.getTransactionHistory());

        // transfer -.01 -boundary case
        CheckingAccount f4 = new CheckingAccount(200);
        CheckingAccount t4 = new CheckingAccount(200);
        assertThrows(IllegalArgumentException.class, () -> f4.transfer(t4, -.01));
        assertEquals(200, f4.getBalance(), delta);
        assertEquals("200.0 | ", f4.getTransactionHistory());
        assertEquals(200, t4.getBalance(), delta);
        assertEquals("200.0 | ", t4.getTransactionHistory());

        // transfer to null CheckingAccount
        CheckingAccount f5 = new CheckingAccount(200);
        CheckingAccount t5 = null;
        assertThrows(IllegalArgumentException.class, () -> f5.transfer(t5, -.01));
        assertEquals(200, f5.getBalance(), delta);
        assertEquals("200.0 | ", f5.getTransactionHistory());
    }


    // CheckingAccount specific tests

    @Test
    public void checkingConstructorTest() {
        double delta = .001;

        CheckingAccount checkingAccount;

        // VALID

        checkingAccount = new CheckingAccount(200);
        assertEquals(200, checkingAccount.getBalance(), delta);
        assertEquals("200.0 | ", checkingAccount.getTransactionHistory());
        assertEquals(false, checkingAccount.isSuspicious());
        assertEquals(false, checkingAccount.isFrozen());

        // balance 0 -boundary case
        checkingAccount = new CheckingAccount(0);
        assertEquals(0, checkingAccount.getBalance(), delta);
        assertEquals("0.0 | ", checkingAccount.getTransactionHistory());
        assertEquals(false, checkingAccount.isSuspicious());
        assertEquals(false, checkingAccount.isFrozen());

        // balance .01 -boundary case
        checkingAccount = new CheckingAccount(.01);
        assertEquals(.01, checkingAccount.getBalance(), delta);
        assertEquals("0.01 | ", checkingAccount.getTransactionHistory());
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
        assertEquals("200.0 | ", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // balance .01 -boundary case
        savingsAccount = new SavingsAccount(.01, .04);
        assertEquals(.01, savingsAccount.getBalance(), delta);
        assertEquals(.04, savingsAccount.getInterestRate(), delta);
        assertEquals("0.01 | ", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // balance 0 -boundary case
        savingsAccount = new SavingsAccount(0, .03);
        assertEquals(0, savingsAccount.getBalance(), delta);
        assertEquals(.03, savingsAccount.getInterestRate(), delta);
        assertEquals("0.0 | ", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // interestRate .01 -boundary case
        savingsAccount = new SavingsAccount(200, .01);
        assertEquals(200, savingsAccount.getBalance(), delta);
        assertEquals(.01, savingsAccount.getInterestRate(), delta);
        assertEquals("200.0 | ", savingsAccount.getTransactionHistory());
        assertEquals(false, savingsAccount.isSuspicious());
        assertEquals(false, savingsAccount.isFrozen());

        // interestRate 0 -boundary case
        savingsAccount = new SavingsAccount(200, 0);
        assertEquals(200, savingsAccount.getBalance(), delta);
        assertEquals(0, savingsAccount.getInterestRate(), delta);
        assertEquals("200.0 | ", savingsAccount.getTransactionHistory());
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
