package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.ItemVariantEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private String testRestaurantId = "NOT_SET";

    @Test
    void itemsAreCorrectlyGettingSavedWithCategories() {

        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(
                restaurantRepository,
                testRestaurantId
        );
        testRestaurantId = restaurantEntity.getId();

        CategoryEntity categoryOne = CategoryEntity.builder()
                .name("CAT_1")
                .restaurant(restaurantEntity)
                .build();
        categoryRepository.persist(categoryOne);

        Optional<CategoryEntity> categorySaveResult = categoryRepository
                .findByIdAndRestaurant(categoryOne.getId(), restaurantEntity);

        assertTrue(categorySaveResult.isPresent(), "Saved category available");
        CategoryEntity savedCategory = categorySaveResult.get();

        ItemEntity itemOne = ItemEntity.builder()
                .name("ITEM_1")
                .description("sample description 1")
                .restaurant(restaurantEntity)
                .build();
        ItemEntity itemTwo = ItemEntity.builder()
                .name("ITEM_2")
                .description("sample description 2")
                .restaurant(restaurantEntity)
                .build();

        itemRepository.persist(Stream.of(itemOne, itemTwo));

        savedCategory.addItem(itemOne);
        savedCategory.addItem(itemTwo);
        categoryRepository.persist(savedCategory);

        assertTrue(
                itemRepository.findByIdAndRestaurant(itemOne.getId(), restaurantEntity).isPresent(),
                "Save first item available"
        );
        assertTrue(
                itemRepository.findByIdAndRestaurant(itemTwo.getId(), restaurantEntity).isPresent(),
                "Save second item available"
        );

    }

    @Test
    void itemVariantsAreCorrectlySavedAndReturned() {
        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(
                restaurantRepository,
                testRestaurantId
        );
        ItemEntity itemEntity = ItemEntity.builder()
                .name("ITEM_1")
                .description("sample description 1")
                .restaurant(restaurantEntity)
                .build();
        itemEntity.addVariant(ItemVariantEntity.builder()
                .type(ItemVariantEntity.VariantType.SIZE)
                .price(BigDecimal.valueOf(2.00f))
                .name("Small")
                .build());
        itemEntity.addVariant(ItemVariantEntity.builder()
                .type(ItemVariantEntity.VariantType.SIZE)
                .price(BigDecimal.valueOf(4.00f))
                .name("Medium")
                .build());
        itemRepository.persist(itemEntity);

        Optional<ItemEntity> savedItemResult = itemRepository.findByIdAndRestaurant(itemEntity.getId(), restaurantEntity);
        assertTrue(savedItemResult.isPresent(), "Saved item not available");
        savedItemResult.ifPresent(savedItem -> {
            assertEquals(2, savedItem.getVariants().size(), "Saved two variants are not available");
        });

    }

}