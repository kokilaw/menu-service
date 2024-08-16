package com.au.eatclub.menu.repository.impl;

import com.au.eatclub.menu.repository.RestaurantRepository;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Override
    public Optional<RestaurantEntity> findByPublicId(String publicId) {
        return find("publicId  = ?1", publicId).firstResultOptional();
    }

}
