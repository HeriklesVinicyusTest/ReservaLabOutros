/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Hilton
 * Created: 15/09/2017
 */


create table pessoa(
id    int auto_increment, 
nome  varchar(70), 
email varchar(60),
senha varchar(30), 
tipo  varchar(9), 
primary key(id),
unique(email));

/*Unica cois que mudei foi essa tabela*/
create table reserva(
id            int auto_increment, 
idPessoa      int, 
dataDaReserva varchar(30) not null, /*Essa coluna aqui!*/
turno         varchar(30), 
horario       varchar(30),
laboratorio   varchar(30),
descricao     varchar(30), 
primary key (id), 
foreign key (idPessoa)
references pessoa(id));

create table horario(
id int auto_increment,
hora varchar(6),
primary key(id));

insert into horario(hora)
value('07:00');

insert into horario(hora)
value('07:10');

insert into horario(hora)
value('07:20');

insert into horario(hora)
value('07:30');

insert into horario(hora)
value('07:40');

insert into horario(hora)
value('07:50');

insert into horario(hora)
value('08:00');

insert into horario(hora)
value('08:10');

insert into horario(hora)
value('08:20');

insert into horario(hora)
value('08:30');

insert into horario(hora)
value('08:40');

insert into horario(hora)
value('08:50');

insert into horario(hora)
value('09:00');

insert into horario(hora)
value('09:10');

insert into horario(hora)
value('09:20');

insert into horario(hora)
value('09:30');

insert into horario(hora)
value('09:40');

insert into horario(hora)
value('09:50');

insert into horario(hora)
value('10:00');

insert into horario(hora)
value('10:10');

insert into horario(hora)
value('10:20');

insert into horario(hora)
value('10:30');

insert into horario(hora)
value('10:40');

insert into horario(hora)
value('10:50');

insert into horario(hora)
value('11:00');

insert into horario(hora)
value('11:10');

insert into horario(hora)
value('11:20');

insert into horario(hora)
value('11:30');

insert into horario(hora)
value('11:40');

insert into horario(hora)
value('11:50');

insert into horario(hora)
value('12:00');

insert into horario(hora)
value('12:10');

insert into horario(hora)
value('12:20');

insert into horario(hora)
value('12:30');

insert into horario(hora)
value('13:00');

insert into horario(hora)
value('13:10');

insert into horario(hora)
value('13:20');

insert into horario(hora)
value('13:30');

insert into horario(hora)
value('13:40');

insert into horario(hora)
value('13:50');

insert into horario(hora)
value('14:00');

insert into horario(hora)
value('14:10');

insert into horario(hora)
value('14:20');

insert into horario(hora)
value('14:30');

insert into horario(hora)
value('14:40');

insert into horario(hora)
value('14:50');

insert into horario(hora)
value('15:00');

insert into horario(hora)
value('15:10');

insert into horario(hora)
value('15:20');

insert into horario(hora)
value('15:30');

insert into horario(hora)
value('15:40');

insert into horario(hora)
value('15:50');

insert into horario(hora)
value('16:00');

insert into horario(hora)
value('16:10');

insert into horario(hora)
value('16:20');

insert into horario(hora)
value('16:30');

insert into horario(hora)
value('16:40');

insert into horario(hora)
value('16:50');

insert into horario(hora)
value('17:00');

insert into horario(hora)
value('17:10');

insert into horario(hora)
value('17:20');

insert into horario(hora)
value('17:30');

insert into horario(hora)
value('19:00');

insert into horario(hora)
value('19:10');

insert into horario(hora)
value('19:20');

insert into horario(hora)
value('19:30');

insert into horario(hora)
value('19:40');

insert into horario(hora)
value('19:50');

insert into horario(hora)
value('20:00');

insert into horario(hora)
value('20:10');

insert into horario(hora)
value('20:20');

insert into horario(hora)
value('20:30');

insert into horario(hora)
value('20:40');

insert into horario(hora)
value('20:50');

insert into horario(hora)
value('21:00');

insert into horario(hora)
value('21:10');

insert into horario(hora)
value('21:20');

insert into horario(hora)
value('21:30');

insert into horario(hora)
value('21:40');

insert into horario(hora)
value('21:50');

insert into horario(hora)
value('22:00');