CREATE INDEX menu_restaurant_id_index ON menu (restaurant_id);
CREATE INDEX category_restaurant_id_index ON category (restaurant_id);
CREATE INDEX item_restaurant_id_index ON item (restaurant_id);
CREATE INDEX item_variant_item_id_index ON item_variant (item_id);
CREATE INDEX modifier_group_restaurant_id_index ON modifier_group (restaurant_id);
CREATE INDEX modifier_option_restaurant_id_index ON modifier_option (restaurant_id);
