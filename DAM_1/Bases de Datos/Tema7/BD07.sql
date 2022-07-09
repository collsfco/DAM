--Actividad 1.
/*Crea el tipo de objetos "Personal" con los siguientes atributos*/
CREATE OR REPLACE TYPE Personal AS OBJECT(
    codigo INTEGER,
    dni VARCHAR2(10),
    nombre VARCHAR2(30),
    apellidos VARCHAR2(30),
    sexo VARCHAR2(1),
    fecha_nac DATE
)NOT FINAL;

/*Crea, como tipo heredado de "Personal", el tipo de objeto "Responsable" con los siguientes atributos:*/
CREATE OR REPLACE TYPE Responsable UNDER Personal(
    tipo CHAR,
    antiguedad INTEGER
);

/*Crea el tipo de objeto "Zonas" con los siguientes atributos:*/
CREATE OR REPLACE TYPE Zonas force AS OBJECT(
    codigo INTEGER,
    nombre VARCHAR2(20),
    refRespon REF Responsable,
    codigoPostal CHAR(5)
);

/*Crea, como tipo heredado de "Personal", el tipo de objeto "Comercial" con los siguientes atributos:*/
CREATE OR REPLACE TYPE Comercial UNDER Personal(
    zonaComercial Zonas
);


--Actividad 2.
/*Crea un método constructor para el tipo de objetos "Responsable", en el que se indiquen como parámetros el código, 
nombre, primer apellido, segundo apellido y tipo. Este método debe asignar al atributo apellidos los datos de primer 
apellido y segundo apellido que se han pasado como parámetros, uniéndolos con un espacio entre ellos.*/

--drop type zonas force;

/*Usamos FORCE para forzar la actualizacion de Responsable, ya que Zonas depende de responsable
y genera error al momento de tratar de actualizar.*/
CREATE OR REPLACE TYPE Responsable FORCE UNDER Personal(
    tipo CHAR,
    antiguedad INTEGER,
    --Método constructor.
    CONSTRUCTOR FUNCTION Responsable (codigo INTEGER,nombre VARCHAR2, primerApellido VARCHAR2,
    segundoApellido VARCHAR2, tipo CHAR)
    RETURN SELF AS RESULT
);

--Declaración del método contructor de Responsable (BODY).
CREATE OR REPLACE TYPE BODY Responsable AS
    CONSTRUCTOR FUNCTION Responsable (codigo INTEGER,nombre VARCHAR2, primerApellido VARCHAR2,
    segundoApellido VARCHAR2, tipo CHAR)        
    RETURN SELF AS RESULT
    IS
    BEGIN
        self.codigo:=codigo;
        self.nombre:=nombre;
        self.apellidos:=primerapellido ||' '|| segundoapellido;
        self.tipo:=tipo;
        RETURN;
    END;
END;


--Actividad 3.
/*Crea un método getNombreCompleto para el tipo de objetos Responsable que permita obtener su nombre 
completo con el formato apellidos nombre*/
CREATE OR REPLACE TYPE Responsable FORCE UNDER Personal(
    tipo CHAR,
    antiguedad INTEGER,
    --Método constructor.
    CONSTRUCTOR FUNCTION Responsable (codigo INTEGER,nombre VARCHAR2, primerApellido VARCHAR2,
    segundoApellido VARCHAR2, tipo CHAR)
    RETURN SELF AS RESULT,
    --Declaramos el método getNombreCompleto
    MEMBER FUNCTION getNombreCompleto RETURN VARCHAR2
);
--Declaración del BODY de Responsable.
CREATE OR REPLACE TYPE BODY Responsable AS
    CONSTRUCTOR FUNCTION Responsable (codigo INTEGER,nombre VARCHAR2, primerApellido VARCHAR2,
    segundoApellido VARCHAR2, tipo CHAR)
    RETURN SELF AS RESULT 
    IS
    BEGIN
        self.codigo:=codigo;
        self.nombre:=nombre;
        self.apellidos:=primerapellido ||' '|| segundoapellido;
        self.tipo:=tipo;
        RETURN;
    END;
    --Agregamos el método getNombreCompleto
    MEMBER FUNCTION getNombreCompleto RETURN VARCHAR2
    IS
    BEGIN
        RETURN apellidos ||' '||nombre;
    END getNombreCompleto;
END;


--Actividad 4.
/*Crea un tabla TablaResponsables de objetos  Responsable. Inserta en dicha tabla dos objetos  Responsable.*/
CREATE TABLE TablaResponsables OF Responsable;

INSERT INTO tablaresponsables VALUES(Responsable(5,'51083099F','Elena','Posta Llanos','F','31/03/1975','N',4));
/*El segundo objeto "Responsable" debes crearlo usando el método constructor que has realizado anteriormente. Debes usar los siguientes datos:*/
INSERT INTO tablaresponsables VALUES(Responsable(6,'Javier','Jaramillo','Hernandez','C'));


--Actividad 5.
/*Crea una colección VARRAY llamada ListaZonas en la que se puedan almacenar hasta 10 objetos Zonas. . 
Guarda en una instancia listaZonas1 de dicha lista, dos Zonas*/
CREATE OR REPLACE TYPE ListaZonas IS VARRAY(10) OF Zonas;

DECLARE
    zona1 Zonas;
    zona2 Zonas;
    listaZonas1 ListaZonas;
    refRespon REF Responsable;
BEGIN
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.codigo=5;
    zona1:= NEW Zonas(1,'zona 1',refRespon,'06834');
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.dni='51083099F';
    zona2:= NEW Zonas(2,'zona 2',refRespon,'28003');
    
    listazonas1:= NEW ListaZonas(zona1, zona2);
    --dbms_output.put_line('zona1-' || listazonas1(3).codigo  || ' zona2-' || listazonas1(2).nombre );
END;

--Actividad 6.
/*Crea una tabla TablaComerciales de objetos Comercial. Inserta en dicha tabla las siguientes filas*/
CREATE TABLE TablaComerciales OF Comercial;

DECLARE
    zona1 Zonas;
    zona2 Zonas;
    listaZonas1 ListaZonas;
    refRespon REF Responsable;
BEGIN
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.codigo=5;
    zona1:= NEW Zonas(1,'zona 1',refRespon,'06834');
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.dni='51083099F';
    zona2:= NEW Zonas(2,'zona 2',refRespon,'28003');
    
    listazonas1:= NEW ListaZonas(zona1, zona2);
    
    INSERT INTO tablacomerciales VALUES (Comercial(100,'23401092Z','Marcos','Suarez Lopez','M','30/03/1990',zona1));
    INSERT INTO tablacomerciales VALUES (Comercial(102,'6932288V','Anastasia','Gomes Perez','F','28/11/1984',listazonas1(2)));
    
END;

--Actividad 7.
/*Obtener, de la tabla TablaComerciales, el Comercial que tiene el código 100, asignándoselo a una variable unComercial */
DECLARE
    unComercial Comercial;
BEGIN
SELECT VALUE(c) INTO unComercial FROM tablacomerciales c WHERE c.codigo=100;
dbms_output.put_line(unComercial.nombre||' '||unComercial.apellidos );
END;

--Actividad 8.
/*Modifica el código del Comercial guardado en esa variable unComercial asignando el valor 101, y su zona debe ser la segunda
que se había creado anteriormente. Inserta ese Comercial en la tabla TablaComerciales  */

DECLARE
    zona1 Zonas;
    zona2 Zonas;
    refRespon REF Responsable;
    unComercial Comercial;
BEGIN
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.codigo=5;
    zona1:= NEW Zonas(1,'zona 1',refRespon,'06834');
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.dni='51083099F';
    zona2:= NEW Zonas(2,'zona 2',refRespon,'28003');
    
    SELECT VALUE(c) INTO unComercial FROM tablacomerciales c WHERE c.codigo=100;
    unComercial.codigo:=101;
    unComercial.zonacomercial:= zona2;

    INSERT INTO tablacomerciales VALUES (unComercial);
    
END;

--Actividad 9.
/*Crea un método MAP ordenarZonas para el tipo Zonas. Este método debe retornar el nombre completo del Responsable 
al que hace referencia cada zona. Para obtener el nombre debes utilizar el método getNombreCompleto que se ha creado anteriormente*/

--NO DEJA ACTUALIZAR PORQUE LA TABLACOMERCIALES TIENE CONTENIDO.....
drop type zonas force;

CREATE OR REPLACE TYPE Zonas force AS OBJECT(
    codigo INTEGER,
    nombre VARCHAR2(20),
    refRespon REF Responsable,
    codigoPostal CHAR(5),
    MAP MEMBER FUNCTION ordenarZonas RETURN VARCHAR2
);

CREATE OR REPLACE TYPE BODY Zonas AS
    MAP MEMBER FUNCTION ordenarZonas RETURN VARCHAR2
    IS
        unResponsable Responsable;
    BEGIN
        SELECT DEREF(refRespon) INTO unResponsable FROM DUAL;
        
        RETURN (unResponsable.getNombreCompleto());
    END;
END;

--Actividad 10.

/*Realiza una consulta de la tabla TablaComerciales ordenada por zonaComercial para comprobar el funcionamiento del método MAP.  */
SELECT tc.codigo, tc.dni, tc.nombre, tc.apellidos, tc.sexo, tc.fecha_nac, 
       tc.zonacomercial.ordenarZonas() as ZonaComercial FROM tablacomerciales tc ORDER BY tc.zonacomercial.ordenarZonas();--NO
       
select * from tablacomerciales order by zonacomercial.ordenarZonas();
