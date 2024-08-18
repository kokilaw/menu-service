package com.au.eatclub.menu.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModifierGroupDTO {
    private String id;
    private String name;
    private Integer minRequired = 0;
    private Integer maxAllowed = 0;
    private List<String> modifierOptionIds = new ArrayList<>();
}
