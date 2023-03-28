CREATE TABLE usuarios(
    id SERIAL PRIMARY KEY,
    login varchar(255) UNIQUE,
    senha varchar(255)
);