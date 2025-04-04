package com.example.repositories;

import com.example.entities.Account;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByEmail(String email); // Sửa thành Optional

    Optional<Account> findByUserName(String username); // Sửa tương tự cho username
}