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

    @Test
    void whenCategoryIsSaved_IdIsSetAndIsReturn() {
        RestaurantEntity restaurantEntity = getRestaurantEntity();

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name("Vegetarian Pizza")
                .restaurant(restaurantEntity)
                .publicId(UUID.randomUUID().toString())
                .build();
        categoryRepository.persist(categoryEntity);
        assertNotNull(restaurantEntity.getId());

        Optional<RestaurantEntity> savedCategory = restaurantRepository.findByIdOptional(restaurantEntity.getId());
        assertTrue(savedCategory.isPresent());
    }

    private RestaurantEntity getRestaurantEntity() {
        return restaurantRepository.findByPublicId(TEST_RESTAURANT_ID).orElseGet(() -> {
            RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                    .publicId(TEST_RESTAURANT_ID)
                    .name("Curry Pot")
                    .email("info@currypot.com.au")
                    .phoneNumber("0061456098345")
                    .countryCode("AU")
                    .currencyCode("AUD")
                    .build();
            restaurantRepository.persist(restaurantEntity);
            return restaurantEntity;
        });
    }

}