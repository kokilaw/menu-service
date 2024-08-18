package com.au.eatclub.menu.service;

import com.au.eatclub.menu.exception.NotFoundException;
import com.au.eatclub.menu.mapper.CategoryMapper;
import com.au.eatclub.menu.mapper.ItemMapper;
import com.au.eatclub.menu.mapper.MenuMapper;
import com.au.eatclub.menu.mapper.ModifierGroupMapper;
import com.au.eatclub.menu.mapper.ModifierOptionMapper;
import com.au.eatclub.menu.repository.CategoryRepository;
import com.au.eatclub.menu.repository.ItemRepository;
import com.au.eatclub.menu.repository.MenuRepository;
import com.au.eatclub.menu.repository.ModifierGroupRepository;
import com.au.eatclub.menu.repository.ModifierOptionRepository;
import com.au.eatclub.menu.repository.RestaurantRepository;
import com.au.eatclub.menu.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;

class MenuServiceTest {

    RestaurantRepository restaurantRepository;
    MenuRepository menuRepository;
    CategoryRepository categoryRepository;
    ItemRepository itemRepository;
    ModifierGroupRepository modifierGroupRepository;
    ModifierOptionRepository modifierOptionRepository;
    private MenuService menuService;


    @BeforeEach
    void setUp() {
        this.restaurantRepository = Mockito.mock(RestaurantRepository.class);
        this.menuRepository = Mockito.mock(MenuRepository.class);
        this.categoryRepository = Mockito.mock(CategoryRepository.class);
        this.itemRepository = Mockito.mock(ItemRepository.class);
        this.modifierGroupRepository = Mockito.mock(ModifierGroupRepository.class);
        this.modifierOptionRepository = Mockito.mock(ModifierOptionRepository.class);
        menuService = new MenuServiceImpl(
                restaurantRepository,
                menuRepository,
                categoryRepository,
                itemRepository,
                modifierGroupRepository,
                modifierOptionRepository,
                MenuMapper.INSTANCE,
                CategoryMapper.INSTANCE,
                ItemMapper.INSTANCE,
                ModifierGroupMapper.INSTANCE,
                ModifierOptionMapper.INSTANCE
        );
    }

    @Test
    void givenNotSavedRestaurantId_whenRequested_errorIsThrown() {
        String restaurantId = UUID.randomUUID().toString();
        Mockito.when(restaurantRepository.findByIdOptional(eq(restaurantId)))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> menuService.getMenu(restaurantId));
    }
}