package edu.ithaca.dragon.bank;
 
public class HumanTeller extends Teller {
 
    public HumanTeller(BankController bankController) {
        super(bankController);
    }
    
    public void createAccount(char accountType) {
        if (accountType == 'c'){
            bankController.createChecking(100);
        }
        else if (accountType == 's'){
            bankController.createSavings(100, 0.1);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public void closeAccount() {
        bankController.closeAccount(currAccountId);
    }
}
