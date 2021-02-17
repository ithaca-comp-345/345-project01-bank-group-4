package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    @Test
    void isAmountValidTest() {
        // VALID (non-negative and <= 2 decimal places)
        // integer component amount
        assertTrue(BankAccountInterface.isAmountValid(200));

        // 1 decimal place float component amount
        assertTrue(BankAccountInterface.isAmountValid(.4));

        // 2 decimal place float component amount
        assertTrue(BankAccountInterface.isAmountValid(.45));

        // integer and float components amount
        assertTrue(BankAccountInterface.isAmountValid(10.15));

        // .01 amount -boundary case
        assertTrue(BankAccountInterface.isAmountValid(.01));

        // 0 amount -boundary case
        assertTrue(BankAccountInterface.isAmountValid(0));


        // INVALID

        // NEGATIVE
        // integer component amount
        assertFalse(BankAccountInterface.isAmountValid(-16));

        // 1 decimal place float component amount
        assertFalse(BankAccountInterface.isAmountValid(-.5));

        // 2 deciamal place float component amount
        assertFalse(BankAccountInterface.isAmountValid(-.42));

        // integer and float components amount
        assertFalse(BankAccountInterface.isAmountValid(-19.50));

        // -.01 amount -boundary case
        assertFalse(BankAccountInterface.isAmountValid(-.01));


        // DECIMAL PLACES > 2
        // decimal place > 2 float component amount
        assertFalse(BankAccountInterface.isAmountValid(.36787944117));

        // 3 decimal place float component amount -boundary case 
        assertFalse(BankAccountInterface.isAmountValid(.256));

        // .009 amount -boundary case
        assertFalse(BankAccountInterface.isAmountValid(.009));

        // integer and 3 decimal place float component amount
        assertFalse(BankAccountInterface.isAmountValid(32.125));


        // NEGATIVE + DECIMAL PLACES > 2
        // float component amount
        assertFalse(BankAccountInterface.isAmountValid(-.14159265359));

        // integer and float component amount
        assertFalse(BankAccountInterface.isAmountValid(-602.1023));

        // -.001 amount -boundary case
        assertFalse(BankAccountInterface.isAmountValid(-.001));

        // -.009 amount -boundary case
        assertFalse(BankAccountInterface.isAmountValid(-.009));
    }

    @Test
    void getBalanceTest() {
        // integer balance
        BankAccount bankAccount = new CheckingAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        // float balance
        bankAccount = new CheckingAccount("a@b.com", 45.13);
        assertEquals(45.13, bankAccount.getBalance());

        // 0 balance -boundary case
        bankAccount = new CheckingAccount("a@b.com", 0);
        assertEquals(0, bankAccount.getBalance());

        // .01 balance -boundary case
        bankAccount = new CheckingAccount("a@b.com", .01);
        assertEquals(.01, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        final double delta = .001;  // allowable difference between actual and expected values

        // VALID
        // withdraw integer
        BankAccount bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance(), delta);

        // withdraw integer leaving 0 -boundary case
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.withdraw(200);
        assertEquals(0, bankAccount.getBalance(), delta);

        // withdraw float
        bankAccount = new CheckingAccount("a@b.com", 15.75);
        bankAccount.withdraw(13);
        assertEquals(2.75, bankAccount.getBalance(), delta);

        // withdraw float leaving 0 -boundary case
        bankAccount = new CheckingAccount("a@b.com", 115.42);
        bankAccount.withdraw(115.42);
        assertEquals(0, bankAccount.getBalance(), delta);

        // withdraw 0 -boundary case
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.withdraw(0);
        assertEquals(200, bankAccount.getBalance(), delta);

        // withdraw 0 leaving 0 -boundary case
        bankAccount = new CheckingAccount("a@b.com", 0);
        bankAccount.withdraw(0);
        assertEquals(0, bankAccount.getBalance(), delta);

        // withdraw .01 -boundary case
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.withdraw(.01);
        assertEquals(199.99, bankAccount.getBalance(), delta);

        // withdraw .01 leaving 0 -boundary case
        bankAccount = new CheckingAccount("a@b.com", .01);
        bankAccount.withdraw(.01);
        assertEquals(0, bankAccount.getBalance(), delta);

        // INVALID
        // withdraw negative integer amount
        final BankAccount a = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> a.withdraw(-100));
        assertEquals(200, a.getBalance(), delta);

        // withdraw negative float amount
        final BankAccount b = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> b.withdraw(-10.20));
        assertEquals(200, b.getBalance(), delta);

        // withdraw -.01 -boundary case
        final BankAccount c = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> c.withdraw(-.01));
        assertEquals(200, c.getBalance(), delta);

        // withdraw amount greater than balance
        final BankAccount d = new CheckingAccount("a@b.com", 200);
        assertThrows(InsufficientFundsException.class, () -> d.withdraw(300));
        assertEquals(200, d.getBalance(), delta);

        // withdraw amount .01 greater than balance -boundary case
        final BankAccount e = new CheckingAccount("a@b.com", 200);
        assertThrows(InsufficientFundsException.class, () -> e.withdraw(200.01));
        assertEquals(200, e.getBalance(), delta);

        // withdraw float component decimal place > 2
        final BankAccount f = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> f.withdraw(.248));
        assertEquals(200, f.getBalance(), delta);

        // withdraw integer and float component decimal place > 2
        final BankAccount g = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> g.withdraw(20.091));
        assertEquals(200, g.getBalance(), delta);

        // withdraw negative float component decimal place > 2
        final BankAccount h = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> h.withdraw(-.009));
        assertEquals(200, h.getBalance(), delta);

        // withdraw negative integer and float component decimal place > 2
        final BankAccount i = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> i.withdraw(-20.1234));
        assertEquals(200, i.getBalance(), delta);       
    }

    @Test
    void depositTest() {
        final double delta = .001;

        // VALID
        // positive integer component
        BankAccount bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.deposit(20);
        assertEquals(220, bankAccount.getBalance(), delta);

        // positive 1 decimal place float component
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.deposit(.5);
        assertEquals(200.5, bankAccount.getBalance(), delta);

        // positive 1 decimal place integer and float component
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.deposit(125.2);
        assertEquals(325.2, bankAccount.getBalance(), delta);

        // positive 2 decimal place float component
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.deposit(.25);
        assertEquals(200.25, bankAccount.getBalance(), delta);

        // positive 2 decimal place integer and float component
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.deposit(88.32);
        assertEquals(288.32, bankAccount.getBalance(), delta);

        // 0 amount -boundary case
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.deposit(0);
        assertEquals(200, bankAccount.getBalance(), delta);

        // .01 amount -boundary case
        bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.deposit(.01);
        assertEquals(200.01, bankAccount.getBalance(), delta);


        // INVALID

        // NEGATIVE
        // negative integer component
        final BankAccount a = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> a.deposit(-10));
        assertEquals(200, a.getBalance(), delta);

        // negative 1 place float component
        final BankAccount b = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> b.deposit(-.5));
        assertEquals(200, b.getBalance(), delta);

        // negative 1 place integer and float component
        final BankAccount c = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> c.deposit(-1.1));
        assertEquals(200, c.getBalance(), delta);

        // negative 2 place float component
        final BankAccount d = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> d.deposit(-.75));
        assertEquals(200, d.getBalance(), delta);

        // negative 2 place integer and float component
        final BankAccount e = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> e.deposit(-6.23));
        assertEquals(200, e.getBalance(), delta);

        // -.01 amount -boundary case
        final BankAccount f = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> f.deposit(-.01));
        assertEquals(200, f.getBalance(), delta);


        // DECIMAL PLACES > 2
        // decimal place > 2 float component amount
        final BankAccount g = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> g.deposit(.121212121212));

        // 3 decimal place float component amount -boundary case
        final BankAccount h = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> h.deposit(.789));

        // .009 amount -boundary case
        final BankAccount i = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> i.deposit(.009));

        // integer and 3 decimal place float component amount
        final BankAccount j = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> j.deposit(793.539));


        // NEGATIVE + DECIMAL PLACES > 2
        // float component amount
        final BankAccount k = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> k.deposit(-.71828));

        // integer and float component amount
        final BankAccount l = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> l.deposit(-123.4556));

        // -.001 amount -boundary case
        final BankAccount m = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> m.deposit(-.001));

        // -.009 amount -boundary case
        final BankAccount n = new CheckingAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> n.deposit(-.009));
    }

    @Test
    void transferTest() throws InsufficientFundsException {
        final double delta = .001;

        // VALID
        // normal case
        BankAccount from = new CheckingAccount("from@doma.in", 200);
        BankAccount to = new CheckingAccount("to@doma.in", 200);
        from.transfer(to, 100);
        assertEquals(100, from.getBalance(), delta);
        assertEquals(300, to.getBalance(), delta);

        // trasfer entire balance -boundary case
        from = new CheckingAccount("from@doma.in", 200);
        to = new CheckingAccount("to@doma.in", 200);
        from.transfer(to, 200);
        assertEquals(0, from.getBalance(), delta);
        assertEquals(400, to.getBalance(), delta);

        // transfer 0 -boundary case
        from = new CheckingAccount("from@doma.in", 200);
        to = new CheckingAccount("to@doma.in", 200);
        from.transfer(to, 0);
        assertEquals(200, from.getBalance(), delta);
        assertEquals(200, to.getBalance(), delta);

        // transfer .01 -boundary case
        from = new CheckingAccount("from@doma.in", 200);
        to = new CheckingAccount("to@doma.in", 200);
        from.transfer(to, .01);
        assertEquals(199.99, from.getBalance(), delta);
        assertEquals(200.01, to.getBalance(), delta);

        // transfer leaving .01 -boundary case
        from = new CheckingAccount("from@doma.in", 200);
        to = new CheckingAccount("to@doma.in", 200);
        from.transfer(to, 199.99);
        assertEquals(.01, from.getBalance(), delta);
        assertEquals(399.99, to.getBalance(), delta);


        // INVALID
        // transfer amount larger than balance
        BankAccount f1 = new CheckingAccount("from@doma.in", 200);
        BankAccount t1 = new CheckingAccount("to@doma.in", 200);
        assertThrows(InsufficientFundsException.class, () -> f1.transfer(t1, 300));
        assertEquals(200, f1.getBalance(), delta);
        assertEquals(200, t1.getBalance(), delta);

        // transfer .01 more than balance -boundary case
        BankAccount f2 = new CheckingAccount("from@doma.in", 200);
        BankAccount t2 = new CheckingAccount("to@doma.in", 200);
        assertThrows(InsufficientFundsException.class, () -> f2.transfer(t2, 200.01));
        assertEquals(200, f2.getBalance(), delta);
        assertEquals(200, t2.getBalance(), delta);

        // transfer negative amount
        BankAccount f3 = new CheckingAccount("from@doma.in", 200);
        BankAccount t3 = new CheckingAccount("to@doma.in", 200);
        assertThrows(IllegalArgumentException.class, () -> f3.transfer(t3, -20));
        assertEquals(200, f3.getBalance(), delta);
        assertEquals(200, t3.getBalance(), delta);

        // transfer -.01 -boundary case
        BankAccount f4 = new CheckingAccount("from@doma.in", 200);
        BankAccount t4 = new CheckingAccount("to@doma.in", 200);
        assertThrows(IllegalArgumentException.class, () -> f4.transfer(t4, -.01));
        assertEquals(200, f4.getBalance(), delta);
        assertEquals(200, t4.getBalance(), delta);

        // transfer to null BankAccount
        BankAccount f5 = new CheckingAccount("from@doma.in", 200);
        BankAccount t5 = null;
        assertThrows(IllegalArgumentException.class, () -> f5.transfer(t5, -.01));
        assertEquals(200, f5.getBalance(), delta);
    }

    @Test
    void isEmailValidTest() {
        // VALID
        // normal cases
        assertTrue(BankAccountInterface.isEmailValid("abc-d@mail.com"));
        assertTrue(BankAccountInterface.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccountInterface.isEmailValid("abc_def@mail.com"));
        assertTrue(BankAccountInterface.isEmailValid("abc.def@mail-archive.com"));

        // single char prefix -boundary case
        assertTrue(BankAccountInterface.isEmailValid("a@domain.com"));
        // single char low level domain -boundary case
        assertTrue(BankAccountInterface.isEmailValid("prefix@b.com"));
        // single char prefix and low level domain -boundary case
        assertTrue(BankAccountInterface.isEmailValid("a@b.com"));

        // special char after leading char in prefix -boundary case
        assertTrue(BankAccountInterface.isEmailValid("p_refix@domain.com"));
        // special char before tailing char in prefix -boundary case
        assertTrue(BankAccountInterface.isEmailValid("prefi.x@domain.com"));

        // special char after leading char in prefix -boundary case
        assertTrue(BankAccountInterface.isEmailValid("prefix@d-omain.com"));
        // special char before tailing char in prefix -boundary case
        assertTrue(BankAccountInterface.isEmailValid("prefix@domai-n.com"));
        
        // 'almost' consecutive special characters in prefix -boundary case
        assertTrue(BankAccountInterface.isEmailValid("p_r.efix@domain.com"));
        // 'almost' consecutive special characters in domain -boundary case
        assertTrue(BankAccountInterface.isEmailValid("prefix@d-o-main.com"));


        // EMPTY
        // empty prefix
        assertFalse(BankAccountInterface.isEmailValid("@domain.com"));
        assertFalse(BankAccountInterface.isEmailValid("domain.com"));

        // first part of domain empty
        assertFalse(BankAccountInterface.isEmailValid("foo@.bar"));
        assertFalse(BankAccountInterface.isEmailValid("prefix.do"));

        // last part of domain empty -boundary cases
        assertFalse(BankAccountInterface.isEmailValid("abc@def."));
        assertFalse(BankAccountInterface.isEmailValid("aaa@xyz"));

        // entire domain empty -boundary cases
        assertFalse(BankAccountInterface.isEmailValid("zoz@."));
        assertFalse(BankAccountInterface.isEmailValid("qwerty@"));

        // missing sections -boundary cases
        assertFalse( BankAccountInterface.isEmailValid(""));
        assertFalse( BankAccountInterface.isEmailValid("@"));
        assertFalse( BankAccountInterface.isEmailValid("."));
        assertFalse( BankAccountInterface.isEmailValid("@."));


        // INVALID PREFIX
        // invalid characters
        assertFalse(BankAccountInterface.isEmailValid("inv@lid123@foo.cc"));
        assertFalse(BankAccountInterface.isEmailValid("is.wr*ong42@mail-archive.com"));
        assertFalse(BankAccountInterface.isEmailValid("!nc0rre(t.domain@abc.gov"));
        assertFalse(BankAccountInterface.isEmailValid("n*t_g**d@foo-bar.zzzz"));

        // leading '_', '.', '-'
        assertFalse(BankAccountInterface.isEmailValid("_leadingunderscore@test.go"));
        assertFalse(BankAccountInterface.isEmailValid("-this.not@good-email.com"));
        assertFalse(BankAccountInterface.isEmailValid(".pre.fix@doma.in"));
        assertFalse(BankAccountInterface.isEmailValid("_@check.it"));

        // trailing '_', '.', '-'
        assertFalse(BankAccountInterface.isEmailValid("leadingunderscore_@test.go"));
        assertFalse(BankAccountInterface.isEmailValid("this.not-@good-email.com"));
        assertFalse(BankAccountInterface.isEmailValid("pre.fix.@doma.in"));
        assertFalse(BankAccountInterface.isEmailValid(".@check.it"));


        // INVALID DOMAIN
        // invalid characters
        assertFalse(BankAccountInterface.isEmailValid("domain@uh#oh.com"));
        assertFalse(BankAccountInterface.isEmailValid("foo-bar.baz@jun%.be"));
        assertFalse(BankAccountInterface.isEmailValid("abc@def^ghi.jklm"));

        // leading '-'
        assertFalse(BankAccountInterface.isEmailValid("prefix@-abc.com"));
        assertFalse(BankAccountInterface.isEmailValid("prefix@-abc-def.uk"));
        assertFalse(BankAccountInterface.isEmailValid("prefix@-.com"));

        // trailing '-'
        assertFalse(BankAccountInterface.isEmailValid("prefix@abc-.com"));
        assertFalse(BankAccountInterface.isEmailValid("prefix@abc.com-"));
        assertFalse(BankAccountInterface.isEmailValid("prefix@abc-def-.com"));
        assertFalse(BankAccountInterface.isEmailValid("prefix@abc-def.com-"));

        // last part of domain length < two characters
        assertFalse(BankAccountInterface.isEmailValid("abc@def.a"));
        assertFalse(BankAccountInterface.isEmailValid("abc@def-a.b"));
    }
   
    @Test
    void constructorTest() {
        BankAccount bankAccount = new CheckingAccount("a@b.com", 200);
        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("", 100));

        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.com", -53));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.com", .125));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.com", 120.369));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.com", -3.14));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.com", -52.999));
    }

    @Test
    void accrewInterestTest() {
        final double delta = .001;

        SavingsAccount savings = new SavingsAccount("a@b.com", 100, .05); //basic use
        savings.accrewInterest();
        assertEquals(105, savings.getBalance(), delta);

        SavingsAccount savings2 = new SavingsAccount("a@b.com", 0, .05); //balance is 0
        savings2.accrewInterest();
        assertEquals(0, savings2.getBalance(), delta);

        SavingsAccount savingsAccount = new SavingsAccount("a@b.com", 200, 0);
        savingsAccount.accrewInterest();
        assertEquals(200, savingsAccount.getBalance(), delta);

        savingsAccount = new SavingsAccount("a@b.com", 200, 1);
        savingsAccount.accrewInterest();
        assertEquals(400, savingsAccount.getBalance(), delta);

        savingsAccount = new SavingsAccount("a@b.com", 200, .1);
        savingsAccount.accrewInterest();
        assertEquals(220, savingsAccount.getBalance(), delta);
    
    }
}