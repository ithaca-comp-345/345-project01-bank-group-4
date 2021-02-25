package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private BankController bankController;
    private Admin admin;
    private List<Teller> tellers;
    
    public CentralBank(BankController bankController, Admin admin) {
        this.bankController = bankController;
        this.admin = admin;
        tellers = new ArrayList<>();
        // TODO add defaul teller
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
}
