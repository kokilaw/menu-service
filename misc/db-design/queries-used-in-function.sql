-- 1. Fetching restaurant by restaurant id
SELECT r.id, r.country_code, r.currency_code, r.email, r.name, r.phone_number, r.website
FROM restaurant r
WHERE r.id = ?

-- 2. Fetching all the menus by restaurant id
SELECT m.id, m.end_at, m.name, m.restaurant_id, m.start_at, m.time_based
FROM menu m
WHERE m.restaurant_id = ?

-- 3. Fetching associated categories for menus fetched from #2 query
SELECT mc.menu_id, c.id, c.name, c.restaurant_id
FROM menu_category mc
         JOIN category c
              ON c.id = mc.category_id
WHERE mc.menu_id IN (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

-- 4. Fetching categories by the restaurant id
SELECT c.id, c.name, c.restaurant_id
FROM category c
WHERE c.restaurant_id = ?

-- 5. Fetching associated items for categories retrieved from the #4 query 
SELECT ci.category_id, i.id, i.description, i.name, i.restaurant_id
FROM category_item ci
         JOIN item i
              ON i.id = ci.item_id
WHERE ci.category_id in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

-- 6. Fetching items by restaurant id
SELECT i.id, i.description, i.name, i.restaurant_id
FROM item i
WHERE i.restaurant_id = ?

-- 7. Fetching associated modifier groups for the items retrieved from the #6 query
SELECT img.item_id, mg.id, mg.max_allowed, mg.min_required, mg.name, mg.restaurant_id
FROM item_modifier_group img
         JOIN modifier_group mg
              ON mg.id = img.modifier_group_id
WHERE img.item_id in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

-- 8. Fetching associated variants for the items retrieved from  the #6 query.
SELECT iv.item_id, iv.id, iv.name, iv.price, iv.type
FROM item_variant iv
WHERE iv.item_id in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

-- 9. Fetching  modifier groups by restaurant id
SELECT mg.id, mg.max_allowed, mg.min_required, mg.name, mg.restaurant_id
FROM modifier_group mg
WHERE mg.restaurant_id = ?

-- 10. Fetching associated modifier options for the modifier groups retrieved from the #9 query.
SELECT mgmo.modifier_group_id, mo.id, mo.name, mo.price, mo.restaurant_id
FROM modifier_group_modifier_option mgmo
         JOIN modifier_option mo
              ON mo.id = mgmo.modifier_option_id
WHERE mgmo.modifier_group_id IN (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

-- 11. Fetching modifier options by restaurant id
SELECT mo.id, mo.name, mo.price, mo.restaurant_id
FROM modifier_option mo
WHERE mo.restaurant_id = ?