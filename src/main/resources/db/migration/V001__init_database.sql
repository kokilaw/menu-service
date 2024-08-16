CREATE TABLE restaurant
(
    id           bigint             NOT NULL AUTO_INCREMENT,
    public_id    varchar(36) UNIQUE NOT NULL,
    name         varchar(50)        NOT NULL,
    email        varchar(50) UNIQUE NOT NULL,
    phone_number varchar(13) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);