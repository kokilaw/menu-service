SET
@restaurant_one_id = 'b30c8aba-7784-4d24-9c7e-5f9c8eeb1153';

SET
@menu_lunch = uuid();
SET
@menu_dinner = uuid();

SET
@cat_veg = uuid();
SET
@cat_nonveg = uuid();

SET
@item_cheese = uuid();
SET
@item_margherita = uuid();
SET
@item_meat_lovers = uuid();
SET
@item_pepperoni = uuid();
SET
@item_seafood = uuid();
SET
@item_hawaiian = uuid();

INSERT INTO restaurant (country_code, currency_code, email, name, phone_number, website, id)
VALUES ('AU', 'AUD', 'cafe1@gmail.com', 'Cafe #1', '0061459765384', 'cafeone.com.au', @restaurant_one_id);

INSERT INTO menu (end_at, start_at, name, restaurant_id, time_based, id)
values ('23:59:59', '18:00:00', 'Dinner', @restaurant_one_id, 1, @menu_dinner),
       ('23:59:59', '00:00:00', 'All Day', @restaurant_one_id, 0, @menu_lunch);

INSERT INTO category (name, restaurant_id, id)
VALUES ('Vegetarian Pizza', @restaurant_one_id, @cat_veg),
       ('Non-Vegetarian Pizza', @restaurant_one_id, @cat_nonveg);

INSERT INTO menu_category (menu_id, category_id)
VALUES (@menu_lunch, @cat_veg),
       (@menu_lunch, @cat_nonveg),
       (@menu_dinner, @cat_veg),
       (@menu_dinner, @cat_nonveg);

INSERT INTO item (description, name, restaurant_id, id)
VALUES ('All children like pressed chicken lards in gold tequila and butterscotch.', 'Cheese Pizza', @restaurant_one_id,
        @item_cheese),
       ('What’s the secret to yellow and tangy apple? Always use gooey vodka.', 'Margherita Pizza', @restaurant_one_id,
        @item_margherita),
       ('Slobbery, warm pudding is best rubed with aged buttermilk.', 'Meat Lovers Pizza', @restaurant_one_id,
        @item_meat_lovers),
       ('What’s the secret to clammy and grey doughnut? Always use sun-dried nutmeg.', 'Pepperoni Pizza',
        @restaurant_one_id, @item_pepperoni),
       ('Apple combines greatly with ripe cracker crumps.', 'Seafood Pizza', @restaurant_one_id, @item_seafood),
       ('Try cooking carrots pie mashed up with sweet chili sauce.', 'Hawaiian Pizza', @restaurant_one_id,
        @item_hawaiian);

INSERT INTO item_variant (item_id, name, price, type, id)
VALUES (@item_cheese, 'Small', 7.99, 'SIZE', uuid()),
       (@item_cheese, 'Medium', 9.99, 'SIZE', uuid()),
       (@item_cheese, 'Large', 12.99, 'SIZE', uuid()),
       (@item_margherita, 'Small', 8.99, 'SIZE', uuid()),
       (@item_margherita, 'Medium', 10.99, 'SIZE', uuid()),
       (@item_margherita, 'Large', 12.99, 'SIZE', uuid()),
       (@item_meat_lovers, 'Small', 9.99, 'SIZE', uuid()),
       (@item_meat_lovers, 'Medium', 11.99, 'SIZE', uuid()),
       (@item_meat_lovers, 'Large', 13.99, 'SIZE', uuid()),
       (@item_pepperoni, 'Small', 7.99, 'SIZE', uuid()),
       (@item_pepperoni, 'Medium', 9.99, 'SIZE', uuid()),
       (@item_pepperoni, 'Large', 12.99, 'SIZE', uuid()),
       (@item_seafood, 'Small', 7.99, 'SIZE', uuid()),
       (@item_seafood, 'Medium', 9.99, 'SIZE', uuid()),
       (@item_seafood, 'Large', 12.99, 'SIZE', uuid()),
       (@item_hawaiian, 'Small', 7.99, 'SIZE', uuid()),
       (@item_hawaiian, 'Medium', 9.99, 'SIZE', uuid()),
       (@item_hawaiian, 'Large', 12.99, 'SIZE', uuid());

INSERT INTO category_item(item_id, category_id)
VALUES (@item_cheese, @cat_veg),
       (@item_margherita, @cat_veg),
       (@item_meat_lovers, @cat_nonveg),
       (@item_pepperoni, @cat_nonveg),
       (@item_seafood, @cat_nonveg),
       (@item_hawaiian, @cat_nonveg);

SET
@mg_sauce = uuid();
SET
@mg_meat = uuid();
SET
@mg_cheese = uuid();

INSERT INTO modifier_group (max_allowed, min_required, name, restaurant_id, id)
VALUES (0, 0, 'Sauce', @restaurant_one_id, @mg_sauce),
       (2, 0, 'Meat AddOn', @restaurant_one_id, @mg_meat),
       (1, 0, 'Cheese AddOn', @restaurant_one_id, @mg_cheese);

INSERT INTO item_modifier_group (modifier_group_id, item_id)
values (@mg_sauce, @item_cheese),
       (@mg_sauce, @item_margherita),
       (@mg_sauce, @item_meat_lovers),
       (@mg_sauce, @item_pepperoni),
       (@mg_sauce, @item_seafood),
       (@mg_sauce, @item_hawaiian),
       (@mg_meat, @item_meat_lovers),
       (@mg_meat, @item_pepperoni),
       (@mg_meat, @item_seafood),
       (@mg_meat, @item_hawaiian),
       (@mg_cheese, @item_cheese),
       (@mg_cheese, @item_margherita),
       (@mg_cheese, @item_meat_lovers),
       (@mg_cheese, @item_pepperoni),
       (@mg_cheese, @item_seafood),
       (@mg_cheese, @item_hawaiian);

SET
@mo_ketchup = uuid();
SET
@mo_mayonnaise = uuid();

SET
@mo_chicken_peperoni = uuid();
SET
@mo_beef_peperoni = uuid();
SET
@mo_bacon = uuid();

SET
@mo_chedar = uuid();
SET
@mo_mozzarella = uuid();

INSERT INTO modifier_option (name, price, restaurant_id, id)
values ('Ketchup', 0.00, @restaurant_one_id, @mo_ketchup),
       ('Mayonnaise', 0.00, @restaurant_one_id, @mo_mayonnaise),
       ('Chicken Peperoni', 1.50, @restaurant_one_id, @mo_chicken_peperoni),
       ('Beef Peperoni', 2.00, @restaurant_one_id, @mo_beef_peperoni),
       ('Bacon', 2.00, @restaurant_one_id, @mo_bacon),
       ('Cheddar', 1.75, @restaurant_one_id, @mo_chedar),
       ('Mozzarella', 1.50, @restaurant_one_id, @mo_mozzarella);

INSERT INTO modifier_group_modifier_option (modifier_group_id, modifier_option_id)
values (@mg_sauce, @mo_ketchup),
       (@mg_sauce, @mo_mayonnaise),
       (@mg_meat, @mo_chicken_peperoni),
       (@mg_meat, @mo_beef_peperoni),
       (@mg_meat, @mo_bacon),
       (@mg_cheese, @mo_chedar),
       (@mg_cheese, @mo_mozzarella);