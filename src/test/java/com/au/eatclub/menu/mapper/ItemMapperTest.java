package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.ItemDTO;
import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.ItemVariantEntity;
import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.util.NumberUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemMapperTest {

    @Test
    void givenItemEntity_whenVariantsAndModifiersIncluded_itemDTOIsReturned() {

        List<ModifierGroupEntity> modifierGroupEntities = List.of(
                ModifierGroupEntity.builder().id(UUID.randomUUID().toString()).build(),
                ModifierGroupEntity.builder().id(UUID.randomUUID().toString()).build()
        );
        List<ItemVariantEntity> itemVariantEntities = List.of(
                ItemVariantEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .name("Default")
                        .type(ItemVariantEntity.VariantType.DEFAULT)
                        .price(BigDecimal.valueOf(4.98f))
                        .build()
        );
        ItemEntity itemEntity = ItemEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("ITEM_001")
                .modifierGroups(modifierGroupEntities)
                .variants(itemVariantEntities)
                .description("sample description")
                .build();

        ItemDTO itemDTO = ItemMapper.INSTANCE.toItemDTO(itemEntity);
        assertNotNull(itemDTO);
        assertEquals(itemEntity.getId(), itemDTO.getId());
        assertEquals(itemEntity.getName(), itemDTO.getName());
        assertEquals(itemEntity.getDescription(), itemDTO.getDescription());

        assertEquals(itemEntity.getModifierGroups().stream().map(ModifierGroupEntity::getId).toList(), itemDTO.getModifierGroupIds());

        assertEquals(1, itemDTO.getVariants().size());
        assertEquals(itemEntity.getVariants().get(0).getId(), itemDTO.getVariants().get(0).getId());
        assertEquals(itemEntity.getVariants().get(0).getName(), itemDTO.getVariants().get(0).getName());
        assertEquals(itemEntity.getVariants().get(0).getType().name(), itemDTO.getVariants().get(0).getType());
        assertEquals(NumberUtil.formatPrice(itemEntity.getVariants().get(0).getPrice()), itemDTO.getVariants().get(0).getPrice());

    }

}