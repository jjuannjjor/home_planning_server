CREATE DATABASE Home_planning;

-- Usar la base de datos Home_basic
USE Home_planning;

-- Crear la tabla user
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enable BOOLEAN NOT NULL DEFAULT TRUE
);

-- Crear la tabla task
CREATE TABLE task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL
);

-- Crear la tabla home
CREATE TABLE home (
    id INT AUTO_INCREMENT PRIMARY KEY,
    house_code VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL
);

-- Crear la tabla intermedia user_home para la relación user-home
CREATE TABLE user_home (
    user_id INT,
    home_id INT
);
CREATE TABLE user_home_task (
    user_id INT,
    home_id INT,
    task_id INT,
    terminado VARCHAR(50) NOT NULL
);

INSERT INTO task (description)
VALUES
("Preparar desayuno"),
("Preparar comida"),
("Preparar cena"),
("Comprar alimentos"),
("Poner lavavajillas"),
("Poner y quitar la mesa"),
("Tirar la basura"),
("Barrer y fregar suelo"),
("Ordenar zonas comunes"),
("Poner lavadora y colgar"),
("Planchar"),
("Doblar y colocar ropa en armarios"),
("Cuidar de plantas y/o mascotas");


