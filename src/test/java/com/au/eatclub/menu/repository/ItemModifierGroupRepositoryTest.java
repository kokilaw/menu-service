package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
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
class ItemModifierGroupRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    ModifierGroupRepository modifierGroupRepository;

    @Inject
    ItemRepository itemRepository;

    private String restaurantId = UUID.randomUUID().toString();

    @Test
    void modifierGroupsAreSavedAndReturned() {

        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(restaurantRepository, restaurantId);
        restaurantId = restaurantEntity.getId();
        ModifierGroupEntity mgOne = ModifierGroupEntity.builder()
                .name("MG_001")
                .restaurant(restaurantEntity)
                .minRequired(1)
                .maxAllowed(0)
                .build();
        modifierGroupRepository.persist(mgOne);
        assertTrue(modifierGroupRepository.findByIdAndRestaurant(mgOne.getId(), restaurantEntity).isPresent(), "Saved modifier group is present");

        ItemEntity itemOne = ItemEntity.builder()
                .name("ITEM_001")
                .restaurant(restaurantEntity)
                .build();
        itemOne.addModifierGroup(mgOne);
        itemRepository.persist(itemOne);

        Optional<ItemEntity> savedItemResult = itemRepository.findByIdAndRestaurant(itemOne.getId(), restaurantEntity);
        assertTrue(savedItemResult.isPresent(), "Saved item is present");
        savedItemResult.ifPresent(itemEntity -> {
            assertTrue(itemEntity.getModifierGroups().contains(mgOne), "Saved modifier group is present");
        });

    }

}