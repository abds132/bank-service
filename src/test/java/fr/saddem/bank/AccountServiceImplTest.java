package fr.saddem.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.saddem.bank.dao.AccountRepository;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.entity.Client;
import fr.saddem.bank.entity.DepositOperation;
import fr.saddem.bank.entity.Operation;
import fr.saddem.bank.entity.WithdrawlOperation;
import fr.saddem.bank.exceptions.AccountNotFoundException;
import fr.saddem.bank.exceptions.NotEnoughBalanceException;
import fr.saddem.bank.service.AccountService;
import fr.saddem.bank.service.AccountServiceImpl;

public class AccountServiceImplTest {

    private AccountService accountService;

    @BeforeEach
    private void init(){
        AccountRepository accountRepository = new AccountRepository();
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    public void shouldReturnAnAccount() throws AccountNotFoundException{
        //GIVEN
        Long idAccount = 1l;
        //WHEN
        Optional<Account> accountOpt = accountService.findAccountById(idAccount);
        //THEN
        assertNotNull(accountOpt);
        assertEquals(accountOpt.get().getClient().getName(), "Patrick");
    }

    @Test
    public void shouldReturnEmptyOptionalIfAnAccountNotFound() throws AccountNotFoundException{
        //GIVEN
        Long idAccount = 4l;
        //WHEN THEN
        assertThrows(AccountNotFoundException.class, () -> accountService.findAccountById(idAccount));
    }

    @Test
    public void shouldIncreaseBalanceWhenDepositOperation() throws AccountNotFoundException{
        //GIVEN
        Long idAccount = 1l;
        BigDecimal amountToDeposit = BigDecimal.valueOf(2.0);
        BigDecimal balanceOfAccount = BigDecimal.valueOf(2.0);
        //WHEN
        accountService.deposit(idAccount, amountToDeposit);
        //THEN
        Optional<Account> accountOpt = accountService.findAccountById(idAccount);
        assertNotNull(accountOpt);
        assertEquals(accountOpt.get().getBalance(), balanceOfAccount);
        assertFalse(accountOpt.get().getOperations().isEmpty());
    }

    @Test
    public void shouldThrowAnExceptionWhenAnAccountNotExistInOperationDeposit() throws AccountNotFoundException{
        //GIVEN
        Long idAccount = 4l;
        BigDecimal amountToDeposit = BigDecimal.valueOf(2.0);;
        //WHEN THEN
        assertThrows(AccountNotFoundException.class, () -> accountService.deposit(idAccount, amountToDeposit));
    }

    @Test
    public void shouldDecreaseBalanceWhenWithdrawlOperation() throws AccountNotFoundException, NotEnoughBalanceException{
        //GIVEN
        Long idAccount = 3l;
        BigDecimal amountToWithdrawl = BigDecimal.valueOf(2.0);;
        Account account = new Account(3l, LocalDateTime.now(), BigDecimal.valueOf(5.0), new Client("Julien"));
        AccountRepository.getAllaccounts().add(account);
        //WHEN
        accountService.withdrawl(idAccount, amountToWithdrawl);
        //THEN
        Optional<Account> accountOpt = accountService.findAccountById(idAccount);
        assertNotNull(accountOpt);
        assertFalse(accountOpt.get().getOperations().isEmpty());
        assertEquals(accountOpt.get().getBalance(), BigDecimal.valueOf(3.0));
    }

    @Test
    public void shouldThrowAnExceptionWhenNoBalanceEnoughInTheAccount() {
        //GIVEN
        Long idAccount = 5l;
        BigDecimal amountToWithdrawl = BigDecimal.valueOf(2.0);
        Account account = new Account(5l, LocalDateTime.now(), BigDecimal.valueOf(0.0), new Client("Julien"));
        AccountRepository.getAllaccounts().add(account);
        //WHEN THEN
        assertThrows(NotEnoughBalanceException.class, () -> accountService.withdrawl(idAccount, amountToWithdrawl));
    }

    @Test
    public void shouldReturnEmptyOperationListWhenNoOperationsInAccount() throws AccountNotFoundException, NotEnoughBalanceException{
        //GIVEN
        Long idAccount = 6l;
        //WHEN
        Optional<ArrayList<Operation>> allOperations = accountService.getOperationsByAccountId(idAccount);
        //THEN
        assertEquals(Optional.empty(), allOperations);
    }

    @Test
    public void shouldGetAllOperationsStatementInOrder() throws AccountNotFoundException, NotEnoughBalanceException{
        //GIVEN
        Long idAccount = 7l;
        accountService.deposit(idAccount, BigDecimal.valueOf(2.0));
        accountService.withdrawl(idAccount, BigDecimal.valueOf(1.0));
        //WHEN
        ArrayList<Operation> allOperations = accountService.getOperationsByAccountId(idAccount).get();
        //THEN
        assertEquals(2, allOperations.size());
        assertInstanceOf(DepositOperation.class, allOperations.get(0));
        assertInstanceOf(WithdrawlOperation.class, allOperations.get(1));
    }

    @Test
    public void shouldGetTheRightBalanceInAllOperationsStatement() throws AccountNotFoundException, NotEnoughBalanceException{
        //GIVEN
        Long idAccount = 8l;
        accountService.deposit(idAccount, BigDecimal.valueOf(6.0));
        accountService.withdrawl(idAccount, BigDecimal.valueOf(1.0));
        //WHEN
        ArrayList<Operation> allOperations = accountService.getOperationsByAccountId(idAccount).get();
        //THEN
        assertEquals(2, allOperations.size());
        assertEquals(BigDecimal.valueOf(6.0), allOperations.get(0).getBalance());
        assertEquals(BigDecimal.valueOf(5.0), allOperations.get(1).getBalance());
    }
}
