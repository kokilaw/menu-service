package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface RestaurantRepository extends PanacheRepositoryBase<RestaurantEntity, String> {
}
