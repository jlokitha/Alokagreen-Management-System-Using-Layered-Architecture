DROP DATABASE IF EXISTS alokagreen;
CREATE DATABASE IF NOT EXISTS alokagreen;


USE alokagreen;


CREATE TABLE employee(
                         employee_Id VARCHAR(20) PRIMARY KEY,
                         first_Name VARCHAR(20) NOT NULL,
                         last_Name VARCHAR(20) NOT NULL,
                         nic VARCHAR(30) UNIQUE NOT NULL,
                         house_No VARCHAR(30),
                         street VARCHAR(30) NOT NULL,
                         city VARCHAR(30) NOT NULL,
                         mobile VARCHAR(30) UNIQUE NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         role VARCHAR(30) NOT NULL,
                         date DATE NOT NULL
);


CREATE TABLE user(
                     user_Name VARCHAR(20) PRIMARY KEY,
                     password VARCHAR(30) NOT NULL,
                     employee_Id VARCHAR(20) NOT NULL,
                     FOREIGN KEY(employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE customer(
                         customer_Id VARCHAR(20) PRIMARY KEY,
                         name VARCHAR(40) NOT NULL,
                         mobile VARCHAR(20) UNIQUE NOT NULL,
                         email VARCHAR(100),
                         address VARCHAR(100),
                         date DATE NOT NULL,
                         time TIME NOT NULL
);


CREATE TABLE supplier(
                         supplier_Id VARCHAR(20) PRIMARY KEY,
                         company_Name VARCHAR(40) NOT NULL,
                         company_Email VARCHAR(100) UNIQUE NOT NULL,
                         company_Mobile VARCHAR(20) UNIQUE NOT NULL,
                         company_Location VARCHAR(100) NOT NULL,
                         time TIME NOT NULL,
                         date DATE NOT NULL
);


CREATE TABLE product_List(
                             product_Code VARCHAR(20) PRIMARY KEY,
                             description VARCHAR(50) UNIQUE NOT NULL,
                             unit_Price DECIMAL NOT NULL
);


CREATE TABLE product_Stock(
                              stock_Id VARCHAR(20) PRIMARY KEY,
                              product_Code VARCHAR(20) NOT NULL,
                              qty_On_Hand INT NOT NULL,
                              qty INT NOT NULL,
                              date DATE NOT NULL,
                              exp_Date DATE NOT NULL,
                              status VARCHAR(20) NOT NULL,
                              FOREIGN KEY(product_Code) REFERENCES product_List(product_Code) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE material_List(
                              material_Code VARCHAR(20) PRIMARY KEY,
                              description VARCHAR(50) UNIQUE NOT NULL
);


CREATE TABLE material_Stock(
                               stock_Id VARCHAR(20) PRIMARY KEY,
                               material_Code VARCHAR(20) NOT NULL,
                               qty_On_Hand INT NOT NULL,
                               qty INT NOT NULL,
                               unit_Price DECIMAL NOT NULL,
                               date DATE NOT NULL,
                               exp_Date DATE NOT NULL,
                               status VARCHAR(20) NOT NULL,
                               FOREIGN KEY(material_Code) REFERENCES material_List(material_Code) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE salary(
                       salary_Id VARCHAR(20) PRIMARY KEY,
                       employee_Id VARCHAR(20) NOT NULL,
                       total_Salary DECIMAL NOT NULL,
                       work_Day_Count INT NOT NULL,
                       bonus DECIMAL,
                       date DATE NOT NULL,
                       time TIME NOT NULL,
                       FOREIGN KEY(employee_Id) REFERENCES employee (employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE attendance(
                           attendance_Id VARCHAR(20) PRIMARY KEY,
                           employee_Id VARCHAR(20) NOT NULL,
                           date DATE NOT NULL,
                           time TIME NOT NULL,
                           FOREIGN KEY(employee_Id) REFERENCES employee(employee_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE customer_Order(
                               customer_Order_Id VARCHAR(20) PRIMARY KEY,
                               customer_Id VARCHAR(20) NOT NULL,
                               total_Amount DECIMAL NOT NULL,
                               date DATE NOT NULL,
                               time TIME NOT NULL,
                               FOREIGN KEY(customer_Id) REFERENCES customer (customer_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE customer_Order_Detail(
                                      Customer_Order_Id VARCHAR(20) NOT NULL,
                                      stock_Id VARCHAR(20) NOT NULL,
                                      qty INT NOT NULL,
                                      FOREIGN KEY(customer_Order_Id) REFERENCES customer_Order (customer_Order_Id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      FOREIGN KEY(stock_Id) REFERENCES product_Stock (stock_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE spoiled_Product_Report(
                                       report_Id VARCHAR(20) PRIMARY KEY,
                                       product_Code VARCHAR(20) NOT NULL,
                                       spoiled_Qty INT NOT NULL,
                                       date DATE NOT NULL,
                                       time TIME NOT NULL,
                                       FOREIGN KEY(product_Code) REFERENCES product_List(product_Code) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE supplier_Order(
                               supplier_Order_Id VARCHAR(20) PRIMARY KEY,
                               supplier_Id VARCHAR(20) NOT NULL,
                               total_Amount DECIMAL NOT NULL,
                               date DATE NOT NULL,
                               time TIME NOT NULL,
                               FOREIGN KEY(supplier_Id) REFERENCES supplier (supplier_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE supplier_Order_Detail(
                                      supplier_Order_Id VARCHAR(20) NOT NULL,
                                      stock_Id VARCHAR(20) NOT NULL,
                                      FOREIGN KEY(supplier_Order_Id) REFERENCES supplier_Order (supplier_Order_Id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      FOREIGN KEY(stock_Id) REFERENCES material_Stock(stock_Id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO employee (employee_Id, first_Name, last_Name, nic, house_No, street, city, mobile, email, role, date)
VALUES
    ('E-01', 'Janindu', 'Lokitha', '200335211420', '123', 'Walagama road', 'Matara', '0711018202', 'ljanindulokiha@gmail.com', 'Manager', '2020-08-10'),
    ('E-02', 'Thimira', 'Nishanth', '200234123425', '456', 'Akuressa road', 'Colombo', '0765432109', 'lokithajanindu040130@mail.com', 'System Manager', '2021-12-06'),
    ('E-03', 'Yasiru', 'Adithya', '199384726340', '789', 'Hakmana road', 'Ahangama', '0754321098', 'solution990fficial@gmail.com', 'Field Staff', '2023-01-01'),
    ('E-04', 'Thushanga', 'Hansara', '288409123412', '23', 'Sun flower garden', 'Galle', '0743210987', 'hansara@gmail.com', 'Shop Staff', '2023-05-04'),
    ('E-05', 'Dumidu', 'Hansaka', '009412341245', '567', 'Main road', 'Matara', '0765432190', 'dumiduhansaka@gmail.com', 'Field Staff', '2022-02-04');


INSERT INTO user (user_Name, password, employee_Id)
VALUES('jl', '123456', 'E-01');



INSERT INTO customer (customer_Id, name, mobile, email, address, date, time)
VALUES
    ('C-01', 'Pubudu Hansaka', '0711018201', 'pubudu@gmail.com', '123 Main Street, Cityville', '2023-01-01', '12:30:00'),
    ('C-02', 'Sasmith Weerasekara', '0765432109', 'sasmith@gmail.com', '456 Side Street, Townsville', '2023-01-02', '14:45:00'),
    ('C-03', 'Sithum Jayasanka', '0654321098', 'sithum@gmail.com', '789 Back Street, Villageville', '2023-01-03', '16:20:00'),
    ('C-04', 'Sanath Manawadu', '0543210987', 'sanath@gmail.com', '101 Central Avenue, Hamletville', '2023-01-04', '18:00:00'),
    ('C-05', 'Yasith Ranathunga', '0765432190', 'ranathunga@gmail.com', '567 Top Street, Suburbville', '2023-01-05', '20:10:00');


INSERT INTO supplier (supplier_Id, company_Name, company_Email, company_Mobile, company_Location, time, date)
VALUES
    ('S-01', 'AgroSupplies Co', 'agrosupplies@gmail.com', '0776543210', '123 Industrial Park, City', '10:00:00', '2023-01-01'),
    ('S-02', 'HarvestProvisions Ltd', 'harvestprovisions@gmail.com', '0765432109', '456 Business Center, Town', '11:30:00', '2023-01-02'),
    ('S-03', 'FarmEssentials Ltd', 'farmessentials@gmail.com', '0754321098', '789 Warehouse Street, Village', '13:15:00', '2023-01-03'),
    ('S-04', 'AgriMaterials', 'agrimaterials@gmail.com', '0743210987', '202 Main Tech Plaza, City', '15:45:00', '2023-01-04'),
    ('S-05', 'FieldHarvest Solutions', 'fieldharvest@gmail.com', '0732109876', '555 Sunny Avenue, Suburb', '17:30:00', '2023-01-05');


INSERT INTO product_List (product_Code, description, unit_Price)
VALUES
    ('P-01', 'Okra', 120),
    ('P-02', 'Cucumber', 200),
    ('P-03', 'Potato', 150),
    ('P-04', 'Carrot', 200),
    ('P-05', 'Green Beans', 130);



INSERT INTO material_List (material_Code, description)
VALUES
    ('M-01', 'Pesticide'),
    ('M-02', 'Flower Fertilizer'),
    ('M-03', 'Seed Fertilizer'),
    ('M-04', 'Compost'),
    ('M-05', 'Seeds');

INSERT INTO product_Stock (stock_Id, product_Code, qty_On_Hand, qty, date, exp_Date, status)
VALUES
    ('PS-01', 'P-01', 275, 350, '2023-11-20', '2023-12-05', 'Not Expired'),
    ('PS-02', 'P-02', 150, 150, '2023-11-25', '2023-11-30', 'Expired'),
    ('PS-03', 'P-03', 390, 450, '2023-11-30', '2023-12-04', 'Not Expired'),
    ('PS-04', 'P-04', 100, 100, '2023-11-30', '2023-12-01', 'Expired'),
    ('PS-05', 'P-05', 410, 500, '2023-12-01', '2023-12-10', 'Not Expired'),
    ('PS-06', 'P-04', 300, 360, '2023-12-01', '2023-12-07', 'Not Expired'),
    ('PS-07', 'P-02', 180, 240, '2023-12-02', '2023-12-11', 'Not Expired');


INSERT INTO material_Stock (stock_Id, material_Code, qty_On_Hand, qty, unit_Price, date, exp_Date, status)
VALUES
    ('MS-01', 'M-01', 100, 100, 500, '2023-01-01', '2024-01-01', 'Not Expired'),
    ('MS-02', 'M-02', 200, 200, 650, '2023-01-02', '2023-01-01', 'Expired'),
    ('MS-03', 'M-03', 250, 250, 350, '2023-01-03', '2024-01-01', 'Not Expired'),
    ('MS-04', 'M-04', 200, 200, 260, '2023-01-04', '2023-01-01', 'Expired'),
    ('MS-05', 'M-05', 500, 500, 340, '2023-01-05', '2024-01-01', 'Not Expired');


INSERT INTO customer_Order (customer_Order_Id, customer_Id, total_Amount, date, time)
VALUES
    ('CO-01', 'C-02', 5300, '2023-11-28', '13:21:05'),
    ('CO-02', 'C-03', 5750, '2023-11-30', '14:45:00'),
    ('CO-03', 'C-04', 13000, '2023-12-01', '16:20:00'),
    ('CO-04', 'C-04', 9150, '2023-12-01', '18:00:00'),
    ('CO-05', 'C-02', 20500, '2023-12-02', '20:10:00');


INSERT INTO customer_Order_Detail (customer_Order_Id, stock_Id, qty)
VALUES
    ('CO-01', 'PS-05', 10),
    ('CO-01', 'PS-06', 20),
    ('CO-02', 'PS-05', 15),
    ('CO-02', 'PS-01', 15),
    ('CO-02', 'PS-07', 10),
    ('CO-03', 'PS-03', 20),
    ('CO-03', 'PS-07', 30),
    ('CO-03', 'PS-06', 20),
    ('CO-04', 'PS-03', 20),
    ('CO-04', 'PS-05', 15),
    ('CO-04', 'PS-01', 35),
    ('CO-05', 'PS-03', 20),
    ('CO-05', 'PS-05', 50),
    ('CO-05', 'PS-01', 25),
    ('CO-05', 'PS-07', 20),
    ('CO-05', 'PS-06', 20);



INSERT INTO supplier_Order (supplier_Order_Id, supplier_Id, total_Amount, date, time)
VALUES
    ('SO-01', 'S-01', 500.00, '2023-11-01', '10:00:00'),
    ('SO-02', 'S-02', 750.00, '2023-01-02', '11:30:00'),
    ('SO-03', 'S-03', 300.00, '2023-01-03', '13:15:00'),
    ('SO-04', 'S-04', 200.00, '2023-11-04', '15:45:00'),
    ('SO-05', 'S-05', 1000.00, '2023-01-05', '17:30:00');



INSERT INTO supplier_Order_Detail (supplier_Order_Id, stock_Id)
VALUES
    ('SO-01', 'MS-01'),
    ('SO-01', 'MS-02'),
    ('SO-02', 'MS-03'),
    ('SO-03', 'MS-04'),
    ('SO-04', 'MS-05'),
    ('SO-05', 'MS-01'),
    ('SO-05', 'MS-03');


INSERT INTO spoiled_Product_Report (report_Id, product_Code, spoiled_Qty, date, time)
VALUES
    ('SR-01', 'P-01', 20, '2023-11-20', '12:30:00'),
    ('SR-02', 'P-02', 21, '2023-11-20', '14:45:00'),
    ('SR-03', 'P-03',102, '2023-11-30', '16:20:00'),
    ('SR-04', 'P-04', 13, '2023-12-01', '18:00:00'),
    ('SR-05', 'P-05', 25, '2023-12-02', '20:10:00');
