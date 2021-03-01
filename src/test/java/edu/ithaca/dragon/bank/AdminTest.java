package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    }
 
    // System Test
    @Test
    public void setSuspiciousTest() {
    }
 
    // System Test
    @Test
    public void setFrozenTest() {
    }
}
