package edu.ithaca.dragon.bank;


public abstract class Teller {
    protected BankController bankController;
    protected int currAccountId;
    protected String history;
 
    public Teller(BankController bankController) {
        if (bankController == null) {
            throw new NullPointerException();
        } 
        else {
            this.bankController = bankController;
            this.history = "";
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
        history+="w " + amount + " | ";
    }
 
    public void deposit(double amount) {
        Account acc = bankController.retrieveAccount(currAccountId);
        acc.deposit(amount);
        history+="d " + amount + " | ";
    }
 
    public void transfer(int accountIdTo, double amount) {
        Account acc1 = bankController.retrieveAccount(currAccountId);
        Account acc2 = bankController.retrieveAccount(accountIdTo);
        acc1.withdraw(amount);
        acc2.deposit(amount);
        history += "t " + amount + " from  "+ " to "+ acc2 + " | ";
        

    }
 
    public String retrieveTransactionHistory() {
        if (history == null || history == ""){
            System.out.println("Transaction History for "+ bankController.retrieveAccount(currAccountId)+ "\n"+ "No History");
        }
        else{
        System.out.println("Transaction History for "+ bankController.retrieveAccount(currAccountId)+ "\n"+ bankController.retrieveAccount(currAccountId).getTransactionHistory() +history);
        }
        return history;
    }
}
