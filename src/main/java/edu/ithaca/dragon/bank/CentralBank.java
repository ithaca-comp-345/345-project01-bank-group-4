package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;
 
public class CentralBank {
    private BankController bankController;
    private Admin admin;
    private List<Teller> tellers;
    
    public CentralBank(BankController bankController, Admin admin) {
        if (bankController == null || admin == null) {
            throw new NullPointerException();
        } else {
            this.bankController = bankController;
            this.admin = admin;
            tellers = new ArrayList<>();
            tellers.add(new ATM(bankController));
            tellers.add(new HumanTeller(bankController));
        }
    }

    public BankController getBankController() {
        return bankController;
    }

    public Admin getAdmin() {
        return admin;
    }

    public List<Teller> getTellers() {
        return tellers;
    }

    public static void main(String[] args) {
        BankController bankController = new BankController();
        Admin admin = new Admin(bankController);

        CentralBank centralBank = new CentralBank(bankController, admin);
        Teller myTeller = centralBank.getTellers().get(1);
        myTeller.confirmCredentials(-2);
        System.out.println(myTeller.checkBalance());

    }
}
