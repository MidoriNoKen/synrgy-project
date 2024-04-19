CREATE TABLE User (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(50)
);

CREATE TABLE `Order` (
    id INT PRIMARY KEY,
    ordertime DATETIME,
    dest VARCHAR(100),
    user_id INT,
    completed CHAR(1),
    FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE Order_Detail (
    id INT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES `Order`(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);

CREATE TABLE Product (
    id INT PRIMARY KEY,
    product_name VARCHAR(100),
    price DECIMAL(10, 2),
    merchant_id INT,
    FOREIGN KEY (merchant_id) REFERENCES Merchant(id)
);

CREATE TABLE Merchant (
    id INT PRIMARY KEY,
    merchant_name VARCHAR(100),
    merchant_location VARCHAR(100),
    open CHAR(1)
);
