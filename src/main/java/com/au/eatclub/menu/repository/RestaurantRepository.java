package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface RestaurantRepository extends PanacheRepository<RestaurantEntity> {
}
