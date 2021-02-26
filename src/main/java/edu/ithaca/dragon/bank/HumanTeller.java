package edu.ithaca.dragon.bank;
 
public class HumanTeller extends Teller {
 
    public HumanTeller(BankController bankController) {
        super(bankController);
    }
    
    public int createAccount(char accountType) {
        return -1;
    }
 
    public void closeAccount() {
    }
}
