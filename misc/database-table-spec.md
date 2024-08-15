# Table Specification

## restuarant

| Column Name   | Properties                                | Description                                                     |
|---------------|-------------------------------------------|-----------------------------------------------------------------|
| id            | Primary key with auto increment, Not Null | Integer value                                                   |
| public_id     | Unique, Not Null                          | UUID to use as public id                                        |
| name          | Not Null                                  | Name of the restaurant                                          |
| website       |                                           | Website of the restaurant                                       |
| phone_number  | Unique, Not Null                          | Phone number of the restaurant in format -                      |
| email         | Unique, Not Null                          | Email of the restaurant in format -                             |
| currency_code | Not Null                                  | Currency used at the restaurant in ISO 4217 standard. (Eg: AUD) |

## category

| Column Name   | Properties                                | Description                          |
|---------------|-------------------------------------------|--------------------------------------|
| id            | Primary key with auto increment, Not Null | Integer value                        |
| public_id     | Unique, Not Null                          | UUID to use as public id             |
| restaurant_id | Foreign key to `restaurant.id`            | `restaurant.id` category belongs to. |
| name          | Not Null                                  | Name of the category                 |

## item

| Column Name   | Properties                                | Description                            |
|---------------|-------------------------------------------|----------------------------------------|
| id            | Primary key with auto increment, Not Null | Integer value                          |
| public_id     | Unique, Not Null                          | UUID to use as public id               |
| restaurant_id | Foreign key to `restaurant.id`            | `restaurant.id` which item belongs to. |
| name          | Not Null                                  | Name of the item                       |
| description   | Not Null                                  | Description of the item                |

## item_variant

| Column Name | Properties                                | Description                             |
|-------------|-------------------------------------------|-----------------------------------------|
| id          | Primary key with auto increment, Not Null | Integer value                           |
| public_id   | Unique, Not Null                          | UUID to use as public id                |
| item_id     | Foreign key to `item.id`                  | `item.id` which the variant belongs to. |
| type        | Not Null                                  | Type of the variant - DEFAULT or SIZE   |
| price       | Decimal, Not Null                         | Description of the item                 |