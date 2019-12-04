/* script to create database truyum */
CREATE DATABASE IF NOT EXISTS truyum;

USE truyum;
/* script to create table user*/
CREATE TABLE IF NOT EXISTS user(
  us_id INT NOT NULL AUTO_INCREMENT,
  us_name VARCHAR(60),
  PRIMARY KEY(us_id)
  );

/* script to create table menu_item*/
CREATE TABLE IF NOT EXISTS menu_item(
 me_id INT NOT NULL AUTO_INCREMENT,
 me_name VARCHAR(100),
 me_price NUMERIC(8,2),
 me_active VARCHAR(3),
 me_date_of_launch DATE,
 me_category VARCHAR(45),
 me_free_delivery VARCHAR(3),
 PRIMARY KEY(me_id)
 );
 
 /* script to create table cart*/
 CREATE TABLE IF NOT EXISTS cart(
  ct_id INT NOT NULL AUTO_INCREMENT,
  ct_us_id INT,
  ct_pr_id INT,
  PRIMARY KEY(ct_id),
  FOREIGN KEY(ct_us_id) REFERENCES user(us_id),
  FOREIGN KEY(ct_pr_id) REFERENCES menu_item(me_id)
  );