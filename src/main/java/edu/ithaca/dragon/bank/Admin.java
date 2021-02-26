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
        return bankController.checkOverallAmount();
    }

    public String checkSuspiciousActivity() {
        return bankController.checkSuspiciousActivity();
    }

    public void setSuspicious(int accountId, boolean suspicious) {
        bankController.setSuspicious(accountId, suspicious);
    }

    public void setFrozen(int accountId, boolean frozen) {
        bankController.setFrozen(accountId, frozen);
    }
}
