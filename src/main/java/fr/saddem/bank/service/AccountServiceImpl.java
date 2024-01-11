package fr.saddem.bank.service;

import java.util.Collection;
import java.util.Optional;

import fr.saddem.bank.dao.AccountRepository;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.entity.Operation;
import fr.saddem.bank.exceptions.AccountNotFoundException;

public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findAccountById(Long idAccount) {
       return accountRepository.findById(idAccount);
    }

    @Override
    public void withdraw(Long idAccount, Double amount) throws AccountNotFoundException {
        Account account = findAccountById(idAccount).orElse(null);
        if (account != null) {
            Operation versement = new Operation();
        }
        throw new AccountNotFoundException();
    }

    @Override
    public void deposit(Long idAccount, Double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    @Override
    public Collection<Operation> printOperations(Long idAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printOperations'");
    }

}
