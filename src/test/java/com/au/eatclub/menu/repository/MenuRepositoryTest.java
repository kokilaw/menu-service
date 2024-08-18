package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.MenuEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestTransaction
@QuarkusTest
class MenuRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    MenuRepository menuRepository;

    @Inject
    CategoryRepository categoryRepository;

    private String testRestaurantId = "NOT_SET";

    @Test
    void givenMenu_whenSaved_isReturned() {
        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(restaurantRepository, testRestaurantId);
        testRestaurantId = restaurantEntity.getId();

        MenuEntity menuEntity = MenuEntity.builder()
                .restaurant(restaurantEntity)
                .name("Dinner")
                .timeBased(true)
                .startAt(LocalTime.of(19, 0, 0))
                .endAt(LocalTime.of(0, 0, 0))
                .build();
        menuRepository.persist(menuEntity);

        Optional<MenuEntity> menuResult = menuRepository.findByIdAndRestaurant(menuEntity.getId(), restaurantEntity);
        assertTrue(menuResult.isPresent());
    }

    @Test
    void givenMenu_whenCategoriesAttached_isReturnedCorrectly() {

        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(restaurantRepository, testRestaurantId);
        testRestaurantId = restaurantEntity.getId();

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name("CAT_001")
                .restaurant(restaurantEntity)
                .build();
        categoryRepository.persist(categoryEntity);
        assertTrue(categoryRepository.findByIdAndRestaurant(categoryEntity.getId(), restaurantEntity).isPresent());

        MenuEntity menuEntity = MenuEntity.builder()
                .restaurant(restaurantEntity)
                .name("Dinner")
                .timeBased(true)
                .startAt(LocalTime.of(19, 0, 0))
                .endAt(LocalTime.of(0, 0, 0))
                .build();
        menuRepository.persist(menuEntity);
        assertTrue(menuRepository.findByIdAndRestaurant(menuEntity.getId(), restaurantEntity).isPresent());

        menuEntity.addCategory(categoryEntity);
        menuRepository.persist(menuEntity);

        menuRepository.findByIdAndRestaurant(menuEntity.getId(), restaurantEntity).ifPresent(menuResult -> {
            assertEquals(1, menuResult.getCategories().size());
        });




    }

}