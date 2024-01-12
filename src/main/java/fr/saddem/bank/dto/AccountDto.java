package fr.saddem.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDto {
    private Long id;
    private LocalDateTime creationDate;
    private BigDecimal balance;
   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    
}
