package com.au.eatclub.menu.api.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Data
public class MenuDTO {
    private String id;
    private String name;
    private Boolean timeBased;
    private LocalTime startAt;
    private LocalTime endAt;
    private List<String> categoryIds = Collections.emptyList();
}
