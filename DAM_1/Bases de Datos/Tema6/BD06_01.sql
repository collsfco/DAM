set SERVEROUTPUT on; --Activamos la salida de texto.
/*Creamos el procedimiento llamando CambiarAgentesFamilia
que recibe 2 parametros id_FamiliaOrigen y id_FamiliaDestino*/
create or replace PROCEDURE CambiarAgentesFamilia(id_FamiliaOrigen in familias.identificador%TYPE, id_FamiliaDestino in familias.identificador%TYPE)
is
    contador_agentes integer; --Declaramos una variable para almacenar los agentes modificados.
    Begin
    if (id_FamiliaOrigen=Id_FamiliaDestino) then --Comprobamos si ambas familias son iguales
        raise_application_error(-20000,'Las familias no pueden ser iguales'); --Si son iguales lanza un error.
    end if;
        -->>>Bloque SQL anidado.
        --Declaramos un cursor de tipo variable llamado cursor_Familia.
        Declare
        TYPE cursor_Familia is REF CURSOR RETURN familias%ROWTYPE;
        cFamilia cursor_Familia;
        familia cFamilia%ROWTYPE;
        
        BEGIN
        -- Iniciamos el cursor para comprobar que existe el id de Familia de origen.
        OPEN cFamilia for select * from familias where identificador=id_FamiliaOrigen;
            FETCH cFamilia into familia;
            if (cFamilia%NOTFOUND) then --Si no existe lanza un error
                raise_application_error(-20001,'Familia origen no existe');
            end if;
        close cFamilia;
         -- Iniciamos el cursor para comprobar que existe el id de Familia de destino.
        OPEN cFamilia for select * from familias where identificador=id_FamiliaDestino;
            FETCH cFamilia into familia;
            if (cFamilia%NOTFOUND) then --Si no existe lanza un error
                raise_application_error(-20002,'Familia destino no existe');
            end if;
        close cFamilia;
        end;
        /*
        select count(*) into contador_agentes from agentes where familia=id_familiaorigen;
        */
        --Si ambas familias existen procedemos a actualizar el id.
        update agentes SET familia=id_familiadestino where familia=id_familiaorigen;
        
        --Obtenemos el número de filas que fueron modificadas.
        contador_agentes:=sql%ROWCOUNT;
        -- Si el número de filas modificadas es 0, eso quiere decir que no hay agentes en la familia de origen.
        if (contador_agentes=0) then
            raise_application_error(-20003,'No hay agentes en la famila de origen');
        end if;
        
        dbms_output.put_line('Se han trasladado ' ||contador_agentes||' agentes de la familia ' ||id_familiaorigen
                                || ' a la familia '||id_familiadestino);
    end CambiarAgentesFamilia;
    

  
 
execute  CambiarAgentesFamilia(&Familia_Origen,&Familia_Destino);

drop trigger t_agentes;
        