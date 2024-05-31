INSERT INTO Category (name_category)
VALUES ('Eletrônicos'), ('Roupas'), ('Livros');

INSERT INTO payment (status)
VALUES ('PENDING'), ('PROCESSING'), ('COMPLETED'), ('FAILED'), ('CANCELLED'), ('REFUNDED');

INSERT INTO product (title, description, price, created_at)
VALUES ('Titulo do Produto', 'Descrição do Produto', 100, '2024-05-29T00:00:00');

INSERT INTO customer (username, email, password, role)
VALUES ('mockuser', 'mockuser@example.com', 'mockpassword', 'ROLE_CLIENTE');


