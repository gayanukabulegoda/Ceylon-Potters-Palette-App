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
                         role VARCHAR(30) NOT NULL,
                         date DATE NOT NULL,
                         time TIME NOT NULL,
                         user_Name VARCHAR(20) NOT NULL
);


INSERT INTO employee (employee_Id, first_Name, last_Name, nic, house_No, street, city, contact_No, email, role, date, time, user_Name)
VALUES ('E-001', 'John', 'Doe', '1234567890123', '123', 'Main Street', 'Cityville', '+1234567890', 'john.doe@example.com', 'Manager', '2023-11-12', '10:15:00', 'grb');


INSERT INTO employee (employee_Id, first_Name, last_Name, nic, house_No, street, city, contact_No, email, role, date, time, user_Name)
VALUES ('E-002', 'Jane', 'Smith', '2345678901234', '456', 'Broadway', 'Towntown', '+9876543210', 'jane.smith@example.com', 'Clerk', '2023-11-12', '10:15:00', 'rgb');


INSERT INTO employee (employee_Id, first_Name, last_Name, nic, house_No, street, city, contact_No, email, role, date, time, user_Name)
VALUES ('E-003', 'Alice', 'Johnson', '3456789012345', '789', 'Park Avenue', 'Villageton', '+1122334455', 'alice.johnson@example.com', 'Salesperson', '2023-11-12', '10:15:00', 'rgb');


INSERT INTO employee (employee_Id, first_Name, last_Name, nic, house_No, street, city, contact_No, email, role, date, time, user_Name)
VALUES ('E-004', 'Bob', 'Williams', '4567890123456', '1011', 'Sunset Boulevard', 'Cityscape', '+9988776655', 'bob.williams@example.com', 'Technician', '2023-11-12', '10:15:00', 'rgb');


INSERT INTO employee (employee_Id, first_Name, last_Name, nic, house_No, street, city, contact_No, email, role, date, time, user_Name)
VALUES ('E-005', 'Eva', 'Miller', '5678901234567', '1314', 'Ocean Drive', 'Seaville', '+1125334455', 'eva.miller@example.com', 'Intern', '2023-11-12', '10:15:00', 'rgb');


CREATE TABLE user(
                     user_Name VARCHAR(20) PRIMARY KEY,
                     password VARCHAR(30) NOT NULL,
                     employee_Id VARCHAR(6) NOT NULL,
                     CONSTRAINT FOREIGN KEY (employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO user (user_Name, password, employee_Id)
VALUES ('grb', '1234', 'E-001');


INSERT INTO user (user_Name, password, employee_Id)
VALUES ('rgb', '1234', 'E-002');


ALTER TABLE employee ADD CONSTRAINT user_Name FOREIGN KEY(user_Name) REFERENCES user(user_Name) ON DELETE CASCADE ON UPDATE CASCADE;

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

INSERT INTO customer (customer_Id, name, contact_No, email, date, time, user_Name)
VALUES ('C-001', 'John Doe', '+1234567890', 'john.doe@example.com', '2023-11-10', '12:30:00', 'grb');


INSERT INTO customer (customer_Id, name, contact_No, email, date, time, user_Name)
VALUES ('C-002', 'Jane Smith', '+9876543210', 'jane.smith@example.com', '2023-11-11', '15:45:00', 'grb');


INSERT INTO customer (customer_Id, name, contact_No, email, date, time, user_Name)
VALUES ('C-003', 'Alice Johnson', '+1122334455', 'alice.johnson@example.com', '2023-11-12', '10:15:00', 'rgb');


INSERT INTO customer (customer_Id, name, contact_No, email, date, time, user_Name)
VALUES ('C-004', 'Bob Williams', '+9988776655', 'bob.williams@example.com', '2023-11-13', '14:00:00', 'rgb');


INSERT INTO customer (customer_Id, name, contact_No, email, date, time, user_Name)
VALUES ('C-005', 'Eva Davis', '+7766554433', 'eva.davis@example.com', '2023-11-14', '17:30:00', 'grb');


CREATE TABLE customer_Order(
                               customer_Order_Id VARCHAR(20) PRIMARY KEY,
                               customer_Id VARCHAR(10) NOT NULL,
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
                                      product_Quantity INT NOT NULL,
                                      CONSTRAINT FOREIGN KEY(customer_Order_Id) REFERENCES customer_Order(customer_Order_Id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT FOREIGN KEY(product_Id) REFERENCES product_Stock(product_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE repair_Stock(
                             product_Id VARCHAR(10) NOT NULL,
                             qty_To_Repair INT NOT NULL,
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

INSERT INTO supplier (supplier_Id, name, email, contact_No, time, date, user_Name)
VALUES ('S-001', 'ABC Suppliers', 'abc.suppliers@example.com', '+1234567890', '08:00:00', '2023-11-10', 'rgb');


INSERT INTO supplier (supplier_Id, name, email, contact_No, time, date, user_Name)
VALUES ('S-002', 'XYZ Distributors', 'xyz.distributors@example.com', '+9876543210', '10:30:00', '2023-11-11', 'grb');


INSERT INTO supplier (supplier_Id, name, email, contact_No, time, date, user_Name)
VALUES ('S-003', 'LMN Traders', 'lmn.traders@example.com', '+1122334455', '13:15:00', '2023-11-12', 'rgb');


INSERT INTO supplier (supplier_Id, name, email, contact_No, time, date, user_Name)
VALUES ('S-004', 'PQR Importers', 'pqr.importers@example.com', '+9988776655', '15:45:00', '2023-11-13', 'grb');


INSERT INTO supplier (supplier_Id, name, email, contact_No, time, date, user_Name)
VALUES ('S-005', 'MNO Wholesalers', 'mno.wholesalers@example.com', '+7766554433', '18:20:00', '2023-11-14', 'rgb');


CREATE TABLE supplier_Order(
                               supplier_Order_Id VARCHAR(20) PRIMARY KEY,
                               supplier_Id VARCHAR(6) NOT NULL,
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
                                      item_Qty INT NOT NULL,
                                      CONSTRAINT FOREIGN KEY(supplier_Order_Id) REFERENCES supplier_Order(supplier_Order_Id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT FOREIGN KEY(item_Id) REFERENCES item_Stock(item_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE salary(
                       salary_Id VARCHAR(6) PRIMARY KEY,
                       employee_Id VARCHAR(6) NOT NULL,
                       worked_Day_Count INT NOT NULL,
                       salary DECIMAL NOT NULL,
                       bonus DECIMAL NOT NULL,
                       total_Payment DECIMAL NOT NULL,
                       date DATE NOT NULL,
                       time TIME NOT NULL,
                       CONSTRAINT FOREIGN KEY(employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE attendance(
                           attendance_Id VARCHAR(6) PRIMARY KEY,
                           employee_Id VARCHAR(20) NOT NULL,
                           date DATE NOT NULL,
                           time TIME NOT NULL,
                           CONSTRAINT FOREIGN KEY(employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO item_Stock (item_Id, description, unit_Price, qty_On_Hand)
VALUES ('I-001', 'Clay', 400.00, 100);


INSERT INTO item_Stock (item_Id, description, unit_Price, qty_On_Hand)
VALUES ('I-002', 'Paints', 500.00, 75);


INSERT INTO item_Stock (item_Id, description, unit_Price, qty_On_Hand)
VALUES ('I-003', 'Oils', 200.00, 120);


INSERT INTO item_Stock (item_Id, description, unit_Price, qty_On_Hand)
VALUES ('I-004', 'Gasoline', 120.00, 90);


INSERT INTO item_Stock (item_Id, description, unit_Price, qty_On_Hand)
VALUES ('I-005', 'Water', 80.00, 60);

SELECT COUNT(*) AS work_days FROM attendance WHERE employee_Id = 'E-005' AND MONTH(date) = MONTH(CURDATE());