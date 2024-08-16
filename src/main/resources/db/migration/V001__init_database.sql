CREATE TABLE restaurant
(
    id            bigint             NOT NULL AUTO_INCREMENT,
    public_id     varchar(36) UNIQUE NOT NULL,
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
    id            bigint             NOT NULL AUTO_INCREMENT,
    public_id     varchar(36) UNIQUE NOT NULL,
    name          varchar(50)        NOT NULL,
    restaurant_id bigint             NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);

CREATE TABLE item
(
    id            bigint             NOT NULL AUTO_INCREMENT,
    public_id     varchar(36) UNIQUE NOT NULL,
    name          varchar(50)        NOT NULL,
    description   varchar(255),
    restaurant_id bigint             NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);

CREATE TABLE category_item
(
    id          bigint NOT NULL AUTO_INCREMENT,
    category_id bigint NOT NULL,
    item_id     bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);

CREATE TABLE item_variant
(
    id        bigint             NOT NULL AUTO_INCREMENT,
    public_id varchar(36) UNIQUE NOT NULL,
    name      varchar(50)        NOT NULL,
    item_id   bigint             NOT NULL,
    type      varchar(10)        NOT NULL,
    price     decimal(10, 2)     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);