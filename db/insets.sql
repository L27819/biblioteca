INSERT INTO Usuarios (nombre_usuario, nombre, apellidos, edad, email, telefono, contrasena, tipo_usuario, activo,
                      fecha_registro, imagen)
VALUES ('Lulu', 'Lucía', 'Felipe Ruiz', 26, 'admin@biblioteca.com', '665122456', SHA1('admin'), 'admin', TRUE,
        CURDATE(), 'admin.jpg'),
       ('User1', 'Mario', 'García Lopez', 29, 'mario@lectores.com', '617554321', SHA1('user'), 'user', TRUE, CURDATE(),
        'user.jpg');

INSERT INTO Autores (nombre, apellidos, nacionalidad, fecha_nacimiento, fecha_defuncion, activo, numero_obras, imagen)
VALUES ('Rebecca', 'Yarros', 'Estados Unidos', '1980-06-01', NULL, TRUE, 10, 'yarros.jpg'),
       ('Jane', 'Austen', 'Inglaterra', '1775-12-16', '1817-07-18', FALSE, 6, 'austen.jpg'),
       ('Freida', 'McFadden', 'Estados Unidos', '1977-03-10', NULL, TRUE, 15, 'mcfadden.jpg'),
       ('Miguel', 'de Cervantes', 'España', '1547-09-29', '1616-04-22', FALSE, 10, 'cervantes.jpg'),
       ('La Vecina', 'Rubia', 'España', '1990-01-01', NULL, TRUE, 3, 'vecinarubia.jpg'),
       ('María', 'Dueñas', 'España', '1964-10-01', NULL, TRUE, 8, 'duenas.jpg'),
       ('Leo', 'Tolstói', 'Rusia', '1828-09-09', '1910-11-20', FALSE, 12, 'tolstoi.jpg'),
       ('Marian', 'Rojas Estapé', 'España', '1980-02-15', NULL, TRUE, 4, 'rojasestape.jpg'),
       ('Julia', 'Navarro', 'España', '1953-10-20', NULL, TRUE, 10, 'navarro.jpg'),
       ('Sally', 'Rippin', 'Australia', '1971-12-05', NULL, TRUE, 20, 'rippin.jpg');

INSERT INTO Libros (isbn, titulo, editorial, fecha_publicacion, genero, descripcion, paginas, precio, disponible,
                    imagen, id_autor)
VALUES ('9788408279990', 'Alas de sangre', 'Editorial Planeta', '2023-06-01', 'Fantasía',
        'Primer libro de la saga Empíreo', 736, 21.95, TRUE, 'alasdesangre.jpg', 1),
       ('9788491294283', 'La asistenta', 'Suma', '2023-02-15', 'Thriller', 'Una mujer acepta un trabajo peligroso...',
        344, 18.90, TRUE, 'asistenta.jpg', 3),
       ('9788448041939', 'Mi querida Lucia', 'Libros Cúpula', '2024-01-10', 'Thriller',
        'Una historia de amor, amistad y traición.', 448, 19.50, TRUE, 'mqlucia.jpg', 5),
       ('9788408299592', 'Por si un día volvemos', 'Editorial Planeta', '2025-03-25', 'Novela contemporánea',
        'Reflexiones sobre la identidad y el regreso.', 544, 22.50, TRUE, 'psudv.jpg', 6),
       ('9788467062212', 'Encuentra tu persona vitamina', 'Espasa', '2021-09-20', 'Crecimiento personal',
        'Guía emocional sobre relaciones y bienestar.', 328, 17.95, TRUE, 'vitamina.jpg', 8),
       ('9788401027970', 'El niño que perdió la guerra', 'Plaza y Janes', '2024-05-01', 'Narrativa española',
        'La vida de un niño marcada por la guerra.', 640, 23.99, TRUE, 'npguerra.jpg', 9),
       ('9788419746030', 'Escuela de monstruos 18', 'MONTENA', '2025-02-15', 'Infantil',
        'Aventuras en una escuela muy poco común.', 48, 8.95, TRUE, 'monstruos18.jpg', 10),
       ('9788491050292', 'Orgullo y prejuicio', 'Penguin Clásicos', '1813-01-28', 'Romántica',
        'Una de las obras más queridas de Jane Austen.', 432, 12.99, TRUE, 'orgullo.jpg', 2),
       ('9788497406832', 'Don Quijote de la Mancha', 'Anaya', '1605-01-16', 'Novela',
        'La aventura inmortal de un hidalgo soñador.', 1024, 15.95, TRUE, 'quijote.jpg', 4),
       ('9780140447934', 'Guerra y Paz', 'Penguin Clásicos', '1869-01-01', 'Histórica',
        'Épico retrato de la sociedad rusa durante la invasión napoleónica.', 1225, 14.95, TRUE, 'guerraypaz.jpg', 7);