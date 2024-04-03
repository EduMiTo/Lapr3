 -- US208 --
CREATE OR REPLACE PROCEDURE US208 (CargoManifestloadedId in INTEGER, shipmmsi in Varchar, occupancyRate out FLOAT) IS

    totalContainers INTEGER;
    shipCapacity FLOAT;

    BEGIN

    SELECT COUNT (*) INTO totalContainers
    FROM CargoManifest_Container 
    WHERE cargoManifestLoadId = CargoManifestloadedId;

    SELECT capacity INTO shipCapacity
    FROM Ship
    WHERE mmsi = shipmmsi;

    occupancyRate:= (totalContainers/shipCapacity)*100;

END;