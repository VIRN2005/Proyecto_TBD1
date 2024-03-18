-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.36 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para agencia_bienes_raices
CREATE DATABASE IF NOT EXISTS `agencia_bienes_raices` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `agencia_bienes_raices`;

-- Volcando estructura para tabla agencia_bienes_raices.agentes
CREATE TABLE IF NOT EXISTS `agentes` (
  `noIdentidad` decimal(8,0) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `celular` decimal(8,0) DEFAULT NULL,
  `telefonoOficina` decimal(8,0) NOT NULL,
  PRIMARY KEY (`noIdentidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla agencia_bienes_raices.bitacora
CREATE TABLE IF NOT EXISTS `bitacora` (
  `idOperacion` int NOT NULL,
  `username` varchar(20) NOT NULL,
  `operacion` varchar(10) NOT NULL,
  `tabla` varchar(25) NOT NULL,
  `id` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idOperacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento agencia_bienes_raices.borraragente
DELIMITER //
CREATE PROCEDURE `borraragente`(
	IN `id` NUMERIC(8)
)
BEGIN
	DELETE FROM agentes
	WHERE noIdentidad = id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.borrarcomprador
DELIMITER //
CREATE PROCEDURE `borrarcomprador`(
	IN `id` NUMERIC(8)
)
BEGIN
	DELETE FROM compradores
	WHERE noIdentidad = id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.borrarpropiedadm
DELIMITER //
CREATE PROCEDURE `borrarpropiedadm`(
	IN `id` INT
)
BEGIN
	DELETE FROM propiedades_en_mercado 
	WHERE id = idPropiedad;
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.borrarpropiedadv
DELIMITER //
CREATE PROCEDURE `borrarpropiedadv`(
	IN `id` INT
)
BEGIN
	DELETE FROM propiedades_vendidas 
	WHERE id = idPropiedad;
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.borrarvendedor
DELIMITER //
CREATE PROCEDURE `borrarvendedor`(
	IN `id` INT
)
BEGIN
	DELETE FROM vendedores
	WHERE id = noIdentidad;
END//
DELIMITER ;

-- Volcando estructura para tabla agencia_bienes_raices.compradores
CREATE TABLE IF NOT EXISTS `compradores` (
  `noIdentidad` decimal(8,0) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `celular` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`noIdentidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento agencia_bienes_raices.crearagente
DELIMITER //
CREATE PROCEDURE `crearagente`(
	IN `id` NUMERIC(8),
	IN `nombre` VARCHAR(20),
	IN `direccion` VARCHAR(30),
	IN `celular` NUMERIC(8),
	IN `telefonoOficina` NUMERIC(8)
)
Begin
	INSERT INTO agentes VALUES(id, nombre, direccion, celular, telefonoOficina);
end//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.crearcomprador
DELIMITER //
CREATE PROCEDURE `crearcomprador`(
	IN `noIdentidad` NUMERIC(8),
	IN `nombre` VARCHAR(20),
	IN `direccion` VARCHAR(30),
	IN `celular` NUMERIC(8)
)
BEGIN
	INSERT INTO compradores VALUES(noIdentidad, nombre, direccion, celular);
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.crearpropiedadm
DELIMITER //
CREATE PROCEDURE `crearpropiedadm`(
	IN `idPropiedad` INT,
	IN `nombre` VARCHAR(30),
	IN `ciudad` VARCHAR(20),
	IN `direccion` VARCHAR(30),
	IN `cantidadDormitorios` NUMERIC(2),
	IN `caracteristicas` VARCHAR(100),
	IN `precio` NUMERIC(13, 2),
	IN `fechaPublicada` DATE,
	IN `noIdentidad_A` NUMERIC(8),
	IN `noIdentidad_V` NUMERIC(8)
)
BEGIN
	INSERT INTO propiedades_en_mercado VALUES(idPropiedad, nombre, ciudad, direccion, cantidadDormitorios, caracteristicas, precio, fechaPublicada, noIdentidad_A, noIdentidad_V);
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.crearpropiedadv
DELIMITER //
CREATE PROCEDURE `crearpropiedadv`(
	IN `idPropiedad` INT,
	IN `nombre` VARCHAR(30),
	IN `ciudad` VARCHAR(20),
	IN `direccion` VARCHAR(30),
	IN `cantidadDormitorios` NUMERIC(2),
	IN `caracteristicas` VARCHAR(100),
	IN `precio` NUMERIC(13, 2),
	IN `fechaPublicada` DATE,
	IN `fechaVendida` DATE,
	IN `noIdentidad_A` NUMERIC(8),
	IN `noIdentidad_V` NUMERIC(8),
	IN `noIdentidad_C` NUMERIC(8),
	IN `comision` NUMERIC(13, 2)
)
BEGIN
	INSERT INTO propiedades_vendidas VALUES(idPropiedad, nombre, ciudad, direccion, cantidadDormitorios, caracteristicas, precio, fechaPublicada, fechaVendida, noIdentidad_A, noIdentidad_V, noIdentidad_C, comision);
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.crearvendedor
DELIMITER //
CREATE PROCEDURE `crearvendedor`(
	IN `noIdentidad` NUMERIC(8),
	IN `nombre` VARCHAR(20),
	IN `direccion` VARCHAR(30),
	IN `celular` NUMERIC(8)
)
BEGIN
	INSERT INTO vendedores VALUES(noIdentidad, nombre, direccion, celular);
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.modificaragente
DELIMITER //
CREATE PROCEDURE `modificaragente`(
	IN `id` NUMERIC(8),
	IN `inombre` VARCHAR(20),
	IN `idireccion` VARCHAR(30),
	IN `icelular` NUMERIC(8),
	IN `itelefono` NUMERIC(8)
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

-- Volcando estructura para procedimiento agencia_bienes_raices.modificarcomprador
DELIMITER //
CREATE PROCEDURE `modificarcomprador`(
	IN `id` NUMERIC(8),
	IN `nombre_i` VARCHAR(20),
	IN `idireccion` VARCHAR(30),
	IN `icelular` NUMERIC(8)
)
BEGIN
	UPDATE compradores 
	SET nombre = nombre_i,
		direccion = idireccion,
		celular = icelular
	WHERE noIdentidad = id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento agencia_bienes_raices.modificarpropiedadm
DELIMITER //
CREATE PROCEDURE `modificarpropiedadm`(
	IN `id` INT,
	IN `inombre` VARCHAR(30),
	IN `iciudad` VARCHAR(20),
	IN `idireccion` VARCHAR(30),
	IN `icantidadDormitorios` NUMERIC(2),
	IN `icaracteristicas` VARCHAR(100),
	IN `iprecio` NUMERIC(13,2),
	IN `ifechaPublicada` DATE,
	IN `noIdentidad_A` NUMERIC(8),
	IN `noIdentidad_V` NUMERIC(8)
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

-- Volcando estructura para procedimiento agencia_bienes_raices.modificarpropiedadv
DELIMITER //
CREATE PROCEDURE `modificarpropiedadv`(
	IN `id` INT,
	IN `inombre` VARCHAR(30),
	IN `iciudad` VARCHAR(20),
	IN `idireccion` VARCHAR(30),
	IN `icantidadDormitorios` NUMERIC(2),
	IN `icaracteristicas` VARCHAR(100),
	IN `iprecio` NUMERIC(13,2),
	IN `ifechaPublicada` DATE,
	IN `ifechaVendida` DATE,
	IN `noIdentidad_A` NUMERIC(8),
	IN `noIdentidad_V` NUMERIC(8),
	IN `noIdentidad_C` NUMERIC(8),
	IN `icomision` NUMERIC(13, 2)
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

-- Volcando estructura para procedimiento agencia_bienes_raices.modificarvendedor
DELIMITER //
CREATE PROCEDURE `modificarvendedor`(
	IN `id` NUMERIC(8),
	IN `nombre_i` VARCHAR(20),
	IN `idireccion` VARCHAR(30),
	IN `icelular` NUMERIC(8)
)
BEGIN
	UPDATE vendedores
	SET nombre = nombre_i,
		direccion = idireccion,
		celular = icelular
	WHERE noIdentidad = id;
END//
DELIMITER ;

-- Volcando estructura para tabla agencia_bienes_raices.propiedades_en_mercado
CREATE TABLE IF NOT EXISTS `propiedades_en_mercado` (
  `idPropiedad` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `ciudad` varchar(20) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `cantidadDormitorios` decimal(2,0) NOT NULL,
  `caracteristicas` varchar(100) NOT NULL,
  `precio` decimal(13,2) NOT NULL,
  `fechaPublicacion` date NOT NULL,
  `noIdentidad_Agente` decimal(8,0) NOT NULL,
  `noIdentidad_Vendedor` decimal(8,0) NOT NULL,
  PRIMARY KEY (`idPropiedad`),
  KEY `noIdentidad_Agente` (`noIdentidad_Agente`),
  KEY `noIdentidad_Vendedor` (`noIdentidad_Vendedor`),
  CONSTRAINT `propiedades_en_mercado_ibfk_1` FOREIGN KEY (`noIdentidad_Agente`) REFERENCES `agentes` (`noIdentidad`),
  CONSTRAINT `propiedades_en_mercado_ibfk_2` FOREIGN KEY (`noIdentidad_Vendedor`) REFERENCES `vendedores` (`noIdentidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla agencia_bienes_raices.propiedades_vendidas
CREATE TABLE IF NOT EXISTS `propiedades_vendidas` (
  `idPropiedad` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `ciudad` varchar(20) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `cantidadDormitorios` decimal(2,0) NOT NULL,
  `caracteristicas` varchar(100) NOT NULL,
  `precio` decimal(13,2) NOT NULL,
  `fechaPublicacion` date NOT NULL,
  `fechaVenta` date NOT NULL,
  `noIdentidad_Agente` decimal(8,0) NOT NULL,
  `noIdentidad_Vendedor` decimal(8,0) NOT NULL,
  `noIdentidad_Comprador` decimal(8,0) NOT NULL,
  `comisionVenta` decimal(13,2) NOT NULL,
  PRIMARY KEY (`idPropiedad`),
  KEY `noIdentidad_Agente` (`noIdentidad_Agente`),
  KEY `noIdentidad_Vendedor` (`noIdentidad_Vendedor`),
  KEY `noIdentidad_Comprador` (`noIdentidad_Comprador`),
  CONSTRAINT `propiedades_vendidas_ibfk_1` FOREIGN KEY (`noIdentidad_Agente`) REFERENCES `agentes` (`noIdentidad`),
  CONSTRAINT `propiedades_vendidas_ibfk_2` FOREIGN KEY (`noIdentidad_Vendedor`) REFERENCES `vendedores` (`noIdentidad`),
  CONSTRAINT `propiedades_vendidas_ibfk_3` FOREIGN KEY (`noIdentidad_Comprador`) REFERENCES `compradores` (`noIdentidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla agencia_bienes_raices.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `username` varchar(20) NOT NULL,
  `passwrd` varchar(10) NOT NULL,
  `noIdentidad` decimal(8,0) NOT NULL,
  `activo` decimal(1,0) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla agencia_bienes_raices.vendedores
CREATE TABLE IF NOT EXISTS `vendedores` (
  `noIdentidad` decimal(8,0) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `celular` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`noIdentidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
