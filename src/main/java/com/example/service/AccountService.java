package com.example.service;

import com.example.dtos.AccountDTO;

public interface AccountService {
     boolean create(AccountDTO userDTO);
     AccountDTO findbyEmail(String email);
     AccountDTO findbyUsername(String username);

    }
