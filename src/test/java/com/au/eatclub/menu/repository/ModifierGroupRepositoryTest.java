package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestTransaction
@QuarkusTest
class ModifierGroupRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    ModifierGroupRepository modifierGroupRepository;

    private String restaurantId = UUID.randomUUID().toString();

    @Test
    void givenModifierGroup_whenSaved_isReturnedCorrectly() {
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

    }

}