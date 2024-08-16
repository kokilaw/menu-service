package com.au.eatclub.menu.repository.impl;

import com.au.eatclub.menu.repository.CategoryRepository;
import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public List<CategoryEntity> findByRestaurant(RestaurantEntity restaurantEntity) {
        return find("restaurant = ?1", restaurantEntity).stream().toList();
    }

    @Override
    public Optional<CategoryEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity) {
        return find("id  = ?1 AND restaurant = ?2", id, restaurantEntity).firstResultOptional();
    }

}
