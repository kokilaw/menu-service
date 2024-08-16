package com.au.eatclub.menu.repository;

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
class RestaurantRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    @Test
    void givenEntity_whenSaved_IdGetsSetAndSavedEntityIsReturning() {
        String publicId = UUID.randomUUID().toString();
        RestaurantEntity entity = RestaurantEntity.builder()
                .publicId(publicId)
                .name("Curry Pot")
                .email("info@currypot.com.au")
                .phoneNumber("0061456098345")
                .countryCode("AU")
                .currencyCode("AUD")
                .build();
        restaurantRepository.persist(entity);
        assertNotNull(entity.getId());

        Optional<RestaurantEntity> savedEntity = restaurantRepository.findByIdOptional(entity.getId());
        assertTrue(savedEntity.isPresent());
        assertTrue(restaurantRepository.findByPublicId(publicId).isPresent());

    }

}