USE cashReg1;

DROP TABLE IF EXISTS `deleted_check`;
DROP TABLE IF EXISTS `mycheck_and_prods`;
DROP TABLE IF EXISTS `mycheck`;
DROP TABLE IF EXISTS `cancel_checks`;
DROP TABLE IF EXISTS `checkofprod`;
DROP TABLE IF EXISTS `_check`;
DROP TABLE IF EXISTS `prod_of_check`;
DROP TABLE IF EXISTS `prod_in_store`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `products`;



CREATE TABLE roles
(
    RoleId INT PRIMARY KEY
);


CREATE TABLE users
(
    Id         INT PRIMARY KEY AUTO_INCREMENT,
    Name       VARCHAR(45) NOT NULL,
    Email      VARCHAR(45) NOT NULL UNIQUE,
    Password   VARCHAR(45) NOT NULL,
    UserRoleId INT         NOT NULL DEFAULT 1,
    FOREIGN KEY (UserRoleId) REFERENCES roles (RoleId)
);



CREATE TABLE products
(
    Id         INT PRIMARY KEY AUTO_INCREMENT,
    NameOfProd VARCHAR(45) NOT NULL UNIQUE,
    Price      DOUBLE      NOT NULL
);


CREATE TABLE prod_in_store
(
    Id         INT PRIMARY KEY AUTO_INCREMENT,
    ProdId     INT NOT NULL,
    Weight     DOUBLE DEFAULT 1.0,
    TotalPrice DOUBLE DEFAULT 1.0,
    FOREIGN KEY (ProdId) REFERENCES products (Id)
);

CREATE TABLE deleted_check
(
    CheckId    INT,
    NameOfProd VARCHAR(45),
    Weight     DOUBLE,
    Price      DOUBLE DEFAULT 0
);


CREATE TABLE mycheck
(
    Id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE cancel_checks
(
    Id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE mycheck_and_prods
(
    CheckId   INT,
    ProductId INT,
    Weight    DOUBLE,
    Price     DOUBLE DEFAULT 0,
#     FOREIGN KEY (ProductId) REFERENCES prod_in_store (Id),
    FOREIGN KEY (CheckId) REFERENCES mycheck (Id)
);