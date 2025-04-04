package com.example.repositories;

import com.example.entities.Account;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;


@Repository
public interface UserRepository extends CrudRepository<Account,Integer> {
    Account findByEmail(String email);

    Account findByUserName(String username);

}
