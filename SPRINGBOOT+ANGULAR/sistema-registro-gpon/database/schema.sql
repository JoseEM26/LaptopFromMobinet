DROP DATABASE IF EXISTS registro_gpon_db;
CREATE DATABASE registro_gpon_db;
USE registro_gpon_db;

CREATE TABLE Rol (
    IdRol INT PRIMARY KEY AUTO_INCREMENT,
    Descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Usuario (
    IdUsuario INT PRIMARY KEY AUTO_INCREMENT,
    IdRol INT NOT NULL,
    Nombre VARCHAR(255) NOT NULL,
    Apellido VARCHAR(255) NOT NULL,
    Correo VARCHAR(255) NOT NULL,
    Contrasena VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    Estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (IdRol) REFERENCES Rol(IdRol)
);

CREATE TABLE Cliente (
    DniCliente VARCHAR(8) PRIMARY KEY,
    Ruc VARCHAR(11) NOT NULL,
    Nombre VARCHAR(255) NOT NULL,
    Apellido VARCHAR(255) NOT NULL,
    Telefono VARCHAR(9) NOT NULL,
    Activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Plan (
    IdPlan INT PRIMARY KEY AUTO_INCREMENT,
    Descripcion VARCHAR(255) NOT NULL,
    Activo BIT DEFAULT 1
);

CREATE TABLE Promocion (
    IdPromocion INT PRIMARY KEY AUTO_INCREMENT,
    Descripcion VARCHAR(255) NOT NULL,
    Estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Distrito (
    IdDistrito INT PRIMARY KEY AUTO_INCREMENT,
    NombreDistrito VARCHAR(255) NOT NULL
);

CREATE TABLE Sector (
    IdSector INT PRIMARY KEY AUTO_INCREMENT,
    NombreSector VARCHAR(255) NOT NULL
);

CREATE TABLE EstadoRegistro (
    IdEstado INT PRIMARY KEY AUTO_INCREMENT,
    Descripcion VARCHAR(30) NOT NULL
);

CREATE TABLE ContactoPrincipal (
    IdContactoPrincipal INT PRIMARY KEY AUTO_INCREMENT,
    NombreContacto VARCHAR(30) NOT NULL,
    Dni VARCHAR(8) NOT NULL,
    Correo VARCHAR(30) NOT NULL,
    Telefono VARCHAR(9) NOT NULL
);

CREATE TABLE ContactoSecundario (
    IdContactoSecundario INT PRIMARY KEY AUTO_INCREMENT,
    NombreContacto VARCHAR(30) NOT NULL,
    Dni VARCHAR(8) NOT NULL,
    Correo VARCHAR(30) NOT NULL,
    Telefono VARCHAR(9) NOT NULL
);

CREATE TABLE Cronograma (
    IdCronograma INT PRIMARY KEY AUTO_INCREMENT,
    UbicacionInstalacion VARCHAR(100),
    RangoInstalacion VARCHAR(100),
    FechaRegistro DATE ,
    FechaInstalacion DATE
);

CREATE TABLE RegistroRUC10 (
    IdRegistro INT PRIMARY KEY AUTO_INCREMENT,
    IdUsuarioConsultor INT NOT NULL,
    IdUsuarioSupervisor INT NOT NULL,
    DniCliente VARCHAR(8) NOT NULL,
    IdContactoPrincipal INT NOT NULL,
    IdContactoSecundario INT NOT NULL,
    IdPlan INT NOT NULL,
    IdPromocion INT,
    IdCronograma INT NOT NULL,
    IdEstado INT DEFAULT 1,
    IdSolicitud VARCHAR(255) DEFAULT 'ninguno',
    IdInstalacion VARCHAR(255)  DEFAULT 'ninguno',
    IdCarrito VARCHAR(255)  DEFAULT 'ninguno',
    Observacion VARCHAR(255),
    FOREIGN KEY (IdUsuarioConsultor) REFERENCES Usuario(IdUsuario),
    FOREIGN KEY (IdUsuarioSupervisor) REFERENCES Usuario(IdUsuario),
    FOREIGN KEY (DniCliente) REFERENCES Cliente(DniCliente),
    FOREIGN KEY (IdContactoPrincipal) REFERENCES ContactoPrincipal(IdContactoPrincipal),
    FOREIGN KEY (IdContactoSecundario) REFERENCES ContactoSecundario(IdContactoSecundario),
    FOREIGN KEY (IdPlan) REFERENCES Plan(IdPlan),
    FOREIGN KEY (IdPromocion) REFERENCES Promocion(IdPromocion),
    FOREIGN KEY (IdCronograma) REFERENCES Cronograma(IdCronograma),
    FOREIGN KEY (IdEstado) REFERENCES EstadoRegistro(IdEstado)
);