package com.au.eatclub.menu.repository.impl;

import com.au.eatclub.menu.repository.ItemRepository;
import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public List<ItemEntity> findByRestaurant(RestaurantEntity restaurantEntity) {
        return find("restaurant = ?1", restaurantEntity).stream().toList();
    }

    @Override
    public Optional<ItemEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity) {
        return find("id  = ?1 AND restaurant = ?2", id, restaurantEntity).firstResultOptional();
    }

}
