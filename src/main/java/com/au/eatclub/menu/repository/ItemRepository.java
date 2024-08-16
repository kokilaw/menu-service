package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends PanacheRepository<ItemEntity> {
    List<ItemEntity> findByRestaurant(RestaurantEntity restaurantEntity);
    Optional<ItemEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity);
}
