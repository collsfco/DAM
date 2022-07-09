/*A) Modificar las tablas creadas en el ejercicio anterior siguiendo las indicaciones. 
Los ejerciciosse incluirán en un script llamado ModificaTienda.sql.
Cada uno de ellos, como en el ejercicio anterior,irá precedido de un comentario con el enunciado.*/

--Añadir a la tabla STOCK.
--------------------------------------------------------
--Una columna de tipo fecha llamada FechaUltimaEntrada que por defecto tome el valorde la fecha actual.

alter table stock
add Fechaultimaentrada date default sysdate ;
--------------------------------------------------------
--Una columna llamada Beneficio que contendrá el tipo de porcentaje de beneficio queesa tienda aplica en ese producto.
--Se debe controlar que el valor que almacene sea 1,2,3, 4 o 5.

alter table stock
add Beneficio number (1) check (Beneficio between 1 and 5)  ;
----------------------------------------------------------------------------------------------------------------

--En la tabla PRODUCTO.
--------------------------------------------------------
--Eliminar de la tabla producto la columna Descripción.

alter table producto
drop column descripcion  ;
--------------------------------------------------------
--Añadir una columna llamada perecedero que únicamente acepte los valores: S o N.

alter table producto
add perecedero char check(perecedero in ('S','N')) ;
--------------------------------------------------------
--Modificar el tamaño de la columna Denoproducto a 50.

alter table producto modify
(denoproducto varchar(50));
----------------------------------------------------------------------------------------------------------------
 
--En la tabla FAMILIA
--------------------------------------------------------
--Añadir una columna llamada IVA, que represente el porcentaje de IVA y únicamentepueda contener los valores 21,10,ó 4.

alter table familia
add iva number (2) check(iva in (21,10,4));
----------------------------------------------------------------------------------------------------------------

--En la tabla TIENDA.
--------------------------------------------------------
--La empresa desea restringir el número de tiendas con las que trabaja, de forma que nopueda haber más de una tienda en
--una misma zona (la zona se identifica por el códigopostal). Definir mediante DDL las restricciones necesarias para que 
--se cumpla en elcampo correspondiente.

alter table tienda modify
(codigopostal unique);
----------------------------------------------------------------------------------------------------------------

--B) Renombra la tabla STOCK por PRODXTIENDAS. 

rename stock to prodxtiendas;
----------------------------------------------------------------------------------------------------------------

--C) Elimina la tabla FAMILIA y su contenido si lo tuviera.

drop table familia cascade constraints;

----------------------------------------------------------------------------------------------------------------
--D) Crea un usuario llamado C##INVITADO siguiendo los pasos de la unidad 1 y dale todos losprivilegios sobre la tabla PRODUCTO.

create user c##invitado IDENTIFIED BY 1234 default tablespace users;
GRANT all on producto TO c##invitado;
----------------------------------------------------------------------------------------------------------------

--E) Retira los permisos de modificar la estructura de la tabla y borrar contenido de la tabla PRODUCTO al usuario anterior.

REVOKE ALTER, Delete ON PRODUCTO from c##invitado;

