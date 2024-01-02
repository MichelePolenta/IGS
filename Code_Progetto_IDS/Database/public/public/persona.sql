create table persona
(
    id_persona    serial
        primary key,
    nome          text not null,
    cognome       text not null,
    mail          text not null
        unique,
    password      text not null,
    ruolo         text not null,
    datadinascita date not null
);

alter table persona
    owner to postgres;

