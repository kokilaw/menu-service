package com.au.eatclub.menu.mapper;

import com.au.eatclub.menu.api.dto.CategoryDTO;
import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CategoryMapper {

    CategoryMapper INSTANCE =
            Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "items", target = "itemIds", qualifiedByName = "getItemIds")
    CategoryDTO toCategoryDTO(CategoryEntity categoryEntity);

    @Named("getItemIds")
    static List<String> getItemIds(List<ItemEntity> categories) {
        return categories.stream().map(ItemEntity::getId).toList();
    }

}
