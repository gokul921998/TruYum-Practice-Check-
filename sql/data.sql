/* script to insert data into menu_item table - TYUC001 */
INSERT INTO menu_item VALUES(DEFAULT, 'Sandwich', 99.00, 'Yes', '2017/03/15', 'MainCourse', 'Yes'),
                            (DEFAULT, 'Burger', 129.00, 'Yes', '2017/12/23', 'MainCourse', 'No'),
                            (DEFAULT, 'Pizza', 149.00, 'Yes', '2017/08/21', 'MainCourse', 'No'),
                            (DEFAULT, 'French Fries', 57.00, 'No', '2017/07/02', 'Starters', 'Yes'),
                            (DEFAULT, 'Chocolate Brownie', 32.00, 'Yes', '2022/11/02', 'Dessert', 'Yes');

/* script to fetch data from menu_item table - TYUC001 */
SELECT * FROM menu_item;

/* script to fetch data from menu_item table - TYUC002 */
SELECT * FROM menu_item WHERE me_active = 'Yes' AND me_date_of_launch <= now();

/* script to fetch data from menu_item table - TYUC003 */
SELECT * FROM menu_item WHERE me_id = 3;

/* script to update data in menu_item table - TYUC003 */
UPDATE menu_item SET me_name='Choco Truffle', me_price='52.00', me_date_of_launch='2018/03/28' WHERE me_id = 5;

/* script to add data into user table - TYUC004 */
INSERT INTO user VALUES (DEFAULT, 'Gokul'),
                        (DEFAULT, 'Logesh');

/* script to add data into cart table - TYUC004 */
INSERT INTO cart VALUES (DEFAULT, 1, NULL), 
                        (DEFAULT, 2, 2),
                        (DEFAULT, 2, 3),
                        (DEFAULT, 2, 5);
                        
/* script to get all menu items in user's cart - TYUC005*/
SELECT menu_item.me_id AS 'Id', menu_item.me_name AS 'Name', menu_item.me_free_delivery AS 'Free Delivery', menu_item.me_price AS 'Price', menu_item.me_category AS 'Category'
 FROM menu_item INNER JOIN cart ON menu_item.me_id = cart.ct_pr_id WHERE cart.ct_us_id = 1;

/* script to get sum of all products in user's cart - TYUC005*/
SELECT SUM(menu_item.me_price) FROM menu_item INNER JOIN cart ON menu_item.me_id = cart.ct_pr_id WHERE cart.ct_us_id = 1;

/* script to remove menu items from user's cart - TYUC006*/
DELETE FROM cart WHERE  ct_us_id = 2 AND ct_pr_id = 3;