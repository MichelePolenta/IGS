create table contributor_aut
(
    persona integer
        constraint persona_fk
            references persona
);

alter table contributor_aut
    owner to postgres;

