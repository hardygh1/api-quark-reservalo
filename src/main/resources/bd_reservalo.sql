create database bd_reservalo

use bd_reservalo
drop database bd_reservalo

CREATE TABLE rol (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rol_id BIGINT,
    CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
);

CREATE TABLE servicio (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100),
    precio_base DECIMAL(10,2),
    profesional_id BIGINT,
    CONSTRAINT fk_pro FOREIGN KEY (profesional_id) REFERENCES usuario(id)
);

CREATE TABLE reserva (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT,
    estado VARCHAR(20),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES usuario(id)
);

CREATE TABLE reserva_detalle (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    reserva_id BIGINT,
    servicio_id BIGINT,
    fecha_hora_inicio DATETIME,
    fecha_hora_fin DATETIME,
    precio_final DECIMAL(10,2),
    CONSTRAINT fk_reserva FOREIGN KEY (reserva_id) REFERENCES reserva(id),
    CONSTRAINT fk_servicio FOREIGN KEY (servicio_id) REFERENCES servicio(id)
);

-- Data de Prueba

INSERT INTO rol (nombre) VALUES ('ADMIN');
INSERT INTO rol (nombre) VALUES ('PROFESIONAL');
INSERT INTO rol (nombre) VALUES ('CLIENTE');


-- Profesionales
INSERT INTO usuario (username, email, rol_id) VALUES ('dr_smith', 'smith@medico.com', 2);
INSERT INTO usuario (username, email, rol_id) VALUES ('ana_estetica', 'ana@beauty.com', 2);

-- Clientes
INSERT INTO usuario (username, email, rol_id) VALUES ('juan_perez', 'juan.perez@email.com', 3);
INSERT INTO usuario (username, email, rol_id) VALUES ('maria_garcia', 'm.garcia@email.com', 3);

-- Servicios del Dr. Smith (ID 1)
INSERT INTO servicio (tipo, precio_base, profesional_id) VALUES ('Consulta General', 50.00, 1);
INSERT INTO servicio (tipo, precio_base, profesional_id) VALUES ('Chequeo Anual', 120.00, 1);

-- Servicios de Ana (ID 2)
INSERT INTO servicio (tipo, precio_base, profesional_id) VALUES ('Limpieza Facial', 40.00, 2);
INSERT INTO servicio (tipo, precio_base, profesional_id) VALUES ('Masaje Relajante', 60.00, 2);

-- Reserva de Juan Perez (ID 3)
INSERT INTO reserva (cliente_id, estado) VALUES (3, 'PENDIENTE');

-- Reserva de Maria Garcia (ID 4)
INSERT INTO reserva (cliente_id, estado) VALUES (4, 'CONFIRMADA');

-- Detalles para la reserva de Juan (Reserva ID 1) - Consulta General
INSERT INTO reserva_detalle (reserva_id, servicio_id, fecha_hora_inicio, fecha_hora_fin, precio_final)
VALUES (1, 1, '2026-02-10 09:00:00', '2026-02-10 10:00:00', 50.00);

-- Detalles para la reserva de Maria (Reserva ID 2) - Masaje y Facial
INSERT INTO reserva_detalle (reserva_id, servicio_id, fecha_hora_inicio, fecha_hora_fin, precio_final)
VALUES (2, 3, '2026-02-15 16:00:00', '2026-02-15 17:00:00', 40.00);

INSERT INTO reserva_detalle (reserva_id, servicio_id, fecha_hora_inicio, fecha_hora_fin, precio_final)
VALUES (2, 4, '2026-02-15 17:00:00', '2026-02-15 18:00:00', 60.00);