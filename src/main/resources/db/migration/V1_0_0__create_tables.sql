create table TB_STUDENT(
    id bigint primary key auto_increment,
    name varchar(200),
    rm varchar(100)
);

create table TB_TRANSACTION(
    id bigint primary key auto_increment,
    student_id bigint,
    price numeric(38,2),
    created_date timestamp not null,
    foreign key (student_id) references TB_STUDENT
);