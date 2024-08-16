package com.au.eatclub.menu.repository.impl;

import com.au.eatclub.menu.repository.CategoryRepository;
import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Optional<CategoryEntity> findByPublicIdAndRestaurantId(String publicId, RestaurantEntity restaurantEntity) {
        return find("publicId  = ?1 AND restaurant = ?2", publicId, restaurantEntity).firstResultOptional();
    }

}
