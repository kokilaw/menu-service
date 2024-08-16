package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface CategoryRepository extends PanacheRepository<CategoryEntity> {
    Optional<CategoryEntity> findByPublicIdAndRestaurantId(String publicId, RestaurantEntity restaurantEntity);
}
