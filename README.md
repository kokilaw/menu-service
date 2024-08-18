# menu-service

## Table Of Contents

* [Requirements](#requirements)
* [Database Design & Design Decisions](#database-design---design-decisions)
* [Table Spec](misc/database-table-spec.md)

## Requirements

- Java 17
- Quarkus 3.13.2
- Docker

## Database Design & Design Decisions

![Alt text](./misc/db-design/eat-club-coding-assignment-db-design.svg)

- Please refer [this page](misc/database-table-spec.md) for the detailed specification of the tables.
- `restaurant` table holds the data related to a specific restaurant.
- `menu` table holds the data related to menus available at the restaurant (Eg: breakfast, lunch).
- `category` table holds the data related to categories of a menu.
- `item` table holds the data related to items of a menu.
- `modifier_group` table holds the data related modifier groups available for items. (Eg: Available addons for an item)
- `modifier_option` table holds the data related modifier option available for modifier groups. (Eg: Addon options)
- `item_variant` table holds the data related to variants of a specific item. (Eg: Sizes of a pizza like small, medium
  and large)
- `category_item` mapping table has been introduced since a category can include multiple items and an item can belong
  to multiple categories.
- `item_modifier_group` mapping table has been introduced since an item can include multiple modifier groups and a
  modifier group can belong to multiple items.
- `modifier_group_modifier_option` mapping table has been introduced since a modifier group can include multiple
  modifier options and a modifier option can belong to multiple modifier groups.

