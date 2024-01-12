package fr.saddem.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositOperation extends Operation{
    public DepositOperation(String id, LocalDateTime operationDate, BigDecimal amount, BigDecimal balance, Account account) {
        super(id, operationDate, amount, balance, account);
    }
}
