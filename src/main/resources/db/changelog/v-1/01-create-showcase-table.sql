create table showcase (
id uuid not null,
address varchar(255) not null,
create_at date not null,
last_update_at date not null,
name varchar(255) not null,
type varchar(255) not null,
primary key (id)
)
GO