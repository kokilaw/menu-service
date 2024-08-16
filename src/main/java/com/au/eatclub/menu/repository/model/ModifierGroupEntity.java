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
@Table(name = "modifier_group")
public class ModifierGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Builder.Default
    @Column(name = "min_required")
    private Integer minRequired = 0;

    @Builder.Default
    @Column(name = "max_allowed")
    private Integer maxAllowed = 0;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "item_modifier_group",
            joinColumns = {
                    @JoinColumn(
                            name = "modifier_group_id",
                            referencedColumnName = "internal_id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "item_id",
                            referencedColumnName = "internal_id"
                    )
            }
    )
    private List<ItemEntity> items = new ArrayList<>();

}
