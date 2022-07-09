alter table agentes add constraint chk_clave check(LENGTH(clave) > 6);

alter table agentes add constraint chk_habilidad check (habilidad BETWEEN 0 and 9);

alter table agentes add constraint chk_categoria check (categoria BETWEEN 0 and 2);

alter table agentes add constraint chk_categoria2  check ((categoria=2 and familia is null and oficina is not null) OR 
(categoria=1 and familia is not null and oficina is null) or ((familia is null and oficina is not null) OR
(familia is not null and oficina is null)));


--Eliminar restriccion en la tabla.
alter table agentes drop constraint chk_categoria2;