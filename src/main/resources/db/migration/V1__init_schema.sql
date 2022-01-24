create table if not exists chat {
    id                      bigint                  not null,
    name                    varchar(255),
    primary key (id)
};

create table if not exists user {
    id                      bigint                  not null,
    birth_date              timestamp               without time zone,
    first_name              varchar(255),
    last_name               varchar(255),
    password                varchar(255),
    primary key (id)
};

create table if not exists chat_message {
    id                      bigint                  not null,
    sender_id               bigint                  not null,
    chat_id                 bigint                  not null,
    content                 text                    not null,
    send_date_time          timestamp               with time zone NOT NULL,
    status                  varchar(255),
    type                    varchar(255),
    primary key (id)
};

create table if not exists user_chats {
    id                      bigint                  not null GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    user_id                 bigint                  not null,
    chat_id                 bigint                  not null
};

create sequence chat_seq
    start with 1
    increment by 1
    no minvalue
    no maxvalue
    cache 1;

ALTER sequence chat_seq owned by chat.id;

create sequence user_seq
    start with 1
    increment by 1
    no minvalue
    no maxvalue
    cache 1;

ALTER sequence user_seq owned by user.id;

create sequence chat_message_seq
    start with 1
    increment by 1
    no minvalue
    no maxvalue
    cache 1;

ALTER sequence chat_message_seq owned by chat_message.id;




