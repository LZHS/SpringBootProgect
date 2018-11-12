# SpringBootProject



### 数据库建表

```


create table order_detail
(
  detail_id        varchar(32)                         not null
    primary key,
  order_id         varchar(32)                         not null,
  product_id       varchar(32)                         not null,
  product_name     varchar(64)                         not null
  comment '商品名称',
  product_price    decimal(8, 2)                       not null
  comment '单价',
  product_icon     varchar(512)                        null
  comment '小图',
  product_quantity int                                 not null
  comment '商品数量',
  create_time      timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time      timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '修改时间',
  constraint idx_order_id
  unique (order_id)
)
  comment '订单详情表';


create table order_master
(
  order_id      varchar(32)                         not null
    primary key,
  buyer_name    varchar(32)                         not null
  comment '买家姓名',
  buyer_iphone  varchar(32)                         not null
  comment '买家电话',
  buyer_address varchar(128)                        not null
  comment '买家地址',
  buyer_openid  varchar(32)                         not null
  comment '买家微信',
  order_amount  decimal(8, 2)                       not null
  comment '订单总金额',
  order_status  tinyint(3) default '0'              not null
  comment '订单状态，0代表默认新订单',
  pay_stayus    tinyint(3) default '0'              not null
  comment '支付状态 0代表默认值未支付',
  create_time   timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time   timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '修改时间',
  constraint idx_buyer_openid
  unique (buyer_openid)
)
  comment '订单主表';

create table product_category
(
  category_id   int auto_increment
    primary key,
  category_name varchar(64)                         not null
  comment '类目名称',
  category_type int                                 not null
  comment '类目编号',
  create_time   timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time   timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '修改时间',
  constraint uqe_category_type
  unique (category_type)
)
  comment '类目表';


create table product_info
(
  product_id          varchar(32)                         not null
    primary key,
  product_name        varchar(64)                         not null
  comment '商品名称',
  product_price       decimal(8, 2)                       not null
  comment '单价',
  product_stock       int                                 not null
  comment '库存',
  product_description varchar(64)                         null
  comment '描述',
  product_status      tinyint(3) default '0'              null
  comment '商品状态,0正常1下架',
  product_icon        varchar(512)                        null
  comment '小图',
  category_type       int                                 not null
  comment '类目',
  create_time         timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time         timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '修改时间'
)
  comment '商品表';



```


## 插入类目数据

```


INSERT INTO `product_category` (`category_name`,`category_type`) VALUES("满减专区",0);
INSERT INTO `product_category` (`category_name`,`category_type`) VALUES("套餐米线",1);
INSERT INTO `product_category` (`category_name`,`category_type`) VALUES("加肉加菜营养",2);
INSERT INTO `product_category` (`category_name`,`category_type`) VALUES("口味必选",3);
INSERT INTO `product_category` (`category_name`,`category_type`) VALUES("清凉饮品",4);


```



## 插入商品

```



INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(0,'a1b2c3d4e5f6g7','招牌花甲米线',30,99,'招牌花甲米线，好吃极了',0,'https://p1.meituan.net/210.0/wmproduct/19d3a894f826ecbf24d0e4b4341c0d4e857902.jpg');

INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(0,'67d346f3370ff5','香菇豆腐米线',22,99,'香菇豆腐米线，好吃极了',0,'https://p0.meituan.net/210.0/wmproduct/67d346f3370ff50fbd3c5a9d7c1a4c57868077.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(0,'60d922ac0a3783','【套餐】开心豪华全家福',45,99,'【套餐】开心豪华全家福，好吃极了',0,'https://p1.meituan.net/210.0/wmproduct/60d922ac0a3783d46805cc4c96d8b058862710.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(0,'266a79ca1bbaa2','加火腿肠',6,99,'招加火腿肠，好吃极了',0,'https://p1.meituan.net/210.0/wmproduct/266a79ca1bbaa27c1746bc90487078bd221314.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(0,'0ed83cacffc8f','加鱼丸',8,99,'加鱼丸，好吃极了',0,'https://p0.meituan.net/210.0/xianfu/0ed83cacffc8f5fb4d6c6cfaf7be8e1f519007.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(0,'8bb425db3b9ba','加巴沙鱼',10,99,'加巴沙鱼，好吃极了',0,'https://p1.meituan.net/210.0/wmproduct/a8bb425db3b9baa8b62b3b1b1aab9911291992.jpg');


INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(1,'19d3a894f826ec','【招牌花甲米线套餐】',34,99,'花甲+嫩豆腐+海带+火腿肠+肥牛',0,'https://p1.meituan.net/210.0/wmproduct/19d3a894f826ecbf24d0e4b4341c0d4e857902.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(2,'e9e1bc6eb0b289','加嫩豆腐',2.99,99,'加嫩豆腐，好吃极了',0,'https://p1.meituan.net/210.0/wmproduct/e9e1bc6eb0b289f0a78189f92182fa8d476125.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(2,'f94806bc4db053','加花甲',4.99,99,'加花甲，好吃极了',0,'https://p1.meituan.net/210.0/wmproduct/a235f94806bc4db053b316e8fb6674d3528091.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(2,'6b1ad6389ab455','加鲜虾',5.99,99,'加鲜虾，好吃极了',0,'https://p0.meituan.net/210.0/wmproduct/9dfbb0ad2096b1ad6389ab455b76f4c2527840.jpg');
INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(2,'f9f2c372c3858a','加米线',1.99,99,'加米线，分量够，吃得饱',0,'https://p0.meituan.net/210.0/wmproduct/04a1ef9f2c372c385871f98417414ecf568171.jpg');

INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(4,'5d980a07847f1b','可口可乐',3,99,'可口可乐',0,'https://p0.meituan.net/210.0/xianfu/65d980a07847f1b104352c8431dc71ba182272.jpg');

INSERT INTO `product_info`( `category_type`,`product_id`,`product_name` ,`product_price`,`product_stock` ,`product_description` , `product_status`, `product_icon` )VALUES
			(4,'478145e8aabbcc','透心凉雪碧',3,99,'透心凉雪碧',0,'https://p0.meituan.net/210.0/xianfu/41d572cc954402d6c478145e8cddda52237568.jpg');


SELECT * FROM product_info ORDER BY category_type; 



```
