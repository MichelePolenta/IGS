create table richieste_accreditamento
(
    id_richiesta    serial
        primary key,
    nome            text not null,
    cognome         text not null,
    mail            text not null,
    password        text not null,
    ruolo_richiesto text not null,
    data_di_nascita date not null,
    messaggio       text not null,
    persona         integer
        constraint persona_richiesta_fk
            references persona
);

alter table richieste_accreditamento
    owner to postgres;

