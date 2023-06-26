alter table contas add constraint FK_cliente_contas foreign key (idcliente) references cliente(id);

insert into cliente(nomecliente) values ('Nickolas');
insert into cliente(nomecliente) values ('Victor');
insert into cliente(nomecliente) values ('Duda');
insert into cliente(nomecliente) values ('Marcela');

insert into contas(valor, data, idcliente) values ('1982.25', '2022-05-31', 1);
insert into contas(valor, data, idcliente) values ('1323.50', '2023-06-27', 3);
insert into contas(valor, data, idcliente) values ('1982.25', '2022-05-31', 2);