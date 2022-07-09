--Script.
/*Borrar datos existentes*/
DROP TABLE TABLACOMERCIALES;
DROP TABLE TABLARESPONSABLES;
DROP TYPE PERSONAL FORCE;
DROP TYPE RESPONSABLE FORCE;
DROP TYPE ZONAS FORCE;
DROP TYPE COMERCIAL;
DROP TYPE LISTAZONAS;

--ACTIVIDAD 1.
/*Crear objetos y heredados (PERSONAL,RESPONSABLE,ZONAS,COMERCIAL)*/

CREATE OR REPLACE TYPE Personal AS OBJECT(
    codigo INTEGER,
    dni VARCHAR2(10),
    nombre VARCHAR2(30),
    apellidos VARCHAR2(30),
    sexo VARCHAR2(1),
    fecha_nac DATE
)NOT FINAL;
/
CREATE OR REPLACE TYPE Responsable UNDER Personal(
    tipo CHAR,
    antiguedad INTEGER,
    
    --ACTIVIDAD 2.
        /*Crea un m�todo constructor para el tipo de objetos "Responsable", en el que se indiquen como par�metros el c�digo, 
    nombre, primer apellido, segundo apellido y tipo. Este m�todo debe asignar al atributo apellidos los datos de primer 
    apellido y segundo apellido que se han pasado como par�metros, uni�ndolos con un espacio entre ellos.*/
    --M�todo constructor.
    CONSTRUCTOR FUNCTION Responsable (codigo INTEGER,nombre VARCHAR2, primerApellido VARCHAR2,segundoApellido VARCHAR2, tipo CHAR)
    RETURN SELF AS RESULT,
    
    --ACTIVIDAD 3.
        /*Crea un m�todo getNombreCompleto para el tipo de objetos Responsable que permita obtener su nombre completo 
    con el formato apellidos nombre*/
    --Declaramos el m�todo getNombreCompleto
    MEMBER FUNCTION getNombreCompleto RETURN VARCHAR2
);
/
--Declaraci�n del BODY de Responsable.
CREATE OR REPLACE TYPE BODY Responsable AS
    --Constructor ACTIVIDAD 2
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
    --M�todos getNombreCompleto ACTIVIDAD  3 
    --Agregamos el m�todo getNombreCompleto
    MEMBER FUNCTION getNombreCompleto RETURN VARCHAR2
    IS
    BEGIN
        RETURN apellidos ||' '||nombre;
    END getNombreCompleto;
END;
/
CREATE OR REPLACE TYPE Zonas AS OBJECT(
    codigo INTEGER,
    nombre VARCHAR2(20),
    refRespon REF Responsable,
    codigoPostal CHAR(5),
    
    --ACTIVIDAD 9.
    /*Crea un m�todo MAP ordenarZonas para el tipo Zonas. Este m�todo debe retornar el nombre completo del Responsable 
    al que hace referencia cada zona. Para obtener el nombre debes utilizar el m�todo getNombreCompleto que se ha creado 
    anteriormente*/
    MAP MEMBER FUNCTION ordenarZonas RETURN VARCHAR2
);
/
--BODY de Zonas.
CREATE OR REPLACE TYPE BODY Zonas AS
    --ACTIVIDAD 9.
    MAP MEMBER FUNCTION ordenarZonas RETURN VARCHAR2
    IS
        unResponsable Responsable;
    BEGIN
        SELECT DEREF(refRespon) INTO unResponsable FROM DUAL;
        RETURN unResponsable.getNombreCompleto();
    END ordenarZonas;
END;
/
 --ACTIVIDAD 1
CREATE OR REPLACE TYPE Comercial UNDER Personal(
    zonaComercial Zonas
);
/
--ACTIVIDAD 4.
/*Crea un tabla TablaResponsables de objetos  Responsable. Inserta en dicha tabla dos objetos  Responsable. */
CREATE TABLE TablaResponsables OF Responsable;
INSERT INTO tablaresponsables VALUES(Responsable(5,'51083099F','Elena','Posta Llanos','F','31/03/1975','N',4));
/*El segundo objeto "Responsable" debes crearlo usando el m�todo constructor que has realizado anteriormente. */
INSERT INTO tablaresponsables VALUES(Responsable(6,'Javier','Jaramillo','Hernandez','C'));

--ACTIVIDAD 5
/*Crea una colecci�n VARRAY llamada ListaZonas en la que se puedan almacenar hasta 10 objetos Zonas.  
Guarda en una instancia listaZonas1 de dicha lista, dos Zonas*/
CREATE OR REPLACE TYPE ListaZonas IS VARRAY(10) OF Zonas;
/
CREATE TABLE TablaComerciales OF Comercial; --ACTIVIDAD 6 /*Crea una tabla TablaComerciales de objetos Comercial.*/

DECLARE
    zona1 Zonas; --refResponsable: Referencia al responsable  cuyo codigo es 5
    zona2 Zonas; --refResponsable: Referencia al responsable cuyo DNI es 51083099F.
    listaZonas1 ListaZonas;
    refRespon REF Responsable;
    unComercial Comercial; --ACTIVIDAD 7 - 8
BEGIN
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.codigo=5;
    zona1:= NEW Zonas(1,'zona 1',refRespon,'06834');
    SELECT REF(r) INTO refRespon FROM tablaresponsables r WHERE r.dni='51083099F';
    zona2:= NEW Zonas(2,'zona 2',refRespon,'28003');
    
    listazonas1:= NEW ListaZonas(zona1, zona2);
    
    --ACTIVIDAD 6
    /*zonacomercial: objeto creado anteriormente para la zona 1*/
    INSERT INTO tablacomerciales VALUES (Comercial(100,'23401092Z','Marcos','Suarez Lopez','M','30/03/1990',zona1));
    /*zonacomercial: objeto que se encuentre en la segunda posici�n de "listaZonas1" */
    INSERT INTO tablacomerciales VALUES (Comercial(102,'6932288V','Anastasia','Gomes Perez','F','28/11/1984',listazonas1(2)));
    
    --ACTIVIDAD 7
    /* Obtener, de la tabla TablaComerciales, el Comercial que tiene el c�digo 100, asign�ndoselo a una variable unComercial */
    SELECT VALUE(c) INTO unComercial FROM tablacomerciales c WHERE c.codigo=100; --ACTIVIDAD 7
    --ACTIVIDAD 8
    /*Modifica el c�digo del Comercial guardado en esa variable unComercial asignando el valor 101, y su zona debe ser la segunda 
    que se hab�a creado anteriormente. Inserta ese Comercial en la tabla TablaComerciales*/
    unComercial.codigo:=101; 
    unComercial.zonacomercial:= zona2; 
    INSERT INTO tablacomerciales VALUES (unComercial);
END;
/
--ACTIVIDAD 10.
/*Realiza una consulta de la tabla TablaComerciales ordenada por zonaComercial para comprobar el funcionamiento del m�todo MAP*/
SELECT * FROM tablacomerciales ORDER BY zonacomercial;
