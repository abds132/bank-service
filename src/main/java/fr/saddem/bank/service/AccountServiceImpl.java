package fr.saddem.bank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.saddem.bank.dao.AccountRepository;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.entity.DepositOperation;
import fr.saddem.bank.entity.Operation;
import fr.saddem.bank.entity.WithdrawlOperation;
import fr.saddem.bank.exceptions.AccountNotFoundException;
import fr.saddem.bank.exceptions.NotEnoughBalanceException;

@Service
public class AccountServiceImpl implements AccountService{
    
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findAccountById(Long idAccount) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(idAccount);
        if(account.isPresent()) return account;
        throw new AccountNotFoundException();
    }

    @Override
    public void deposit(Long idAccount, BigDecimal amount) throws AccountNotFoundException {
        final Account account = findAccountById(idAccount).get();
        final BigDecimal currentBalance = account.getBalance();
        final BigDecimal newBalance = currentBalance.add(amount);
        DepositOperation depositOperation = new DepositOperation(UUID.randomUUID().toString(), LocalDateTime.now(), amount, newBalance, account);
        account.setBalance(newBalance);
        account.addAnOperation(depositOperation);
    }


    @Override
    public void withdrawl(Long idAccount, BigDecimal amount) throws AccountNotFoundException, NotEnoughBalanceException {
        Account account = findAccountById(idAccount).get();
        checkBalanceOfTheAccount(account, amount);
        final BigDecimal currentBalance = account.getBalance();
        final BigDecimal newBalance = currentBalance.subtract(amount);
        WithdrawlOperation depositOperation = new WithdrawlOperation(UUID.randomUUID().toString(), LocalDateTime.now(), amount, newBalance, account);
        account.setBalance(newBalance);
        account.addAnOperation(depositOperation);
    }

    private void checkBalanceOfTheAccount(final Account account, final BigDecimal amount) throws NotEnoughBalanceException {
        if(account.getBalance().compareTo(amount) <= 0) throw new NotEnoughBalanceException();
    }

    @Override
    public Optional<ArrayList<Operation>> getOperationsByAccountId(Long idAccount) throws AccountNotFoundException {
        Account account = findAccountById(idAccount).get();
        return account.getOperations() != null ? Optional.of(account.getOperations()) : Optional.empty();
    }

}
