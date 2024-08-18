package com.au.eatclub.menu.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FullMenuDTO {
    private List<MenuDTO> menus;
    private List<CategoryDTO> categories;
    private List<ItemDTO> items;
    private List<ModifierGroupDTO> modifierGroups;
    private List<ModifierOptionDTO> modifierOptions;
}
