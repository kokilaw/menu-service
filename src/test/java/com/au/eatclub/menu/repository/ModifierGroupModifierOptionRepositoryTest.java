package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestTransaction
@QuarkusTest
class ModifierGroupModifierOptionRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    ModifierGroupRepository modifierGroupRepository;

    @Inject
    ModifierOptionRepository modifierOptionRepository;

    private String restaurantId = UUID.randomUUID().toString();

    @Test
    void givenModifierOptions_whenAttachedToModifierGroup_isReturnedWithModifierGroup() {

        RestaurantEntity restaurantEntity = RepositoryTestUtil.getRestaurantEntity(restaurantRepository, restaurantId);
        restaurantId = restaurantEntity.getId();

        ModifierOptionEntity moOne = ModifierOptionEntity.builder()
                .name("MO_001")
                .price(BigDecimal.valueOf(2.00f))
                .restaurant(restaurantEntity)
                .build();
        ModifierOptionEntity moTwo = ModifierOptionEntity.builder()
                .name("MO_002")
                .price(BigDecimal.valueOf(3.00f))
                .restaurant(restaurantEntity)
                .build();

        modifierOptionRepository.persist(moOne);
        modifierOptionRepository.persist(moTwo);

        ModifierGroupEntity mgOne = ModifierGroupEntity.builder()
                .name("MG_001")
                .restaurant(restaurantEntity)
                .minRequired(1)
                .maxAllowed(0)
                .build();

        modifierGroupRepository.persist(mgOne);
        assertTrue(modifierGroupRepository.findByIdAndRestaurant(mgOne.getId(), restaurantEntity).isPresent(), "Saved modifier group is present");

        mgOne.addModifierOption(moOne);
        mgOne.addModifierOption(moTwo);

        modifierGroupRepository.persist(mgOne);
        Optional<ModifierGroupEntity> modifierGroupResult = modifierGroupRepository.findByIdAndRestaurant(mgOne.getId(), restaurantEntity);
        assertTrue(modifierGroupResult.isPresent(), "Saved modifier group is present");
        modifierGroupResult.ifPresent(modifierGroupEntity -> {
            assertTrue(modifierGroupEntity.getModifierOptions().contains(moOne), "Saved modifier option is present");
            assertTrue(modifierGroupEntity.getModifierOptions().contains(moTwo), "Saved modifier option is present");
        });

    }

}