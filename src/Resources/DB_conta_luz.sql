Sobre os construtores
Sobre o tipo DAte
sobre a criacao do objeto com o id
Sobre o teste
Problema no poste DAO tive que mudar para * no select

create table tipo_fase
(
    id SERIAL primary key,
    descricao varchar(45) not null unique
);

create table tipo_pessoa
(
    id SERIAL primary key,
    descricao varchar(45) not null unique
);

create table poste
(
    id SERIAL primary key,
    codigo varchar(45) not null unique,
    latitude varchar(45),
    longitude varchar(45),
    observacao varchar(255)
);

create table rota
(
    id SERIAL primary key,
    descricao varchar(255) not null unique
);

create table tarefa_rota
(
    id SERIAL primary key,
    observacao varchar(45),
    data_inicio TIMESTAMP not null,
    data_fim TIMESTAMP not null,
    tarefa_rotacol varchar(45) not null,
    id_rota int references rota (id)
);

create table medidor
(
    id SERIAL primary key,
    descricao varchar(255) not null unique,
    id_poste int references poste (id),
    id_rota int references rota (id)
);


create table classe
(
    id SERIAL primary key,
    descricao varchar(255) not null unique,
    id_tipo_fase int references tipo_fase (id)
);

create table tarifa
(
    id SERIAL primary key,
    taxa varchar(45) not null,
    lei varchar(45) not null.
    data_inicio TIMESTAMP not null,
    data_fim TIMESTAMP not null,
    id_classe int references classe (id)	
);

create table pessoa
(
    id SERIAL primary key,
    nome varchar(255) not null,
    cpf varchar(11) unique,
    cnpj varchar(14) unique,
    id_tipo_pessoa int references tipo_pessoa (id)
);

create table cliente
(
    id SERIAL primary key,
    numero_documento varchar(255) not null unique,
    numero_cliente varchar(255) not null unique,
    id_pessoa int references pessoa (id)
);

create table funcionario
(
    id 	SERIAL primary key,
    codigo_funcional varchar(45) not null unique,
    id_pessoa int references pessoa (id)
);

create table time_rota
(
    id SERIAL primary key,
    id_funcionario int references funcionario (id),
    id_tarefa_rota int references tarefa_rota (id)
);

create table medicao
(
    id SERIAL primary key,
    mes varchar(45) not null,
    ano varchar(45) not null,
    data_medicao TIMESTAMP not null,
    valor varchar(45) not null,
    id_medidor int references medidor (id),
    id_time_rota int references time_rota (id)
);

CREATE TABLE cobranca(
    id SERIAL PRIMARY KEY,
    mes_referencia varchar(45),
    ano_referencia varchar(45),
    id_tarifa INT REFERENCES  tarifa (id),
    id_medicao INT REFERENCES medicao (id)
);

create table contrato
(
    id SERIAL primary key,
    data_inicio TIMESTAMP not null,
    data_criacao TIMESTAMP not null,
    id_cliente int references cliente (id),
    id_classe int references classe (id),
    id_medidor int references medidor (id)
);
