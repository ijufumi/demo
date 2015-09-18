drop table if exists customer;
create table if not exists customer (
    id int primary key auto_increment,
    name varchar(50)
);