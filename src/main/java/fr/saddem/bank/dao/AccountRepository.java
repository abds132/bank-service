package fr.saddem.bank.dao;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Repository;

import fr.saddem.bank.entity.Account;
import fr.saddem.bank.entity.Client;

@Repository
public class AccountRepository {

    static final Collection<Account> allAccounts = new CopyOnWriteArraySet<>();
    static final Collection<Client> allClients = new CopyOnWriteArraySet<>();

    static {
        Client c1 = new Client("Patrick");
        Client c2 = new Client("Robert");
        allClients.addAll(Arrays.asList(c1, c2));

        allAccounts.add(new Account(1l, LocalDateTime.now(), 0.0, c1));
        allAccounts.add(new Account(2l, LocalDateTime.now(), 0.0, c2));
    }

    public Optional<Account> findById(Long id){
        return allAccounts.stream().filter(a->a.getId() == id).findFirst();
    }
}
