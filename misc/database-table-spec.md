# Table Specification

## restaurant

| Column Name   | Properties       | Description                                                     |
|---------------|------------------|-----------------------------------------------------------------|
| id            | Primary Key      | UUID value                                                      |
| name          | Not Null         | Name of the restaurant                                          |
| website       |                  | Website of the restaurant                                       |
| phone_number  | Unique, Not Null | Phone number of the restaurant in format -                      |
| email         | Unique, Not Null | Email of the restaurant in format -                             |
| country_code  | Not Null         | Country code in ISO2 standard. (Eg: AU)                         |
| currency_code | Not Null         | Currency used at the restaurant in ISO 4217 standard. (Eg: AUD) |

## menu

| Column Name   | Properties                     | Description                                        |
|---------------|--------------------------------|----------------------------------------------------|
| id            | Primary Key                    | UUID value                                         |
| name          | Not Null                       | Name of the menu                                   |
| restaurant_id | Foreign key to `restaurant.id` | `restaurant.id` which the menu belongs to.         |
| time_based    | boolean                        | Defines whether the menu is time restricted or not |
| start_at      | time                           | if `time_based` is true, start time of the menu    |
| end_at        | time                           | if `time_based` is true, end time of the menu      |

## category

| Column Name   | Properties                     | Description                                    |
|---------------|--------------------------------|------------------------------------------------|
| id            | Primary Key                    | UUID value                                     |
| restaurant_id | Foreign key to `restaurant.id` | `restaurant.id` which the category belongs to. |
| name          | Not Null                       | Name of the category                           |

## item

| Column Name   | Properties                     | Description                                |
|---------------|--------------------------------|--------------------------------------------|
| id            | Primary Key                    | UUID value                                 |
| restaurant_id | Foreign key to `restaurant.id` | `restaurant.id` which the item belongs to. |
| name          | Not Null                       | Name of the item                           |
| description   | Not Null                       | Description of the item                    |

## item_variant

| Column Name | Properties                                | Description                             |
|-------------|-------------------------------------------|-----------------------------------------|
| id          | Primary key with auto increment, Not Null | Integer value                           |
| item_id     | Foreign key to `item.id`                  | `item.id` which the variant belongs to. |
| type        | Not Null                                  | Type of the variant - DEFAULT or SIZE   |
| price       | Decimal, Not Null                         | Price of the item                       |
| name        | Not Null                                  | Name of the item variant                |

## modifier_group

| Column Name   | Properties                                | Description                                          |
|---------------|-------------------------------------------|------------------------------------------------------|
| id            | Primary key with auto increment, Not Null | Integer value                                        |
| restaurant_id | Foreign key to `restaurant.id`            | `restaurant.id` which the modifier group belongs to. |
| name          | Not Null                                  | Name of the modifier group                           |
| min_required  | Integer                                   | Minimum required when selecting modifier options     |
| max_allowed   | Integer                                   | Maximum allowed when selection modifier options      |

## modifier_option

| Column Name | Properties                                | Description                  |
|-------------|-------------------------------------------|------------------------------|
| id          | Primary key with auto increment, Not Null | Integer value                |
| name        | Not Null                                  | Name of the modifier option  |
| price       | Decimal, Not Null                         | Price of the modifier option |
