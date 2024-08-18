package com.au.eatclub.menu.repository.impl;

import com.au.eatclub.menu.repository.MenuRepository;
import com.au.eatclub.menu.repository.model.MenuEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MenuRepositoryImpl implements MenuRepository {

    @Override
    public List<MenuEntity> findByRestaurant(RestaurantEntity restaurantEntity) {
        return find("restaurant = ?1", restaurantEntity).stream().toList();
    }

    @Override
    public Optional<MenuEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity) {
        return find("id  = ?1 AND restaurant = ?2", id, restaurantEntity).firstResultOptional();
    }

}
