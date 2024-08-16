package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface ItemRepository extends PanacheRepository<ItemEntity> {
    Optional<ItemEntity> findByPublicIdAndRestaurantId(String publicId, RestaurantEntity restaurantEntity);
}
