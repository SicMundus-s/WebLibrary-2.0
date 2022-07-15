CREATE TABLE person
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" varchar(45)  NOT NULL,
    surname varchar(60) NOT NULL,
    middle_name varchar(60)  NOT NULL,
    birthday date NOT NULL
);

CREATE TABLE book
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title varchar(100) UNIQUE NOT NULL,
    author varchar(150) NOT NULL,
    year_book date NOT NULL,
    person_id int REFERENCES person(id) ON DELETE SET NULL
);