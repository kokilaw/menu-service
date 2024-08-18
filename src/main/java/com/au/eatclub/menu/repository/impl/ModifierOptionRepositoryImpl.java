package com.au.eatclub.menu.repository.impl;

import com.au.eatclub.menu.repository.ModifierOptionRepository;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ModifierOptionRepositoryImpl implements ModifierOptionRepository {

    @Override
    public List<ModifierOptionEntity> findByRestaurant(RestaurantEntity restaurantEntity) {
        return find("restaurant = ?1", restaurantEntity).stream().toList();
    }

    @Override
    public Optional<ModifierOptionEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity) {
        return find("id  = ?1 AND restaurant = ?2", id, restaurantEntity).firstResultOptional();
    }

}
