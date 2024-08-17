package com.au.eatclub.menu.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestTransaction
@QuarkusTest
class ModifierGroupRepositoryTest {

    @Inject
    RestaurantRepository restaurantRepository;

    private final static String DEFAULT_RESTAURANT_ID = UUID.randomUUID().toString();



}