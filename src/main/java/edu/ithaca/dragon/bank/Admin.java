package edu.ithaca.dragon.bank;

public class Admin {
    private BankController bankController;

    public Admin(BankController bankController) {
        this.bankController = bankController;
    }

    public BankController getBankController() {
        return bankController;
    }

    public double checkOverallAmount() {
        // TODO implement checkOverallAmount
        return 0;
    }

    public String checkSuspiciousActivity() {
        // TODO implement checkSuspiciousActivity
        return "";
    }

    public void freeze(int accountId) {
        // TODO implement freeze
    }

    public void unfreeze(int accountId) {
        // TODO implement unfreeze
    }
}
