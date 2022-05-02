drop table if exists clients cascade
drop table if exists investment_stock cascade
drop table if exists investments cascade
drop table if exists stocks cascade
drop table if exists users cascade
create table clients (id  bigserial not null, created_at timestamp, email varchar(255), name varchar(255), updated_at timestamp, primary key (id))
create table investment_stock (id  bigserial not null, created_at timestamp, percent float8 not null, updated_at timestamp, investment_id int8 not null, stock_id int8 not null, primary key (id))
create table investments (id  bigserial not null, amount float8 not null, created_at timestamp, indicador_carencia boolean not null, name varchar(255), updated_at timestamp, client_id int8 not null, primary key (id))
create table stocks (id  bigserial not null, created_at timestamp, name varchar(255), updated_at timestamp, primary key (id))
create table users (id  bigserial not null, created_at timestamp, email varchar(255), name varchar(255), password varchar(255), updated_at timestamp, username varchar(255), primary key (id))
alter table if exists clients add constraint UK_srv16ica2c1csub334bxjjb59 unique (email)
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table if exists users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)
alter table if exists investment_stock add constraint FKlybcn8lavdx5widjaut30peou foreign key (investment_id) references investments
alter table if exists investment_stock add constraint FKl3d3xq9egt7waet5m6rh4y3in foreign key (stock_id) references stocks
alter table if exists investments add constraint FKrst49wjxgj0suyv8ceesdv7w1 foreign key (client_id) references clients