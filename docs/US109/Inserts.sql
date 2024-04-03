--------------#INSERT : SHIP#--------------
--expected: pass 
INSERT INTO Ship(mmsi, imo, callSign, numEnergyGenerators, generatorType, draft, name, vesselType, width, lenght, capacity)
VALUES ('210950000', 'IMO9395044', 'C4SQ2', 2, 'A', 9.5, 'VARAMO', 70, 25, 166, 400);

--expected: fail (unique - imo)
INSERT INTO Ship(mmsi, imo, callSign, numEnergyGenerators, generatorType, draft, name, vesselType, width, lenght, capacity)
VALUES ('210950001', 'IMO9395044', 'C4SQ3', 2, 'A', 9.5, 'SAITA I', 70, 25, 166, 400);

--------------#INSERT : SHIPPOSITION#--------------
--expected: pass
INSERT INTO ShipPosition(shipmmsi, dateaismessage, latitude, longitude, sog, cog, heading, position, cargo)
VALUES ('210950000', '2004-11-03 18:44:33', '90', '180', 110, 4, '355', 3, 'NA');

--expected: fail (null - latitude)
INSERT INTO ShipPosition(shipmmsi, dateaismessage, latitude, longitude, sog, cog, heading, position, cargo)
VALUES ('210950000', '2004-11-03 18:44:33', null, '180', 110, 4, '355', 3, 'NA');

--------------#INSERT : CONTAINER#--------------
--expected: pass
INSERT INTO Container(id, isoCode, checkDigit, maxWeight, weight, maxWeightPacked, maxVolumePacked, repairRecommendation, certificates, payload, tare, gross)
VALUES (123456789, 'aaabbb', 1110001110, 1000.0, 500.0, 50.0, 10.0, 'repair', 'certificate', 100.0, 500.0, 800.0);

--expected: fail (integer vs varchar - checkDigit)
INSERT INTO Container(id, isoCode, checkDigit, maxWeight, weight, maxWeightPacked, maxVolumePacked, repairRecommendation, certificates, payload, tare, gross)
VALUES (123456789, 'aaabbb', 'cccttt', 1000.0, 500.0, 50.0, 10.0, 'repair', 'certificate', 100.0, 500.0, 800.0);

--------------#INSERT : CARGOMANIFEST_SHIP#--------------
--expected: pass
INSERT INTO CargoManifest_Ship(id, shipmmsi)
VALUES (1112223334, '210950000');

--expected: fail (shipmmsi exceeds the limit)
INSERT INTO CargoManifest_Ship(id, shipmmsi)
VALUES (1112223334, '21095000099999999');

--------------#INSERT : CARGOMANIFEST_CONTAINER#--------------
--expected: pass
INSERT INTO CargoManifest_Container(cargoManifestid, containerid, xContainer, yContainer, zContainer, grossContainer)
VALUES (1112223334, 123456789, 1, 1, 1, 100.0);

--expected: fail (cargoManifestid doesn't exist)
INSERT INTO CargoManifest_Container(cargoManifestid, containerid, xContainer, yContainer, zContainer, grossContainer)
VALUES (333222111, 123456789, 1, 1, 1, 100.0);

--------------#INSERT : COUNTRY#--------------
--expected: pass
INSERT INTO Country(name, continent)
VALUES ('Portugal', 'Europa');

--expected: fail (primary key is null - name)
INSERT INTO Country(name, continent)
VALUES (null, 'Europa');

--------------#INSERT : PLACELOCATION#--------------
--expected: pass
INSERT INTO PlaceLocation(latitude, longitude, countryname)
VALUES ('36', '-122', 'Portugal');

--expected: fail (foreign key doesn't exist - countryname)
INSERT INTO PlaceLocation(latitude, longitude, countryname)
VALUES ('36', '-122', 'Suécia');

--------------#INSERT : DESTINATION#--------------
--expected: pass
INSERT INTO Destination(id, name, placeLocationlatitude, placeLocationlongitude)
VALUES (5556667778, 'Espanha', '36', '-122');

--------------#DELETE : SHIP#--------------
--expected: fail (can´t delete a ship if it other tables have dependency of that ship)
DELETE FROM Ship WHERE mmsi = '210950000';

--------------#DELETE : SHIPPOSITION#--------------
--expected: pass
--delete ShipPosition
DELETE FROM ShipPosition WHERE shipmmsi = '210950000';

--------------#DELETE : SHIP#--------------
--expected: pass
DELETE FROM Ship WHERE mmsi = '210950000';

--------------#DELETE : CARGOMANIFEST_CONTAINER#--------------
--expected: pass
DELETE FROM CargoManifest_Container WHERE cargoManifestid = 1112223334 AND containerid = 123456789;

--------------#DELETE : CONTAINER#--------------
--expected: pass
DELETE FROM Container WHERE id = 123456789;

--------------#DELETE : CARGOMANIFEST_SHIP#--------------
--expected: pass
DELETE FROM CargoManifest_Ship WHERE id = 1112223334;

--------------#DELETE : DESTINATION#--------------
--expected: pass
DELETE FROM Destination WHERE id = 5556667778;

--------------#DELETE : PLACELOCATION#--------------
--expected: pass
DELETE FROM PlaceLocation WHERE latitude = '36' AND longitude = '-122';

--------------#DELETE : COUNTRY#--------------
--expected: pass
DELETE FROM Country WHERE name = 'Portugal';

