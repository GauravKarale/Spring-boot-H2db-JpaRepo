INSERT INTO PRODUCT (ID,NAME,PRODUCT_DESC,PRICE,CURRENCY_CODE,CATEGORY_NAME) VALUES (1,'GoPro HERO5 Black','Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.',200.00,'USD','Electronics');
INSERT INTO PRODUCT (ID,NAME,PRODUCT_DESC,PRICE,CURRENCY_CODE,CATEGORY_NAME) VALUES (2,'Worl war 2','Call of duty',230.00,'USD','Electronics');	



INSERT INTO ORDERS (id,customer_id,cart_id,total_price,payment_mode,currency_code,product_quantity,order_date) VALUES(1,1,1,660.00,'CREDITCARD','USD','1,2',CURRENT_DATE());


INSERT INTO ORDER_PRODUCT(order_id,product_id) VALUES(1,1);
INSERT INTO ORDER_PRODUCT(order_id,product_id) VALUES(1,2);