package com.example.service;

import java.util.Optional;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) {
        if(account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank/empty");
        }

        if(account.getPassword() == null || account.getPassword().length() < 4) {
            throw new IllegalArgumentException("Username cannot be blank/empty");
        }

        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            return null;
        }

        return accountRepository.save(account);
    }

    public Account login(Account account) {
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());

        if (existingAccount.isPresent() && existingAccount.get().getPassword().equals(account.getPassword())) {
            return existingAccount.get();
        }
        return null;
    }
}
