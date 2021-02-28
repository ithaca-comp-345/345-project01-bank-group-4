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
