package com.au.eatclub.menu.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDTO {
    private String id;
    private String name;
    private String description;
    private List<ItemVariantDTO> variants = new ArrayList<>();
    private List<String> modifierGroupIds = new ArrayList<>();
}
