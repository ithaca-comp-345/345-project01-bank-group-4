package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AdminTest {
    @Test
    public void constructorTest() {
        Admin admin;
        BankController bankController;

        // VALID
        bankController = new BankController();
        admin = new Admin(bankController);
        assertEquals(bankController, admin.getBankController());

        // INVALID

        assertThrows(NullPointerException.class, () -> new Admin(null));
    }
  
    // System Test
    @Test
    public void checkOverallAmountTest() {
        BankController bankController = new BankController();
        Admin admin = new Admin(bankController);

        // no accounts -boundary case
        assertEquals(0, admin.checkOverallAmount());

        // 0 amount -boudary case
        bankController.createSavings(0, .04);
        assertEquals(0, admin.checkOverallAmount());

        // multiple accounts
        bankController.createChecking(200);
        bankController.createSavings(100, .03);
        bankController.createChecking(300);

        assertEquals(600, admin.checkOverallAmount());
    }
 
    // System Test
    @Test
    public void checkSuspiciousActivityTest() {
        BankController bankController = new BankController();
        Admin admin = new Admin(bankController);

        int accountId1, accountId2;

        // no accounts -boundary case
        assertEquals("Suspicious Accounts: ", admin.checkSuspiciousActivity());

        // no suspicious accounts -boudary case
        bankController.createSavings(0, .04);
        assertEquals("Suspicious Accounts: ", admin.checkSuspiciousActivity());

        // 1 suspicious account
        accountId1 = bankController.createChecking(200);
        bankController.setSuspicious(accountId1, true);
        assertEquals("Suspicious Accounts: " + accountId1 + ", ", admin.checkSuspiciousActivity());


        // multiple accounts
        bankController.createSavings(0, .5);
        accountId2 = bankController.createChecking(100);
        bankController.setSuspicious(accountId2, true);
        String suspiciousActivity = admin.checkSuspiciousActivity();
        assertTrue(suspiciousActivity.contains(accountId1 + "") && suspiciousActivity.contains(accountId2 + ""));
    }
 
    // System Test
    @Test
    public void setSuspiciousTest() {
        BankController bankController = new BankController();
        Admin admin = new Admin(bankController);

        int accountId1, accountId2;

        // no accounts -boundary case
        assertEquals("Suspicious Accounts: ", admin.checkSuspiciousActivity());

        // no suspicious accounts -boudary case
        bankController.createSavings(0, .04);
        assertEquals("Suspicious Accounts: ", admin.checkSuspiciousActivity());

        // 1 suspicious account
        accountId1 = bankController.createChecking(200);
        admin.setSuspicious(accountId1, true);
        assertEquals("Suspicious Accounts: " + accountId1 + ", ", admin.checkSuspiciousActivity());

        // multiple accounts
        bankController.createSavings(0, .5);
        accountId2 = bankController.createChecking(100);
        admin.setSuspicious(accountId2, true);
        String suspiciousActivity = admin.checkSuspiciousActivity();
        assertTrue(suspiciousActivity.contains(accountId1 + "") && suspiciousActivity.contains(accountId2 + ""));
    }
 
    // System Test
    @Test
    public void setFrozenTest() {
    }
}
