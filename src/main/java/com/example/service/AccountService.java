package com.example.service;

import com.example.dtos.AccountDTO;

public interface AccountService {
    public boolean create(AccountDTO userDTO);
    public AccountDTO findbyEmail(String email);
    public AccountDTO findbyUsername(String username);

    }
