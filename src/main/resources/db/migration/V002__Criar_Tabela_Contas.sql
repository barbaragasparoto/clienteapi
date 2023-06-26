create table contas(
    id int not null primary key auto_increment,
    data date,
    valor decimal(12, 2),
    idcliente int not null
);