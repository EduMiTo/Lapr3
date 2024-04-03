--#DROP TABLE#--
drop table Ship CASCADE Constraints PURGE;
drop table ShipPosition CASCADE Constraints PURGE;
drop table Container CASCADE Constraints PURGE;
drop table CargoManifest_Ship CASCADE Constraints PURGE;
drop table CargoManifest_Container CASCADE Constraints PURGE;
drop table Country CASCADE Constraints PURGE;
drop table PlaceLocation CASCADE Constraints PURGE;
drop table Destination CASCADE Constraints PURGE;


--#CREATE TABLE SHIP#--
CREATE TABLE Ship(
    mmsi VARCHAR(9),
    imo VARCHAR(10)CONSTRAINT un_imo UNIQUE CONSTRAINT nn_imo NOT NULL,
    callSign VARCHAR(255) CONSTRAINT un_callSign UNIQUE CONSTRAINT nn_callSign NOT NULL,
    numEnergyGenerators INTEGER,
    generatorType VARCHAR(255),
    draft FLOAT CONSTRAINT nn_draft NOT NULL,
    name VARCHAR(255)CONSTRAINT un_name UNIQUE CONSTRAINT nn_name NOT NULL,
    vesselType INTEGER CONSTRAINT nn_vesselType NOT NULL,
    width FLOAT CONSTRAINT nn_width NOT NULL,
    lenght FLOAT CONSTRAINT nn_lenght NOT NULL,
    capacity FLOAT,

    CONSTRAINT pk_ship PRIMARY KEY (mmsi)
);

--#CREATE TABLE SHIPPOSITION#--
CREATE TABLE ShipPosition(
    shipmmsi VARCHAR(9),
    dateaismessage TIMESTAMP,
    latitude VARCHAR(255) CONSTRAINT nn_latitude NOT NULL,
    longitude VARCHAR(255) CONSTRAINT nn_longitude NOT NULL,
    sog FLOAT CONSTRAINT nn_sog NOT NULL,
    cog FLOAT CONSTRAINT nn_cog NOT NULL,
    heading VARCHAR(255) CONSTRAINT nn_heading NOT NULL,
    position INTEGER,
    cargo VARCHAR(255) CONSTRAINT nn_cargo NOT NULL,

    CONSTRAINT pk_shipPosition PRIMARY KEY (dateaismessage, shipmmsi)
);

--#CREATE TABLE Container#--
CREATE TABLE Container(
    id INTEGER,
    isoCode VARCHAR(255) CONSTRAINT nn_isoCode NOT NULL,
    checkDigit INTEGER CONSTRAINT nn_checkDigit NOT NULL,
    maxWeight FLOAT CONSTRAINT nn_maxWeight NOT NULL,
    weight FLOAT CONSTRAINT nn_weight NOT NULL,
    maxWeightPacked FLOAT CONSTRAINT nn_maxWeightPacked NOT NULL,
    maxVolumePacked FLOAT CONSTRAINT nn_maxVolumePacked NOT NULL,
    repairRecommendation VARCHAR(10) CONSTRAINT nn_repairRecommendation NOT NULL,
    certificates VARCHAR(255) CONSTRAINT nn_certificates NOT NULL,
    payload FLOAT CONSTRAINT nn_payload NOT NULL,
    tare FLOAT CONSTRAINT nn_tare NOT NULL,
    gross FLOAT CONSTRAINT nn_gross NOT NULL,

    CONSTRAINT pk_id PRIMARY KEY (id)
);

--#CREATE TABLE CargoManifest_Ship#--
CREATE TABLE CargoManifest_Ship(
    id INTEGER CONSTRAINT pk_cargoManifest PRIMARY KEY,
    shipmmsi VARCHAR(9)
);

--#CREATE TABLE CargoManifest_Container#--
CREATE TABLE CargoManifest_Container(
    cargoManifestid INTEGER,
    containerid INTEGER,
    xContainer INTEGER CONSTRAINT nn_xContainer NOT NULL,
    yContainer INTEGER CONSTRAINT nn_yContainer NOT NULL,
    zContainer INTEGER CONSTRAINT nn_zContainer NOT NULL,
    grossContainer FLOAT CONSTRAINT nn_grossContainer NOT NULL,
    
    CONSTRAINT pk_containerid_cargoManifestid PRIMARY KEY (containerid, cargoManifestid)
);

--#CREATE TABLE Country#--
CREATE TABLE Country(
    name VARCHAR(255) CONSTRAINT pk_country PRIMARY KEY,
    continent VARCHAR(255)
);

--#CREATE TABLE PlaceLocation#--
CREATE TABLE PlaceLocation(
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    countryname VARCHAR(255) CONSTRAINT nn_countryname NOT NULL,

    CONSTRAINT pk_placeLocation PRIMARY KEY (latitude, longitude)
);

--#CREATE TABLE Destination#--
CREATE TABLE Destination(
    id INTEGER CONSTRAINT pk_destination PRIMARY KEY,
    name VARCHAR(255),
    placeLocationlatitude VARCHAR(255) CONSTRAINT nn_placeLocationlatitude NOT NULL,
    placeLocationlongitude VARCHAR(255) CONSTRAINT nn_placeLocationlongitude NOT NULL
);

--#ALTER TABLE#--
ALTER TABLE ShipPosition  ADD CONSTRAINT fk_ShipPosition_ship FOREIGN KEY (shipmmsi) REFERENCES ship(mmsi);
ALTER TABLE CargoManifest_Container ADD CONSTRAINT fk_CargoManifest_Container_Container FOREIGN KEY (containerid) REFERENCES container(id);
ALTER TABLE CargoManifest_Container ADD CONSTRAINT fk_CargoManifest_Container_cargoManifest_Ship FOREIGN KEY (cargoManifestid) REFERENCES CargoManifest_Ship(id);
ALTER TABLE PlaceLocation ADD CONSTRAINT fk_PlaceLocation_country FOREIGN KEY (countryname) REFERENCES country(name);
ALTER TABLE Destination ADD CONSTRAINT fk_Destination_placeLocation FOREIGN KEY (placeLocationlatitude, placeLocationlongitude) REFERENCES placeLocation(latitude, longitude);
