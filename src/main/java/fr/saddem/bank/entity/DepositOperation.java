package fr.saddem.bank.entity;

import java.time.LocalDateTime;

public class DepositOperation extends Operation{
    public DepositOperation(String id, LocalDateTime operationDate, Double amount, Double balance, Account account) {
        super(id, operationDate, amount, balance, account);
    }
}
