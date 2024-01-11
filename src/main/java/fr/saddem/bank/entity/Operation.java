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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
}
