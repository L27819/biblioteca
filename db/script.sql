CREATE TABLE Usuarios (
                          id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                          nombre_usuario VARCHAR(30) NOT NULL,
                          nombre VARCHAR(30),
                          apellidos VARCHAR(50),
                          edad INT,
                          email VARCHAR(50) NOT NULL,
                          telefono VARCHAR(15),
                          contrasena VARCHAR(100) NOT NULL,
                          tipo_usuario ENUM('admin', 'user') DEFAULT 'user',
                          activo BOOLEAN DEFAULT TRUE,
                          fecha_registro DATE NOT NULL,
                          imagen VARCHAR(255)
);

CREATE TABLE Autores (
                         id_autor INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(30) NOT NULL,
                         apellidos VARCHAR(50),
                         nacionalidad VARCHAR(30),
                         fecha_nacimiento DATE,
                         fecha_defuncion DATE,
                         activo BOOLEAN DEFAULT TRUE,
                         numero_obras INT,
                         imagen VARCHAR(255)
);

CREATE TABLE Libros (
                        id_libro INT AUTO_INCREMENT PRIMARY KEY,
                        isbn VARCHAR(20) NOT NULL,
                        titulo VARCHAR(100) NOT NULL,
                        editorial VARCHAR(50),
                        fecha_publicacion DATE,
                        genero VARCHAR(30),
                        descripcion VARCHAR(500),
                        paginas INT,
                        precio DECIMAL(4,2),
                        disponible BOOLEAN DEFAULT TRUE,
                        imagen VARCHAR(255),
                        id_autor INT,
                        FOREIGN KEY (id_autor) REFERENCES Autores(id_autor)
);

CREATE TABLE Prestamos (
                           id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
                           id_usuario INT,
                           id_libro INT,
                           fecha_inicio DATE NOT NULL,
                           fecha_fin DATE,
                           estado ENUM('pendiente', 'devuelto', 'atrasado') NOT NULL,
                           FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
                           FOREIGN KEY (id_libro) REFERENCES Libros(id_libro)
);

CREATE TABLE Resenas (
                         id_resena INT AUTO_INCREMENT PRIMARY KEY,
                         id_usuario INT,
                         id_libro INT,
                         comentario VARCHAR(500),
                         puntuacion INT CHECK (puntuacion BETWEEN 1 AND 10),
                         me_gusta BOOLEAN DEFAULT FALSE,
                         fecha_resena DATE,
                         FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
                         FOREIGN KEY (id_libro) REFERENCES Libros(id_libro)
);