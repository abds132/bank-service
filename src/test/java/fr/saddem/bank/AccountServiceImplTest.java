package fr.saddem.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import fr.saddem.bank.dao.AccountRepository;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.entity.Client;
import fr.saddem.bank.exceptions.AccountNotFoundException;
import fr.saddem.bank.exceptions.NotEnoughBalanceException;
import fr.saddem.bank.service.AccountService;
import fr.saddem.bank.service.AccountServiceImpl;

public class AccountServiceImplTest {

    AccountRepository accountRepository = new AccountRepository();
    AccountService accountService = new AccountServiceImpl(accountRepository);

    @Test
    public void shouldReturnAnAccount(){
        //GIVEN
        Long idAccount = 1l;
        //WHEN
        Optional<Account> accountOpt = accountService.findAccountById(idAccount);
        //THEN
        assertNotNull(accountOpt);
        assertEquals(accountOpt.get().getClient().getName(), "Patrick");
    }

    @Test
    public void shouldReturnEmptyOptionalIfAnAccountNotFound(){
        //GIVEN
        Long idAccount = 4l;
        //WHEN
        Optional<Account> accountOpt = accountService.findAccountById(idAccount);
        //THEN
        assertNotNull(accountOpt);
        assertEquals(accountOpt, Optional.empty());
    }

    @Test
    public void shouldIncreaseBalanceWhenDepositOperation() throws AccountNotFoundException{
        //GIVEN
        Long idAccount = 1l;
        Double amountToDeposit = 2.0;
        Double balanceOfAccount = 2.0;
        //WHEN
        accountService.deposit(idAccount, amountToDeposit);
        //THEN
        Optional<Account> accountOpt = accountService.findAccountById(idAccount);
        assertNotNull(accountOpt);
        assertEquals(accountOpt.get().getBalance(), balanceOfAccount);
        assertFalse(accountOpt.get().getOperations().isEmpty());
    }

    @Test
    public void shouldThrowAnExceptionWhenAnAccountNotExist() throws AccountNotFoundException{
        //GIVEN
        Long idAccount = 4l;
        Double amountToDeposit = 2.0;
        //WHEN THEN
        assertThrows(AccountNotFoundException.class, () -> accountService.deposit(idAccount, amountToDeposit));
    }

    @Test
    public void shouldDecreaseBalanceWhenWithdrawlOperation() throws AccountNotFoundException, NotEnoughBalanceException{
        //GIVEN
        Long idAccount = 3l;
        Double amountToWithdrawl = 2.0;
        Account account = new Account(3l, LocalDateTime.now(), 5.0, new Client("Julien"));
        AccountRepository.getAllaccounts().add(account);
        //WHEN
        accountService.withdrawl(idAccount, amountToWithdrawl);
        //THEN
        Optional<Account> accountOpt = accountService.findAccountById(idAccount);
        assertNotNull(accountOpt);
        assertFalse(accountOpt.get().getOperations().isEmpty());
        assertEquals(accountOpt.get().getBalance(), 3.0);
    }

    @Test
    public void shouldThrowAnExceptionWhenNoBalanceEnoughInTheAccount() {
        //GIVEN
        Long idAccount = 5l;
        Double amountToWithdrawl = 2.0;
        Account account = new Account(5l, LocalDateTime.now(), 0.0, new Client("Julien"));
        AccountRepository.getAllaccounts().add(account);
        //WHEN THEN
        assertThrows(NotEnoughBalanceException.class, () -> accountService.withdrawl(idAccount, amountToWithdrawl));
    }

}
