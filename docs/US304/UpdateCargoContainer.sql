CREATE OR REPLACE PROCEDURE UpdateCargoContainer(cmId in INTEGER,cont in Integer, gross in FLOAT, staff in VARCHAR)
    IS

    BEGIN

    UPDATE CargoManifest_Container
    SET grossContainer = gross,
        changesresponsible = staff
    WHERE containerId = cont AND cargomanifestloadId= cmId;
    

    END;