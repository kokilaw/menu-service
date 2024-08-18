package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.ModifierGroupDTO;
import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ModifierGroupMapper {

    ModifierGroupMapper INSTANCE =
            Mappers.getMapper(ModifierGroupMapper.class);

    @Mapping(source = "modifierOptions", target = "modifierOptionIds", qualifiedByName = "getModifierOptionIds")
    ModifierGroupDTO toModifierGroupDTO(ModifierGroupEntity modifierGroupEntity);

    @Named("getModifierOptionIds")
    static List<String> getModifierOptionIds(List<ModifierOptionEntity> modifierOptionEntities) {
        return modifierOptionEntities.stream().map(ModifierOptionEntity::getId).toList();
    }

}
