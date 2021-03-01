package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
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
            //default tellers
            tellers = new ArrayList<>();
            tellers.add(new HumanTeller(bankController));
            tellers.add(new ATM(bankController));
            
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
        /* //mini-tester
        Teller myTeller = centralBank.getTellers().get(1);
        myTeller.confirmCredentials(-2);
        System.out.println(myTeller.checkBalance());
 */
        Scanner user = new Scanner(System.in);
        System.out.println("Welcome to Central Bank\n");
        BankController bankController = new BankController();
        Admin admin = new Admin(bankController);
        CentralBank centralBank = new CentralBank(bankController, admin);
        System.out.println("Please Sign in. Email:");
        user.nextLine();
        System.out.println("Password:");
        while (user.nextLine() != "done") {
            System.out.println("Select a Teller: 1) Human or 2) ATM");
            int teller = user.nextInt();
            while(teller < 1 || teller>=3 ){
                    System.out.println("Select a Teller: 1) Human or 2) ATM");
                    teller = user.nextInt();
            }
            System.out.println("Woud you like to work in your checkings [1] or savings [2] account?");
            int accountChoice = user.nextInt();
            while(accountChoice<1 || accountChoice >=3){
                System.out.println("Woud you like to work in your checkings [1] or savings [2] account?");
                accountChoice = user.nextInt();
            }
            //HUMAN
            if (accountChoice == 1){
                System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            int action = user.nextInt();
            if (teller == 1){
                Teller hT = centralBank.getTellers().get(0);
            while(action != 7){
                int check = hT.bankController.createChecking(100);
                hT.confirmCredentials(check);
                if(action == 1){
                    System.out.print("Balance: "+ hT.checkBalance()+ "\n");
                    System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                    action = user.nextInt();
                }
                else if(action == 2){
                    System.out.println("Enter in deposit amount: ");
                    double deposit = user.nextDouble();
                    hT.deposit(deposit);
                    System.out.print("Balance: "+ hT.checkBalance()+ "\n");
                    System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                    action = user.nextInt();
                }
                else if(action == 3){
                    System.out.println("Enter in withdraw amount: ");
                    double withdraw = user.nextDouble();
                    hT.withdraw(withdraw);
                    System.out.print("Balance: "+ hT.checkBalance()+ "\n");
                    System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                    action = user.nextInt();
                }
                else if(action == 4){
                    hT.bankController.checkSuspiciousActivity();
                    System.out.print("Your account "+hT.currAccountId+ " is "+hT.bankController.isSuspicious(hT.currAccountId)+ "\n");
                    System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                    action = user.nextInt();
                }
                else if(action == 5){
                    hT.bankController.setFrozen(check, true);
                    System.out.print("Your account "+hT.currAccountId+ " is "+hT.bankController.retrieveAccount(hT.currAccountId).isFrozen()+"\n");
                    System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                    action = user.nextInt();
                }
                else if(action == 6){
                    hT.retrieveTransactionHistory();
                    System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                    action = user.nextInt();
                }
            }
        }
    }
        else if (accountChoice == 2){
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
        int action = user.nextInt();
        if (teller == 1){
            Teller hT = centralBank.getTellers().get(0);
        while(action != 7){
            int check = hT.bankController.createSavings(100, 0.1);
            hT.confirmCredentials(check);
            if(action == 1){
                hT.checkBalance();
                System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                action = user.nextInt();
            }
            else if(action == 2){
                System.out.println("Enter in deposit amount: ");
                double deposit = user.nextDouble();
                hT.deposit(deposit);
                System.out.print("Balance: "+ hT.checkBalance()+ "\n");
                System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                action = user.nextInt();
            }
            else if(action == 3){
                System.out.println("Enter in withdraw amount: ");
                double withdraw = user.nextDouble();
                hT.withdraw(withdraw);
                System.out.print("Balance: "+ hT.checkBalance()+ "\n");
                System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                action = user.nextInt();
            }
            else if(action == 4){
                hT.bankController.checkSuspiciousActivity();
                System.out.print("Your account "+hT.currAccountId+ " is "+hT.bankController.isSuspicious(hT.currAccountId));
                System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                action = user.nextInt();
            }
            else if(action == 5){
                hT.bankController.setFrozen(check, true);
                System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                action = user.nextInt();
            }
            else if(action == 6){
                hT.retrieveTransactionHistory();
                System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
                action = user.nextInt();
            }
        }
    }
    }
//ATM
    if (accountChoice == 1){
        System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
    int action = user.nextInt();
    if (teller == 2){
        Teller aT = centralBank.getTellers().get(0);
    while(action != 7){
        int check = aT.bankController.createChecking(100);
        aT.confirmCredentials(check);
        if(action == 1){
            aT.checkBalance();
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 2){
            System.out.println("Enter in deposit amount: ");
            double deposit = user.nextDouble();
            aT.deposit(deposit);
            System.out.print("Balance: "+ aT.checkBalance()+ "\n");
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 3){
            System.out.println("Enter in withdraw amount: ");
            double withdraw = user.nextDouble();
            aT.withdraw(withdraw);
            System.out.print("Balance: "+ aT.checkBalance()+ "\n");
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 4){
            aT.bankController.checkSuspiciousActivity();
            System.out.print("Your account "+aT.currAccountId+ " is "+aT.bankController.isSuspicious(aT.currAccountId));
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 5){
            aT.bankController.setFrozen(check, true);
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 6){
            aT.retrieveTransactionHistory();
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
    }
    }
    }
    if (accountChoice == 2){
        System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
    int action = user.nextInt();
    if (teller == 2){
        Teller aT = centralBank.getTellers().get(0);
    while(action != 7){
        int check = aT.bankController.createChecking(100);
        aT.confirmCredentials(check);
        if(action == 1){
            aT.checkBalance();
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 2){
            System.out.println("Enter in deposit amount: ");
            double deposit = user.nextDouble();
            aT.deposit(deposit);
            System.out.print("Balance: "+ aT.checkBalance()+ "\n");
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 3){
            System.out.println("Enter in withdraw amount: ");
            double withdraw = user.nextDouble();
            aT.withdraw(withdraw);
            System.out.print("Balance: "+ aT.checkBalance()+ "\n");
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 4){
            aT.bankController.checkSuspiciousActivity();
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 5){
            aT.bankController.setFrozen(check, true);
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
        else if(action == 6){
            aT.retrieveTransactionHistory();
            System.out.println("Pick a banking action: 1) Check Balance, 2) Deposit, 3) Withdaw, 4) Suspicious Activity Check, 5) Freeze Account, 6) Transaction History, 7) Done");
            action = user.nextInt();
        }
    }
    }
    }
                
    }
      user.close();
      System.out.println("Thank you for choosing Central Bank :)");

        }
}
