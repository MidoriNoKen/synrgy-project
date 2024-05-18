-- Create Users table
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email_address VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create Merchant table
CREATE TABLE Merchant (
    merchant_code SERIAL PRIMARY KEY,
    merchant_name VARCHAR(100) NOT NULL,
    merchant_location VARCHAR(255) NOT NULL,
    open BOOLEAN DEFAULT TRUE
);

-- Create Product table
CREATE TABLE Product (
    product_code SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    merchant_code INT,
    FOREIGN KEY (merchant_code) REFERENCES Merchant(merchant_code)
);

-- Create Order table
CREATE TABLE "Order" (
    order_id SERIAL PRIMARY KEY,
    order_time TIMESTAMP NOT NULL,
    destination_address VARCHAR(255) NOT NULL,
    user_id INT,
    completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Create Order_detail table
CREATE TABLE Order_detail (
    order_id INT,
    product_code INT,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (order_id, product_code),
    FOREIGN KEY (order_id) REFERENCES "Order"(order_id),
    FOREIGN KEY (product_code) REFERENCES Product(product_code)
);
