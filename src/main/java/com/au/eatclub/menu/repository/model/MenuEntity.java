package com.au.eatclub.menu.repository.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Column(name = "time_based")
    private Boolean timeBased;

    @Column(name = "start_at")
    private LocalTime startAt;

    @Column(name = "end_at")
    private LocalTime endAt;

    @Builder.Default
    @ManyToMany(
            mappedBy = "menus",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<CategoryEntity> categories = new ArrayList<>();

}
