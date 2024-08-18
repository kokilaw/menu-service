CREATE TABLE restaurant
(
    id            varchar(36)        NOT NULL,
    name          varchar(50)        NOT NULL,
    website       varchar(50),
    email         varchar(50) UNIQUE NOT NULL,
    phone_number  varchar(13) UNIQUE NOT NULL,
    country_code  varchar(2)         NOT NULL,
    currency_code varchar(3)         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id            varchar(36) NOT NULL,
    name          varchar(50) NOT NULL,
    restaurant_id varchar(36) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);

CREATE TABLE item
(
    id            varchar(36) NOT NULL,
    name          varchar(50) NOT NULL,
    description   varchar(255),
    restaurant_id varchar(36) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);

CREATE TABLE category_item
(
    category_id varchar(36) NOT NULL,
    item_id     varchar(36) NOT NULL,
    PRIMARY KEY (category_id, item_id),
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);

CREATE TABLE item_variant
(
    id      varchar(36)    NOT NULL,
    name    varchar(50)    NOT NULL,
    item_id varchar(36)    NOT NULL,
    type    varchar(10)    NOT NULL,
    price   decimal(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);

CREATE TABLE modifier_group
(
    id            varchar(36) NOT NULL,
    restaurant_id varchar(36) NOT NULL,
    name          varchar(50) NOT NULL,
    min_required  int DEFAULT 0,
    max_allowed   int DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);

CREATE TABLE item_modifier_group
(
    item_id           varchar(36) NOT NULL,
    modifier_group_id varchar(36) NOT NULL,
    PRIMARY KEY (item_id, modifier_group_id),
    FOREIGN KEY (item_id) REFERENCES item (id),
    FOREIGN KEY (modifier_group_id) REFERENCES modifier_group (id)
);

CREATE TABLE modifier_option
(
    id            varchar(36)    NOT NULL,
    name          varchar(50)    NOT NULL,
    restaurant_id varchar(36)    NOT NULL,
    price         decimal(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);

CREATE TABLE modifier_group_modifier_option
(
    modifier_group_id  varchar(36) NOT NULL,
    modifier_option_id varchar(36) NOT NULL,
    PRIMARY KEY (modifier_group_id, modifier_option_id),
    FOREIGN KEY (modifier_group_id) REFERENCES modifier_group (id),
    FOREIGN KEY (modifier_option_id) REFERENCES modifier_option (id)
);