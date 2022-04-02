create table orders (
    order_id  bigserial not null,
    code varchar(64),
    created_date timestamp,
    total_price float8,
    user_id int8,
    primary key (order_id)
);

create table products (
    product_id  bigserial not null,
    created_date timestamp,
    price float8,
    storage_quantity float8,
    measure varchar(64),
    title varchar(128),
    primary key (product_id)
);

create table products_orders (
    product_id int8 not null,
    order_id int8 not null,
    quantity float8,
    priceforquantity float8,
    primary key (product_id, order_id)
);

create table users (
    user_id  bigserial not null,
    created_date timestamp,
    firstname varchar(64),
    lastname varchar(64),
    password varchar(3000),
    role varchar(128),
    status varchar(128),
    login varchar(64),
    primary key (user_id)
);

alter table orders add constraint UK_gt3o4a5bqj59e9y6wakgk926t unique (code);

alter table products add constraint UK_8xtpej5iy2w4cte2trlvrlayy unique (title);

alter table users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (login);

alter table orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;

alter table products_orders add constraint FK22csaamlxupxb7vy4uow5xww6 foreign key (order_id) references orders;

alter table products_orders add constraint FKixe7cct0ge9ihyc9okwpw3mpf foreign key (product_id) references products;