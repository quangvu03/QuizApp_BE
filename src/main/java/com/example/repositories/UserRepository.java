package com.example.repositories;
import com.example.entities.Account;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    Optional<Account> findByUserName(String username);

    @Query(value = "SELECT * FROM account WHERE email = :identifier OR username = :identifier LIMIT 1", nativeQuery = true)
    Optional<Account> checkusernameorEmail(String identifier);

    @Query(value = "SELECT * FROM account WHERE username LIKE CONCAT('%', :username, '%')", nativeQuery = true)
    List<Account> findAccountsByUsernameContaining( String username);





}