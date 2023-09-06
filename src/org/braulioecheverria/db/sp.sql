-- DML Laboratorio administración educativa
-- Braulio Echeverria
-- Carnet
-- 24/08/2023
-- 25/08/2023

use admin_educacion;

-- inserts entidad alumno
insert into alumno (carne,no_expediente, apellidos, nombres, email)
	values ('2022006','E2021-006','Javier','Mario José','jecheverria@kinal.edu.gt');
    
insert into carrera_tecnica (codigo_carrera,nombre)
	values (UUID(),'Prueba');
    
delimiter $$
create procedure sp_agregar_carrera(in _codigo_carrera varchar(128), in _nombre varchar(128))
begin
	insert into carrera_tecnica(codigo_carrera,nombre)
    values(_codigo_carrera,_nombre);
end$$
delimiter ;

call sp_agregar_carrera(UUID(),'Perito en Computación');
    
delimiter $$
create procedure sp_buscar_carrera(in _codigo_carrera varchar(128))
begin
	select * from carrera_tecnica where codigo_carrera = _codigo_carrera;
end$$
delimiter ;

call sp_buscar_carrera('1f1c549c-42cc-11ee-a157-f8e43b83c669');

delimiter $$
create procedure sp_listar_carreras()
begin
	select * from carrera_tecnica;
end$$
delimiter ;
    
call sp_listar_carreras();

delimiter $$
create procedure sp_actualizar_carrera(in _codigo_carrera varchar(128), in _nombre varchar(255))
begin
	update carrera_tecnica
    set
		nombre = _nombre
	where
		codigo_carrera = _codigo_carrera;
end$$
delimiter ;

call sp_actualizar_carrera('1f1c549c-42cc-11ee-a157-f8e43b83c669','Perito en dibujo');

delimiter $$
create procedure sp_eliminar_carrera(in _codigo_carrera varchar(128))
begin
	delete from carrera_tecnica where codigo_carrera = _codigo_carrera;
end$$
delimiter ;

call sp_eliminar_carrera('b15d383c-4368-11ee-a157-f8e43b83c669');

insert into horario (horario_id, horario_inicio, horario_final)
	values (UUID(), '12:40:00','17:40:00');
    
delimiter $$
create procedure sp_agregar_horario(in _horario_id varchar(128), in _horario_inicio time, in _horario_final time)
begin
	insert into horario (horario_id, horario_inicio, horario_final) values (_horario_id, _horario_inicio, _horario_final);
end$$
delimiter ;

call sp_agregar_horario(UUID(), '13:00:00','14:00:00');

delimiter $$
create procedure sp_listar_horario()
begin
	select * from horario;
end$$
delimiter ;

call sp_listar_horario();

delimiter $$
create procedure sp_buscar_horario(in _horario_id varchar(128))
begin
	select * from horario where horario_id = _horario_id;
end$$
delimiter ;

call sp_buscar_horario('677b6494-434f-11ee-a157-f8e43b83c669');

delimiter $$
create procedure sp_actualizar_horario(in _horario_id varchar(128), in _horario_inicio time, in _horario_final time)
begin
	update horario
    set horario_inicio = _horario_inicio,
    horario_final = _horario_final
    where
    horario_id = _horario_id;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_horario(in _horario_id varchar(128))
begin
	delete from horario where horario_id = _horario_id;
end$$
delimiter ;
    
insert into instructor(instructor_id, apellidos, nombres, comentario, direccion, estatus, foto, telefono)
	values(UUID(),'Duarte','Cristopher','Ciencias Exactas','Ciudad',1,'foto1.jpg',22160000);
    
delimiter $$
create procedure sp_agregar_instructor(in _instructor_id varchar(128), in _apellidos varchar(128), in _nombres varchar(128), 
		in _comentario varchar(255), in _direccion varchar(255),in _estatus tinyint(1), in _foto varchar(128), in _telefono int(11))
begin
	insert into instructor(instructor_id, apellidos, nombres, comentario, direccion, estatus, foto, telefono)
	values(_instructor_id, _apellidos, _nombres, _comentario, _direccion, _estatus, _foto, _telefono);
end$$
delimiter ;

delimiter $$
create procedure sp_buscar_instructor(in _instructor_id varchar(128))
begin
	select * from instructor where instructor_id = _instructor_id;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_instructor(in _instructor_id varchar(128))
begin
	delete from instructor where instructor_id = _instructor_id;
end$$
delimiter ;

delimiter $$
create procedure sp_editar_instructor(in _instructor_id varchar(128), in _apellidos varchar(128), in _nombres varchar(128), 
		in _comentario varchar(255), in _direccion varchar(255),in _estatus tinyint(1), in _foto varchar(128), in _telefono int(11))
begin
	update instructor
    set
		apellidos = _apellidos,
        nombres = _nombres,
        comentario = _comentario,
        direccion = _direccion,
        estatus = _estatus,
        foto = _foto,
        telefono = _telefono
	where
		instructor_id = _instructor_id;
end$$
delimiter ;

delimiter $$
create procedure sp_listar_instructores()
begin
	select * from instructor;
end$$
delimiter ;
    
insert into salon(salon_id, capacidad, descripcion, nombre_salon)
	values(UUID(),45,'Laboratorio de informática','Salón C-28');
    
delimiter $$
create procedure sp_agregar_salon(in _salon_id varchar(128), in _capacidad int(11), in _descripcion varchar(255), in _nombre_salon varchar(255))
begin
	insert into salon(salon_id, capacidad, descripcion, nombre_salon)
    values(_salon_id, _capacidad, _descripcion, _nombre_salon);
end$$
delimiter ;

delimiter $$
create procedure sp_listar_salones()
begin
	select * from salon;
end $$
delimiter ;

delimiter $$ 
create procedure sp_buscar_salon(in _salon_id varchar(128))
begin
	select * from salon where salon_id = _salon_id;
end$$
delimiter $$

delimiter $$
create procedure sp_actulizar_salon(in _salon_id varchar(128), in _capacidad int(11), in _descripcion varchar(255), in _nombre_salon varchar(255))
begin
	update salon
    set
		capacidad = _capacidad,
        descripcion = _descripcion,
        nombre_salon = _nombre_salon
	where
		salon_id = _salon_id;
end$$
delimiter ;

delimiter $$ 
create procedure sp_eliminar_salon(in _salon_id varchar(128))
begin
	delete from salon where salon_id = _salon_id;
end$$

delimiter $$

create procedure sp_agregar_alumno(in _carne varchar(16), in _no_expediente varchar(64), in _apellidos varchar(128), in _nombres varchar(128), in _email varchar(32))
begin
	insert into alumno (carne,no_expediente,apellidos,nombres,email)
    values (_carne,_no_expediente,_apellidos,_nombres,_email)
end$$
delimiter ;

call sp_agregar_alumno('2023002','E2022-002','Escobar Ponce','Anthony','aescobar@kinal.edu.gt');

select * from alumno;

delimiter $$
create procedure sp_listar_alumnos()
begin
	select * from alumno;
end$$
delimiter ;

call sp_listar_alumnos();

delimiter $$
create procedure sp_buscar_alumnno(in _carne varchar(16))
begin
	select * from alumno where alumno.carne = _carne;
end$$
delimiter ;

call sp_buscar_alumnno('2023002');

delimiter $$
create procedure sp_actualizar_alumno(in _carne varchar(16), in _no_expediente varchar(64), in _apellidos varchar(128), in _nombres varchar(128), in _email varchar(32))
begin
	update alumno
    set 
		no_expediente = _no_expediente,
        apellidos = _apellidos,
        nombres = _nombres,
        email = _email
	where
		carne = _carne;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_alumno(in _carne varchar(16))
begin
	delete from alumno where carne = _carne;
end$$
delimiter ;

call sp_actualizar_alumno('2023002','E2021-002','Escobar','Anthony','aescobar@gmail.com');



select * from alumno where nombres like 'jos%';




select * from carrera_tecnica where codigo_carrera = '1f1c549c-42cc-11ee-a157-f8e43b83c669';

delimiter $$
create procedure sp_agregar_clase(in _clase_id varchar(128), in _ciclo int(11), in _cupo_maximo int(11),
		in _cupo_minimo int(11), in _descripcion varchar(255), in _carrera_id varchar(128), 
        in _horario_id varchar(128),in _instructor_id varchar(128), in _salon_id varchar(128))
begin
	insert into clase 
		(clase_id, ciclo, cupo_maximo, cupo_minimo, descripcion, carrera_id, horario_id, instructor_id, salon_id)
	values
		(_clase_id, _ciclo, _cupo_maximo, _cupo_minimo, _descripcion, _carrera_id, _horario_id, _instructor_id, _salon_id);
end$$
delimiter ;

delimiter $$
create procedure sp_buscar_clase(in _clase_id varchar(128))
begin
	select * from clase where clase_id = _clase_id;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_clase(in _clase_id varchar(128))
begin
	delete from clase where clase_id = _clase_id;
end$$
delimiter ;

delimiter $$
create procedure sp_listar_clases()
begin
	select * from clase;
end$$
delimiter ;

call sp_listar_clases();

delimiter $$
create procedure sp_actulizar_clase(in _clase_id varchar(128), in _ciclo int(11), in _cupo_maximo int(11),
		in _cupo_minimo int(11), in _descripcion varchar(255), in _carrera_id varchar(128), 
        in _horario_id varchar(128),in _instructor_id varchar(128), in _salon_id varchar(128))
begin
	update clase
    set
		ciclo = _ciclo,
        cupo_maximo = _cupo_maximo,
        cupo_minimo = _cupo_minimo,
        descripcion = _descripcion,
        carrera_id = _carrera_id,
        horario_id = _horario_id,
        instructor_id = _instructor_id,
        salon_id = _salon_id
	where
        clase_id = _clase_id;
end$$
delimiter ;

call sp_agregar_clase(UUID(), 2023, 30, 30, 'Perito en dibujo técnico Sección B','1f1c549c-42cc-11ee-a157-f8e43b83c669','677b6494-434f-11ee-a157-f8e43b83c669','e0a46171-4366-11ee-a157-f8e43b83c669','bb94a1f6-4366-11ee-a157-f8e43b83c669');

create view vw_detalle_instructor as
select clase.descripcion as "Sección", concat(instructor.nombres, " ", instructor.apellidos) as "Nombres", carrera_tecnica.nombre as "Carrera  a la pertenece" from clase
inner join instructor on clase.instructor_id = instructor.instructor_id
inner join carrera_tecnica on clase.carrera_id = carrera_tecnica.codigo_carrera
where instructor.nombres like 'Cris%';


select * from vw_detalle_instructor;