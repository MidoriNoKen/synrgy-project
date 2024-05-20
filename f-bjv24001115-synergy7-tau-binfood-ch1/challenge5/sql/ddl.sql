-- Create Users table
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create Merchant table
CREATE TABLE Merchant (
    code SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    open BOOLEAN DEFAULT TRUE
);

-- Create Product table
CREATE TABLE Product (
    code SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    merchant_code BIGINT,
    FOREIGN KEY (merchant_code) REFERENCES Merchant(code)
);

-- Create Order table
CREATE TABLE "Order" (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    address VARCHAR(255) NOT NULL,
    user_id BIGINT,
    completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- Create Order_detail table
CREATE TABLE Order_detail (
    id BIGINT,
    order_id BIGINT,
    product_code BIGINT,
    quantity BIGINT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (order_id, product_code),
    FOREIGN KEY (order_id) REFERENCES "Order"(id),
    FOREIGN KEY (product_code) REFERENCES Product(code)
);
