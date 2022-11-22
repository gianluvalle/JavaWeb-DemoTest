CREATE TABLE athletes
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name          VARCHAR(255)                             NOT NULL,
    last_name     VARCHAR(255)                             NOT NULL,
    age           INTEGER,
    height        DOUBLE PRECISION,
    discipline_id INTEGER,
    CONSTRAINT pk_athletes PRIMARY KEY (id)
);

CREATE TABLE disciplines
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_disciplines PRIMARY KEY (id)
);

ALTER TABLE athletes
    ADD CONSTRAINT FK_ATHLETES_ON_DISCIPLINE FOREIGN KEY (discipline_id) REFERENCES disciplines (id);

INSERT INTO disciplines (name)
VALUES ('Pallavolo');

INSERT INTO athletes (name, last_name, age, height, discipline_id)
VALUES ('Edoardo', 'Carradori', 25, 187, 1);