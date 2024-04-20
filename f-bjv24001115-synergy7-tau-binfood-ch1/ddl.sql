-- Membuat tabel Users
CREATE TABLE Users (
    id UUID PRIMARY KEY,
    username VARCHAR(255),
    email_address VARCHAR(255),
    password VARCHAR(255)
);

-- Membuat tabel Merchant
CREATE TABLE Merchant (
    id UUID PRIMARY KEY,
    merchant_name VARCHAR(255),
    merchant_location VARCHAR(255),
    open BOOLEAN
);

-- Membuat tabel Product
CREATE TABLE Product (
    id UUID PRIMARY KEY,
    product_name VARCHAR(255),
    price DECIMAL(10, 2),
    merchant_id UUID REFERENCES Merchant(id)
);

-- Membuat tabel Order
CREATE TABLE Order (
    id UUID PRIMARY KEY,
    order_time TIMESTAMP,
    destination_address VARCHAR(255),
    user_id UUID REFERENCES Users(id),
    completed BOOLEAN
);

-- Membuat tabel Order Detail
CREATE TABLE Order_Detail (
    id UUID PRIMARY KEY,
    order_id UUID REFERENCES Order(id),
    product_id UUID REFERENCES Product(id),
    quantity INT,
    total_price DECIMAL(10, 2)
);

-- Menambahkan indeks untuk meningkatkan performa pencarian
CREATE INDEX idx_user_id ON Order (user_id);
CREATE INDEX idx_merchant_id ON Product (merchant_id);
CREATE INDEX idx_order_id ON Order_Detail (order_id);
CREATE INDEX idx_product_id ON Order_Detail (product_id);
