package com.example.service.impl;

import com.example.configurations.UserMapper;
import com.example.dtos.AccountDTO;
import com.example.dtos.ChannelDTO;
import com.example.entities.Account;
import com.example.entities.Channel;
import com.example.repositories.ChannelRepository;
import com.example.repositories.UserRepository;
import com.example.service.AccountService;
import com.example.service.ChannelService;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class AccountServiceImpl implements AccountService {

    @Inject
    UserMapper userMapper;

    @Inject
    UserRepository userRepository;

    @Inject
    ChannelService channelService;

    @Inject
    ChannelRepository channelRepository;

    @Override
    public boolean create(AccountDTO userDTO) {
        try {
            Account user = userMapper.toEntity(userDTO);
            user.setStatus(false);
            user.setRole("user");
            String rawPassword = userDTO.getPassword();
            if (user.getUserName() == null || user.getEmail() == null || user.getFullName() == null
                    || user.getPhone() == null) {
                throw new IllegalArgumentException("Vui long nhap day du thong tin");
            }
            if (rawPassword == null || rawPassword.trim().isEmpty()) {
                throw new IllegalArgumentException("Password must not be null or empty");
            }
            userDTO.setPassword(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
            Account userResponse= userRepository.save(user);

            Channel channel = new Channel();
            channel.setDescription("Xin chào ! Đây là kênh của " + user.getFullName());
            channel.setCreatedAt(LocalDateTime.now());
            channel.setUserId(userResponse.getId());
            channel.setNameChanel(user.getFullName());
            channel.setBackground("image1.png");
            channelRepository.save(channel);

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
                .map(userMapper::toDTO)
                .orElse(null);
    }

    @Override
    public AccountDTO loginbyEmail(String username, String password) {
        Optional<Account> accountOpt = userRepository.checkusernameorEmail(username);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            String hashedPassword = account.getPassword();
            boolean isPasswordValid = BCrypt.checkpw(password, hashedPassword);

            if (isPasswordValid) {
                return userMapper.toDTO(account);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String changPassword(String email, String changePassword) {
        String result = "";
        Optional<Account> accoutOTN = userRepository.findByEmail(email);

        if(accoutOTN.isEmpty()){
            return "notFound";
        }
        Account account = accoutOTN.get();
            if (changePassword.length() < 8) {
                result = "password to short";
            } else {
                result = "successfully";
                account.setPassword(changePassword);
                userRepository.save(account);
            }
        return result;
    }

    @Override
    @Transactional
    public Account updateAccount(Long id, AccountDTO accountDTO) {
        Optional<Account> existingAccount = userRepository.findById(id);
        if(existingAccount.isPresent()){
            Account account = existingAccount.get();
            account.setPhone(accountDTO.getPhone());
            account.setFullName(accountDTO.getFullName());
            return userRepository.save(account);
        }else{
            return null;
        }
    }

    @Override
    public Iterable<AccountDTO> findAllUsers() {
        Iterable<Account> accounts = userRepository.findAll();
        return StreamSupport.stream(accounts.spliterator(), false)
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
