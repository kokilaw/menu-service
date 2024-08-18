package com.au.eatclub.menu.api.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class CategoryDTO {
    private String id;
    private String name;
    private List<String> itemIds = Collections.emptyList();
}
