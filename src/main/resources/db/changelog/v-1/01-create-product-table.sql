create table product (
id  bigserial not null,
create_at timestamp not null,
last_update_at timestamp not null,
name varchar(255) not null,
position_product varchar(255) not null,
price float8 not null,
type_product varchar(255) not null,
showcase_id uuid not null,
primary key (id)
)
GO
alter table product
    add constraint FKcmft4t1d66r24lq2e3ewd012c
        foreign key (showcase_id)
            references "hardware-store_db".public.showcase
GO