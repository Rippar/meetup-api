DROP TABLE IF EXISTS meetup;

CREATE TABLE meetup
(
    id          bigserial                      NOT NULL,
    topic       character varying(50)          NOT NULL,
    description character varying(100)         NOT NULL,
    organizer   character varying(50)          NOT NULL,
    time        timestamp(6) without time zone NOT NULL,
    location    character varying(50)          NOT NULL,
    PRIMARY KEY (id)
);