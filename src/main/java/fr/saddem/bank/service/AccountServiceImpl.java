package fr.saddem.bank.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import fr.saddem.bank.dao.AccountRepository;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.entity.DepositOperation;
import fr.saddem.bank.entity.Operation;
import fr.saddem.bank.entity.WithdrawlOperation;
import fr.saddem.bank.exceptions.AccountNotFoundException;
import fr.saddem.bank.exceptions.NotEnoughBalanceException;

public class AccountServiceImpl implements AccountService{

    /**
     * Balance amount limit.
     */
    private static final double BALANCE_LIMIT = 0.0;
    
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findAccountById(Long idAccount) {
       return accountRepository.findById(idAccount);
    }

    @Override
    public void deposit(Long idAccount, Double amount) throws AccountNotFoundException {
        final Account account = findAccountById(idAccount).orElse(null);
        if (account != null) {
            final Double currentBalance = account.getBalance();
            final Double newBalance = currentBalance + amount;
            DepositOperation depositOperation = new DepositOperation(UUID.randomUUID().toString(), LocalDateTime.now(), amount, currentBalance, account);
            account.setBalance(newBalance);
            account.addAnOperation(depositOperation);
        } else throw new AccountNotFoundException();
    }


    @Override
    public void withdrawl(Long idAccount, Double amount) throws AccountNotFoundException, NotEnoughBalanceException {
        Account account = findAccountById(idAccount).orElse(null);
        if (account != null) {
            checkBalanceOfTheAccount(account);
            final Double currentBalance = account.getBalance();
            WithdrawlOperation depositOperation = new WithdrawlOperation(UUID.randomUUID().toString(), LocalDateTime.now(), amount, currentBalance, account);
            final Double newBalance = currentBalance - amount;
            account.setBalance(newBalance);
            account.addAnOperation(depositOperation);
        } else throw new AccountNotFoundException();
    }

    
    private void checkBalanceOfTheAccount(final Account account) throws NotEnoughBalanceException {
        if(account.getBalance() <= BALANCE_LIMIT) throw new NotEnoughBalanceException();
    }

    @Override
    public Collection<Operation> printOperations(Long idAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printOperations'");
    }

}
