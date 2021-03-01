package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CentralBankTest {
    @Test
    public void constructorTest() {
        BankController bankController = new BankController();
        Admin admin = new Admin(bankController);
        CentralBank centralBank = new CentralBank(bankController, admin);

        assertEquals(bankController, centralBank.getBankController());
        assertEquals(admin, centralBank.getAdmin());
    }

    @Test
    public void getTellersTest(){
        BankController bankController = new BankController();
        Admin admin = new Admin(bankController);
        CentralBank centralBank = new CentralBank(bankController, admin);
        System.out.println("List of Central Bank Tellers: "+centralBank.getTellers());
    }

 }