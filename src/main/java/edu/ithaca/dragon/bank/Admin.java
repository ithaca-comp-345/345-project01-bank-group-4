package edu.ithaca.dragon.bank;
 
public class Admin {
    private BankController bankController;
 
    public Admin(BankController bankController) {
        if (bankController == null) {
            throw new NullPointerException();
        } else {
            this.bankController = bankController;
        }
    }
 
    public BankController getBankController() {
        return bankController;
    }
 
    public double checkOverallAmount() {
        return -1;
    }
 
    public String checkSuspiciousActivity() {
        return null;
    }
 
    public void setSuspicious(int accountId, boolean suspicious) {
    }
 
    public void setFrozen(int accountId, boolean frozen) {
    }
}
