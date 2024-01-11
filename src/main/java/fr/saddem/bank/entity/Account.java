package fr.saddem.bank.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.CollectionUtils;

public class Account{
    private Long id;
    private LocalDateTime creationDate;
    private Double balance;
    private Client client;
    private ArrayList<Operation> operations;
    
    public Account() {
    }

    public Account(Long id, LocalDateTime creationDate, Double balance, Client client) {
        this.id = id;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
    }

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public void addAnOperation(Operation operation){
        if(CollectionUtils.isEmpty(this.operations)) operations = new ArrayList<>();
        operations.add(operation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return account.getId() == this.id;
    }
}
