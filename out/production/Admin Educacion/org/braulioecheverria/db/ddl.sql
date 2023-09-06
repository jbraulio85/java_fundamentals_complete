-- DDL laboratorio administracion educativa
-- Braulio Echeverria
-- carnet
-- 24/08/2023
-- 25/08/2023

-- eliminar la base de datos si existe antes de crearla para evitar conflicto
DROP DATABASE IF exists admin_educacion;
-- creamos la base de datos
create database admin_educacion;
-- seleccionamos la base de datos a utilizar
use admin_educacion;

-- creamos entidad Alumno
create table alumno
(
	carne varchar(16) primary key not null,
    no_expediente varchar(64) not null,
    apellidos varchar(128),
    nombres varchar(128),
    email varchar(32)
) engine = innodb;

-- creamos entidad horarios
create table horario
(
	horario_id varchar(128) primary key not null,
    horario_inicio time,
    horario_final time
) engine = innodb;

-- creamos entidad salon
create table salon
(
	salon_id varchar(128) primary key not null,
    capacidad int,
    descripcion varchar(255),
    nombre_salon varchar(255)
) engine = innodb;

-- creamos entidad instructor
create table instructor
(
	instructor_id varchar(128) primary key not null,
    apellidos varchar(128),
    nombres varchar(128),
    comentario varchar(255),
    direccion varchar(255),
    estatus boolean,
    foto varchar(128),
    telefono int
) engine = innodb;

-- creamos entidad carrera técnica
create table carrera_tecnica
(
	codigo_carrera varchar(128) primary key not null,
    nombre varchar(255)
) engine = innodb;

-- creamos entidad clase
create table clase
(
	clase_id varchar(128) primary key not null,
    ciclo int(11),
    cupo_maximo int(11),
    cupo_minimo int(11),
    descripcion varchar(255),
    carrera_id varchar(128),
    horario_id varchar(128),
    instructor_id varchar(128),
    salon_id varchar(128),
    foreign key (carrera_id) references carrera_tecnica (codigo_carrera),
    foreign key (horario_id) references horario (horario_id),
    foreign key (instructor_id) references instructor (instructor_id),
    foreign key (salon_id) references salon (salon_id)
) engine = innodb;

-- creamos entidad asignacion_alumno
create table asignacion_alumno
(
	asignacion_id varchar(128) primary key not null,
    carne varchar (16),
    clase_id varchar(128),
    fecha_asignacion datetime,
    foreign key (carne) references alumno (carne),
    foreign key (clase_id) references clase (clase_id)
) engine = innodb;

-- creamos entidad modulo
create table modulo
(
	modulo_id varchar(128) primary key not null,
    carrera_id varchar(128),
    nombre varchar(255),
    numero_seminarios int(11),
    foreign key (carrera_id) references carrera_tecnica (codigo_carrera)
) engine = innodb;

-- creamos entidad seminarios
create table seminario
(
	seminario_id varchar(128) primary key not null,
    modulo_id varchar(128),
    nombre varchar(255),
    fecha_inicio date,
    fecha_final date,
    foreign key (modulo_id) references modulo (modulo_id)
) engine = innodb;

-- creamos entidad detalle_actividad
create table detalle_actividad
(
	detalle_actividad_id varchar(128) primary key not null,
    seminario_id varchar(128),
    nombre varchar(255),
    nota int(11),
    fecha_creacion date,
    fecha_entrega date,
    fecha_postergacion date,
    estado boolean,
    foreign key (seminario_id) references seminario (seminario_id)
) engine = innodb;

-- creamos entidad detalle_nota
create table detalle_nota
(
	detalle_nota_id varchar(128) primary key not null,
    detalle_actividad_id varchar(128),
    carne varchar(16),
    valor_nota int(11),
    foreign key (detalle_actividad_id) references detalle_actividad (detalle_actividad_id),
    foreign key (carne) references alumno (carne)
) engine = innodb;
