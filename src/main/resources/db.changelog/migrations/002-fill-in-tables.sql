INSERT INTO users (user_id, created_date, fullname, password, role, status, username) VALUES
(0,'1982-01-15', 'Alex Shulyaev', null ,'ADMIN', 'ACTIVE', 'alex93');

INSERT INTO products (product_id, created_date, price, title) VALUES
(0, '1982-01-15', 60, 'product best');

INSERT INTO orders (order_id, code, created_date, total_price, user_id) VALUES
(0, 'code1', '1982-01-15', 120.1, 0);

INSERT INTO products_orders (product_id, order_id) VALUES
(0, 0);