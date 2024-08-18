package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.CategoryDTO;
import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.ItemEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryMapperTest {

    @Test
    void givenCategoryEntity_whenMapped_aValidCategoryDTOIsReturn() {
        List<ItemEntity> itemEntities = List.of(
                ItemEntity.builder().id(UUID.randomUUID().toString()).build(),
                ItemEntity.builder().id(UUID.randomUUID().toString()).build()
        );
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("CAT_001")
                .items(itemEntities)
                .build();
        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.toCategoryDTO(categoryEntity);
        assertNotNull(categoryDTO);
        assertEquals(categoryEntity.getId(), categoryDTO.getId());
        assertEquals(categoryEntity.getName(), categoryDTO.getName());
        assertEquals(itemEntities.stream().map(ItemEntity::getId).toList(), categoryDTO.getItemIds());
    }

}