CREATE SCHEMA IF NOT EXISTS TALLER;

USE TALLER;

CREATE TABLE IF NOT EXISTS TALLER.CLIENTE(
	NIT VARCHAR(13) NOT NULL,
	nombre VARCHAR(45) NOT NULL DEFAULT 'HOLA',
	apellido VARCHAR(45) NOT NULL,
	telefono VARCHAR(8),
	PRIMARY KEY(NIT)
);

CREATE TABLE IF NOT EXISTS TALLER.VEHICULO(
	matricula VARCHAR(10) NOT NULL,
	marca VARCHAR(45) NOT NULL,
	modelo INT NOT NULL,
	CLIENTE_NIT VARCHAR(13) NOT NULL,
	PRIMARY KEY(matricula),
	FOREIGN KEY(CLIENTE_NIT) REFERENCES CLIENTE(NIT)
);

CREATE TABLE IF NOT EXISTS TALLER.MECANICO(
	DPI VARCHAR(13),
	nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45) NOT NULL,
	telefono VARCHAR(8),
	PRIMARY KEY(DPI)
);

CREATE TABLE IF NOT EXISTS TALLER.REPARACION(
	ID INT NOT NULL AUTO_INCREMENT,
	descripcion VARCHAR(200) NOT NULL,
	fecha DATE NOT NULL,
	costo DOUBLE NOT NULL,
	matricula_vehiculo VARCHAR(10) NOT NULL,
	DPI_mecanico VARCHAR(13),
	PRIMARY KEY(ID),
	FOREIGN KEY(matricula_vehiculo) REFERENCES VEHICULO(matricula),
	FOREIGN KEY(DPI_mecanico) REFERENCES MECANICO(DPI)
);

INSERT INTO CLIENTE (NIT,nombre,apellido) VALUES ('1111','angel','racancoj');
INSERT INTO CLIENTE VALUES ('2222','orlando','garcia','12236547');
INSERT INTO CLIENTE VALUES ('3333','Daniel','Albizurez','48596215');
INSERT INTO CLIENTE VALUES ('4444','Willy','Ardiano','32165487');
INSERT INTO CLIENTE VALUES ('5555','Jose','Rodas','45698725');
INSERT INTO CLIENTE VALUES ('6666','Fernando','Ocaña','25836921');
INSERT INTO CLIENTE VALUES ('7777','Dulce','Monzon','15926348');

INSERT INTO VEHICULO VALUES ('P 100SDF','Toyota',2018,'1111');
INSERT INTO VEHICULO VALUES ('P 101ABC','Ferrari',2019,'1111');
INSERT INTO VEHICULO VALUES ('P 200BDE','Nissan',2011,'2222');
INSERT INTO VEHICULO VALUES ('P 201ANF','Chevrolet',2020,'2222');
INSERT INTO VEHICULO VALUES ('P 310YHR','Honda',2010,'3333');
INSERT INTO VEHICULO VALUES ('P 320NGH','Suzuki',2011,'3333');
INSERT INTO VEHICULO VALUES ('P 331UIU','BMW',2015,'3333');
INSERT INTO VEHICULO VALUES ('P 532NGS','Toyota',2018,'5555');
INSERT INTO VEHICULO VALUES ('P 505RJX','Nissan',2020,'5555');
INSERT INTO VEHICULO VALUES ('P 102AAA','Toyota',2011,'1111');

INSERT INTO MECANICO VALUES ('10100901','Luis','Guzman','25836921');
INSERT INTO MECANICO VALUES ('11000901','Karla','Matias','45659874');
INSERT INTO MECANICO VALUES ('45810901','Sergio','Hernandez','95149594');
INSERT INTO MECANICO VALUES ('25250901','Antonio','Toyom','35265428');
INSERT INTO MECANICO VALUES ('35210901','Jonathan','Valiente','96317854');
INSERT INTO MECANICO VALUES ('35270901','Giovani','Alvarado','96317854');
INSERT INTO MECANICO VALUES ('45810901','Sergio','Hernandez','95149594');

INSERT INTO REPARACION (descripcion,fecha,costo,matricula_vehiculo,DPI_mecanico) VALUES ('Descripcion del trabajo 1','2020-8-5',100,'P 100SDF','11000901');
INSERT INTO REPARACION (descripcion,fecha,costo,matricula_vehiculo,DPI_mecanico) VALUES ('Descripcion del trabajo 2','2020-8-6',200.25,'P 201ANF','11000901');
INSERT INTO REPARACION (descripcion,fecha,costo,matricula_vehiculo,DPI_mecanico) VALUES ('Descripcion del trabajo 3','2020/8/10',500,'P 331UIU','35210901');
INSERT INTO REPARACION (descripcion,fecha,costo,matricula_vehiculo,DPI_mecanico) VALUES ('Descripcion del trabajo 4','2020/8/11',752.10,'P 505RJX','25250901');
