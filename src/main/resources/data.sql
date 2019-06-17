DROP TABLE IF EXISTS product_order;

CREATE TABLE product_order (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name_order VARCHAR(250) NOT NULL,
  amount INT NOT NULL,
  discount INT DEFAULT NULL
);

--insert into product_order (id, name_order, amount, discount) values(1, 'Product 1', 10, 2);
--insert into product_order (id, name_order, amount, discount) values(2, 'Product 2', 11, 3);
--insert into product_order (id, name_order, amount, discount) values(3, 'Product 3', 12, 4);