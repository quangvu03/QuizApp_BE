package com.example.repositories;

import com.example.entities.Channel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ChannelRepository extends CrudRepository<Channel, Long> {
    List<Channel> findByUserId(Long userId);
}
