package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TellerTest {
    @Test
    public void constructorTest() {
        BankController bankController = new BankController();
        Teller aT = new ATM(bankController);
        Teller hT = new HumanTeller(bankController);
        assertEquals(bankController, aT.getBankController());
        assertEquals(bankController, hT.getBankController());

        //default accounts
        int accC = bankController.createChecking(100);
        int accS = bankController.createSavings(100, 0.1);

        //Tests for ATM
        for ( int key : bankController.getAccounts().keySet() ) {
            if(key == aT.getCurrAccountId()){
                aT.confirmCredentials(accC);
                assertEquals(key, aT.getCurrAccountId());
            }
            else if(key == hT.getCurrAccountId()){
                aT.confirmCredentials(accS);
                assertEquals(key, hT.getCurrAccountId());
            }
            
        }
    }
 
   //System test
    @Test
    public void retrieveTransactionHistoryTest() {
        BankController bankController = new BankController();
        Teller aT = new ATM(bankController);
        Teller hT = new HumanTeller(bankController);
        
        //ATM Transaction History
        /*Checking*/
        int accCheck = aT.bankController.createChecking(100);
        int accSave = aT.bankController.createSavings(100, 0.1);
        aT.confirmCredentials(accCheck);
        assertEquals(100, aT.checkBalance());
        assertEquals("",aT.retrieveTransactionHistory());
        assertEquals(100, aT.checkBalance());
        assertThrows(IllegalArgumentException.class, () -> {aT.withdraw(-1);});
        assertThrows(IllegalArgumentException.class, () -> {aT.withdraw(200);});
        aT.withdraw(50);
        assertEquals(50, aT.checkBalance());
        assertEquals("w 50.0 | ",aT.retrieveTransactionHistory());

        /*Saving*/
        aT.confirmCredentials(accSave);
        assertEquals(100, aT.checkBalance());
        aT.deposit(100);
        aT.confirmCredentials(accSave);
        assertThrows(IllegalArgumentException.class, () -> {aT.deposit(-1);});
        assertEquals(200, aT.checkBalance());
        assertEquals("w 50.0 | d 100.0 | ", aT.retrieveTransactionHistory());

         /*Transfering*/
         aT.transfer(accCheck, 10);
         assertEquals(190, aT.checkBalance());
         //assertEquals("w 50.0 | d 100.0 | t 10.0 from  edu.ithaca.dragon.bank.SavingsAccount@5700d6b1 to edu.ithaca.dragon.bank.CheckingAccount@402a079c | ",aT.retrieveTransactionHistory());

        //Human-Teller Transaction History
        /*Checking*/
        /* accCheck = hT.bankController.createChecking(100);
        accSave = hT.bankController.createSavings(100, 0.1);
        aT.confirmCredentials(accCheck);
        assertEquals(100, hT.checkBalance());
        aT.withdraw(50);
        aT.transfer(accSave, 50);
        //assertEquals(expected, );
        aT.confirmCredentials(accSave);
        assertEquals(100, hT.checkBalance()); */
         
        /*Saving*/

        

    }
}
