package com.au.eatclub.menu.service;

import com.au.eatclub.menu.api.dto.FullMenuDTO;
import com.au.eatclub.menu.api.response.OutputObject;

public interface MenuService {
    OutputObject<FullMenuDTO> getMenu(String restaurantId);
}
