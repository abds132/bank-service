package fr.saddem.bank.entity;

import java.time.LocalDateTime;

public class Operation {
    private Long id;
    private LocalDateTime operationDate;
    private Double amount;
    private Account account;
}
