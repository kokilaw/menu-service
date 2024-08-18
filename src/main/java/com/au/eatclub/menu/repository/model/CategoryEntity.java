package com.au.eatclub.menu.repository.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @ManyToMany(
            mappedBy = "categories",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<ItemEntity> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "menu_category",
            joinColumns = {
                    @JoinColumn(
                            name = "menu_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "category_id",
                            referencedColumnName = "id"
                    )
            }
    )
    private List<MenuEntity> menus = new ArrayList<>();

    public void addItem(ItemEntity itemEntity) {
        items.add(itemEntity);
        itemEntity.getCategories().add(this);
    }

    public void removeItem(ItemEntity itemEntity) {
        items.remove(itemEntity);
        itemEntity.getCategories().remove(this);
    }

}
