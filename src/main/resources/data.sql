INSERT INTO Category (name_category)
VALUES ('Eletrônicos'), ('Roupas'), ('Livros');

INSERT INTO payment (status)
VALUES ('PENDING'), ('PROCESSING'), ('COMPLETED'), ('FAILED'), ('CANCELLED'), ('REFUNDED');

INSERT INTO produto (id, title, description, price, cod, photo_url, created_at, update_at)
VALUES (10, 'Example Product', 'This is an example description.', 1999, 'EX12345', 'http://example.com/photo.jpg', '2023-01-01 10:00:00', '2023-01-01 10:00:00');

INSERT INTO customer (username, email, password, role)
VALUES ('mockuser', 'mockuser@example.com', 'mockpassword', 'ROLE_CLIENTE');


