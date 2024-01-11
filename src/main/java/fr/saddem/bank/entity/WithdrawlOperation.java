package fr.saddem.bank.entity;

import java.time.LocalDateTime;

public class WithdrawlOperation extends Operation{
    public WithdrawlOperation(String id, LocalDateTime operationDate, Double amount, Double balance, Account account) {
        super(id, operationDate, amount, balance, account);
    }
}
