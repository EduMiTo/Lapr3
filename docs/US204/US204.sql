--US204--

set serveroutput on;

CREATE OR REPLACE FUNCTION US204 (containerNumberid INT) RETURN VARCHAR
IS
    cargoscode INT;
    cmucmu INT;
    v_shipmmsi INT;
    v_shipname VARCHAR(255);
    v_trip INT;
    v_datareal TIMESTAMP;
    v_portid INT;
    v_portname VARCHAR(255);
    v_datarealatual TIMESTAMP;
    v_cargoFinal integer;
    v_destination Varchar(255);
    counter Integer:=0;

    Cursor cargos IS
        Select cargoManifestLoadid
        from CargoManifest_Container
        where containerid=containerNumberid
        AND containerid=containerNumberid;
BEGIN


   open cargos;
    LOOP 
        fetch cargos INTO cargoscode;
        Exit When cargos%notfound;
        SELECT CargoManifestUnloadid INTO cmucmu
        FROM CargoManifest_Container
        WHERE cargoManifestLoadid=cargoscode
        AND containerId=containerNumberId;

            
            
            IF cmucmu is NULL THEN

            SELECT shipmmsi INTO v_shipmmsi
            FROM CargoManifestLoad
            WHERE id=cargoscode;

            SELECT name INTO v_shipname
            FROM Ship 
            WHERE mmsi=v_shipmmsi;

            return ('SHIP: ' || v_shipname);
            END IF;

        END LOOP;
        CLOSE cargos;

    open cargos;
    LOOP 
        fetch cargos INTO cargoscode;
        Exit When cargos%notfound;
        
        SELECT tripid INTO v_trip
        FROM CargoManifest_Container
        WHERE cargomanifestloadid=cargoscode
        AND containerid=containerNumberid;

        SELECT realArrivalDate INTO v_datareal
        FROM Trip
        WHERE cargoManifestLoadid=cargoscode
        AND id= v_trip;
                   
        counter := counter +1;
        
        IF counter = 1  then
            v_datarealatual:= v_datareal;
            v_cargoFinal:= cargoscode;
        ELSE IF counter > 1 AND v_datareal > v_datarealatual THEN
            v_datarealatual := v_datareal;
            v_cargoFinal:= cargoscode;
            END IF;
        END IF;
        
        END LOOP;
        CLOSE cargos;

        select destination INTO v_destination
        From Trip
        Where realArrivalDate = v_datarealatual
        AND cargomanifestloadId= v_cargoFinal;

        return ('Port: ' || v_destination);
END;
