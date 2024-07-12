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
    product_id		INT 		    AUTO_INCREMENT,
    category_id     INT             NOT NULL,
    user_id        	INT             NOT NULL,
    name 			VARCHAR(100) 	NOT NULL,
    description     VARCHAR(255)	NOT NULL,
    discount		DECIMAL(10, 2)	NULL,
    image			VARCHAR(50)		NOT NULL,
    quantity 		INT 			NOT NULL,
    selling_price	DECIMAL(10, 2)  NOT NULL,
    CONSTRAINT PK_tbl_product PRIMARY KEY (product_id)
);

CREATE TABLE tbl_order (
    order_id        INT             AUTO_INCREMENT,
    payment_id      INT             NOT NULL,
    user_id     	INT             NOT NULL,
    order_date      DATE            NOT NULL,
    delivery_date   DATE            NOT NULL,
    status          BOOLEAN         NOT NULL ,
    note            VARCHAR(255)    NOT NULL,
    address         VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_tbl_order PRIMARY KEY (order_id)
);
CREATE TABLE tbl_payment (
    payment_id      INT             AUTO_INCREMENT,
    name            VARCHAR(255)    NOT NULL,
    status          BOOLEAN         NOT NULL,
    CONSTRAINT PK_tbl_payment PRIMARY KEY (payment_id)
);

CREATE TABLE tbl_order_detail (
    product_id      INT             NOT NULL,
    order_id        INT             NOT NULL,
    discount        DECIMAL(5, 2)   NOT NULL,
    quantity        INT             NOT NULL,
    total_bill      DECIMAL(10, 2)  NOT NULL,
    CONSTRAINT PK_tbl_order_detail PRIMARY KEY (product_id, order_id)
);

CREATE TABLE tbl_cart (
    cart_id     INT     NOT NULL,
    user_id     INT     NOT NULL,
    CONSTRAINT PK_tbl_cart primary key (cart_id)
);

CREATE TABLE tbl_cart_item (
    product_id  INT     NOT NULL,
    cart_id     INT     NOT NULL,
    quantity    INT     NOT NULL,
    CONSTRAINT PK_cart_item primary key (product_id, cart_id)
);

CREATE TABLE tbl_admin (
    user_id          INT            NOT NULL,
    full_name        VARCHAR(100)   NULL,
    CONSTRAINT PK_tbl_admin PRIMARY KEY (user_id)
);

CREATE TABLE tbl_role (
    role_id         INT             AUTO_INCREMENT,
    name            VARCHAR(50)     NOT NULL,
    CONSTRAINT PK_tbl_role PRIMARY KEY (role_id)
);

CREATE TABLE tbl_customer (
    user_id         INT              NOT NULL,
    full_name       VARCHAR(100)     NULL,
    CONSTRAINT PK_tbl_customer PRIMARY KEY (user_id)
);

CREATE TABLE tbl_article (
    article_id     INT                 AUTO_INCREMENT,
    user_id        INT                 NOT NULL,
    name           VARCHAR(100)        NOT NULL,
    description    VARCHAR(255)        NOT NULL,
    CONSTRAINT PK_tbl_article  PRIMARY KEY (article_id)
);

CREATE TABLE tbl_user (
    user_id       INT               AUTO_INCREMENT,
    role_id       INT               NOT NULL,
    username      VARCHAR(50)       NOT NULL UNIQUE,
    password      VARCHAR(255)      NOT NULL,
    CONSTRAINT PK_tbl_user PRIMARY KEY (user_id)
);
-- 3. RELATIONSHIP
---- FOREIGN KEY OF THE 'tbl_product'.
------ The foreign key of the 'tbl_product' references the 'category_id' column of the 'tbl_category'.
ALTER TABLE tbl_product
    ADD CONSTRAINT FK_tbl_product_tbl_category FOREIGN KEY (category_id) REFERENCES tbl_category (category_id);
------ The foreign key of the 'tbl_product' references the 'user_id' column of the 'tbl_admin'.
ALTER TABLE tbl_product
    ADD CONSTRAINT FK_tbl_product_tbl_admin FOREIGN KEY (user_id) REFERENCES tbl_admin (user_id);

---- FOREIGN KEY OF THE 'tbl_order_detail'.
------ The foreign key of the 'tbl_order_detail' references the 'order_id' column of the 'tbl_order'.
ALTER TABLE tbl_order_detail
    ADD CONSTRAINT FK_tbl_order_detail_tbl_oder FOREIGN KEY (order_id) REFERENCES tbl_order (order_id);
------ The foreign key of the 'tbl_order_detail' references the 'product_id' column of the 'tbl_product'.
ALTER TABLE tbl_order_detail
    ADD CONSTRAINT FK_tbl_order_detail_tbl_product FOREIGN KEY (product_id) REFERENCES tbl_product (product_id);

---- FOREIGN KEY OF THE 'tbl_order'.
------ The foreign key of the 'tbl_order' references the 'payment_id' column of the 'tbl_payment'.
ALTER TABLE tbl_order
    ADD CONSTRAINT FK_tbl_order_tbl_payment FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);
------ The foreign key of the 'tbl_order' references the 'user_id' column of the 'tbl_customer'.
ALTER TABLE tbl_order
    ADD CONSTRAINT FK_tbl_order_tbl_customer FOREIGN KEY (user_id) REFERENCES tbl_customer (user_id);

---- FOREIGN KEY OF THE 'tbl_cart_item'.
------ The foreign key of the 'tbl_cart_item' references the 'product_id' column of the 'tbl_product'.
ALTER TABLE tbl_cart_item
    ADD CONSTRAINT FK_tbl_cart_item_tbl_product FOREIGN KEY (product_id) REFERENCES tbl_product (product_id);
------ The foreign key of the 'tbl_cart_item' references the 'cart_id' column of the 'tbl_cart'.
ALTER TABLE tbl_cart_item
    ADD CONSTRAINT FK_tbl_cart_item_tbl_cart FOREIGN KEY (cart_id) REFERENCES tbl_cart (cart_id);

---- FOREIGN KEY OF THE 'tbl_cart'.
------ The foreign key of the 'tbl_cart' references the 'user_id' column of the 'tbl_customer'.
ALTER TABLE tbl_cart
    ADD CONSTRAINT FK_tbl_cart_tbl_customer FOREIGN KEY (user_id) REFERENCES tbl_customer (user_id);

---- FOREIGN KEY OF THE 'tbl_admin'.
------ The foreign key of the 'tbl_admin' references the 'user_id' column of the 'tbl_user'.
ALTER TABLE tbl_admin
    ADD CONSTRAINT FK_tbl_admin_tbl_user FOREIGN KEY (user_id) REFERENCES tbl_user (user_id);

---- FOREIGN KEY OF THE 'tbl_article'.
------ The foreign key of the 'tbl_admin' references the 'user_id' column of the 'tbl_admin'.
ALTER TABLE tbl_article
    ADD CONSTRAINT FK_tbl_article_tbl_admin FOREIGN KEY (user_id) REFERENCES tbl_admin (user_id);

---- FOREIGN KEY OF THE 'tbl_customer'.
------ The foreign key of the 'tbl_customer' references the 'user_id' column of the 'tbl_user'.
ALTER TABLE tbl_customer
    ADD CONSTRAINT FK_tbl_customer_tbl_user FOREIGN KEY (user_id) REFERENCES tbl_user (user_id);

---- FOREIGN KEY OF THE 'tbl_user'.
------ The foreign key of the 'tbl_user' references the 'role_id' column of the 'tbl_role'.
ALTER TABLE tbl_user
    ADD CONSTRAINT FK_tbl_user_tbl_role FOREIGN KEY (role_id) REFERENCES tbl_role(role_id);
