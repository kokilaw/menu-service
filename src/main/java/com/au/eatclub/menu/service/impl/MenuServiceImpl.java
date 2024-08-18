package com.au.eatclub.menu.service.impl;

import com.au.eatclub.menu.api.dto.CategoryDTO;
import com.au.eatclub.menu.api.dto.FullMenuDTO;
import com.au.eatclub.menu.api.dto.ItemDTO;
import com.au.eatclub.menu.api.dto.MenuDTO;
import com.au.eatclub.menu.api.dto.ModifierGroupDTO;
import com.au.eatclub.menu.api.dto.ModifierOptionDTO;
import com.au.eatclub.menu.api.response.OutputObject;
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
import com.au.eatclub.menu.repository.model.CategoryEntity;
import com.au.eatclub.menu.repository.model.ItemEntity;
import com.au.eatclub.menu.repository.model.MenuEntity;
import com.au.eatclub.menu.repository.model.ModifierGroupEntity;
import com.au.eatclub.menu.repository.model.ModifierOptionEntity;
import com.au.eatclub.menu.repository.model.RestaurantEntity;
import com.au.eatclub.menu.service.MenuService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class MenuServiceImpl implements MenuService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final ModifierGroupRepository modifierGroupRepository;
    private final ModifierOptionRepository modifierOptionRepository;

    private final MenuMapper menuMapper;
    private final CategoryMapper categoryMapper;
    private final ItemMapper itemMapper;
    private final ModifierGroupMapper modifierGroupMapper;
    private final ModifierOptionMapper modifierOptionMapper;

    @Inject
    public MenuServiceImpl(
            RestaurantRepository restaurantRepository,
            MenuRepository menuRepository,
            CategoryRepository categoryRepository,
            ItemRepository itemRepository,
            ModifierGroupRepository modifierGroupRepository,
            ModifierOptionRepository modifierOptionRepository,
            MenuMapper menuMapper,
            CategoryMapper categoryMapper,
            ItemMapper itemMapper,
            ModifierGroupMapper modifierGroupMapper,
            ModifierOptionMapper modifierOptionMapper
    ) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.modifierGroupRepository = modifierGroupRepository;
        this.modifierOptionRepository = modifierOptionRepository;
        this.menuMapper = menuMapper;
        this.categoryMapper = categoryMapper;
        this.itemMapper = itemMapper;
        this.modifierGroupMapper = modifierGroupMapper;
        this.modifierOptionMapper = modifierOptionMapper;
    }

    @Transactional
    @Override
    public OutputObject<FullMenuDTO> getMenu(String restaurantId) {

        RestaurantEntity restaurantEntity = restaurantRepository.findByIdOptional(restaurantId).orElseThrow(
                () -> new NotFoundException("Restaurant not found", Map.of("restaurantId", restaurantId))
        );

        List<MenuEntity> menuEntities = menuRepository.findByRestaurant(restaurantEntity);
        List<MenuDTO> menuDTOS = menuEntities.stream().map(menuMapper::toMenuDTO).toList();
        log.info("Found {} menu entries for restaurant - {}", menuDTOS.size(), restaurantId);

        List<CategoryEntity> categoryEntities = categoryRepository.findByRestaurant(restaurantEntity);
        List<CategoryDTO> categoryDTOS = categoryEntities.stream().map(categoryMapper::toCategoryDTO).toList();
        log.info("Found {} category entries for restaurant - {}", categoryDTOS.size(), restaurantId);

        List<ItemEntity> itemEntities = itemRepository.findByRestaurant(restaurantEntity);
        List<ItemDTO> itemDTOS = itemEntities.stream().map(itemMapper::toItemDTO).toList();
        log.info("Found {} item entries for restaurant - {}", itemDTOS.size(), restaurantId);

        List<ModifierGroupEntity> modifierGroupEntities = modifierGroupRepository.findByRestaurant(restaurantEntity);
        List<ModifierGroupDTO> modifierGroupDTOS = modifierGroupEntities.stream().map(modifierGroupMapper::toModifierGroupDTO).toList();
        log.info("Found {} modifier group entries for restaurant - {}", modifierGroupDTOS.size(), restaurantId);

        List<ModifierOptionEntity> modifierOptionEntities = modifierOptionRepository.findByRestaurant(restaurantEntity);
        List<ModifierOptionDTO> modifierOptionDTOS = modifierOptionEntities.stream().map(modifierOptionMapper::toModifierOptionDTO).toList();
        log.info("Found {} modifier option entries for restaurant - {}", modifierOptionDTOS.size(), restaurantId);

        FullMenuDTO fullMenuDTO = FullMenuDTO.builder()
                .menus(menuDTOS)
                .categories(categoryDTOS)
                .items(itemDTOS)
                .modifierGroups(modifierGroupDTOS)
                .modifierOptions(modifierOptionDTOS)
                .build();

        return new OutputObject<FullMenuDTO>().setResult(fullMenuDTO);
    }

}
