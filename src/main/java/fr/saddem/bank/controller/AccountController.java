package fr.saddem.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.exceptions.AccountNotFoundException;
import fr.saddem.bank.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AccountController {
    
    private AccountService accountService;
    
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(final String id){
        try {
            return ResponseEntity.ok().body(accountService.findAccountById(Long.valueOf(id)).get());
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
