package kz.accounting.commons.exception;

public class UserBalanceNotFoundException extends RuntimeException {

    public UserBalanceNotFoundException() {
        super("UserBalance not found");
    }
}