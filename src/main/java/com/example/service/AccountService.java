package com.example.service;

import com.example.dtos.AccountDTO;
import com.example.entities.Account;

public interface AccountService {
     boolean create(AccountDTO userDTO);
     AccountDTO findbyEmail(String email);
     AccountDTO findbyUsername(String username);
     AccountDTO loginbyEmail(String username,String password);
    }
