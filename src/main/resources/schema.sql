-- 1. CREATE DATABASE
CREATE DATABASE IF NOT EXISTS db_coffee;
USE db_coffee;
-- 2. CREATE TABLE
CREATE TABLE tbl_category (
    categoryId     	INT             AUTO_INCREMENT,
    name            VARCHAR(100)    NOT NULL,
    CONSTRAINT PK_tbl_category PRIMARY KEY (categoryId)
);

CREATE TABLE tbl_product (
    productId		INT 		    AUTO_INCREMENT,
    categoryId     	INT             NOT NULL,
    userId        	INT             NOT NULL,
    name 			VARCHAR(100) 	NOT NULL,
    description     VARCHAR(255)	NOT NULL,
    discount		DECIMAL(10, 2)	NULL,
    image			VARCHAR(50)		NOT NULL,
    quantity 		INT 			NOT NULL,
    sellingPrice	DECIMAL(10, 2)  NOT NULL,
    CONSTRAINT PK_tbl_product PRIMARY KEY (productId)
);

CREATE TABLE tbl_order (
    orderId        	INT             AUTO_INCREMENT,
    payment_id      INT             NOT NULL,
    userId     		INT             NOT NULL,
    orderDate      	DATE            NOT NULL,
    deliveryDate   	DATE            NOT NULL,
    status          BOOLEAN         NOT NULL ,
    note            VARCHAR(255)    NOT NULL,
    address         VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_tbl_order PRIMARY KEY (orderId)
);
CREATE TABLE tbl_payment (
    payment_id      INT             AUTO_INCREMENT,
    name            VARCHAR(255)    NOT NULL,
    CONSTRAINT PK_tbl_payment PRIMARY KEY (payment_id)
);

CREATE TABLE tbl_order_detail (
    productId      	INT             NOT NULL,
    orderId        	INT             NOT NULL,
    discount        DECIMAL(10, 2)  NOT NULL,
    quantity        INT             NOT NULL,
    totalBill      	DECIMAL(10, 2)  NOT NULL,
    CONSTRAINT PK_tbl_order_detail PRIMARY KEY (productId, orderId)
);

CREATE TABLE tbl_cart (
    cartId         	INT             AUTO_INCREMENT,
    userId         	INT             NOT NULL,
    productId      	INT             NOT NULL,
    cartQuantity   	INT             NOT NULL,
    totalBill	    DECIMAL(10, 2)  NOT NULL,
    CONSTRAINT PK_tbl_cart primary key (cartId)
);

CREATE TABLE tbl_admin (
    userId          INT            NOT NULL,
    fullName        VARCHAR(100)   NULL,
    CONSTRAINT PK_tbl_admin PRIMARY KEY (userId)
);

CREATE TABLE tbl_role (
    roleId         INT             AUTO_INCREMENT,
    name            VARCHAR(50)     NOT NULL,
    CONSTRAINT PK_tbl_role PRIMARY KEY (roleId)
);

CREATE TABLE tbl_customer (
    userId         INT              NOT NULL,
    fullName       VARCHAR(100)     NULL,
    CONSTRAINT PK_tbl_customer PRIMARY KEY (userId)
);

CREATE TABLE tbl_article (
    articleId     	INT                 AUTO_INCREMENT,
    userId        	INT                 NOT NULL,
    name           	VARCHAR(100)        NOT NULL,
    description    	VARCHAR(255)        NOT NULL,
    CONSTRAINT PK_tbl_article  PRIMARY KEY (articleId)
);

CREATE TABLE tbl_user (
    userId       INT               AUTO_INCREMENT,
    roleId       INT               NOT NULL,
    username     VARCHAR(50)       NOT NULL UNIQUE,
    password     VARCHAR(255)      NOT NULL,
    CONSTRAINT PK_tbl_user PRIMARY KEY (userId)
);
-- 3. RELATIONSHIP
---- FOREIGN KEY OF THE 'tbl_product'.
------ The foreign key of the 'tbl_product' references the 'categoryId' column of the 'tbl_category'.
ALTER TABLE tbl_product
    ADD CONSTRAINT FK_tbl_product_tbl_category FOREIGN KEY (categoryId) REFERENCES tbl_category (categoryId);
------ The foreign key of the 'tbl_product' references the 'userId' column of the 'tbl_admin'.
ALTER TABLE tbl_product
    ADD CONSTRAINT FK_tbl_product_tbl_admin FOREIGN KEY (userId) REFERENCES tbl_admin (userId);

---- FOREIGN KEY OF THE 'tbl_order_detail'.
------ The foreign key of the 'tbl_order_detail' references the 'orderId' column of the 'tbl_order'.
ALTER TABLE tbl_order_detail
    ADD CONSTRAINT FK_tbl_order_detail_tbl_oder FOREIGN KEY (orderId) REFERENCES tbl_order (orderId);
------ The foreign key of the 'tbl_order_detail' references the 'productId' column of the 'tbl_product'.
ALTER TABLE tbl_order_detail
    ADD CONSTRAINT FK_tbl_order_detail_tbl_product FOREIGN KEY (productId) REFERENCES tbl_product (productId);

---- FOREIGN KEY OF THE 'tbl_order'.
------ The foreign key of the 'tbl_order' references the 'payment_id' column of the 'tbl_payment'.
ALTER TABLE tbl_order
    ADD CONSTRAINT FK_tbl_order_tbl_payment FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);
------ The foreign key of the 'tbl_order' references the 'userId' column of the 'tbl_customer'.
ALTER TABLE tbl_order
    ADD CONSTRAINT FK_tbl_order_tbl_customer FOREIGN KEY (userId) REFERENCES tbl_customer (userId);
---- FOREIGN KEY OF THE 'tbl_cart'.
------ The foreign key of the 'tbl_cart' references the 'userId' column of the 'tbl_user'.
ALTER TABLE tbl_cart
    ADD CONSTRAINT FK_tbl_cart_tbl_user FOREIGN KEY (userId) REFERENCES tbl_user (userId);
------ The foreign key of the 'tbl_cart' references the 'productId' column of the 'tbl_product'.
ALTER TABLE tbl_cart
    ADD CONSTRAINT FK_tbl_cart_tbl_product FOREIGN KEY (productId) REFERENCES tbl_product (productId);
--
-- ---- FOREIGN KEY OF THE 'tbl_cart_item'.
-- ------ The foreign key of the 'tbl_cart_item' references the 'productId' column of the 'tbl_product'.
-- ALTER TABLE tbl_cart_item
--     ADD CONSTRAINT FK_tbl_cart_item_tbl_product FOREIGN KEY (productId) REFERENCES tbl_product (productId);
-- ------ The foreign key of the 'tbl_cart_item' references the 'cartId' column of the 'tbl_cart'.
-- ALTER TABLE tbl_cart_item
--     ADD CONSTRAINT FK_tbl_cart_item_tbl_cart FOREIGN KEY (cartId) REFERENCES tbl_cart (cartId);

---- FOREIGN KEY OF THE 'tbl_admin'.
------ The foreign key of the 'tbl_admin' references the 'userId' column of the 'tbl_user'.
ALTER TABLE tbl_admin
    ADD CONSTRAINT FK_tbl_admin_tbl_user FOREIGN KEY (userId) REFERENCES tbl_user (userId);

---- FOREIGN KEY OF THE 'tbl_article'.
------ The foreign key of the 'tbl_admin' references the 'userId' column of the 'tbl_admin'.
ALTER TABLE tbl_article
    ADD CONSTRAINT FK_tbl_article_tbl_admin FOREIGN KEY (userId) REFERENCES tbl_admin (userId);

---- FOREIGN KEY OF THE 'tbl_customer'.
------ The foreign key of the 'tbl_customer' references the 'userId' column of the 'tbl_user'.
ALTER TABLE tbl_customer
    ADD CONSTRAINT FK_tbl_customer_tbl_user FOREIGN KEY (userId) REFERENCES tbl_user (userId);

---- FOREIGN KEY OF THE 'tbl_user'.
------ The foreign key of the 'tbl_user' references the 'roleId' column of the 'tbl_role'.
ALTER TABLE tbl_user
    ADD CONSTRAINT FK_tbl_user_tbl_role FOREIGN KEY (roleId) REFERENCES tbl_role(roleId);
INSERT INTO tbl_role (roleId, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');