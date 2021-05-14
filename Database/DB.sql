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
account_password varchar(255),
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
auction_id int,
transaction_time datetime,
foreign key (product_id) references product (product_id),
foreign key (account_id) references `account` (account_id),
foreign key (auction_id) references auction (auction_id)
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
comment_time datetime,
account_id int,
product_id int,
foreign key (account_id) references `account` (account_id),
foreign key (product_id) references product (product_id)
);

insert into `user` (user_name)
values
('phuc'),
('loc'),
('tho'),
('nhan'),
('nghia'),
('tri'),
('tin');

insert into `account` (account_name,account_email,account_password,account_enable,user_id)
values
('phuc123456','truongphucdn@gmail.com','$2y$12$zl1tVC73T8anhOY0VHDcVehT.ZM.6WYku1Z7tOb2gJkQaWtwVC1mm',1,1),
('loc123456','truongphucdn@gmail.com','$2y$12$zl1tVC73T8anhOY0VHDcVehT.ZM.6WYku1Z7tOb2gJkQaWtwVC1mm',1,2),
('tho123456','truongphucdn@gmail.com','$2y$12$zl1tVC73T8anhOY0VHDcVehT.ZM.6WYku1Z7tOb2gJkQaWtwVC1mm',1,3),
('nhan123456','truongphucdn@gmail.com','$2y$12$zl1tVC73T8anhOY0VHDcVehT.ZM.6WYku1Z7tOb2gJkQaWtwVC1mm',1,4), 
('nghia123456','truongphucdn@gmail.com','$2y$12$zl1tVC73T8anhOY0VHDcVehT.ZM.6WYku1Z7tOb2gJkQaWtwVC1mm',1,5), 
('tri123456','truongphucdn@gmail.com','$2y$12$zl1tVC73T8anhOY0VHDcVehT.ZM.6WYku1Z7tOb2gJkQaWtwVC1mm',1,6), 
('tin123456','truongphucdn@gmail.com','$2y$12$zl1tVC73T8anhOY0VHDcVehT.ZM.6WYku1Z7tOb2gJkQaWtwVC1mm',1,7);

insert into `role` (role_name)
values
('ADMIN'),
('MEMBER');

insert into `account_role`(account_id,role_id)
values
(1,2),
(2,2),
(3,2),
(4,2),
(5,2),
(6,2),
(7,2);

insert into product_status (status_name) values ('pending'),('approved'),('purchasing'),('fail'),('close');
insert into category (category_name) values ('smartphone'),('tablet'),('washing');
insert into product (product_name, product_price, product_price_step, product_quantity, product_description, product_register_time, product_auction_time,account_id, category_id, product_status_id)
values
('Máy hút bụi Electrolux Z1230 (Loại O2B)', 2070000, 50000, 1, 'Máy hút bụi Electrolux EC31-2BB với công suất lên đến1800W cho lực hút mạnh mẽ dễ dàng hút sạch mọi bụi bẩn.', '2021-05-13 21:34:00.000+07:00', 15, 1, 1, 2),
('PANASONIC 9.0KG NA-F90A4GRV', 7190000, 100000, 1, 'Với khối lượng giặt lên đến 9 kg cho một mẻ giặt, chiếc máy giặt Panasonic NA-F90A4GRV sẽ là một sự lựa chọn lý tưởng cho các gia đình có từ 6 người trở lên.', '2021-05-13 21:34:00.000+07:00', 20, 2, 1, 2),
('iPhone 12 Mini 64GB, Đen (VN/A) ', 17190000, 200000, 1, 'iPhone 12 Mini - Smartphone kích thước nhỏ mạnh nhất hiện tại! Bên cạnh việc chạy theo các mẫu smartphone màn hình lớn thì Apple lại bất ngờ cho ra mắt thêm một phân khúc hoàn toàn mới với kích thước “mi nhon” hơn các đàn anh rất nhiều mang tên iPhone 12 Mini.', '2021-05-13 21:35:00.000+07:00', 15, 3, 2, 2),
('Máy Tính Bảng HANET Smartlist 2016 ', 6690000, 100000, 1, 'Dung lượng pin cực lớn 12.000 mAh sẵn sàng cho mọi cuộc vui kéo dài 24h không ngừng nghỉ. Cùng với công nghệ sạc nhanh tiên tiến, thời gian sạc được rút ngắn chỉ còn 4-5h cho một lần sạc đầy', '2021-05-13 21:35:00', 25, 1, 1, 2),
('Máy Giặt/Sấy SAMSUNG 10.5Kg/7Kg WD10N64FR2W/SV', 11690000, 100000, 1, 'Máy giặt/sấy Samsung 10.5kg/7kg WD10N64FR2W/SV được tích hợp chức năng giặt và sấy giúp quần áo được làm sạch, khô nhanh hơn, nhất là vào những ngày mưa gió. Tuy nhiên, quần sẽ không khô hoàn toàn khi sấy, bạn nên phơi ngoài gió thêm.', '2021-05-13 21:36:00.000+07:00', 23, 2, 2, 2);

insert into product_image (image, product_id) values
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public/picture/product/product14032/dmcl_450_product_14032_1.png', 1),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product14032/product_14032_3.png', 1),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product14032/product_14032_4.png', 1),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product14032/product_14032_5.png', 1),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public/picture/product/product11438/dmcl_450_product_11438_1.png', 2),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product11438/product_11438_3.png', 2),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product11438/product_11438_4.png', 2),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product11438/product_11438_4.png', 2),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public/picture/product/product17538/dmcl_450_product_17538_1.png', 3),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public/picture/color/color1981/color_1981_1.png', 3),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//userupload/images/image001(127).jpg', 3),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//userupload/images/image003(90).jpg', 3),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public/picture/product/product11545/dmcl_450_product_11545_1.png', 4),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product11545/product_11545_3.png', 4),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product11545/product_11545_4.png', 4),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//userupload/images/may-tinh-bang-hanet-samrtlist-2016(2).png', 4),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public/picture/product/product17290/dmcl_450_product_17290_1.png', 5),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product17290/product_17290_3.png', 5),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product17290/product_17290_4.png', 5),
('https://cdn01.dienmaycholon.vn/filewebdmclnew/public//picture/product/product17290/product_17290_5.png', 5);


