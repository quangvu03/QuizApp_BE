package com.example.service.impl;


import com.example.configurations.UserMapper;
import com.example.dtos.AccountDTO;
import com.example.entities.Account;
import com.example.repositories.UserRepository;
import com.example.service.AcountService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

@Singleton
public class AccountServiceImpl implements AcountService {

    @Inject
    UserMapper userMapper ;

    @Inject
    UserRepository userRepository;

    @Override
    public boolean create(AccountDTO userDTO) {
        try{
            Account user = userMapper.toEntity(userDTO);
            user.setStatus(false);
            String rawPassword = userDTO.getPassword();
            if (rawPassword == null || rawPassword.trim().isEmpty()) {
                throw new IllegalArgumentException("Password must not be null or empty");
            }
            userDTO.setPassword(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
