package com.au.eatclub.menu.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVariantDTO {
    private String id;
    private String name;
    private String type;
    private String price;
}
