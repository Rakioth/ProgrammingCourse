INSERT INTO card_types (code, ref)
VALUES ('visa', 'swiftly.card.visa'),
       ('master', 'swiftly.card.mastercard'),
       ('american', 'swiftly.card.american');

INSERT INTO client_documents (code, ref)
VALUES ('dni', 'swiftly.document.dni'),
       ('nie', 'swiftly.document.nie'),
       ('passport', 'swiftly.document.passport'),
       ('social', 'swiftly.document.social');

INSERT INTO client_types (code, ref)
VALUES ('bronze', 'swiftly.client.bronze'),
       ('silver', 'swiftly.client.silver'),
       ('gold', 'swiftly.client.gold'),
       ('platinum', 'swiftly.client.platinum');

INSERT INTO country_types (code, ref)
VALUES ('es', 'swiftly.country.es'),
       ('en', 'swiftly.country.en');

INSERT INTO supplier_documents (code, ref)
VALUES ('cif', 'swiftly.document.cif'),
       ('dni', 'swiftly.document.dni');

INSERT INTO supplier_types (code, ref)
VALUES ('basic', 'swiftly.supplier.basic'),
       ('priority', 'swiftly.supplier.priority'),
       ('essential', 'swiftly.supplier.essential');

INSERT INTO via_types (code, ref)
VALUES ('avenue', 'swiftly.via.avenue'),
       ('street', 'swiftly.via.street'),
       ('roundabout', 'swiftly.via.roundabout'),
       ('walkway', 'swiftly.via.walkway'),
       ('plaza', 'swiftly.via.plaza');

INSERT INTO promotions (description, discount, end_date, start_date)
VALUES ('Spring Sale', 0.1, '2023-05-31 23:59:59.000000', '2023-05-15 00:00:00.000000'),
       ('Summer Sale', 0.2, '2023-08-31 23:59:59.000000', '2023-06-01 00:00:00.000000'),
       ('Autumn Sale', 0.15, '2023-11-30 23:59:59.000000', '2023-09-01 00:00:00.000000'),
       ('Winter Sale', 0.3, '2023-02-28 23:59:59.000000', '2023-01-01 00:00:00.000000');

INSERT INTO products (accumulated_expenses, brand, code, comments, description, hidden_threshold, is_new, model, on_sale, price, rating, request_threshold, sold_units, stock, promotion_id, cat)
VALUES (245.32, 'JBL', 'PROD001', 'Great sound quality', 'Experience powerful and immersive sound with the JBL Xtreme 2 Portable Bluetooth Speaker. With its rugged design and waterproof construction, this speaker is perfect for outdoor adventures or parties. The built-in rechargeable battery provides up to 15 hours of playtime, and you can even connect multiple speakers for an enhanced audio experience.', 10, true, 'JBL Xtreme 2', true, 199.99, 'ONE', 30, 75, 31, 1, 'ELECTRONICS'),
       (586.17, 'Levi\'s', 'PROD002', 'Classic and comfortable', 'Step out in style and comfort with the Levi\'s 501 Original Fit Jeans. These timeless jeans feature a straight leg and a regular fit that sits at the waist. Made from durable denim, they are perfect for everyday wear and can be dressed up or down for any occasion.', 10, false, 'Blue Jeans', false, 79.99, 'TWO', 30, 150, 50, NULL, 'CLOTHES'),
       (1023.89, 'Samsung', 'PROD003', 'Immersive audio experience', 'Enjoy an immersive audio experience with the Samsung Galaxy Buds2 True Wireless Earbuds. These earbuds deliver clear and dynamic sound, and the active noise cancellation feature lets you focus on your music or calls without any distractions. With a comfortable fit and long battery life, these earbuds are perfect for on-the-go use.', 10, false, 'Galaxy Buds2', false, 149.99, 'THREE', 50, 200, 100, NULL, 'ELECTRONICS'),
       (309.75, 'OPPO', 'PROD004', 'Sleek and powerful', 'Discover the sleek and powerful OPPO A15 Smartphone. Featuring a vibrant display, fast performance, and a high-capacity battery, this smartphone is designed to keep up with your daily tasks and entertainment needs. Capture stunning photos with the AI-powered triple camera system and enjoy a seamless user experience with the ColorOS operating system.', 10, false, 'OPPO A15', true, 199.99, 'TWO', 30, 100, 47, 1, 'ELECTRONICS'),
       (731.46, 'Adidas', 'PROD005', 'Iconic design and comfort', 'Step up your sneaker game with the Adidas Yeezy 500 Bone White Sneakers. These iconic sneakers combine style and comfort, featuring a premium leather and mesh upper with reflective detailing. The adiPRENE+ cushioning provides a responsive feel, making these sneakers perfect for all-day wear.', 10, true, 'Yeezy 500 Bone White', false, 249.99, 'TWO', 30, 88, 55, NULL, 'CLOTHES'),
       (415.92, 'Supreme', 'PROD006', 'Streetwear at its finest', 'Elevate your streetwear style with the Supreme Black Hoodie. Made from premium materials, this hoodie offers both comfort and durability. The iconic Supreme logo on the front adds a touch of urban flair, making it a must-have for fashion enthusiasts and streetwear aficionados.', 10, false, 'Supreme Black Hoodie', false, 149.99, 'FOUR', 30, 13, 35, NULL, 'CLOTHES'),
       (893.58, 'Apple', 'PROD007', 'Powerful and reliable', 'Experience the power and innovation of the Apple MacBook Pro. This cutting-edge laptop is designed to meet the demands of creative professionals and tech enthusiasts alike. With its sleek and compact design, the MacBook Pro is highly portable, making it perfect for working on the go. Equipped with a stunning Retina display, powerful processors, and advanced graphics, it delivers an immersive visual experience for editing photos, designing graphics, or watching movies.', 10, false, 'Apple MacBook Pro', true, 1999.99, 'FIVE', 30, 4, 77, 1, 'ELECTRONICS'),
       (632.11, 'Prada', 'PROD008', 'Stylish and vibrant', 'Add a vibrant touch to your wardrobe with the Yellow Double Match Poplin Shirt from Prada. This stylish shirt features a sunny yellow color and is made from high-quality poplin fabric for a comfortable and breathable fit. With its versatile design, it can be dressed up for formal occasions or paired with jeans for a more casual look. Stand out from the crowd and express your unique style with this eye-catching yellow shirt.', 10, true, 'Yellow Double Match Poplin Shirt', false, 399.99, 'FIVE', 30, 11, 56, NULL, 'CLOTHES'),
       (178.27, 'Ferrari', 'PROD009', 'Show your love for Ferrari', 'Complete your sporty and stylish look with the Ferrari Cap. This cap features the iconic Ferrari logo embroidered on the front, representing luxury and performance. Made from high-quality materials, it offers a comfortable and adjustable fit. Whether you\'re a Ferrari fan or simply appreciate fine craftsmanship, this cap is a must-have accessory that adds a touch of sophistication to your outfits. Show your love for speed and elegance with the Ferrari Cap.', 10, false, 'Ferrari Cap', false, 79.99, 'TWO', 30, 1, 97, NULL, 'CLOTHES');

INSERT INTO product_images (product_id, image_description, image_filename, image_filesize, image_type, image_path)
VALUES (1, 'Product View', 'product1.png', 1024, 'PNG', 'http://localhost:8000/img/product1.png'),
       (2, 'Product View', 'product2.png', 1024, 'PNG', 'http://localhost:8000/img/product2.png'),
       (3, 'Product View', 'product3.png', 1024, 'PNG', 'http://localhost:8000/img/product3.png'),
       (4, 'Product View', 'product4.png', 1024, 'PNG', 'http://localhost:8000/img/product4.png'),
       (5, 'Product View', 'product5.png', 1024, 'PNG', 'http://localhost:8000/img/product5.png'),
       (6, 'Product View', 'product6.png', 1024, 'PNG', 'http://localhost:8000/img/product6.png'),
       (7, 'Product View', 'product7.png', 1024, 'PNG', 'http://localhost:8000/img/product7.png'),
       (8, 'Product View', 'product8.png', 1024, 'PNG', 'http://localhost:8000/img/product8.png'),
       (9, 'Product View', 'product9.png', 1024, 'PNG', 'http://localhost:8000/img/product9.png');

INSERT INTO stores (addr_via, addr_name, addr_floor, addr_portal, addr_number, addr_locality, addr_postal_code, addr_region, latitude_cardinal_point, latitude_degrees, latitude_minutes, latitude_seconds, longitude_cardinal_point, longitude_degrees, longitude_minutes, longitude_seconds, image_description, image_filename, image_filesize, image_type, image_path)
VALUES ('street', 'Evergreen Crossing', '2', 'A', 5, 'Madrid', '28013', 'Spain', 'NORTH', 40, 25, 12, 'WEST', 3, 42, 20, 'Store View', 'store1.png', 1024, 'PNG', 'http://localhost:8000/img/store1.png'),
       ('avenue', 'Rivercrest Avenue', '1', 'B', 25, 'Barcelona', '08007', 'Spain', 'NORTH', 41, 23, 31, 'EAST', 2, 9, 51, 'Store View', 'store2.png', 1024, 'PNG', 'http://localhost:8000/img/store2.png'),
       ('plaza', 'Serenity Lane', '1', 'C', 123, 'Lisbon', '1250-096', 'Portugal', 'NORTH', 38, 42, 59, 'WEST', 9, 8, 34, 'Store View', 'store3.png', 1024, 'PNG', 'http://localhost:8000/img/store3.png');

INSERT INTO suppliers (addr_via, addr_name, addr_floor, addr_portal, addr_number, addr_locality, addr_postal_code, addr_region, comments, document, landline, name, phone_number, document_type, type)
VALUES ('street', 'Wildflower Path', '2', 'B', '123', 'Madrid', '28001', 'Madrid', 'Preferred supplier for premium products. Reliable and prompt service.', '12345678', '+34 987654321', 'ABC Suppliers', '+34 123456789', 'cif', 'priority'),
       ('avenue', 'Brookside Court', '1', 'C', '456', 'Barcelona', '08002', 'Catalonia', 'Specializes in sourcing unique and artisanal products.', '87654321', '+34 987654321', 'XYZ Traders', '+34 987654321', 'cif', 'basic'),
       ('roundabout', 'Willowbrook Lane', '3', 'A', '789', 'Lisbon', '1250-123', 'Lisbon', 'Global supplier with a wide range of products. Flexible delivery options.', '56789012', '+351 987654321', 'Global Supplies Ltd.', '+351 987654321', 'cif', 'essential');

INSERT INTO catalogs (end_date, start_date, supplier_id)
VALUES ('2023-05-31 23:59:59.999999', '2023-05-01 00:00:00.000000', 1),
       ('2023-06-30 23:59:59.999999', '2023-06-01 00:00:00.000000', 3);

INSERT INTO catalog_details (catalog_id, price, product_id)
VALUES (1, 199.99, 1),
       (1, 79.99, 2),
       (1, 149.99, 3),
       (1, 199.99, 4),
       (1, 1999.99, 7),
       (2, 249.99, 5),
       (2, 149.99, 6),
       (2, 399.99, 8),
       (2, 79.99, 9);

INSERT INTO categories (code, description, product_id, category_parent)
VALUES ('ELECTRONICS', 'Electronic devices and accessories', 3, NULL),
       ('CLOTHES', 'Clothing and accessories', 3, 1),
       ('FOOD', 'Food and beverages', 4, NULL),
       ('HOME', 'Home appliances and furniture', 1, 2),
       ('PHONES', 'Mobile phones and accessories', 3, NULL),
       ('TABLETS', 'Tablets and accessories', 3, NULL),
       ('LAPTOPS', 'Laptops and accessories', 3, 5),
       ('TV', 'Televisions and accessories', 3, NULL),
       ('HEADPHONES', 'Headphones and speakers', 3, NULL),
       ('CAMERAS', 'Cameras and accessories', 3, 7),
       ('CLOTHES_WOMEN', 'Women clothing and accessories', 3, NULL),
       ('CLOTHES_MEN', 'Men clothing and accessories', 3, 8);

INSERT INTO categories_child (category_1_id, categories_2_id)
VALUES (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (2, 11),
       (2, 12);

INSERT INTO users (email, password, role, success_auth, username, failed_auth, last_connection)
VALUES ('pidop90816@andorem.com', '$2a$10$dSFZZuGGMf2pSDfPeujiauNy2EAA3FCYN1R5UIa.Zi2LNzwR4rmH.', 'USER', 10, 'raks', 0, '2023-05-12 13:00:00.000000'),
       ('hidokib731@aicogz.com', '$2a$10$tfxCNXlg5V73VxSNmpX7k.GUxTMkupvk9gFd7lytIy50G5856o7Im', 'ADMIN', 20, 'admin', 0, '2023-05-12 18:00:00.000000'),
       ('basab69690@glumark.com', '$2a$10$FawnmgB4UqCG9GqSZBbxL.2MONj9rLX54EcOMyEc5/cuN9XqBpkd2', 'CLIENT', 30, 'laura', 0, '2023-05-12 17:00:00.000000'),
       ('xicaxey299@glumark.com', '$2a$10$NzmctoPeVZE1ZPnhWZnzw.gUVUPtv3TY2ugxiGYu5/O3vxft5vMt6', 'CLIENT', 15, 'kalin', 0, '2023-05-12 13:00:00.000000'),
       ('yagapo7779@jwsuns.com', '$2a$10$W4OdZedsDI8vX0YVod.ED.DO.ZgfWHUI3JZonCShO.Y5gapbBcYZS', 'CLIENT', 26, 'daniel', 0, '2023-05-12 19:00:00.000000'),
       ('yifacad127@dekaps.com', '$2a$10$84QTdThYm0//vu7DxMuTE.9MT9R88WRZ1/VSa5UNmVa3GTaG0Ftgi', 'DELETED', 15, 'josera', 0, '2023-05-13 07:45:00.000000'),
       ('godemad823@dekaps.com', '$2a$10$Cm4WUqREHVOxwXrJa93Ree/O4WbeizGbZoDC10CbFjyuHWCMQ0bzm', 'ADMIN', 13, 'chloe', 0, '2023-05-13 02:45:00.000000');

INSERT INTO clients (accumulated_expenses, addr_name, addr_floor, addr_locality, addr_number, addr_portal, addr_postal_code, addr_region, birthdate, name, comments, country_code, document, gender, license, phone_number, surnames, addr_via, document_type, type, user_id)
VALUES (0, 'Meadowbrook Terrace', '3', 'Cityville', 123, 'A', '12345', 'Regionville', '1990-01-01', 'Laura', 'No comments', 'en', 'ABC123', 'FEMALE', true, '+1 123-456-7890', 'Smith', 'street', 'passport', 'bronze', 3),
       (0, 'Whispering Pines Lane', '6', 'Suburbville', 555, 'F', '98765', 'Regionville', '1988-04-12', 'Kalin', 'New comments', 'en', 'QWE123', 'OTHER', true, '+1 444-555-6666', 'Lopez', 'street', 'nie', 'gold', 4),
       (356.99, 'Greenwood Court', '5', 'Villageville', 789, 'C', '67890', 'Regionville', '1982-09-15', 'Daniel', 'Additional comments', 'es', 'GHI789', 'MALE', true, '+1 555-123-4567', 'Brown', 'street', 'social', 'platinum', 5),
       (0, 'Lakeside Drive', '1', 'Hamletville', 987, 'D', '09876', 'Regionville', '1978-12-20', 'Josera', 'Additional notes', 'es', 'JKL012', 'MALE', true, '+1 111-222-3333', 'Davis', 'street', 'dni', 'silver', 6);

INSERT INTO client_credit_cards (client_id, type, ccv, expired_date, number)
VALUES (3, 'visa', '123', '2024-01-01', '1234567890123456'),
       (3, 'master', '456', '2025-02-01', '2345678901234567'),
       (3, 'american', '789', '2026-03-01', '3456789012345678');

INSERT INTO client_addresses (client_id, addr_name, addr_floor, addr_locality, addr_number, addr_portal, addr_postal_code, addr_region, addr_via)
VALUES (3, 'Sunflower Way', '1', 'Main Street', 123, 'A', '12345', 'New York', 'street'),
       (3, 'Stonybrook Circle', '2', 'Broadway', 456, 'B', '54321', 'New York', 'roundabout'),
       (3, 'Timberland Heights', '3', 'State Street', 789, 'C', '67890', 'California', 'plaza');

INSERT INTO orders (date, state, total_price, client_id)
VALUES ('2023-03-16 00:00:00.000000', 'COMPLETED', 929.94, 3),
       ('2023-04-16 00:00:00.000000', 'IN_TRANSIT', 1999.99, 3),
       ('2023-05-16 00:00:00.000000', 'LOST', 639.96, 3);

INSERT INTO order_details (order_id, price, product_id, units)
VALUES (1, 149.99, 3, 4),
       (1, 249.99, 5, 1),
       (1, 79.99, 2, 1),
       (2, 1999.99, 7, 1),
       (3, 399.99, 8, 1),
       (3, 79.99, 9, 3);

INSERT INTO shopping_carts (creation_date, price, client_id)
VALUES ('2022-01-01 12:00:00.000000', 0, 1),
       ('2022-01-01 12:00:00.000000', 0, 2),
       ('2022-01-01 12:00:00.000000', 359.97, 3),
       ('2022-01-01 12:00:00.000000', 0, 4);

INSERT INTO shopping_cart_details (shopping_cart_id, product_id, units)
VALUES (3, 1, 1),
       (3, 2, 2);

INSERT INTO warnings (description, type)
VALUES ('Hide product: JBL Xtreme 2', 'LOW'),
       ('Hide product: Ferrari Cap', 'MEDIUM'),
       ('Request product: Apple MacBook Pro', 'HIGH');