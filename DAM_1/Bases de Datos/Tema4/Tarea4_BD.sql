1. /*Obtener los nombres y salarios de los empleados con más de 1000 euros de salario por orden alfabético.*/

SELECT NOMBRE,APE1, SALARIO FROM EMPLEADO WHERE SALARIO > 1000 ORDER BY 1;

2./*Obtener el nombre de los empleados cuya comisión es superior al 20% de su salario.*/

SELECT NOMBRE, APE1, SALARIO, COMISION FROM EMPLEADO WHERE NVL(COMISION,0) >  SALARIO*0.2 ;


NVL, SI HAY UN NULL LO CAMBIA POR 0.

3./* Obtener el código de empleado, código de departamento, nombre y sueldo total en pesetas de aquellos empleados 
cuyo sueldo total (salario más comisión) supera los 1800 euros. Presentarlos ordenados por código de departamento 
y dentro de éstos por orden alfabético.*/

select coddpto,codemple,nombre,ape1,(salario+nvl(comision,0))*166.386 as sueldopts
from empleado where salario+nvl(comision,0)>1800
order by coddpto,nombre,ape1;

4./*Obtener por orden alfabético los nombres de empleados cuyo salario igualen o superen en más de un 5% al
salario de la empleada ‘MARIA JAZMIN’.*/

SELECT NOMBRE, APE1, SALARIO FROM EMPLEADO WHERE SALARIO >= (SELECT SALARIO*1.05 FROM EMPLEADO WHERE NOMBRE='MARIA' AND APE1='JAZMIN')
ORDER BY 2,1 ASC;

5.- Obtener una listado ordenado por años en la empresa con los nombres, y apellidos de los empleados y los años de antigüedad en la empresa

SELECT nombre,ape1,ape2, months_between(sysdate,fechaingreso)/12 
as "Antigüedad" from empleado order by 4 asc;

<<<<<otra forma_ calcular fechas>>>>>>>>
select nombre, ape1, ape2, to_char(sysdate,'YYYY')- to_char(fechaingreso,'YYYY') 
as antiguedad from empleado order by  antiguedad desc;

6. /*Obtener el nombre de los empleados que trabajan en un departamento con presupuesto superior a 50.000 euros.
Hay que usar predicado cuantificado*/

SELECT NOMBRE,APE1 FROM EMPLEADO WHERE CODDPTO=ANY(SELECT CODDPTO FROM DPTO WHERE PRESUPUESTO > 50000);

las palabras cuantificadoras ANY, SOME o ALL. Los predicados
asI construidos se llaman cuantificados. Los cuantificadores ANY y SOME son equivalentes.

--7.-Obtener los nombres y apellidos de empleados que más cobran en la empresa.Considerar el salario más la comisión.

select ape1,nombre, nvl(comision,0)+salario from empleado where nvl(comision,0)+salario > =all(select nvl(comision,0)+salario from empleado);

--De otra forma sin predicado
select ape1, nombre,  nvl(comision,0)+salario from empleado 
where nvl(comision,0)+salario in (select max(nvl(comision,0)+salario ) from empleado);

SELECT NOMBRE,APE1,SALARIO+NVL(COMISION,0) AS TOTAL FROM EMPLEADO 
WHERE (SALARIO+NVL(COMISION,0)) = (SELECT MAX (SALARIO+NVL(COMISION,0))FROM EMPLEADO);

/*8.Obtener en orden alfabético los nombres de empleado cuyo salario es inferior al mínimo 
de los empleados del departamento 1.*/

SELECT NOMBRE,APE1 FROM EMPLEADO WHERE SALARIO < (SELECT MIN(SALARIO) FROM EMPLEADO WHERE CODDPTO=1);
select nombre,ape1,salario from empleado where salario < all(select salario from empleado where coddpto=1) order by 2,1;

 /* 9.Obtener los nombre de empleados que trabajan en el departamento del cuál 
es jefe el empleado con código 1*/

SELECT NOMBRE,APE1 FROM EMPLEADO WHERE CODDPTO=
(SELECT CODDPTO FROM EMPLEADO WHERE CODEMPLE=1); 

select nombre,ape1 from empleado where coddpto = some 
(select coddpto from dpto where codemplejefe=1);

 /* 10.Obtener los nombres de los empleados cuyo primer apellido empiece por las letras p, q, r, s.*/
 Select nombre,ape1,ape2 from empleado where ape1 like 'P%' or ape1 like 'Q%' or ape1 like 'R%' or ape1 like 'S%';

select nombre,ape1 from empleado where substr(ape1,1,1) between 'P' and 'S';

--De otra forma

select nombre,ape1 from empleado where substr(ape1,1,1) in ('P','Q','R','S');


/* 11.Obtener los empleados cuyo nombre de pila contenga el nombre JUAN. */

SELECT NOMBRE,APE1 FROM EMPLEADO WHERE NOMBRE='JUAN';
select nombre,ape1 from empleado where nombre like '%JUAN%';
 Select nombre,ape1,ape2 from empleado where upper(nombre) like '%JUAN%';

/* 12.Obtener los nombres de los empleados que viven en ciudades en las que hay algún centro de trabajo */

SELECT NOMBRE,APE1,LOCALIDAD FROM EMPLEADO WHERE UPPER(LOCALIDAD) IN (SELECT UPPER(LOCALIDAD) FROM CENTRO);

UPPER(cad)
Devuelve la cadena cad con todos sus caracteres en mayúsculas.

SELECT * FROM JUEGOS WHERE UPPER(NOMBRE)='AJEDREZ'; 
SELECT * FROM JUEGOS WHERE LOWER(NOMBRE)='ajedrez';

 /* 13.Obtener el nombre del jefe de departamento que tiene mayor salario de entre los jefes de departamento.*/
 
SELECT NOMBRE,APE1,SALARIO FROM EMPLEADO 
WHERE SALARIO=(SELECT MAX(SALARIO) FROM EMPLEADO 
WHERE CODEMPLE IN (SELECT CODEMPLEJEFE FROM DPTO))

 /*14.Obtener en orden alfabético los salarios y nombres de los empleados 
cuyo salario sea superior al 60% del máximo salario de la empresa.*/

SELECT NOMBRE,APE1 SALARIO FROM EMPLEADO 
WHERE SALARIO > (SELECT MAX(SALARIO)*0.6 FROM EMPLEADO);

select salario,nombre, ape1 from empleado where salario > 
(select 0.6* max(salario)  from empleado) order by ape1, nombre;

 /* 15.Obtener en cuántas ciudades distintas viven los empleados*/
 
 SELECT COUNT(DISTINCT LOCALIDAD) AS LOCALIDADES FROM EMPLEADO;

/* 16.El nombre y apellidos del empleado que más salario cobra*/

SELECT NOMBRE,APE1,SALARIO FROM EMPLEADO WHERE SALARIO =(SELECT MAX(SALARIO) FROM EMPLEADO);

--De otra forma

select nombre,ape1,ape2, salario from empleado where salario>=all(select salario from empleado);

/* 17.Obtener las localidades y número de empleados de aquellas en las que viven más de 3 empleados*/

SELECT LOCALIDAD,COUNT(LOCALIDAD) FROM EMPLEADO GROUP BY LOCALIDAD HAVING COUNT(LOCALIDAD)>3;


HAVING Syntax
SELECT column_name(s)
FROM table_name
WHERE condition
GROUP BY column_name(s)
HAVING condition
ORDER BY column_name(s);

/*18 Obtener para cada departamento cuántos empleados trabajan, la suma de sus salarios 
y la suma de sus comisiones para aquellos departamentos en los que hay algún empleado 
cuyo salario es superior a 1700 euros.*/

SELECT CODDPTO, COUNT(CODEMPLE),SUM(SALARIO), SUM(NVL(COMISION,0)) FROM EMPLEADO
GROUP BY CODDPTO HAVING CODDPTO IN
(SELECT DISTINCT CODDPTO FROM EMPLEADO WHERE SALARIO > 1700);

select coddpto, sum(salario) as salarioT, sum(nvl(comision,0)) as comisionT, count(codemple) as numEmple 
from empleado 
group by coddpto
having coddpto=some(select coddpto from empleado where salario>1700);

--De otra forma

select coddpto, sum(salario) as salarioT, sum(nvl(comision,0)) as comisionT, count(codemple) as numEmple 
from empleado 
where coddpto in (select distinct coddpto from empleado where salario>1700)
group by coddpto;

/*19.Obtener el departamento que más empleados tiene*/

Select d.denominacion, count(e.codemple) from dpto d INNER join empleado e on (d.coddpto=e.coddpto) 
GROUP by d.denominacion having count(e.codemple)= (select max(count(*)) from empleado group by coddpto);

select denominacion from dpto,empleado 
where empleado.coddpto=dpto.coddpto 
group by dpto.coddpto,denominacion 
having count(empleado.codemple)>=all(select count(codemple) from empleado group by coddpto);


/* 20.Obtener los nombres de todos los centros y los departamentos que se 
ubican en cada uno,así como aquellos centros que no tienen departamentos *los centros no tienen nombre)*/

select direccion as nomcentro,denominacion as dpto from centro tc left 
join dpto td on tc.codcentro=td.codcentro order by 1,2;

Select c.codcentro, c.localidad, d.denominacion, d.coddpto from 
dpto d LEFT join centro c on (d.codcentro=c.codcentro); 

SELECT DIRECCION, LOCALIDAD, DENOMINACION FROM CENTRO CT LEFT JOIN DPTO DT
ON (CT.CODCENTRO=DT.CODCENTRO);

/*21.Obtener el nombre del departamento de más alto nivel, es decir, aquel que no depende de ningún otro.*/

SELECT DENOMINACION FROM DPTO WHERE coddptodepende IS NULL;
 
/*22.Obtener todos los departamentos existentes en la empresa y 
los empleados (si los tiene) que pertenecen a él.*/

SELECT DENOMINACION AS DEPARTAMENTO,NOMBRE,APE1 FROM DPTO DT 
LEFT JOIN EMPLEADO EM ON (DT.CODDPTO=EM.CODDPTO);

--23.- Obtener un listado en el que aparezcan todos los departamentos existentes
 y el departamento del cual depende,si depende de alguno.

select td.denominacion as nomdpto,  nvl(A.denominacion,' ') as nomdptodelqdepnde
 from dpto td, dpto A
where A.coddpto(+)=td.coddptodepende;

--De otra forma

select dpt1.denominacion as nomdpto, nvl(dptdelqdepende.denominacion,' ') 
as nomdptodelqdepnde from dpto dpt1 left join dpto dptdelqdepende 
on dpt1.coddptodepende=dptdelqdepende.coddpto;

/* 24.Obtener un listado ordenado alfabéticamente donde aparezcan los nombres de los empleados 
 y a continuación el literal "tiene comisión" si la tiene,y "no tiene comisión" si no la tiene.*/
 
Select nombre,ape1,ape2, 
case when comision is null then 'No TIENE COMISION'
else 'Tiene comision' end 
as comision from empleado order by nombre asc; 

/* 25.Obtener un listado de las localidades en las que hay centros 
y no vive ningún empleado ordenado alfabéticamente.*/

select rtrim(ltrim(upper(tc.localidad))) from centro tc
minus
select rtrim(ltrim(upper(te.localidad))) from empleado te order by 1;

--De otra forma

select upper(tc.localidad) from centro tc where rtrim(ltrim(upper(tc.localidad))) 
not in (select distinct rtrim(ltrim(upper(te.localidad))) from empleado te);

 Select codcentro, localidad from centro where upper(localidad) 
 not in(select upper(localidad) from empleado) order by codcentro asc;

--26.- Obtener un listado de las localidades en las que hay centros y además vive al menos un empleado ordenado alfabéticamente.

select distinct rtrim(ltrim(upper(localidad))) from centro
intersect
select distinct rtrim(ltrim(upper(localidad))) from empleado
order by 1 asc;

--De otra forma

select upper(tc.localidad) from centro tc where rtrim(ltrim(upper(tc.localidad))) 
in (select distinct rtrim(ltrim(upper(te.localidad))) from empleado te);

Select nombre,ape1,ape2, localidad from empleado where upper(localidad) 
in(select upper(localidad) from centro) order by localidad,nombre ;////NO ///

--27.- Esta cuestión puntúa por 3. Se desea dar una gratificación por navidades en función de la antigüedad en la empresa siguiendo estas pautas:
-- Si lleva entre 1 y 5 años, se le dará 100 euros
-- Si lleva entre 6 y 10 años, se le dará 50 euros por año
-- Si lleva entre 11 y 20 años, se le dará 70 euros por año
-- Si lleva más de 21 años, se le dará 100 euros por año
--Obtener un listado de los empleados,ordenado alfabéticamente,indicando cuánto le corresponde de gratificación.

--Con union

select nombre,ape1,ape2, 100 as Gratificacion from empleado where trunc((sysdate-fechaingreso)/365) between 1 and 5
union
select nombre,ape1,ape2, 50*trunc((sysdate-fechaingreso)/365) from empleado where trunc((sysdate-fechaingreso)/365) between 6 and 10
union
select nombre,ape1,ape2, 70*trunc((sysdate-fechaingreso)/365) from empleado where trunc((sysdate-fechaingreso)/365) between 11 and 20
union
select nombre,ape1,ape2, 100*trunc((sysdate-fechaingreso)/365) from empleado where (sysdate-fechaingreso)/365>=21
order by 2,3,1;

--Con case, os he añadido formas de cálculo para que tengáis las diferencias entre unas y otras

select nombre,ape1,ape2,fechaingreso,((sysdate-fechaingreso)/365),(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12),EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM fechaingreso),case when trunc((sysdate-fechaingreso)/365) between 1 and 5 then 100
            when trunc((sysdate-fechaingreso)/365) between 6 and 10 then 50*trunc((sysdate-fechaingreso)/365)
            when trunc((sysdate-fechaingreso)/365) between 11 and 20 then 70*trunc((sysdate-fechaingreso)/365)
            when trunc((sysdate-fechaingreso)/365) >=21 then 100*trunc((sysdate-fechaingreso)/365) end as gratificacion
            from empleado where trunc((sysdate-fechaingreso)/365) >=1
            order by 2,3,1;

--29.- Obtener a los nombres, apellidos de los empleados que no son jefes de departamento.

select nombre,ape1,ape2 from empleado where codemple not in (select codemplejefe from dpto);