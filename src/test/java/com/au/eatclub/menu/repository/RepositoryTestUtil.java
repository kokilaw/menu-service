package com.au.eatclub.menu.repository;

import com.au.eatclub.menu.repository.model.RestaurantEntity;
import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class RepositoryTestUtil {

    private static final Logger LOGGER = Logger.getLogger(RepositoryTestUtil.class);

    public static RestaurantEntity getRestaurantEntity(RestaurantRepository restaurantRepository, String restaurantId) {
        return restaurantRepository.findByIdOptional(restaurantId).orElseGet(() -> {
            RestaurantEntity restaurantEntity = RestaurantEntity.builder()
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
