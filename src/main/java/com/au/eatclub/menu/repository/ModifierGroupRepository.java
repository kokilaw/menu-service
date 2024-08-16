package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Optional;

public interface ModifierGroupRepository extends PanacheRepository<ModifierGroupEntity> {
    List<ModifierGroupEntity> findByRestaurant(RestaurantEntity restaurantEntity);
    Optional<ModifierGroupEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity);
}
