package fr.saddem.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawlOperation extends Operation{
    public WithdrawlOperation(String id, LocalDateTime operationDate, BigDecimal amount, BigDecimal balance, Account account) {
        super(id, operationDate, amount, balance, account);
    }
}
