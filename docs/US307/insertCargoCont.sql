create or replace Procedure insertCargoContainer(cargoId In Integer, containerId in Integer, tripId in Integer, x in Integer, y in Integer, z in Integer, gross in Float,portStaff in Varchar)
IS

Begin

    Insert into CARGOMANIFEST_CONTAINER (CARGOMANIFESTLOADID,CONTAINERID,TRIPID,XCONTAINER,YCONTAINER,ZCONTAINER,GROSSCONTAINER,TRIPCARGOMANIFESTLOADID,changesresponsible) values (cargoId,containerId,tripId,x,y,z,gross,cargoId,portStaff);

End;