CREATE TABLE AGENTES(
	noIdentidad NUMERIC(8) PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	direccion VARCHAR(30),
	celular NUMERIC(8),
	telefonoOficina NUMERIC(8) NOT null
);

CREATE TABLE VENDEDORES(
	noIdentidad NUMERIC(8) PRIMARY KEY,
	nombre VARCHAR(20) NOT null,
	direccion VARCHAR(30),
	celular NUMERIC(8) 
);

CREATE TABLE COMPRADORES(
	noIdentidad NUMERIC(8) PRIMARY KEY,
	nombre VARCHAR(20) NOT null,
	direccion VARCHAR(30),
	celular NUMERIC(8) 
);

CREATE TABLE PROPIEDADES_VENDIDAS(
	idPropiedad INT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	ciudad VARCHAR(20) NOT NULL,
	direccion VARCHAR(30) NOT NULL,
	cantidadDormitorios NUMERIC(2) NOT NULL,
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

CREATE TABLE PROPIEDADES_EN_MERCADO(
	idPropiedad INT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	ciudad VARCHAR(20) NOT NULL,
	direccion VARCHAR(30) NOT NULL,
	cantidadDormitorios NUMERIC(2) NOT NULL,
	caracteristicas VARCHAR(100) NOT NULL,
	precio NUMERIC(13, 2) NOT NULL,
	fechaPublicacion DATE NOT NULL,
	noIdentidad_Agente NUMERIC(8) NOT NULL,
	noIdentidad_Vendedor NUMERIC(8) NOT NULL,
	FOREIGN KEY (noIdentidad_Agente) REFERENCES AGENTES(noIdentidad),
	FOREIGN KEY (noIdentidad_Vendedor) REFERENCES VENDEDORES(noIdentidad)
);

