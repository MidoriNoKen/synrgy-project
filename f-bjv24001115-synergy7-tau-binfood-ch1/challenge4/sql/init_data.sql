

-- Insert initial data into Users table
INSERT INTO Users (username, email_address, password) VALUES 
('midorinoken', 'midor1nok3n@gmail.com', '123123123'),
('diah', 'diah@gmail.com', '313131'),
('debian', 'debian@gmail.com', '312331123');

-- Insert initial data into Merchant table
INSERT INTO Merchant (merchant_name, merchant_location, open) VALUES 
('Indomaret', 'Malang', TRUE),
('Alfamart', 'Surabaya', TRUE),
('Alfamidi', 'Pasuruan', FALSE);

-- Insert initial data into Product table
INSERT INTO Product (product_name, price, merchant_code) VALUES 
('Biskuat', 7000, 1),
('Oreo', 10000, 1),
('Yoghurt', 10000, 2),
('Ultramilk', 8000, 2),
('Semangka', 11000, 3),
('Nanas', 12000, 3);

-- Insert initial data into Order table
INSERT INTO "Order" (order_time, destination_address, user_id, completed) VALUES 
('2024-05-17 10:00:00', 'Address 1', 1, FALSE),
('2024-05-17 11:30:00', 'Address 2', 2, TRUE),
('2024-05-17 13:45:00', 'Address 3', 3, FALSE);

-- Insert initial data into Order_detail table
INSERT INTO Order_detail (order_id, product_code, quantity, total_price) VALUES 
(1, 1, 2, 14000),
(1, 2, 1, 10000),
(2, 3, 3, 30000),
(3, 4, 1, 8000),
(3, 5, 5, 55000),
(3, 6, 2, 24000);
