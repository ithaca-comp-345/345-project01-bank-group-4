package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class BankControllerTest {
    BankController controller;
    int acc1;
    int acc2;
    int acc3; 
    int acc4;

    public void createTestAccounts() {
        controller = new BankController();
        acc1 = controller.createChecking(100);
        acc2 = controller.createSavings(200, .01);
        acc3 = controller.createChecking(300);
        acc4 = controller.createChecking(0);
    }

    @Test
    public void runAllTests() {
        constructorTest();
        getAccountsTest();
        retrieveAccountTest();
        checkBalanceTest();
        withdrawTest();
        depositTest();
        transferTest();
        retrieveTransactionHistoryTest();
        createCheckingTest();
        createSavingsTest();
        closeAccountTest();
        checkOverallAmountTest();
        checkSuspiciousActivityTest();
        setSuspiciousTest();
        isSuspiciousTest();
        setFrozenTest();
    }

    @Test
    public void constructorTest() {
    }
 
    @Test
    public void getAccountsTest() {
        // Create accounts with different values of properties
        createTestAccounts();

        // Get accounts Map
        Map<Integer, Account> accounts = controller.getAccounts();

        // Check accounts are the same
        assertEquals(100, ((Account) accounts.get(acc1)).getBalance());
        assertEquals(200, ((Account) accounts.get(acc2)).getBalance());
        assertEquals(.01, ((SavingsAccount) accounts.get(acc2)).getInterestRate());
        assertEquals(300, ((Account) accounts.get(acc3)).getBalance());
    }
 
    @Test
    public void retrieveAccountTest() {
        // Create accounts of both types, different values in properties
        createTestAccounts();

        // Retrieve accounts and check for correct balances
        assertEquals(100, controller.retrieveAccount(acc1).getBalance());
        assertEquals(200, controller.retrieveAccount(acc2).getBalance());

        // @throws IllegalArgumentException if no account is found
        assertThrows(IllegalArgumentException.class, () -> controller.retrieveAccount(999));
    }
 
    @Test
    public void checkBalanceTest() {
        // Create accounts with different values of properties
        createTestAccounts();

        // Check balances return correctly
        assertEquals(100, controller.checkBalance(acc1));
        assertEquals(200, controller.checkBalance(acc2));
        assertEquals(0, controller.checkBalance(acc4));

        // @throws IllegalArgumentException when account ID doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {controller.checkBalance(999);});
    }
 
    @Test
    public void withdrawTest() {
        createTestAccounts();
        
        Map<Integer, Account> accounts = controller.getAccounts();
        assertEquals(100, ((Account) accounts.get(acc1)).getBalance());
        assertEquals(200, ((Account) accounts.get(acc2)).getBalance());
        assertEquals(300, ((Account) accounts.get(acc3)).getBalance());
        assertEquals(0, ((Account) accounts.get(acc4)).getBalance());

        controller.withdraw(acc1, 50);
        controller.withdraw(acc2, 75.12);
        controller.withdraw(acc3, 299.99);

        // Check that values were properly adjusted
        assertEquals(50, ((Account) accounts.get(acc1)).getBalance());
        assertEquals(124.88, ((Account) accounts.get(acc2)).getBalance());
        assertEquals(.01, ((Account) accounts.get(acc3)).getBalance());
        assertEquals(0, ((Account) accounts.get(acc4)).getBalance());

        // Checking edge case decimals, and getting to zero
        controller.withdraw(acc1, .1);
        controller.withdraw(acc3, .01);

        assertEquals(49.90, ((Account) accounts.get(acc1)).getBalance());
        assertEquals(0, ((Account) accounts.get(acc3)).getBalance());

        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc4, .01);}); // Insufficient funds decimal
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc4, 100000);}); // Insufficient funds when withdrawing higher value
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc2, 100000);}); // Insufficient funds withdrawing higher value from higher balance

        // Illegal inputs for withdrawal
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc2, 0);});
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc2, -1);});
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc2, .011);});
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc2, -.111);});
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(acc2, -.1);});

        // @throws IllegalArgumentException when account ID doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {controller.withdraw(999, 1);});
    }
 
    @Test
    public void depositTest() {
        createTestAccounts();

        Map<Integer, Account> accounts = controller.getAccounts();
        assertEquals(100, ((Account) accounts.get(acc1)).getBalance());
        assertEquals(200, ((Account) accounts.get(acc2)).getBalance());
        assertEquals(300, ((Account) accounts.get(acc3)).getBalance());
        assertEquals(0, ((Account) accounts.get(acc4)).getBalance());

        // Checking different decimal values
        controller.deposit(acc1, .01);
        controller.deposit(acc2, .1);
        controller.deposit(acc3, 1);
        controller.deposit(acc4, 10);

        assertEquals(100.01, ((Account) accounts.get(acc1)).getBalance());
        assertEquals(200.1, ((Account) accounts.get(acc2)).getBalance());
        assertEquals(301, ((Account) accounts.get(acc3)).getBalance());
        assertEquals(10, ((Account) accounts.get(acc4)).getBalance());

        // Check large numbers
        controller.deposit(acc4, 1000000);
        assertEquals(1000010, ((Account) accounts.get(acc4)).getBalance());

        // @throws IllegalArgumentException for illegal decimals and negatives
        assertThrows(IllegalArgumentException.class, () -> {controller.deposit(acc1, .001);});
        assertThrows(IllegalArgumentException.class, () -> {controller.deposit(acc1, -.001);});
        assertThrows(IllegalArgumentException.class, () -> {controller.deposit(acc1, 0);});
        assertThrows(IllegalArgumentException.class, () -> {controller.deposit(acc1, -1);});
        assertThrows(IllegalArgumentException.class, () -> {controller.deposit(acc1, -10000);});

        // @throws IllegalArgumentException when account ID doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {controller.deposit(999, 1);});
    }
 
    @Test
    public void transferTest() {
        createTestAccounts();

        Map<Integer, Account> accounts = controller.getAccounts();
        assertEquals(100, ((Account) accounts.get(acc1)).getBalance());
        assertEquals(200, ((Account) accounts.get(acc2)).getBalance());
        assertEquals(300, ((Account) accounts.get(acc3)).getBalance());
        assertEquals(0, ((Account) accounts.get(acc4)).getBalance());

        controller.transfer(acc1, acc2, 50);
        assertEquals(50, controller.checkBalance(acc1));
        assertEquals(250, controller.checkBalance(acc2));

        // Valid decimals
        controller.transfer(acc1, acc2, .1);
        assertEquals(49.9, controller.checkBalance(acc1));
        assertEquals(250.1, controller.checkBalance(acc2));
        controller.transfer(acc1, acc2, .01);
        assertEquals(49.89, controller.checkBalance(acc1));
        assertEquals(250.11, controller.checkBalance(acc2));

        // Transfer to 0
        controller.transfer(acc1, acc2, 49.89);
        assertEquals(0, controller.checkBalance(acc1));
        assertEquals(300, controller.checkBalance(acc2));

        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(acc1, acc2, 100);}); // Overdraw acc1
        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(acc1, acc2, -1);}); // Negative transfer
        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(acc1, acc2, .001);}); // Invalid Decimal
        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(acc1, acc2, -.1);}); // Valid decimal but negative
        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(acc1, acc2, -100000);}); // Negative large number
        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(acc1, acc1, 1);}); // Transfer to and from same account

        // @throws IllegalArgumentException when account ID doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(999, acc2, 10);});
        assertThrows(IllegalArgumentException.class, () -> {controller.transfer(acc1, 999, 10);});

    }
 
    @Test
    public void retrieveTransactionHistoryTest() {
        //TODO decide format for transaction history in Account class
        createTestAccounts();

        controller.deposit(acc1, 10);
        controller.withdraw(acc1, 29.11);
        controller.deposit(acc1, .01);

        assertEquals("10.0 | d 10.0 | w 29.11 | d 0.01 | ", controller.retrieveTransactionHistory(acc1));

        // @throws IllegalArgumentException when account ID doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {controller.retrieveTransactionHistory(999);});
    }
 
    @Test
    public void createCheckingTest() {
        controller = new BankController();
        int checking1 = controller.createChecking(0); // 0 starting balance
        int checking2 = controller.createChecking(.01); // Valid decimal starting balances
        int checking3 = controller.createChecking(.1);
        int checking4 = controller.createChecking(1); // Whole number starting balances
        int checking5 = controller.createChecking(1000);

        assertEquals(0, controller.checkBalance(checking1));
        assertEquals(.01, controller.checkBalance(checking2));
        assertEquals(.1, controller.checkBalance(checking3));
        assertEquals(1, controller.checkBalance(checking4));
        assertEquals(1000, controller.checkBalance(checking5));

        assertThrows(IllegalArgumentException.class, () -> {controller.createChecking(-1);}); // Negative starting balance
        assertThrows(IllegalArgumentException.class, () -> {controller.createChecking(.001);}); // Invalid decimal
        assertThrows(IllegalArgumentException.class, () -> {controller.createChecking(-.001);}); // Negative and invalid decimal
        assertThrows(IllegalArgumentException.class, () -> {controller.createChecking(10.001);}); // Invalid decimal > .01
        assertThrows(IllegalArgumentException.class, () -> {controller.createChecking(-10.001);}); // Negative invalid decimal > .01
    }

    @Test
    public void createSavingsTest() {
        controller = new BankController();
        int savings1 = controller.createSavings(0, .01); // 0 starting balance, 1% interest
        int savings2 = controller.createSavings(.01, .001); // Valid decimal starting balances, .1% interest
        int savings3 = controller.createSavings(.1, .1); // 10% interest
        int savings4 = controller.createSavings(1, .0001); // Whole number starting balances, .01% interest
        int savings5 = controller.createSavings(1000, .01);

        assertEquals(0, controller.checkBalance(savings1));
        assertEquals(.01, controller.checkBalance(savings2));
        assertEquals(.1, controller.checkBalance(savings3));
        assertEquals(1, controller.checkBalance(savings4));
        assertEquals(1000, controller.checkBalance(savings5));

        assertThrows(IllegalArgumentException.class, () -> {controller.createSavings(-1, .1);}); // Negative starting balance
        assertThrows(IllegalArgumentException.class, () -> {controller.createSavings(.001, .1);}); // Invalid decimal
        assertThrows(IllegalArgumentException.class, () -> {controller.createSavings(-.001, .1);}); // Negative and invalid decimal
        assertThrows(IllegalArgumentException.class, () -> {controller.createSavings(10.001, .1);}); // Invalid decimal > .01
        assertThrows(IllegalArgumentException.class, () -> {controller.createSavings(-10.001, .1);}); // Negative invalid decimal > .01

        assertThrows(IllegalArgumentException.class, () -> {controller.createSavings(10, -.1);}); // Negative interest
        
        //assertThrows(IllegalArgumentException.class, () -> {controller.createSavings(10, .00001);}); // Only allow 4 decimal places

        // TODO should we allow more 100% interest and above? Where should the cutoff be?
        // TODO should we allow more decimal places than 12.34%?
    }
 
    @Test
    public void closeAccountTest() {
        createTestAccounts();

        controller.closeAccount(acc1);
        //Check that other accounts are unaffected
        assertEquals(200, controller.checkBalance(acc2));
        assertEquals(300, controller.checkBalance(acc3));
        assertEquals(0, controller.checkBalance(acc4));
        assertThrows(IllegalArgumentException.class, () -> controller.checkBalance(acc1));

        controller.closeAccount(acc4);
        // Check that other accounts are unaffected
        assertEquals(200, controller.checkBalance(acc2));
        assertEquals(300, controller.checkBalance(acc3));
        assertThrows(IllegalArgumentException.class, () -> controller.checkBalance(acc1));
        assertThrows(IllegalArgumentException.class, () -> controller.checkBalance(acc4));

        // @throws IllegalArgumentException when trying to remove an account that doesn't exist
        assertThrows(IllegalArgumentException.class, () -> controller.closeAccount(999));
    }
 
    @Test
    public void checkOverallAmountTest() {
        createTestAccounts();
        assertEquals(600, controller.checkOverallAmount());
    }
 
    @Test
    public void checkSuspiciousActivityTest() {
        createTestAccounts();
        controller.setSuspicious(acc1, true);
        assertEquals("Suspicious Accounts: " + acc1, controller.checkSuspiciousActivity());
        controller.setSuspicious(acc4, true);
        assertEquals("Suspicious Accounts: " + acc1 + ", " + acc4, controller.checkSuspiciousActivity());
    }
 
    @Test
    public void setSuspiciousTest() {
        createTestAccounts();
        controller.setSuspicious(acc1, true);
        assertEquals("Suspicious Accounts: " + acc1, controller.checkSuspiciousActivity());
        controller.setSuspicious(acc4, true);
        assertEquals("Suspicious Accounts: " + acc1 + ", " + acc4, controller.checkSuspiciousActivity());

        // @throws IllegalArgumentException when account ID input does not exist
        assertThrows(IllegalArgumentException.class, () -> controller.setSuspicious(999, true));
    }

    @Test
    public void isSuspiciousTest(){
        createTestAccounts();
        controller.setSuspicious(acc1, true);
        assertTrue(controller.isSuspicious(acc1));
        controller.setSuspicious(acc1, false);
        assertFalse(controller.isSuspicious(acc1));

        // @throws IllegalArgumentException when account ID input does not exist
        assertThrows(IllegalArgumentException.class, () -> controller.isSuspicious(999));
    }
 
    @Test
    public void setFrozenTest() {
        createTestAccounts();
        controller.setFrozen(acc1, true);
        //@throws AccountFrozenException wehn attempting to withdraw, deposit or transfer
        assertThrows(AccountFrozenException.class, () -> controller.withdraw(acc1, 10));
        assertThrows(AccountFrozenException.class, () -> controller.deposit(acc1, 10));
        assertThrows(AccountFrozenException.class, () -> controller.transfer(acc1, acc2, 10));
        assertThrows(AccountFrozenException.class, () -> controller.transfer(acc2, acc1, 10));

        controller.setFrozen(acc1, false);
        // Check that these functionalities work after unfrozen
        controller.withdraw(acc1, 10);
        assertEquals(90, controller.checkBalance(acc1));
        controller.deposit(acc1, 10);
        assertEquals(100, controller.checkBalance(acc1));
        controller.transfer(acc1, acc2, 10);
        assertEquals(90, controller.checkBalance(acc1));
        assertEquals(210, controller.checkBalance(acc2));
        controller.transfer(acc2, acc1, 10);
        assertEquals(100, controller.checkBalance(acc1));
        assertEquals(200, controller.checkBalance(acc2));    
    }
}
