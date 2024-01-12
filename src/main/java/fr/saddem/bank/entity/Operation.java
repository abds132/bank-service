package fr.saddem.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Operation {
    private String id;
    private LocalDateTime operationDate;
    private BigDecimal amount;
    private BigDecimal balance;
    private Account account;
    
    public Operation(String id, LocalDateTime operationDate, BigDecimal amount, BigDecimal balance, Account account) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
}
