package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.ModifierOptionDTO;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import com.au.eatclub.menu.util.NumberUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "cdi")
public interface ModifierOptionMapper {

    ModifierOptionMapper INSTANCE =
            Mappers.getMapper(ModifierOptionMapper.class);

    @Mapping(source = "price", target = "price", qualifiedByName = "getFormattedPrice")
    ModifierOptionDTO toModifierOptionDTO(ModifierOptionEntity modifierOptionEntity);

    @Named("getFormattedPrice")
    static String getFormattedPrice(BigDecimal price) {
        return NumberUtil.formatPrice(price);
    }

}
