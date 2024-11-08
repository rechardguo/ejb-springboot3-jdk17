CREATE USER 'exporter'@'*' IDENTIFIED BY 'exporter' WITH MAX_USER_CONNECTIONS 3;
GRANT PROCESS, REPLICATION CLIENT, SELECT ON *.* TO 'exporter'@'*';

use appdb;

create table Employees (
  id int not null auto_increment,
  name varchar(255) not null,
  age int not null,
  primary key (id)
);

insert into Employees (name, age) values ('John Doe', 30);
insert into Employees (name, age) values ('Jack', 28);