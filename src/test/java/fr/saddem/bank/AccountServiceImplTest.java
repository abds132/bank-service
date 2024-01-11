package fr.saddem.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import fr.saddem.bank.dao.AccountRepository;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.service.AccountService;
import fr.saddem.bank.service.AccountServiceImpl;

public class AccountServiceImplTest {

    AccountRepository accountRepository = new AccountRepository();
    AccountService accountService = new AccountServiceImpl(accountRepository);

    @Test
    public void shouldFindAnAccount(){
        Optional<Account> accountOpt = accountService.findAccountById(1l);
        assertNotNull(accountOpt.get());
        assertEquals(accountOpt.get().getClient().getName(), "Patrick");
    }

}
