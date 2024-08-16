package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.RestaurantEntity;

public class RepositoryTestUtil {

    public static RestaurantEntity getRestaurantEntity(RestaurantRepository restaurantRepository, String restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseGet(() -> {
            RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                    .id(restaurantId)
                    .name("Curry Pot")
                    .email("info@currypot.com.au")
                    .phoneNumber("0061456098345")
                    .countryCode("AU")
                    .currencyCode("AUD")
                    .build();
            restaurantRepository.persist(restaurantEntity);
            return restaurantEntity;
        });
    }

}
