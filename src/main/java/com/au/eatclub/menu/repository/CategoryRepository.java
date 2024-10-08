package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends PanacheRepositoryBase<CategoryEntity, String> {
    List<CategoryEntity> findByRestaurant(RestaurantEntity restaurantEntity);
    Optional<CategoryEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity);
}
