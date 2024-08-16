package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.RestaurantEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class RestaurantRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    @Transactional
    @Test
    void givenEntity_whenSaved_IdGetsSetAndSavedEntityIsReturning() {
        RestaurantEntity entity = RestaurantEntity.builder()
                .publicId(UUID.randomUUID().toString())
                .name("Curry Pot")
                .email("info@currypot.com.au")
                .phoneNumber("0061456098345")
                .build();
        restaurantRepository.persist(entity);
        assertNotNull(entity.getId());

        Optional<RestaurantEntity> savedEntity = restaurantRepository.findByIdOptional(entity.getId());
        assertTrue(savedEntity.isPresent());
    }

}