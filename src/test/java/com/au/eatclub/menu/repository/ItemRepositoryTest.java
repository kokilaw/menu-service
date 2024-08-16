package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestTransaction
@QuarkusTest
class ItemRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    ItemRepository itemRepository;

    @Test
    void itemsAreCorrectlyGettingSavedWithCategories() {

        String restaurantId = UUID.randomUUID().toString();
        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(restaurantRepository, restaurantId);

        String categoryId = UUID.randomUUID().toString();
        restaurantEntity.addCategory(CategoryEntity.builder()
                .publicId(categoryId)
                .name("CAT_1")
                .build());
        restaurantRepository.persist(restaurantEntity);

        Optional<CategoryEntity> categorySaveResult = categoryRepository.findByPublicIdAndRestaurantId(categoryId, restaurantEntity);
        assertTrue(categorySaveResult.isPresent());
        CategoryEntity savedCategory = categorySaveResult.get();

        String itemOneId = UUID.randomUUID().toString();
        ItemEntity itemOne = ItemEntity.builder()
                .publicId(itemOneId)
                .name("ITEM_1")
                .description("sample description 1")
                .restaurant(restaurantEntity)
                .build();
        String itemTwoId = UUID.randomUUID().toString();
        ItemEntity itemTwo = ItemEntity.builder()
                .publicId(itemTwoId)
                .name("ITEM_2")
                .description("sample description 2")
                .restaurant(restaurantEntity)
                .build();

        savedCategory.addItem(itemOne);
        savedCategory.addItem(itemTwo);
        categoryRepository.persist(savedCategory);

        assertTrue(itemRepository.findByPublicIdAndRestaurantId(itemOneId, restaurantEntity).isPresent());
        assertTrue(itemRepository.findByPublicIdAndRestaurantId(itemTwoId, restaurantEntity).isPresent());

    }

}