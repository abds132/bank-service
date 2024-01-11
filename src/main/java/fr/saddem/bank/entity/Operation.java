package fr.saddem.bank.entity;

import java.time.LocalDateTime;

public abstract class Operation {
    private String id;
    private LocalDateTime operationDate;
    private Double amount;
    private Double balance;
    private Account account;
    
    public Operation(String id, LocalDateTime operationDate, Double amount, Double balance, Account account) {
        this.id = id;
        this.operationDate = operationDate;
        this.amount = amount;
        this.account = account;
        this.balance = balance;
    }
}
