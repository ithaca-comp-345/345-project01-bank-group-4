package edu.ithaca.dragon.bank;

public class AccountFrozenException extends Exception{

    private static final long serialVersionUID = 1L;

    public AccountFrozenException(String s) {
        super(s);
    }
}
