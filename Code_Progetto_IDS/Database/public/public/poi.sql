create table poi
(
    id_poi      serial
        primary key,
    nome        text             not null,
    descrizione text             not null,
    latitudine  double precision not null,
    longitudine double precision not null,
    tipo        boolean          not null
);

alter table poi
    owner to postgres;

