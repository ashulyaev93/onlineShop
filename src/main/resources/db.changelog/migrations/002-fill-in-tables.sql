INSERT INTO users (user_id, created_date, firstname, lastname, password, role, status, login) VALUES
(0,'1982-01-15', 'Alex', 'Shulyaev', '$2a$12$vw22uFWtGMMWFBfgKl/5/eVLKoSXwZyZEA5LYhlat65OKIHlquoXm' ,'ADMIN', 'ACTIVE', 'alex93');

INSERT INTO products (product_id, created_date, price, title, storage_quantity, measure) VALUES
(0, '1982-01-15', 60.0, 'banan', 2.2, 'kg');

INSERT INTO orders (order_id, code, created_date, total_price, user_id) VALUES
(0, 'code1', '1982-01-15', 72.0, 0);

INSERT INTO products_orders (product_id, order_id, quantity, priceForQuantity) VALUES
(0, 0, 1.2, 72.0);
