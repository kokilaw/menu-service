package com.au.eatclub.menu.repository.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Builder.Default
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "item",
            orphanRemoval = true
    )
    private List<ItemVariantEntity> variants = new ArrayList<>();

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "category_item",
            joinColumns = {
                    @JoinColumn(
                            name = "item_id",
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
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Builder.Default
    @ManyToMany(
            mappedBy = "items",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<ModifierGroupEntity> modifierGroups = new ArrayList<>();

    public enum ItemType {
        DEFAULT_ITEM, MODIFIER
    }

    public void addVariant(ItemVariantEntity variant) {
        variants.add(variant);
        variant.setItem(this);
    }

    public void removeVariant(ItemVariantEntity variant) {
        variant.setItem(null);
        variants.remove(variant);
    }

    public void addModifierGroup(ModifierGroupEntity modifierGroup) {
        this.modifierGroups.add(modifierGroup);
        modifierGroup.getItems().add(this);
    }

    public void removeModifierGroup(ModifierGroupEntity modifierGroup) {
        this.modifierGroups.remove(modifierGroup);
        modifierGroup.getItems().remove(this);
    }

}
