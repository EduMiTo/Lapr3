--US207--

set serveroutput on;

CREATE OR REPLACE PROCEDURE US207 (givenYear in INTEGER, mmsiCode in VARCHAR, totalCargoManifest out INTEGER, media out FLOAT) 
IS
    CURSOR cargoManifestsLoaded IS
    SELECT id
    FROM cargoManifestLoad
    WHERE shipMmsi = mmsiCode
    AND isConcluded = 1;
    lattestTrip INTEGER;
    yearOfCargo INTEGER;
    containerCargo INTEGER := 0;
    totalContainers INTEGER := 0;
    arrivalDate date;
    CargoManifestloadedid integer;


BEGIN
    totalCargoManifest := 0;
    OPEN cargoManifestsLoaded;
    LOOP
        FETCH cargoManifestsLoaded INTO CargoManifestloadedid;
        EXIT WHEN cargoManifestsLoaded%NOTFOUND;

        SELECT realArrivalDate into arrivalDate
        FROM Trip
        WHERE cargoManifestLoadId = CargoManifestloadedid
        AND id = (select MAX(id) from trip where cargoManifestLoadId = CargoManifestloadedid);

        SELECT EXTRACT(YEAR FROM TO_DATE(arrivalDate,  'YY.MM.DD HH24:MI:SS')) INTO yearOfCargo
        FROM DUAL;

        IF yearOfCargo = givenYear THEN
            SELECT COUNT (*) INTO containerCargo
            FROM cargoManifest_Container
            WHERE cargoManifestLoadId = CargoManifestloadedid;
            totalContainers := totalContainers + containerCargo;

            totalCargoManifest := totalCargoManifest + 1;

        END IF;

    END LOOP;
    Close cargoManifestsLoaded;

    IF totalContainers = 0 OR totalCargoManifest = 0 THEN 
        media := 0;
    ELSE
        media := totalContainers/totalCargoManifest;
    END IF;

END;

Declare
contadorReal Integer;
somaTotalContainer Integer;
Begin
    US207('2004','683407693', contadorReal, somaTotalContainer);
    dbms_output.put_line('containers: ' ||somaTotalContainer);
    dbms_output.put_line('cargos: ' ||contadorReal);

End;