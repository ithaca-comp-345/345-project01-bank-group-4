package edu.ithaca.dragon.bank;
 
public class HumanTeller extends Teller {
 
    public HumanTeller(BankController bankController) {
        super(bankController);
    }
    
    public int createAccount(char accountType) {
        return bankController.createAccount(accountType);
    }

    public void closeAccount() {
        bankController.closeAccount(currAccountId);
    }
}
