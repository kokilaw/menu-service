package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestTransaction
@QuarkusTest
class CategoryRepositoryTest {

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    RestaurantRepository restaurantRepository;

    private static final String TEST_RESTAURANT_ID = UUID.randomUUID().toString();
    private static final String TEST_CATEGORY_ID = UUID.randomUUID().toString();

    @Test
    void whenCategoryIsSaved_IdIsSetAndIsReturned() {
        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(restaurantRepository, TEST_RESTAURANT_ID);

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name("Vegetarian Pizza")
                .restaurant(restaurantEntity)
                .publicId(TEST_CATEGORY_ID)
                .build();
        categoryRepository.persist(categoryEntity);
        assertNotNull(restaurantEntity.getId());

        Optional<RestaurantEntity> savedCategory = restaurantRepository.findByIdOptional(restaurantEntity.getId());
        assertTrue(savedCategory.isPresent());

        assertTrue(categoryRepository.findByPublicIdAndRestaurantId(TEST_CATEGORY_ID, restaurantEntity).isPresent());

    }

}