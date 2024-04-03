create or replace PROCEDURE US407(idport IN INTEGER, outString OUT CLOB) IS

    nextSunday DATE;
    nextMonday DATE;
    cml INTEGER;
    portName VARCHAR(255);
    containersDeparting INTEGER;
    containersArriving INTEGER;
    totalDep INTEGER := 0;
    totArr INTEGER := 0;
    output VARCHAR2(5000);
    contdays Integer :=0;
    currentDay Integer;



    CURSOR cargoManifestsLoad IS
    SELECT cargoManifestLoad.id
    FROM CargoManifestLoad
    INNER JOIN Trip
    ON (cargoManifestLoad.Id = Trip.CargoManifestLoadId)
    WHERE (expectedArrivalDate <= nextSunday
    AND expectedArrivalDate >= nextMonday)
    OR (expectedDepartureDate <= nextSunday
    AND expectedDepartureDate >= nextMonday)
    Group by cargomanifestload.id;

    BEGIN
        dbms_lob.createTemporary(outString, true);
        SELECT NEXT_DAY(sysdate,'Segunda') INTO nextMonday FROM DUAL;
        SELECT NEXT_DAY(nextMonday,'Domingo') INTO nextSunday FROM DUAL;

        SELECT name INTO portName FROM Port WHERE id = idport;
        output := 'Port with name ' || portName || ' in the week of the days ' || nextMonday || ' until the day ' || nextSunday || chr(10);
        dbms_lob.append(outString, output);

        WHILE contDays < 7
        LOOP
        currentDay := EXTRACT (DAY FROM nextMonday) + contDays;

        IF currentDay > 31 THEN
            currentDay := currentDay - 31;
        END IF;
        output :='In the Day: ' || currentDay || chr(10);
        dbms_lob.append(outString, output);


            OPEN cargoManifestsLoad;
            LOOP

                FETCH cargoManifestsLoad INTO cml;
                EXIT WHEN cargoManifestsLoad%notFound;

                FOR tripss IN
                (SELECT origin, destination, id, expectedArrivalDate, expectedDepartureDate
                FROM Trip
                WHERE cargoManifestLoadId = cml
                AND EXTRACT (DAY FROM expectedArrivalDate) = currentDay)
                LOOP

                    IF tripss.destination = portName THEN


                        SELECT COUNT(*) INTO containersArriving
                        FROM CargoManifest_Container
                        WHERE cargoManifestLoadId = cml
                        AND tripId = tripss.id;

                        totArr := totArr + containersArriving;

                        FOR allContainersArriving IN
                        (SELECT containerId
                        FROM CargoManifest_Container
                        WHERE cargoManifestLoadId = cml
                        AND tripId = tripss.id)
                        LOOP
                            output :=  'Container number id: ' || allContainersArriving.containerId || ' was arriving in the port, ' ;
                            dbms_lob.append(outString, output);

                        END LOOP;
                    END IF;
                    END LOOP;

                    FOR tripss2 IN
                    (SELECT origin, destination, id, expectedArrivalDate, expectedDepartureDate
                    FROM Trip
                    WHERE cargoManifestLoadId = cml
                    AND EXTRACT (DAY FROM expectedDepartureDate) = currentDay)
                    Loop

                    IF tripss2.origin = portName THEN

                        SELECT COUNT(*) INTO containersDeparting
                        FROM CargoManifest_Container
                        WHERE cargoManifestLoadId = cml
                        AND tripId = tripss2.id;

                        totalDep := totalDep + containersDeparting;

                        FOR allContainersDeparting IN
                        (SELECT containerId
                        FROM CargoManifest_Container
                        WHERE cargoManifestLoadId = cml
                        AND tripId = tripss2.id)
                        LOOP
                            output :=  'Container number id: ' || allContainersDeparting.containerid || ' was leaving the port with destination: ' ||tripss2.destination||  ', ';
                            dbms_lob.append(outString, output);

                        END LOOP;
                    END IF;


                END LOOP;

            END LOOP;

            output := chr(10) || 'The total number of containers who arrived in the port was: '|| totArr || chr(10);
            dbms_lob.append(outString, output);

            output :=  'The total number of containers who left the port was: ' || totalDep || chr(10);
            dbms_lob.append(outString, output);

            totalDep := 0;
            totArr := 0;
            close cargoManifestsLoad;

            contDays:= contDays +1 ;

        END LOOP;

    END; 