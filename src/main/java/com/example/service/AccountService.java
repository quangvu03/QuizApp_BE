package com.example.service;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import io.micronaut.transaction.annotation.Transactional;

public interface AccountService {
     boolean create(AccountDTO userDTO);
     AccountDTO findbyEmail(String email);
     AccountDTO findbyUsername(String username);
     AccountDTO loginbyEmail(String username,String password);
     String changPassword(String email, String changePassword);
     @Transactional
     Account updateAccount(Long id, AccountDTO accountDTO);
}
