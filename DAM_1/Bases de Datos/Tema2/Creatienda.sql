--TABLA FAMILIA: Contiene las familias a las que pertenecen los productos, como por ejemplo ordenadores, impresoras,etc. 
CREATE TABLE familia (
    Codfamilia int primary key,
    Denofamilia varchar(50) not null
  );
--TABLA PRODUCTO => contendrá información general sobre los productos que distribuye la empresa a las tiendas.
CREATE TABLE producto (
    Codproducto number(5) PRIMARY KEY  NOT NULL,
    Denoproducto varchar(20) NOT NULL,
    Descripcion varchar(100),
    PrecioBase number(8,2) check(Preciobase>0) NOT NULL  ,
    PorcReposición number (3) check(PorcReposición>0) NOT NULL,
    UnidadesMinimas number (4) check(UnidadesMinimas>0) NOT NULL,
    Codfamilia numeric(3) not null,
    CONSTRAINT PRODUCTO_FK FOREIGN KEY (CODFAMILIA)
    REFERENCES  FAMILIA (CODFAMILIA)
);

--TABLA TIENDA=>contendrá información básica sobre las tiendas que distribuyen los productos.
CREATE TABLE tienda (
    Codtienda number (3) primary key,
    Denotienda varchar(20) not null,
    Telefono varchar(11),
    CodigoPostal varchar(5) not null ,
    Provincia varchar(5) not null
  );

--TABLA STOCK => Contendrá para cada tienda el número de unidades disponibles de cada producto. La clave primaria
--está formada por la concatenación de los campos Codtienda y Codproducto.

CREATE TABLE stock (
    Codtienda number (3) not null,
    Codproducto number (5) not null,
    Unidades number (6) check(Unidades>=0) NOT NULL,
    primary key(Codtienda,Codproducto),
    CONSTRAINT stock_FK1 FOREIGN KEY (Codtienda)
    REFERENCES  tienda (Codtienda),
    CONSTRAINT stock_FK2 FOREIGN KEY (Codproducto)
    REFERENCES  producto (Codproducto)
);
