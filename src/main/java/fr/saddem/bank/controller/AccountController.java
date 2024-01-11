package fr.saddem.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import fr.saddem.bank.dto.AccountDto;
import fr.saddem.bank.dto.DepositRequest;
import fr.saddem.bank.dto.WithdrawlRequest;
import fr.saddem.bank.entity.Account;
import fr.saddem.bank.exceptions.AccountNotFoundException;
import fr.saddem.bank.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AccountController {
    
    private AccountService accountService;
    
    public AccountController(@Autowired final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable final String id){
        try {
            Account account = accountService.findAccountById(Long.valueOf(id)).get();
            AccountDto accountDto = new AccountDto();
            accountDto.setId(account.getId());
            accountDto.setBalance(account.getBalance());
            accountDto.setCreationDate(account.getCreationDate());
            return ResponseEntity.ok().body(accountDto);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    

    @PostMapping("/account/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable final String id, 
                                        @RequestBody final DepositRequest depositRequest){
        try {
            accountService.deposit(Long.valueOf(id), depositRequest.getAmount());
            return ResponseEntity.ok().build();
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/account/{id}/withdrawl")
    public ResponseEntity<Void> withdrawl(@PathVariable final String id, 
                                          @RequestBody final WithdrawlRequest withdrawlRequest){
        try {
            accountService.withdrawl(Long.valueOf(id), withdrawlRequest.getAmount());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
