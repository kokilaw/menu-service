package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.ModifierOptionDTO;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import com.au.eatclub.menu.util.NumberUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ModifierOptionMapperTest {

    @Test
    void givenModifierOption_whenMapped_modifierOptionDTOIsReturned() {
        ModifierOptionEntity modifierOptionEntity = ModifierOptionEntity.builder()
                .price(BigDecimal.valueOf(2.55f))
                .name("MO_001")
                .id(UUID.randomUUID().toString())
                .build();
        ModifierOptionDTO modifierOptionDTO = ModifierOptionMapper.INSTANCE.toModifierOptionDTO(modifierOptionEntity);
        assertNotNull(modifierOptionDTO);
        assertEquals(modifierOptionEntity.getId(), modifierOptionDTO.getId());
        assertEquals(modifierOptionEntity.getName(), modifierOptionDTO.getName());
        assertEquals(NumberUtil.formatPrice(modifierOptionEntity.getPrice()), modifierOptionDTO.getPrice());
    }

}