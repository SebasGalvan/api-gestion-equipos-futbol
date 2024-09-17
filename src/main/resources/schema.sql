CREATE TABLE "users" (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         fullName VARCHAR(80),
                         username VARCHAR(50),
                         password VARCHAR(100),
                         createdAt varchar(20),
                         updated_at varchar(20)
);

CREATE TABLE equipo (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nombre VARCHAR(50) UNIQUE,
                         liga VARCHAR(50),
                         pais VARCHAR(50)
);
