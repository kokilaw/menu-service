package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface CategoryRepository extends PanacheRepository<CategoryEntity> {
}
