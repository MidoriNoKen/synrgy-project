-- Create Users table
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'customer',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Create Merchants table
CREATE TABLE Merchants (
    code SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    open BOOLEAN DEFAULT TRUE
);

-- Create Products table
CREATE TABLE Products (
    code SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    merchant_code BIGINT,
    FOREIGN KEY (merchant_code) REFERENCES Merchants(code)
);

-- Create Orders table
CREATE TABLE Orders (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    address VARCHAR(255) NOT NULL,
    user_id BIGINT,
    completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- Create Orders_details table
CREATE TABLE Order_details (
    id SERIAL PRIMARY KEY,
    order_id BIGINT,
    product_code BIGINT,
    quantity BIGINT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (product_code) REFERENCES Products(code)
);
