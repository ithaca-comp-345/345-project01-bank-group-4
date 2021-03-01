package edu.ithaca.dragon.bank;

import java.util.List;

public abstract class Teller {
    protected BankController bankController;
    protected int currAccountId;
    protected List<String> history;
 
    public Teller(BankController bankController) {
        if (bankController == null) {
            throw new NullPointerException();
        } 
        else {
            this.bankController = bankController;
            confirmCredentials(bankController.);
            history.add("Account History: "+ bankController.retrieveTransactionHistory(currAccountId)+"\n");
        }
    }   
 
    public BankController getBankController() {
        return bankController;
    }
 
    public int getCurrAccountId() {
        return currAccountId;
    }
 
    public void confirmCredentials(int accountId) {
        for ( Integer key : bankController.getAccounts().keySet() ) {
            if(key == accountId){
                this.currAccountId = accountId;
            }
        }
    }
 
    public double checkBalance() {
        return bankController.checkBalance(currAccountId);
    }
 
    public void withdraw(double amount) {
        Account acc = bankController.retrieveAccount(currAccountId);
        acc.withdraw(amount);
        history.add("W " + amount +" | ");
    }
 
    public void deposit(double amount) {
        Account acc = bankController.retrieveAccount(currAccountId);
        acc.deposit(amount);
        history.add("D " + amount +" | ");
    }
 
    public void transfer(int accountIdTo, double amount) {
        Account acc1 = bankController.retrieveAccount(currAccountId);
        Account acc2 = bankController.retrieveAccount(accountIdTo);
        acc1.transfer(acc2, amount);
        history.add("T " + amount + "from "+ acc1+ "to"+ acc2 +" | ");

    }
 
    public String retrieveTransactionHistory() {
        return history.toString();
    }
}
