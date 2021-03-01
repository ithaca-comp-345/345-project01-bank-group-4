package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TellerTest {
    @Test
    public void constructorTest() {
        BankController bankController = new BankController();
        //default accounts
        int accC= bankController.createChecking(100);
        int accS = bankController.createSavings(100, .10);

        Teller t = new ATM(bankController);
        assertEquals(bankController,t.getBankController());
        System.out.println(bankController.getAccounts());
        System.out.println(bankController.getAccounts().keySet());
    }
 
    @Test
    public void getBankControllerTest() {
    }
 
    @Test
    public void getCurrAccountIdTest() {
    }
 
    @Test
    public void confirmCredentialsTest() {
    }
 
    @Test
    public void checkBalanceTest() {
    }
 
    @Test
    public void withdrawTest() {
    }
 
    @Test
    public void depositTest() {
    }
 
    @Test
    public void transferTest() {
    }
 
    @Test
    public void retrieveTransactionHistoryTest() {
    }
}
