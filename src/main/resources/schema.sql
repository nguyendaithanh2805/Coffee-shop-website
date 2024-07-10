-- 1. CREATE DATABASE
DROP DATABASE IF EXISTS db_coffee;
CREATE DATABASE db_coffee;
USE db_coffee;
-- 2. CREATE TABLE
CREATE TABLE tbl_category (
    category_id     INT             AUTO_INCREMENT,
    name            VARCHAR(100)    NOT NULL,
    CONSTRAINT PK_tbl_category PRIMARY KEY (category_id)
);

CREATE TABLE tbl_product (
    product_id			INT 			AUTO_INCREMENT,
    name 			VARCHAR(100) 	NOT NULL,
    description     VARCHAR(255)	NOT NULL,
    discount		DECIMAL(10, 2)	NULL,
    image			VARCHAR(50)		NOT NULL,
    quantity 		INT 			NOT NULL,
    selling_price	DECIMAL(10, 2)  NOT NULL,
    CONSTRAINT PK_tbl_product PRIMARY KEY (product_id)
);

CREATE TABLE tbl_order (
    order_id         INT            AUTO_INCREMENT,
    order_date      DATE            NOT NULL,
    shipping_date   DATE            NOT NULL,
    status          VARCHAR(255)    NOT NULL ,
    note            VARCHAR(255)   NOT NULL,
    address         VARCHAR(255)   NOT NULL,
    CONSTRAINT PK_tbl_order PRIMARY KEY (order_id)
);
CREATE TABLE tbl_payment (
    payment_id      INT             AUTO_INCREMENT,
    payment_name    VARCHAR(255)    NOT NULL,
    status          BOOLEAN         NOT NULL,
    CONSTRAINT PK_tbl_payment PRIMARY KEY (payment_id)
);

CREATE TABLE tbl_order_detail (
    product_id      INT             NOT NULL,
    order_id        INT             NOT NULL,
    discount        DECIMAL(5, 2)   NOT NULL,
    quantity        INT             NOT NULL,
    selling_price   DECIMAL(10, 2)  NOT NULL,
    CONSTRAINT PK_tbl_order_detail PRIMARY KEY (product_id, order_id)
);

CREATE TABLE tbl_cart (
    cart_id     INT     NOT NULL,
    CONSTRAINT PK_tbl_cart primary key (cart_id)
);

CREATE TABLE tbl_cart_item (
    product_id  INT     NOT NULL,
    cart_id     INT     NOT NULL,
    quantity    INT     NOT NULL,
    CONSTRAINT PK_cart_item primary key (product_id, cart_id)
);

CREATE TABLE tbl_admin (
    admin_id         INT            AUTO_INCREMENT,
    username        VARCHAR(255)    NOT NULL UNIQUE,
    password        VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_tbl_admin PRIMARY KEY (admin_id)
);

CREATE TABLE tbl_role (
    role_id         INT             AUTO_INCREMENT,
    name            VARCHAR(50)     NOT NULL,
    CONSTRAINT PK_tbl_role PRIMARY KEY (role_id)
);

 CREATE TABLE tbl_customer (
     customer_id    INT             AUTO_INCREMENT,
     username       VARCHAR(20)     NOT NULL UNIQUE,
     password       VARCHAR(255)    NOT NULL,
     email          VARCHAR(255)    NOT NULL,
     CONSTRAINT PK_tbl_customer PRIMARY KEY (customer_id)
 );

 CREATE TABLE tbl_article (
     article_id     INT         AUTO_INCREMENT,
     name           VARCHAR(100)        NOT NULL,
     description    VARCHAR(255)        NOT NULL,
     CONSTRAINT PK_tbl_article  PRIMARY KEY (article_id)
 );
-- 3. RELATIONSHIP
