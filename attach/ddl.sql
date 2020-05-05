drop table if exists im_user;
create table im_user(
user_id BIGINT PRIMARY key ,
user_name VARCHAR(50),
password VARCHAR(50),
nick_name VARCHAR(50),
avatar_src MEDIUMTEXT,
phone_num VARCHAR(25),
mail VARCHAR(50),
create_time date
);
drop table if exists im_friend;
create table im_friend(
seq_id BIGINT PRIMARY key,
friend_id BIGINT  ,
friend_name VARCHAR(50),
remark_name VARCHAR(50),
user_id BIGINT,
create_time date
);
drop table if exists im_friend_rel;
create table im_friend_rel(
friend_rel BIGINT PRIMARY key ,
group_id BIGINT,
group_name VARCHAR(50),
friend_id BIGINT  ,
friend_name VARCHAR(50),
parent_group_id BIGINT,
user_id BIGINT
);


