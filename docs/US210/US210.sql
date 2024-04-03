create or replace PROCEDURE US210 (output out VARCHAR2) IS

    ships VARCHAR(255);
    nextMonday DATE;
    cont INTEGER;
    arrivalDestination VARCHAR(255);
    nTrips INTEGER;
    arrivalDate DATE;
    lattestDate DATE;
    slatitude VARCHAR(255);
    slongitude VARCHAR(255);
    cargoManifestLoadedID INTEGER;
    totalCargoManifests INTEGER;
    counter INTEGER;
    finalDestination VARCHAR(255);
    flag BOOLEAN;

    CURSOR allShips IS
    SELECT mmsi FROM Ship;

    CURSOR cargoManifestsLoaded IS
    SELECT id FROM CargoManifestLoad
    WHERE shipMmsi = ships;


BEGIN

    SELECT NEXT_DAY(sysdate,'SEGUNDA') INTO nextMonday FROM dual;

    output := output || 'Ships ready in next Monday (' || nextMonday || ')' || chr(10);

    OPEN allShips;

    LOOP
        FETCH allShips INTO ships;
        EXIT WHEN allShips%NOTFOUND;

        flag := false;
        nTrips := 0;
        cont := 0;

        OPEN cargoManifestsLoaded;

        LOOP
            FETCH cargoManifestsLoaded into cargoManifestLoadedID;
            EXIT WHEN cargoManifestsLoaded%NOTFOUND;

            flag := true;


            SELECT COUNT (*) INTO nTrips
            FROM Trip
            WHERE cargoManifestLoadId = cargoManifestLoadedID;

            SELECT expectedArrivalDate INTO arrivalDate
            FROM Trip
            WHERE id = nTrips
            AND cargoManifestLoadId = cargoManifestLoadedID;

            IF (arrivalDate < nextMonday) THEN
                cont := cont + 1;
            END IF;
        END LOOP;
        --Verificar se cont (conta todos os cm que acabam antes da proxima segunda) = total cargos--
        CLOSE cargoManifestsLoaded;
        IF flag=true Then
            SELECT COUNT (*) INTO totalCargoManifests
            FROM CargoManifestLoad
            WHERE shipMmsi = ships;

            IF cont = totalCargoManifests THEN
                counter := 0;
                OPEN cargoManifestsLoaded;
                LOOP

                    FETCH cargoManifestsLoaded into cargoManifestLoadedID;
                    EXIT WHEN cargoManifestsLoaded%NOTFOUND;

                    SELECT COUNT (*) INTO nTrips
                    FROM Trip
                    WHERE cargoManifestLoadId = cargoManifestLoadedID;


                    SELECT expectedArrivalDate, destination INTO arrivalDate, arrivalDestination
                    FROM Trip
                    WHERE id = nTrips
                    AND cargoManifestLoadId = cargoManifestLoadedID;

                    counter := counter + 1;

                    --Definir na primeira vez os valores para a comparaçao--
                    IF counter = 1 THEN
                        finalDestination := arrivalDestination;
                        lattestDate := arrivalDate;
                    END IF;
                    IF counter > 1 AND arrivalDate > lattestDate THEN
                        finalDestination := arrivalDestination;
                        lattestDate := arrivalDate;
                    END IF;
                END LOOP;

                CLOSE cargoManifestsLoaded;

                 output:= output || 'MMSI: '|| ships || ' Located at --> ' ||finalDestination|| chr(10);

            END IF;
        END IF;


        IF flag = false THEN

            SELECT latitude, longitude INTO slatitude, slongitude
            FROM ShipPosition
            WHERE shipMmsi = ships
            AND dateaismessage= (Select MAX(dateaismessage) from ShipPosition Where shipMmsi = ships);

            output := output || 'MMSI: '|| ships || ' Located at --> Latitude: ' || slatitude || ' Longitude: ' || slongitude || chr(10);
        END IF;
    END LOOP;
    CLOSE allShips;
END;