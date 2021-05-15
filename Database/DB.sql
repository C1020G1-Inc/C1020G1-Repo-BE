drop database if exists auction_website;
create database auction_website;
use auction_website;

create table province (
province_id int primary key auto_increment,
province_name varchar(50) not null
);

create table district (
district_id int primary key auto_increment,
district_name varchar(50) not null,
province_id int,
foreign key (province_id) references province (province_id)
);

create table ward (
ward_id int primary key auto_increment,
ward_name varchar(50) not null,
district_id int,
foreign key (district_id) references district (district_id)
);

create table `user` (
user_id int primary key auto_increment,
user_name varchar (50),
user_birthday date,
user_phone varchar(20),
user_identity varchar(20),
user_avatar text,
user_address varchar(255)
);

create table `account` (
account_id int primary key auto_increment,
account_name varchar(50),
account_password varchar(50),
account_email varchar(50),
account_enable bit,
account_logout_time datetime,
user_id int,
foreign key (user_id) references `user` (user_id)
);

create table `role` (
role_id int primary key auto_increment,
role_name varchar(20)
);

create table account_role (
account_role_id int primary key auto_increment,
account_id int,
role_id int,
foreign key (account_id) references `account` (account_id),
foreign key (role_id) references `role` (role_id)
);

create table category (
category_id int primary key auto_increment,
category_name varchar(50)
);

create table product_status(
product_status_id int primary key auto_increment,
status_name varchar(20)
);

create table product (
product_id int primary key auto_increment,
product_name varchar (50),
product_price DOUBLE,
product_price_step DOUBLE,
product_service_fee DOUBLE,
product_quantity INT,
product_last_price DOUBLE,
product_description TEXT,
product_register_time DATETIME,
product_auction_time INT,
product_end_time DATETIME,
account_id int,
category_id int,
product_status_id int,
foreign key (account_id) references `account` (account_id),
foreign key (category_id) references category (category_id),
foreign key (product_status_id) references product_status (product_status_id)
);

create table auction (
auction_id int primary key auto_increment,
price double,
time_auction datetime,
`status` varchar(50),
account_id int,
product_id int,
foreign key (account_id) references `account` (account_id),
foreign key (product_id) references product (product_id)
);

create table product_image (
product_image_id int primary key auto_increment,
image text,
product_id int,
foreign key (product_id) references product (product_id)
);

create table product_transaction (
product_transaction_id int primary key auto_increment,
`status` varchar(50),
product_id int,
account_id int,
foreign key (product_id) references product (product_id),
foreign key (account_id) references `account` (account_id)
);

create table `order` (
order_id int primary key auto_increment,
address varchar(255),
phone varchar(20),
guide text,
total double,
method_pay bit,
account_id int,
ward_id int,
foreign key (account_id) references `account` (account_id),
foreign key (ward_id) references ward (ward_id)
);


create table order_product (
order_product_id int primary key auto_increment,
product_id int,
order_id int,
foreign key (product_id) references product (product_id),
foreign key (order_id) references `order` (order_id)
);

create table comment (
comment_id int primary key auto_increment,
content text,
image varchar(100),
comment_time datetime,
account_id int,
product_id int,
foreign key (account_id) references `account` (account_id),
foreign key (product_id) references product (product_id)
);
