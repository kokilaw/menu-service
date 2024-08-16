package com.au.eatclub.menu.repository.impl;

import com.au.eatclub.menu.repository.ModifierGroupRepository;
import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ModifierGroupRepositoryImpl implements ModifierGroupRepository {

    @Override
    public List<ModifierGroupEntity> findByRestaurant(RestaurantEntity restaurantEntity) {
        return find("restaurant = ?1", restaurantEntity).stream().toList();
    }

    @Override
    public Optional<ModifierGroupEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity) {
        return find("id  = ?1 AND restaurant = ?2", id, restaurantEntity).firstResultOptional();
    }

}
