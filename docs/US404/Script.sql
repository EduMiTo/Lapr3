CREATE OR REPLACE PROCEDURE US404a (outString OUT VARCHAR2) IS

    shipsMmsiCode VARCHAR(9);
    cmship INTEGER;
    flag BOOLEAN;
    idleTime Integer:=0;
    allCml Integer;

    CURSOR ships IS
    SELECT mmsi
    FROM Ship;

    CURSOR cm IS
    SELECT CargoManifestLoad.id
    FROM CargoManifestLoad
    INNER JOIN Trip
    ON (cargoManifestLoad.Id = Trip.CargoManifestLoadId)
    WHERE shipmmsi = shipsMmsiCode
    AND (EXTRACT (YEAR FROM expectedArrivalDate) = EXTRACT(YEAR FROM SYSDATE)
    OR EXTRACT (YEAR FROM expectedDepartureDate) = EXTRACT(YEAR FROM SYSDATE))
    Group by cargomanifestload.id;

    BEGIN
        OPEN ships;
        LOOP
        FETCH ships INTO shipsMmsiCode;
        EXIT WHEN ships%notFound;
        
            flag := false;
            OPEN cm;
            LOOP
            FETCH cm INTO cmship;
            EXIT WHEN cm%notFound;

                flag := true;

                FOR trips IN
                (SELECT id, expectedDepartureDate, expectedArrivalDate
                FROM Trip
                WHERE cargoManifestLoadId = cmship
                AND EXTRACT(YEAR FROM expectedDepartureDate) = EXTRACT(YEAR FROM SYSDATE))
                LOOP
                      
                    select cast(trips.expectedArrivalDate as date) - cast(trips.expectedDepartureDate as date) + idleTime into idleTime from dual;
                    dbms_output.put_line('a' || idleTime);

                       -- idleTime := idleTime + (trips.expectedDepartureDate - expectedArrivalDateAux);
                    
                END LOOP;
               
            END LOOP;
            
            close cm;
          
            IF flag = false THEN
            
                SELECT COUNT(*) INTO allCml
                FROM CargoManifestLoad
                WHERE shipmmsi = shipsMmsiCode
                AND isConcluded = 1;
                
                IF allCml = 0 THEN
                    outString := outString || 'The ship: ' || shipsMmsiCode || ' was idle for the entire year.' || chr(10);    
                ELSE
                    outString := outString || 'The ship: ' || shipsMmsiCode || ' wasnt in idle during this year.' || chr(10);
                END IF;
            ELSE

                outString := outString || 'The ship: ' || shipsMmsiCode || ' was idle for : ' || (365-idleTime) ||' days.' ||  chr(10);
            END IF;

        END LOOP;
        close ships;


    END;



set serveroutput on;
    Declare
    outstring varchar2(5000);
    begin
        us404a(outstring);
        dbms_output.put_line(outstring);

    end;