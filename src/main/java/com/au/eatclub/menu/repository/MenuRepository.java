package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.MenuEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends PanacheRepositoryBase<MenuEntity, String> {
    List<MenuEntity> findByRestaurant(RestaurantEntity restaurantEntity);
    Optional<MenuEntity> findByIdAndRestaurant(String id, RestaurantEntity restaurantEntity);
}
