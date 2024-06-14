-- Insert data into Users table
INSERT INTO Users (username, email, password, phone, address, created_at, updated_at, deleted, role)
VALUES 
('taufik', 'taufik@taufik.com', MD5('313131'), '1234567890', 'Address 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 'customer'),
('jane_smith', 'jane@example.com', MD5('password456'), '0987654321', 'Address 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 'customer'),
('alice_wonder', 'alice@example.com', MD5('password789'), '9876543210', 'Address 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 'customer');

-- Insert data into Merchants table
INSERT INTO Merchants (name, location, open)
VALUES 
('Merchant A', 'Location A', TRUE),
('Merchant B', 'Location B', FALSE),
('Merchant C', 'Location C', TRUE);

-- Insert data into Products table
INSERT INTO Products (name, price, merchant_code)
VALUES 
('Product 1', 10.00, 1),  -- Assuming 'Merchant A' has code 1
('Product 2', 20.00, 2),  -- Assuming 'Merchant B' has code 2
('Product 3', 30.00, 3);  -- Assuming 'Merchant C' has code 3

-- Insert data into Orders table
INSERT INTO Orders (date, address, user_id, merchant_code, completed, quantity, price)
VALUES 
('2024-05-23 10:00:00', 'Address 1', 1, 1, FALSE, 5, 100000),  -- Assuming 'john_doe' has id 1
('2024-05-23 11:00:00', 'Address 2', 2, 1, TRUE, 4, 20000),   -- Assuming 'jane_smith' has id 2
('2024-05-23 12:00:00', 'Address 3', 3, 1, FALSE, 2, 200000);  -- Assuming 'alice_wonder' has id 3

-- Insert data into Order_details table
INSERT INTO Order_details (order_id, product_code, quantity, total_price)
VALUES 
(1, 1, 2, 20.00),  -- Assuming 'Order 1' has id 1 and 'Product 1' has code 1
(2, 2, 1, 20.00),  -- Assuming 'Order 2' has id 2 and 'Product 2' has code 2
(3, 3, 3, 90.00);  -- Assuming 'Order 3' has id 3 and 'Product 3' has code 3
