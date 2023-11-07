DROP DATABASE IF EXISTS `Ceylon Potters' Palette`;
CREATE DATABASE IF NOT EXISTS `Ceylon Potters' Palette`;

USE `Ceylon Potters' Palette`;

CREATE TABLE employee(
                         employee_Id VARCHAR(6) PRIMARY KEY,
                         first_Name VARCHAR(20) NOT NULL,
                         last_Name VARCHAR(20) NOT NULL,
                         nic VARCHAR(13) UNIQUE NOT NULL,
                         house_No VARCHAR(30),
                         street VARCHAR(30) NOT NULL,
                         city VARCHAR(30) NOT NULL,
                         contact_No VARCHAR(14) UNIQUE NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         role VARCHAR(15) NOT NULL
);

CREATE TABLE user(
                     user_Name VARCHAR(20) PRIMARY KEY,
                     password VARCHAR(30) NOT NULL,
                     employee_Id VARCHAR(6) NOT NULL,
                     CONSTRAINT FOREIGN KEY (employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE customer(
                         customer_Id VARCHAR(10) PRIMARY KEY,
                         name VARCHAR(20) NOT NULL,
                         contact_No VARCHAR(14) UNIQUE NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         date DATE NOT NULL,
                         time TIME NOT NULL,
                         user_Name VARCHAR(20),
                         CONSTRAINT FOREIGN KEY (user_Name) REFERENCES user(user_Name) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE customer_Order(
                               customer_Order_Id VARCHAR(20) PRIMARY KEY,
                               customer_Id VARCHAR(10) NOT NULL,
                               product_Id VARCHAR(50) NOT NULL,
                               product_Qty INT NOT NULL,
                               total_Price DECIMAL NOT NULL,
                               date DATE NOT NULL,
                               time TIME NOT NULL,
                               CONSTRAINT FOREIGN KEY(customer_Id) REFERENCES customer(customer_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE product_Stock(
                              product_Id VARCHAR(10) PRIMARY KEY,
                              description VARCHAR(20) NOT NULL,
                              qty_On_Hand INT NOT NULL,
                              unit_Price DECIMAL NOT NULL,
                              category VARCHAR(12) NOT NULL,
                              qty INT NOT NULL
);

CREATE TABLE customer_Order_Detail(
                                      customer_Order_Id VARCHAR(20) NOT NULL,
                                      product_Id VARCHAR(10) NOT NULL,
                                      CONSTRAINT FOREIGN KEY(customer_Order_Id) REFERENCES customer_Order(customer_Order_Id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT FOREIGN KEY(product_Id) REFERENCES product_Stock(product_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE repair_Stock(
                             product_Id VARCHAR(10) NOT NULL,
                             qty_To_Repair INT NOT NULL,
                             category VARCHAR(12) NOT NULL,
                             CONSTRAINT FOREIGN KEY(product_Id) REFERENCES product_Stock(product_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE supplier(
                         supplier_Id VARCHAR(6) PRIMARY KEY,
                         name VARCHAR(30) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         contact_No VARCHAR(14) UNIQUE NOT NULL,
                         time TIME NOT NULL,
                         date DATE NOT NULL,
                         user_Name VARCHAR(20),
                         CONSTRAINT FOREIGN KEY (user_Name) REFERENCES user(user_Name) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE supplier_Order(
                               supplier_Order_Id VARCHAR(20) PRIMARY KEY,
                               supplier_Id VARCHAR(6) NOT NULL,
                               item_Id VARCHAR(6) NOT NULL,
                               item_Qty INT NOT NULL,
                               unit_Price DECIMAL NOT NULL,
                               total_Price DECIMAL NOT NULL,
                               date DATE NOT NULL,
                               time TIME NOT NULL,
                               CONSTRAINT FOREIGN KEY(supplier_Id) REFERENCES supplier(supplier_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE item_Stock(
                           item_Id VARCHAR(6) PRIMARY KEY,
                           description VARCHAR(20) NOT NULL,
                           unit_Price DECIMAL NOT NULL,
                           qty_On_Hand INT NOT NULL
);

CREATE TABLE supplier_Order_Detail(
                                      supplier_Order_Id VARCHAR(20) NOT NULL,
                                      item_Id VARCHAR(6) NOT NULL,
                                      CONSTRAINT FOREIGN KEY(supplier_Order_Id) REFERENCES supplier_Order(supplier_Order_Id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT FOREIGN KEY(item_Id) REFERENCES item_Stock(item_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE salary(
                       employee_Id VARCHAR(6) NOT NULL,
                       worked_Day_Count INT NOT NULL,
                       total_Payment DECIMAL NOT NULL,
                       bonus DECIMAL NOT NULL,
                       date DATE NOT NULL,
                       time TIME NOT NULL,
                       CONSTRAINT FOREIGN KEY(employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE attendance(
                           employee_Id VARCHAR(20) NOT NULL,
                           date DATE NOT NULL,
                           time TIME NOT NULL,
                           CONSTRAINT FOREIGN KEY(employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO employee VALUES ('E001','Nisal','Gamage','200209801818','30/7','Matara Road','Matara','070 241 9999','nisalgamage@gmail.com','Manager');

INSERT INTO user VALUES ('grb','1234','E001');