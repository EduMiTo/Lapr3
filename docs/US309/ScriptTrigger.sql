create or replace Trigger US309s 

Before Insert
ON Trip
FOR EACH ROW

DECLARE

    cargos INTEGER;
    flag BOOLEAN:=false;
    ships VARCHAR(9);


Cursor cml IS
    select id
    from CargoManifestLoad
    Where IsConcluded IS NULL
    And shipmmsi =ships;

    BEGIN

    SELECT shipmmsi into ships
    From CargoManifestLoad
    Where id=:NEW.cargomanifestloadid;

    open cml;
    Loop
        FETCH cml INTO cargos;
        EXIT WHEN cml%notfound;

        For loop
        IN(SELECT realDepartureDate, realArrivalDate
        FROM Trip
        Where cargomanifestloadid=cargos)
        LOOP
            IF :NEW.expectedDepartureDate > loop.realDepartureDate AND :NEW.expectedDepartureDate < loop.realArrivalDate THEN

                flag:= true;


            END IF;
        END LOOP;
    END LOOP;

    IF flag = true THEN
        raise_application_error(-20000,'Ship is busy');
    END IF;

    END;