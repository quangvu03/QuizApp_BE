package com.example.service.impl;

import com.example.entities.Favorite;
import com.example.service.FavoriteService;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class FavoriteImpl implements FavoriteService {

    @Override
    public List<Favorite> findByUserId(long userId) {
        return null;
    }
}
