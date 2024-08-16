package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.ItemVariantEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

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

    @Inject
    EntityManager entityManager;

    private final static String DEFAULT_RESTAURANT_ID = UUID.randomUUID().toString();

    @Test
    void itemsAreCorrectlyGettingSavedWithCategories() {

        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(
                restaurantRepository,
                DEFAULT_RESTAURANT_ID
        );

        String categoryId = UUID.randomUUID().toString();
        restaurantEntity.addCategory(CategoryEntity.builder()
                .id(categoryId)
                .name("CAT_1")
                .build());
        restaurantRepository.persist(restaurantEntity);

        Optional<CategoryEntity> categorySaveResult = categoryRepository
                .findByIdAndRestaurant(categoryId, restaurantEntity);

        assertTrue(categorySaveResult.isPresent(), "Saved category not available");
        CategoryEntity savedCategory = categorySaveResult.get();

        String itemOneId = UUID.randomUUID().toString();
        ItemEntity itemOne = ItemEntity.builder()
                .id(itemOneId)
                .name("ITEM_1")
                .type(ItemEntity.ItemType.DEFAULT_ITEM)
                .description("sample description 1")
                .restaurant(restaurantEntity)
                .build();
        String itemTwoId = UUID.randomUUID().toString();
        ItemEntity itemTwo = ItemEntity.builder()
                .id(itemTwoId)
                .name("ITEM_2")
                .type(ItemEntity.ItemType.DEFAULT_ITEM)
                .description("sample description 2")
                .restaurant(restaurantEntity)
                .build();

        savedCategory.addItem(itemOne);
        savedCategory.addItem(itemTwo);
        categoryRepository.persist(savedCategory);

        assertTrue(
                itemRepository.findByIdAndRestaurant(itemOneId, restaurantEntity).isPresent(),
                "Save first item not available"
        );
        assertTrue(
                itemRepository.findByIdAndRestaurant(itemTwoId, restaurantEntity).isPresent(),
                "Save second item not available"
        );

    }

    @Test
    void itemVariantsAreCorrectlySavedAndReturned() {
        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(
                restaurantRepository,
                DEFAULT_RESTAURANT_ID
        );
        String itemId = UUID.randomUUID().toString();
        ItemEntity itemEntity = ItemEntity.builder()
                .id(itemId)
                .name("ITEM_1")
                .description("sample description 1")
                .type(ItemEntity.ItemType.DEFAULT_ITEM)
                .restaurant(restaurantEntity)
                .build();
        itemEntity.addVariant(ItemVariantEntity.builder()
                .type(ItemVariantEntity.VariantType.SIZE)
                .price(BigDecimal.valueOf(2.00f))
                .name("Small")
                .id(UUID.randomUUID().toString())
                .build());
        itemEntity.addVariant(ItemVariantEntity.builder()
                .type(ItemVariantEntity.VariantType.SIZE)
                .price(BigDecimal.valueOf(4.00f))
                .name("Medium")
                .id(UUID.randomUUID().toString())
                .build());
        itemRepository.persist(itemEntity);

        Optional<ItemEntity> savedItemResult = itemRepository.findByIdAndRestaurant(itemId, restaurantEntity);
        assertTrue(savedItemResult.isPresent(), "Saved item not available");
        savedItemResult.ifPresent(savedItem -> {
            assertEquals(2, savedItem.getVariants().size(), "Saved two variants are not available");
        });

    }

}