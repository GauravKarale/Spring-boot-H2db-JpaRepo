
CREATE TABLE  product (
  id         INTEGER PRIMARY KEY,
  name VARCHAR(30),
  product_desc  VARCHAR(200),
  price FLOAT,
  currency_code VARCHAR(50),
  category_name VARCHAR(50)
);

CREATE TABLE orders(
 id   INTEGER PRIMARY KEY,
 customer_id INTEGER,
 cart_id INTEGER,
 total_price DECIMAL(5,2),
 payment_mode VARCHAR(20),
 currency_code VARCHAR(6),
 product_quantity VARCHAR(10),
 order_date  DATE 
);


CREATE TABLE order_product(
	order_id INTEGER ,
	product_id INTEGER,
);