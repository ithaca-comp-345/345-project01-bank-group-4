package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

public class BankAdminTest {
    @Test
    void getOverallAmountTest() {
        Administrator admin = new Administrator();
        CentralBank centralBank = new CentralBank();
        
        centralBank.createUser('abc@123.com', 'password');
    }
}
