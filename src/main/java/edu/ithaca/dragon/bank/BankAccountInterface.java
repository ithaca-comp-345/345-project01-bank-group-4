package edu.ithaca.dragon.bank;

import java.math.BigDecimal;

public interface BankAccountInterface {

    public double getBalance();
    public String getEmail();
    public void withdraw (double amount) throws InsufficientFundsException;
    public void deposit(double amount);
    public void transfer(double amount);

    /**
     * Returns true if all chars in str are found in charSet, false otherwise
     * @param str String to verify
     * @param charSet Character set to verify against
     * @return True if str only contains chars in charSet, false otherwise
     */
    private static boolean isBuiltFrom(String str, String charSet) {
        String s;
        for (char c : str.toCharArray()) {
            s = "" + c;
            if (!charSet.contains(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if str has consecutive chars from charSet, false otherwise
     * @param str String to check for consecutive chars
     * @param c Char to check
     * @return True if consecutive chars from charSet, false otherwise
     */
    private static boolean hasConsecutive(String str, String charSet) {
        for (char c1 : charSet.toCharArray()) {
            for (char c2 : charSet.toCharArray()) {
                String s = "" + c1 + c2;
                if (str.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if:
     * only chars in prefix are (a-z), (0-9), '_', '.' and '-'
     * only chars in domain are (a-z), (0-9), and '-'
     * only chars in topLevelDomain are (a-z)
     * no leading or trailing '_', '.', '-' in the prefix
     * no leading or trailing '-' in the domain
     * no topLevelDomain with length < 2
     * no consecutive speical characters,
     * flase otherwise
     * @param email Address to verify
     * @return True if valid email, false otherwise
     */
    public static boolean isEmailValid(String email) {
        // allowed character sets
        String alpha, digit, special;
        alpha = "abcdefghijklmnopqrstuvwxyz";
        digit = "0123456789";
        special = "_.-";

        int prefixIndex = email.indexOf('@');
        int domainIndex = email.lastIndexOf('.');

        if (prefixIndex == -1 || domainIndex == -1) {  // if @ or . is missing return false
            return false;
        } else if (prefixIndex > domainIndex) {  // if the last . occurs before the @ return false
            return false;
        }

        String prefix, domain, topLevelDomain;
        prefix = email.substring(0, prefixIndex);
        domain = email.substring(prefixIndex + 1, domainIndex);
        topLevelDomain = email.substring(domainIndex + 1);

        if (prefix.length() == 0 || domain.length() == 0 || topLevelDomain.length() < 2) {  // if prefix or domain length == 0, or topLevelDomain < 2
            return false;
        } else if (!isBuiltFrom(prefix, alpha + digit + special)) {  // if any char in prefix is not (a-z), (0-9), '_', '.' or '-' return false
            return false;
        } else if (!isBuiltFrom(domain, alpha + digit + "-")) {  // if any char in domain is not (a-z), (0-9), or '-' return false
            return false;
        } else if (!isBuiltFrom(topLevelDomain, alpha)) {  // if any char in topLevelDomain is not (a-z) return false
            return false;
        } else if (special.contains("" + prefix.charAt(0))) {  // if prefix leading special char return false
            return false;
        } else if (special.contains("" + email.charAt(prefixIndex - 1))) {  // if prefix trailing special char return false
            return false;
        } else if (domain.charAt(0) == '-') {  // if domain leading special char return false
            return false;
        } else if (email.charAt(domainIndex - 1) == '-') {  // if domain trailing special char return false
            return false;
        } else if (hasConsecutive(email, special)) {  // if email contains consecutive special chars return false
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Returns true if amount has 2 or fewer decimal places and is non-negative, false otherwise
     * @param amount value to check
     * @return true if amount is valid, false otherwise
     */
    public static boolean isAmountValid(double amount) {
        if (amount < 0) {
            return false;
        } else if (BigDecimal.valueOf(amount).scale() > 2) {
            return false;
        } else {
            return true;
        }
    }
}