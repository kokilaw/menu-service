package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.MenuDTO;
import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.MenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface MenuMapper {

    MenuMapper INSTANCE =
            Mappers.getMapper(MenuMapper.class);

    @Mapping(source = "categories", target = "categoryIds", qualifiedByName = "getCategoryIds")
    MenuDTO toMenuDTO(MenuEntity menu);

    @Named("getCategoryIds")
    static List<String> getCategoryIds(List<CategoryEntity> categories) {
        return categories.stream().map(CategoryEntity::getId).toList();
    }

}
