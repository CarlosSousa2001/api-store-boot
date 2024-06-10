INSERT INTO Category (name_category)
VALUES ('Eletrônicos'), ('Roupas'), ('Livros');



INSERT INTO produto (id, title, description, price, cod, photo_url, created_at, update_at)
VALUES (10, 'Example Product', 'This is an example description.', 1999, 'EX12345', 'http://example.com/photo.jpg', '2023-01-01 10:00:00', '2023-01-01 10:00:00');

INSERT INTO produto (id, title, description, price, cod, photo_url, created_at, update_at)
VALUES (12, 'Example Product two', 'This is an example description.', 1999, 'EZFF222', 'http://example.com/photo.jpg', '2023-01-01 10:00:00', '2023-01-01 10:00:00');

INSERT INTO customer (username, email, password, role)
VALUES ('mockuser', 'mockuser@example.com', 'mockpassword', 'ROLE_CLIENTE');

INSERT INTO orders (id, customer_id)
VALUES (1, 1);

INSERT INTO Payment (order_id, status) VALUES (1, 'CANCELLED');

INSERT INTO order_item (order_id, product_id, size, quantity, price)
VALUES (1, 10, 'M', 2, 1999);

