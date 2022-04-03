create table if not exists MATCH
(
    ID                      bigserial             not null,
    DESCRIPTION             varchar(50)           not null,
    MATCH_DATE              timestamp,
    MATCH_TIME              varchar(5),
    TEAM_A                  varchar(20)           not null,
    TEAM_b                  varchar(20)           not null,
    SPORT                   integer               not null,
    constraint MATCH_PK
        primary key (ID)
);

create index if not exists INDX_MATCH_DESCRIPTION
    on MATCH (DESCRIPTION);

create table if not exists MATCH_ODD
(
    ID                 bigserial  not null,
    SPECIFIER          varchar(1) not null,
    ODD                numeric(3,2) not null,
    MATCH_ID           bigint,
    constraint MATCH_ODD_PK
        primary key (ID),
    constraint MATCH_ID_FK
        foreign key (MATCH_ID) references MATCH
);

create index if not exists INDX_FK_MATCH_ODD_MATCH_ID
    on MATCH_ODD (MATCH_ID);

