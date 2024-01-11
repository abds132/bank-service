package fr.saddem.bank.service;

import java.util.ArrayList;
import java.util.Optional;

import fr.saddem.bank.entity.Account;
import fr.saddem.bank.entity.Operation;
import fr.saddem.bank.exceptions.AccountNotFoundException;
import fr.saddem.bank.exceptions.NotEnoughBalanceException;

public interface AccountService {
    public Optional<Account> findAccountById(Long idAccount) throws AccountNotFoundException;
    public void deposit(Long idAccount, Double amount) throws AccountNotFoundException;
    public void withdrawl(Long idAccount, Double amount) throws AccountNotFoundException, NotEnoughBalanceException;
    public Optional<ArrayList<Operation>> getOperationsByAccountId(Long idAccount) throws AccountNotFoundException;
}
