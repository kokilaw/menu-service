package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestTransaction
@QuarkusTest
class CategoryRepositoryTest {

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    RestaurantRepository restaurantRepository;

    private String testRestaurantId = "NOT_SET";

    @Test
    void givenCategory_whenSaved_IsReturnedCorrectly() {
        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(restaurantRepository, testRestaurantId);
        testRestaurantId = restaurantEntity.getId();

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name("Vegetarian Pizza")
                .restaurant(restaurantEntity)
                .build();
        categoryRepository.persist(categoryEntity);
        assertNotNull(restaurantEntity.getId());

        Optional<RestaurantEntity> savedCategory = restaurantRepository.findByIdOptional(restaurantEntity.getId());
        assertTrue(savedCategory.isPresent());

        assertTrue(categoryRepository.findByIdAndRestaurant(categoryEntity.getId(), restaurantEntity).isPresent());

    }

}