package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.MenuDTO;
import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.MenuEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuMapperTest {

    @Test
    void givenMenuEntity_whenMapped_aValidMenuDTOIsReturn() {

        CategoryEntity catOne = CategoryEntity.builder().id(UUID.randomUUID().toString()).build();

        MenuEntity menuEntity = MenuEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("Breakfast")
                .timeBased(Boolean.TRUE)
                .restaurant(RestaurantEntity.builder().build())
                .startAt(LocalTime.now())
                .endAt(LocalTime.now())
                .categories(List.of(catOne))
                .build();
        MenuDTO menuDTO = MenuMapper.INSTANCE.toMenuDTO(menuEntity);
        assertNotNull(menuDTO);
        assertEquals(menuEntity.getId(), menuDTO.getId());
        assertEquals(menuEntity.getName(), menuDTO.getName());
        assertEquals(menuEntity.getTimeBased(), menuDTO.getTimeBased());
        assertEquals(menuEntity.getStartAt(), menuDTO.getStartAt());
        assertEquals(menuEntity.getEndAt(), menuDTO.getEndAt());
        assertEquals(menuEntity.getCategories().stream().map(CategoryEntity::getId).toList(), menuDTO.getCategoryIds());

    }

}