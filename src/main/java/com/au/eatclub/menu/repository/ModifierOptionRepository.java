package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface ModifierOptionRepository extends PanacheRepositoryBase<ModifierOptionEntity, String> {
    List<ModifierOptionEntity> findByRestaurant(RestaurantEntity restaurantEntity);
    Optional<ModifierOptionEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity);
}
