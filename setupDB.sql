CREATE DATABASE IF NOT EXISTS agencia_bienes_raices;
USE agencia_bienes_raices;

CREATE TABLE if not exists AGENTES(
	noIdentidad NUMERIC(8) PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	direccion VARCHAR(30),
	celular NUMERIC(8),
	telefonoOficina NUMERIC(8) NOT null
);

CREATE TABLE if not exists VENDEDORES(
	noIdentidad NUMERIC(8) PRIMARY KEY,
	nombre VARCHAR(20) NOT null,
	direccion VARCHAR(30),
	celular NUMERIC(8) 
);

CREATE TABLE if not exists COMPRADORES(
	noIdentidad NUMERIC(8) PRIMARY KEY,
	nombre VARCHAR(20) NOT null,
	direccion VARCHAR(30),
	celular NUMERIC(8) 
);

CREATE TABLE if not exists PROPIEDADES_VENDIDAS(
	idPropiedad INT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	ciudad VARCHAR(20) NOT NULL,
	direccion VARCHAR(30) NOT NULL,
	cantidadDormitorios INT NOT NULL,
	caracteristicas VARCHAR(100) NOT NULL,
	precio NUMERIC(13, 2) NOT NULL,
	fechaPublicacion DATE NOT NULL,
	fechaVenta DATE NOT NULL,
	noIdentidad_Agente NUMERIC(8) NOT NULL,
	noIdentidad_Vendedor NUMERIC(8) NOT NULL,
	noIdentidad_Comprador NUMERIC(8) NOT NULL,
	comisionVenta NUMERIC(13, 2) NOT NULL,
	FOREIGN KEY (noIdentidad_Agente) REFERENCES AGENTES(noIdentidad),
	FOREIGN KEY (noIdentidad_Vendedor) REFERENCES VENDEDORES(noIdentidad),
	FOREIGN KEY (noIdentidad_Comprador) REFERENCES COMPRADORES(noIdentidad)
);

CREATE TABLE if not exists PROPIEDADES_EN_MERCADO(
	idPropiedad INT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	ciudad VARCHAR(20) NOT NULL,
	direccion VARCHAR(30) NOT NULL,
	cantidadDormitorios INT NOT NULL,
	caracteristicas VARCHAR(100) NOT NULL,
	precio NUMERIC(13, 2) NOT NULL,
	fechaPublicacion DATE NOT NULL,
	noIdentidad_Agente NUMERIC(8) NOT NULL,
	noIdentidad_Vendedor NUMERIC(8) NOT NULL,
	FOREIGN KEY (noIdentidad_Agente) REFERENCES AGENTES(noIdentidad),
	FOREIGN KEY (noIdentidad_Vendedor) REFERENCES VENDEDORES(noIdentidad)
);

CREATE TABLE if not exists USUARIOS(
	username VARCHAR(20) PRIMARY KEY,
	passwrd VARCHAR(10) NOT NULL,
	noIdentidad NUMERIC(8) NOT NULL
);

DELIMITER //
CREATE PROCEDURE  borraragente (
	IN  id  NUMERIC(8)
)
BEGIN
	DELETE FROM agentes
	WHERE noIdentidad = id;
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  borrarcomprador (
	IN  id  NUMERIC(8)
)
BEGIN
	DELETE FROM compradores
	WHERE noIdentidad = id;
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  borrarpropiedadm (
	IN  id  INT
)
BEGIN
	DELETE FROM propiedades_en_mercado 
	WHERE id = idPropiedad;
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  borrarpropiedadv (
	IN  id  INT
)
BEGIN
	DELETE FROM propiedades_vendidas 
	WHERE id = idPropiedad;
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  borrarvendedor (
	IN  id  INT
)
BEGIN
	DELETE FROM vendedores
	WHERE id = noIdentidad;
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  crearagente (
	IN  id  NUMERIC(8),
	IN  nombre  VARCHAR(20),
	IN  direccion  VARCHAR(30),
	IN  celular  NUMERIC(8),
	IN  telefonoOficina  NUMERIC(8)
)
Begin
	INSERT INTO agentes VALUES(id, nombre, direccion, celular, telefonoOficina);
end//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  crearcomprador (
	IN  noIdentidad  NUMERIC(8),
	IN  nombre  VARCHAR(20),
	IN  direccion  VARCHAR(30),
	IN  celular  NUMERIC(8)
)
BEGIN
	INSERT INTO compradores VALUES(noIdentidad, nombre, direccion, celular);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE  crearpropiedadm (
	IN  idPropiedad  INT,
	IN  nombre  VARCHAR(30),
	IN  ciudad  VARCHAR(20),
	IN  direccion  VARCHAR(30),
	IN  cantidadDormitorios  NUMERIC(2),
	IN  caracteristicas  VARCHAR(100),
	IN  precio  NUMERIC(13, 2),
	IN  fechaPublicada  DATE,
	IN  noIdentidad_A  NUMERIC(8),
	IN  noIdentidad_V  NUMERIC(8)
)
BEGIN
	INSERT INTO propiedades_en_mercado VALUES(idPropiedad, nombre, ciudad, direccion, cantidadDormitorios, caracteristicas, precio, fechaPublicada, noIdentidad_A, noIdentidad_V);
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  crearpropiedadv (
	IN  idPropiedad  INT,
	IN  nombre  VARCHAR(30),
	IN  ciudad  VARCHAR(20),
	IN  direccion  VARCHAR(30),
	IN  cantidadDormitorios  NUMERIC(2),
	IN  caracteristicas  VARCHAR(100),
	IN  precio  NUMERIC(13, 2),
	IN  fechaPublicada  DATE,
	IN  fechaVendida  DATE,
	IN  noIdentidad_A  NUMERIC(8),
	IN  noIdentidad_V  NUMERIC(8),
	IN  noIdentidad_C  NUMERIC(8),
	IN  comision  NUMERIC(13, 2)
)
BEGIN
	INSERT INTO propiedades_vendidas VALUES(idPropiedad, nombre, ciudad, direccion, cantidadDormitorios, caracteristicas, precio, fechaPublicada, fechaVendida, noIdentidad_A, noIdentidad_V, noIdentidad_C, comision);
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  crearvendedor (
	IN  noIdentidad  NUMERIC(8),
	IN  nombre  VARCHAR(20),
	IN  direccion  VARCHAR(30),
	IN  celular  NUMERIC(8)
)
BEGIN
	INSERT INTO vendedores VALUES(noIdentidad, nombre, direccion, celular);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE  modificaragente (
	IN  id  NUMERIC(8),
	IN  inombre  VARCHAR(20),
	IN  idireccion  VARCHAR(30),
	IN  icelular  NUMERIC(8),
	IN  itelefono  NUMERIC(8)
)
BEGIN
	UPDATE agentes
	SET nombre = inombre,
		direccion = idireccion,
		celular = icelular, 
		telefonoOficina = itelefono
	WHERE noIdentidad = id;
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE  modificarcomprador (
	IN  id  NUMERIC(8),
	IN  nombre_i  VARCHAR(20),
	IN  idireccion  VARCHAR(30),
	IN  icelular  NUMERIC(8)
)
BEGIN
	UPDATE compradores 
	SET nombre = nombre_i,
		direccion = idireccion,
		celular = icelular
	WHERE noIdentidad = id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE  modificarpropiedadm (
	IN  id  INT,
	IN  inombre  VARCHAR(30),
	IN  iciudad  VARCHAR(20),
	IN  idireccion  VARCHAR(30),
	IN  icantidadDormitorios  NUMERIC(2),
	IN  icaracteristicas  VARCHAR(100),
	IN  iprecio  NUMERIC(13,2),
	IN  ifechaPublicada  DATE,
	IN  noIdentidad_A  NUMERIC(8),
	IN  noIdentidad_V  NUMERIC(8)
)
BEGIN
	UPDATE propiedades_en_mercado
	SET nombre = inombre,
		ciudad = iciudad,
		direccion = idireccion,
		cantidadDormitorios = icantidadDormitorios,
		caracteristicas = icaracteristicas,
		precio = iprecio,
		fechaPublicacion = ifechaPublicada,
		noIdentidad_Agente = noIdentidad_A,
		noIdentidad_Vendedor = noIdentidad_V
	WHERE idPropiedad = id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE  modificarpropiedadv (
	IN  id  INT,
	IN  inombre  VARCHAR(30),
	IN  iciudad  VARCHAR(20),
	IN  idireccion  VARCHAR(30),
	IN  icantidadDormitorios  NUMERIC(2),
	IN  icaracteristicas  VARCHAR(100),
	IN  iprecio  NUMERIC(13,2),
	IN  ifechaPublicada  DATE,
	IN  ifechaVendida  DATE,
	IN  noIdentidad_A  NUMERIC(8),
	IN  noIdentidad_V  NUMERIC(8),
	IN  noIdentidad_C  NUMERIC(8),
	IN  icomision  NUMERIC(13, 2)
)
BEGIN
	UPDATE propiedades_vendidas
	SET nombre = inombre,
		ciudad = iciudad,
		direccion = idireccion,
		cantidadDormitorios = icantidadDormitorios,
		caracteristicas = icaracteristicas,
		precio = iprecio,
		fechaPublicacion = ifechaPublicada,
		fechaVenta = ifechaVendida,
		noIdentidad_Agente = noIdentidad_A,
		noIdentidad_Vendedor = noIdentidad_V,
		noIdentidad_Comprador = noIdentidad_C,
		comisionVenta = icomision
	WHERE idPropiedad = id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE  modificarvendedor (
	IN  id  NUMERIC(8),
	IN  nombre_i  VARCHAR(20),
	IN  idireccion  VARCHAR(30),
	IN  icelular  NUMERIC(8)
)
BEGIN
	UPDATE vendedores
	SET nombre = nombre_i,
		direccion = idireccion,
		celular = icelular
	WHERE noIdentidad = id;
END//
DELIMITER ;