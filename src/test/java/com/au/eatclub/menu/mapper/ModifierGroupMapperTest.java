package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.ModifierGroupDTO;
import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ModifierGroupMapperTest {

    @Test
    void givenModifierGroupWithModifierOptions_whenMapped_modifierGroupDTOIsReturned() {
        List<ModifierOptionEntity> modifierOptionEntities = List.of(ModifierOptionEntity.builder().build());
        ModifierGroupEntity modifierGroupEntity = ModifierGroupEntity.builder()
                .id(UUID.randomUUID().toString())
                .name("MG_001")
                .modifierOptions(modifierOptionEntities)
                .minRequired(1)
                .maxAllowed(4)
                .build();
        ModifierGroupDTO modifierGroupDTO = ModifierGroupMapper.INSTANCE.toModifierGroupDTO(modifierGroupEntity);
        assertNotNull(modifierGroupDTO);
        assertEquals(modifierGroupEntity.getId(), modifierGroupDTO.getId());
        assertEquals(modifierGroupEntity.getName(), modifierGroupDTO.getName());
        assertEquals(modifierGroupEntity.getMinRequired(), modifierGroupDTO.getMinRequired());
        assertEquals(modifierGroupEntity.getMaxAllowed(), modifierGroupDTO.getMaxAllowed());
        assertEquals(modifierGroupEntity.getModifierOptions().stream().map(ModifierOptionEntity::getId).toList(), modifierGroupDTO.getModifierOptionIds());
    }

}