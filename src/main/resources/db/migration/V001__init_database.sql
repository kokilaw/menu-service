CREATE TABLE restaurant
(
    internal_id   bigint             NOT NULL AUTO_INCREMENT,
    id            varchar(36) UNIQUE NOT NULL,
    name          varchar(50)        NOT NULL,
    website       varchar(50),
    email         varchar(50) UNIQUE NOT NULL,
    phone_number  varchar(13) UNIQUE NOT NULL,
    country_code  varchar(2)         NOT NULL,
    currency_code varchar(3)         NOT NULL,
    PRIMARY KEY (internal_id)
);

CREATE TABLE category
(
    internal_id   bigint             NOT NULL AUTO_INCREMENT,
    id            varchar(36) UNIQUE NOT NULL,
    name          varchar(50)        NOT NULL,
    restaurant_id bigint             NOT NULL,
    PRIMARY KEY (internal_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (internal_id)
);

CREATE TABLE item
(
    internal_id   bigint             NOT NULL AUTO_INCREMENT,
    id            varchar(36) UNIQUE NOT NULL,
    name          varchar(50)        NOT NULL,
    description   varchar(255),
    type          varchar(20)        NOT NULL,
    restaurant_id bigint             NOT NULL,
    PRIMARY KEY (internal_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (internal_id)
);

CREATE TABLE category_item
(
    internal_id bigint NOT NULL AUTO_INCREMENT,
    category_id bigint NOT NULL,
    item_id     bigint NOT NULL,
    PRIMARY KEY (internal_id),
    FOREIGN KEY (category_id) REFERENCES category (internal_id),
    FOREIGN KEY (item_id) REFERENCES item (internal_id)
);

CREATE TABLE item_variant
(
    internal_id bigint             NOT NULL AUTO_INCREMENT,
    id          varchar(36) UNIQUE NOT NULL,
    name        varchar(50)        NOT NULL,
    item_id     bigint             NOT NULL,
    type        varchar(10)        NOT NULL,
    price       decimal(10, 2)     NOT NULL,
    PRIMARY KEY (internal_id),
    FOREIGN KEY (item_id) REFERENCES item (internal_id)
);

CREATE TABLE modifier_group
(
    internal_id   bigint             NOT NULL AUTO_INCREMENT,
    id            varchar(36) UNIQUE NOT NULL,
    restaurant_id bigint             NOT NULL,
    name          varchar(50)        NOT NULL,
    min_required  int DEFAULT 0,
    max_allowed   int DEFAULT 0,
    PRIMARY KEY (internal_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (internal_id)
);

CREATE TABLE item_modifier_group
(
    internal_id       bigint NOT NULL AUTO_INCREMENT,
    item_id           bigint NOT NULL,
    modifier_group_id bigint NOT NULL,
    PRIMARY KEY (internal_id),
    FOREIGN KEY (item_id) REFERENCES item (internal_id),
    FOREIGN KEY (modifier_group_id) REFERENCES modifier_group (internal_id)
);