package com.example.service.impl;

import com.example.configurations.UserMapper;
import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import com.example.repositories.UserRepository;
import com.example.service.AccountService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

@Singleton
public class AccountServiceImpl implements AccountService {

    @Inject
    UserMapper userMapper;

    @Inject
    UserRepository userRepository;

    @Override
    public boolean create(AccountDTO userDTO) {
        try {
            Account user = userMapper.toEntity(userDTO);
            user.setStatus(false);
            String rawPassword = userDTO.getPassword();
            if (user.getUserName() == null || user.getEmail() == null || user.getFullName() == null
                    || user.getPhone() == null || user.getDateOfBirth() == null) {
                throw new IllegalArgumentException("Vui long nhap day du thong tin");
            }
            if (rawPassword == null || rawPassword.trim().isEmpty()) {
                throw new IllegalArgumentException("Password must not be null or empty");
            }
            userDTO.setPassword(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AccountDTO findbyEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO) // Nếu có kết quả, map sang DTO
                .orElse(null); // Nếu không, trả về null
    }

    @Override
    public AccountDTO findbyUsername(String username) {
        return userRepository.findByUserName(username)
                .map(userMapper::toDTO) // Nếu có kết quả, map sang DTO
                .orElse(null); // Nếu không, trả về null
    }
}