--Creamos un trigger llamado t_agentes.
create or replace trigger t_agentes
before insert or UPDATE on agentes --El trigger se activa antes de insertar o actualizar la tabla agentes.
for each row
begin
    if (length(:new.clave)<6) then --Si la longitud es menor a 6 lanza un error
        raise_application_error(-20004,'La longitud de la clave de un agente no puede ser inferior a 6.');
    end if;
    if (:new.habilidad <0 OR :new.habilidad >9) then
        raise_application_error(-20005,'La habilidad de un agente debe estar comprendida entre 0 y 9');
    end if;
    
    if (:new.categoria <0 OR :new.categoria >2) then
        raise_application_error(-20006,'La categoría de un agente sólo puede ser igual a 0, 1 o 2.');
    end if;
    
    if (:new.categoria=2) then
        if :new.familia is not null then
            raise_application_error(-20007,'No puede pertenecer a ninguna familia ');
        elsif :new.oficina is null then
            raise_application_error(-20008, 'Debe pertenecer a una oficina.');
        end if;
    end if;
    
    if (:new.categoria=1) then
        if (:new.oficina is not null) then
            raise_application_error(-20009,'No puede pertenecer a ninguna oficina ');
        elsif (:new.familia is null) then
            raise_application_error(-20010, 'Debe pertenecer a una familia');
        end if;
    end if;
    
    if (:new.oficina is null and :new.familia is null) then
        raise_application_error(-20011,'Todos los agentes deben pertenecer  a una oficina o a una familia pero nunca a ambas a la vez');
    elsif (:new.oficina is not null and :new.familia is not null) then
        raise_application_error(-20012,'Todos los agentes deben pertenecer  a una oficina o a una familia pero nunca a ambas a la vez');
    end if;
               
end t_agentes;



update agentes set habilidad=25 where identificador=311;

update agentes set categoria=-1 where identificador=311;

update agentes set clave=123 where identificador=315;

update agentes set oficina=3 where identificador=311;
