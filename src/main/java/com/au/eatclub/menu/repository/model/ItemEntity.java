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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "item",
            orphanRemoval = true
    )
    private List<ItemVariantEntity> variants = new ArrayList<>();

    @Builder.Default
    @ManyToMany(cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "category_item",
            joinColumns = {
                    @JoinColumn(
                            name = "item_id",
                            referencedColumnName = "internal_id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "category_id",
                            referencedColumnName = "internal_id"
                    )
            }
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public void addVariant(ItemVariantEntity variant) {
        variants.add(variant);
        variant.setItem(this);
    }

    public void removeVariant(ItemVariantEntity variant) {
        variant.setItem(null);
        variants.remove(variant);
    }

}
