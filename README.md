# menu-service

## Requirements

For building and running the application you need:
- Java 17
- Quarkus 3.13.2

## Database Design & Design Decisions

![Alt text](./misc/db-design/eat-club-coding-assignment-db-design.svg)
- Please refer [this page](misc/database-table-spec.md) for the detailed specification of the tables.  
- `restaurant` table holds the data related to a specific restaurant.
- `category` table holds the data related to categories of a menu.
- `item` table holds the data related to items of a menu.
- `modifier_group` table holds the data related modifier groups available for items. (Eg: Available addons for an item)
- `modifier_option` table holds the data related modifier option available for modifier groups. (Eg: Addon options)
- `item_variant` table holds the data related to variants of a specific item. (Eg: Sizes of a pizza like small, medium and large)
- `category_item` mapping table has been introduced since a category can include multiple items and an item can belong to multiple categories.
- `item_modifier_group` mapping table has been introduced since an item can include multiple modifier groups and a modifier group can belong to multiple items.
- `modifier_group_modifier_option` mapping table has been introduced since a modifier group can include multiple modifier options and a modifier option can belong to multiple modifier groups.
- `public_id` column on tables `restaurant, category, item, modifier group and modifier option` will hold an UUID value which can be exposed to public as an id if needed. Column `id` is not used to avoid exposing guessable id to public.
- `item_variant.type` column will have two possible values as DEFAULT and SIZE.
  - DEFAULT - when item has no variants
  - SIZE - when item variants are based on size


