package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.ItemDTO;
import com.au.eatclub.menu.api.dto.ItemVariantDTO;
import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.ItemVariantEntity;
import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.util.NumberUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface ItemMapper {

    ItemMapper INSTANCE =
            Mappers.getMapper(ItemMapper.class);

    @Mapping(source = "modifierGroups", target = "modifierGroupIds", qualifiedByName = "getModifierGroupIds")
    ItemDTO toItemDTO(ItemEntity itemEntity);

    @Mapping(source = "price", target = "price", qualifiedByName = "getFormattedPrice")
    ItemVariantDTO toItemVariantDTO(ItemVariantEntity itemVariantEntity);

    @Named("getModifierGroupIds")
    static List<String> getModifierGroupIds(List<ModifierGroupEntity> modifierGroupEntities) {
        return modifierGroupEntities.stream().map(ModifierGroupEntity::getId).toList();
    }

    @Named("getFormattedPrice")
    static String getFormattedPrice(BigDecimal price) {
        return NumberUtil.formatPrice(price);
    }

}
