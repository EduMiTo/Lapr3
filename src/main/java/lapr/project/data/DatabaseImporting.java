package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Eduardo Silva <1201371@isep.ipp.pt>
 */
public class DatabaseImporting implements DatabaseMethods {

    /**
     * The database Connection
     */
    DatabaseConnection connectionDatabase;

    /**
     * Send to Database Connection
     */
    public DatabaseImporting() {
        this.connectionDatabase = App.getInstance().getDatabaseConnection();
        if(!connectionDatabase.connectionWorking())
            connectionDatabase = null;
    }

    /**
     * Method responsible for sending the Ship and its locations to the database
     */
    public void sendShipsAndLocationsToDatabase(Company company) {
        if (connectionDatabase != null) {
            for (Object objectShip : company.getBstShip().inOrderIterable()) {
                Ships ship = (Ships) objectShip;
                saveShip(connectionDatabase, ship);

                for (Object objectLocation : ship.getBstMessage().inOrderIterable()) {
                    ShipPosition shipLocation = (ShipPosition) objectLocation;
                    savePosition(connectionDatabase, shipLocation);
                }
            }
        }
    }

    /**
     * Method responsible for saving Ports, PlaceLocations and Countries to the database
     */
    public void sendPortsToDatabase(Company company) {
        if (connectionDatabase != null) {
            for(Port port : company.getPortStore().getPortList())
                savePort(connectionDatabase, port);
        }
    }


    /**
     * Responsible for saving an object to the database.
     *
     * @param databaseConnection to the database
     * @param ship that is going to be saved
     */
    @Override
    public void saveShip(DatabaseConnection databaseConnection, Ships ship){

        try {
            saveShipToDatabase(databaseConnection, ship);

        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
        }
    }

    /**
     * Checks is a Ship is already registered on the database. If the Ship
     * is registered, it updates it. If it is not, it inserts a new one.
     *
     * @param databaseConnection to the database
     * @param ship that is related to the database
     * @throws SQLException that may occur from the database
     */
    private void saveShipToDatabase(DatabaseConnection databaseConnection, Ships ship)
            throws SQLException {
        if (isShipOnDatabase(databaseConnection, ship))
            updateShipOnDatabase(databaseConnection, ship);
        else
            insertShipOnDatabase(databaseConnection, ship);
    }

    /**
     * Checks if a ship is registered on the Database by its ID.
     *
     * @param databaseConnection to the database
     * @param ship that is related to the database
     * @return True if the ship is registered, False if otherwise.
     * @throws SQLException in case an error with the database occurs
     */
    protected boolean isShipOnDatabase(DatabaseConnection databaseConnection, Ships ship) throws SQLException {
        boolean isShipOnDatabase;
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from ship where mmsi = ?")) {
            preparedStatement.setString(1, ship.getMmsi());



            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isShipOnDatabase = resultSet.next();
                resultSet.close();

            }
        }
        return isShipOnDatabase;
    }

    /**
     * Updates an existing ship record on the database.
     *
     * @param databaseConnection to the database
     * @param ship that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    protected void updateShipOnDatabase(DatabaseConnection databaseConnection, Ships ship) throws SQLException {
        String command = "update ship set imo = ?, numEnergyGenerators = ?, generatorType = ?, callSign = ?, draft = ?, Name = ?, vesselType = ?, Lenght = ?, width = ?, capacity = ? where mmsi = " + ship.getMmsi();

        executeShipQueryOnDatabase(databaseConnection, ship, command);
    }

    /**
     * Inserts the ship on the database
     *
     * @param databaseConnection to the database
     * @param ship that is related to the database
     * @throws SQLException with the database
     */
    protected void insertShipOnDatabase(DatabaseConnection databaseConnection, Ships ship) throws SQLException {
        String command = "insert into ship(mmsi, imo, numEnergyGenerators, generatorType, callSign, draft, Name, vesselType, Lenght, width, capacity) values (" + ship.getMmsi() +", ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        executeShipQueryOnDatabase(databaseConnection, ship, command);
    }

    /**
     * Executes the prepared statement query
     *
     * @param databaseConnection to the database
     * @param ship that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void executeShipQueryOnDatabase(DatabaseConnection databaseConnection, Ships ship, String sqlCommand) throws SQLException {
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sqlCommand);) {
            preparedStatement.setString(1, ship.getImo());
            preparedStatement.setInt(2, ship.getGenerators());
            preparedStatement.setString(3, ship.getGenertorPowerOutput());
            preparedStatement.setString(4, ship.getCallSign());
            preparedStatement.setFloat(5, ship.getDraft());
            preparedStatement.setString(6, ship.getShipName());
            preparedStatement.setInt(7, Integer.parseInt(ship.getVesselType()));
            preparedStatement.setFloat(8, ship.getLenght());
            preparedStatement.setFloat(9, ship.getWidth());
            preparedStatement.setFloat(10, ship.getCapacity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            databaseConnection.registerError(e);
        }
    }




    /**
     * Saves a ShipPosition object to the database
     *
     * @param databaseConnection to the database
     * @param shipPosition that is going to be saved
     */
    @Override
    public void savePosition(DatabaseConnection databaseConnection, ShipPosition shipPosition){

        try {
            savePositionToDatabase(databaseConnection, shipPosition);

        } catch (SQLException e) {
            databaseConnection.registerError(e);
        }
    }

    /**
     * Checks is a ShipLocation is registered. If so
     * it is updated. If not, it inserts a new one.
     *
     * @param databaseConnection to the database
     * @param shipPosition that contains the location of a ship
     * @throws SQLException in case an error with the database occurs
     */
    private void savePositionToDatabase(DatabaseConnection databaseConnection, ShipPosition shipPosition) throws SQLException {
        if (isPositionOnDatabase(databaseConnection, shipPosition))
            updatePositionOnDatabase(databaseConnection, shipPosition);
        else
            insertPositionOnDatabase(databaseConnection, shipPosition);
    }

    /**
     * Verifies if a certain Location exists on the database
     *
     * @param databaseConnection to the database
     * @param shipPosition that is related to the database
     * @return if a location exists on the database
     * @throws SQLException in case a database error occurs
     */
    protected boolean isPositionOnDatabase(DatabaseConnection databaseConnection, ShipPosition shipPosition) throws SQLException {
        boolean isShipOnDatabase;
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from ShipPosition where shipMmsi = ? and dateaismessage = ?")) {


            preparedStatement.setString(1, shipPosition.getMmsi());
            preparedStatement.setTimestamp(2, new Timestamp(shipPosition.getAisMessage().getTime()));



            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isShipOnDatabase = resultSet.next();
                resultSet.close();

            }
        }
        return isShipOnDatabase;
    }

    /**
     * Updates a Position record on the database.
     *
     * @param databaseConnection to the database
     * @param shipPosition that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    protected void updatePositionOnDatabase(DatabaseConnection databaseConnection, ShipPosition shipPosition) throws SQLException {
        String command = "update ShipPosition set latitude = ?, longitude = ?, sog = ?, cog = ?, heading = ?, position = ?, cargo = ? where shipMmsi = ? and dateaismessage = ?";

        executeSPStatementOnDatabase(databaseConnection, shipPosition, command, false);
    }

    /**
     * Adds a new shipLocation to the database.
     *
     * @param databaseConnection to the database
     * @param shipPosition that is related to the database
     * @throws SQLException with the database
     */
    protected void insertPositionOnDatabase(DatabaseConnection databaseConnection, ShipPosition shipPosition) throws SQLException {

        String command = "insert into ShipPosition(shipMmsi, dateaismessage, latitude, longitude, sog, cog, heading, position, cargo) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeSPStatementOnDatabase(databaseConnection, shipPosition, command, true);
    }

    /**
     * Executes the built query.
     * @param databaseConnection connection to the database
     * @param shipPosition that is related to the database
     * @param insertion of insert that is being made
     * @throws SQLException that crashed the process
     */
    private void executeSPStatementOnDatabase(DatabaseConnection databaseConnection, ShipPosition shipPosition, String sqlCommand, boolean insertion) throws SQLException {

        try (PreparedStatement getShipLocationPreparedStatement = databaseConnection.getConnection().prepareStatement(sqlCommand)) {
            if (insertion) {
                getShipLocationPreparedStatement.setString(1, shipPosition.getMmsi());
                getShipLocationPreparedStatement.setTimestamp(2, new Timestamp(shipPosition.getAisMessage().getTime()));
                getShipLocationPreparedStatement.setString(3, shipPosition.getLatitude());
                getShipLocationPreparedStatement.setString(4, shipPosition.getLongitude());
                getShipLocationPreparedStatement.setFloat(5, shipPosition.getSog());
                getShipLocationPreparedStatement.setFloat(6, shipPosition.getCog());
                getShipLocationPreparedStatement.setString(7, shipPosition.getHeading());
                getShipLocationPreparedStatement.setInt(8, shipPosition.getPosition());
                getShipLocationPreparedStatement.setString(9, shipPosition.getCargo());
            } else {
                getShipLocationPreparedStatement.setString(1, shipPosition.getLatitude());
                getShipLocationPreparedStatement.setString(2, shipPosition.getLongitude());
                getShipLocationPreparedStatement.setFloat(3, shipPosition.getSog());
                getShipLocationPreparedStatement.setFloat(4, shipPosition.getCog());
                getShipLocationPreparedStatement.setString(5, shipPosition.getHeading());
                getShipLocationPreparedStatement.setInt(6, shipPosition.getPosition());
                getShipLocationPreparedStatement.setString(7, shipPosition.getCargo());
                getShipLocationPreparedStatement.setString(8, shipPosition.getMmsi());
                getShipLocationPreparedStatement.setTimestamp(9, new Timestamp(shipPosition.getAisMessage().getTime()));
            }

            try {
                getShipLocationPreparedStatement.executeUpdate();
            } catch (SQLException e) {
                databaseConnection.registerError(e);
            }
        }
    }




    /**
     * Method responsible for saving a port to the database.
     *
     * @param databaseConnection to the database
     * @param port that is going to be saved
     */
    @Override
    public void savePort(DatabaseConnection databaseConnection, Port port){

        try {
            savePortToDatabase(databaseConnection, port);

        } catch (SQLException ex) {

            databaseConnection.registerError(ex);
        }
    }

    /**
     * Checks is a Port is registered on the database. If so, updates it.
     * If not, inserts it.
     *
     * @param databaseConnection to the database
     * @param port that is related to the database
     * @throws SQLException related to the database
     */
    private void savePortToDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {

        if(!isCountryOnDatabase(databaseConnection, port))
            insertCountryOnDatabase(databaseConnection, port);
        if(!isPLOnDatabase(databaseConnection, port))
            insertPLOnDatabase(databaseConnection, port);
        if (isPortOnDatabase(databaseConnection, port))
            updatePortOnDatabase(databaseConnection, port);
        else
            insertPortOnDatabase(databaseConnection, port);
    }

    /**
     * Verifies if a certain port is on the database.
     *
     * @param databaseConnection to the database
     * @param port that is related to the database
     * @return True if the ship is registered, False if otherwise.
     * @throws SQLException to the database
     */
    protected boolean isPortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        boolean isPortOnDatabase;
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from port where id = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(port.getId()));


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isPortOnDatabase = resultSet.next();
                resultSet.close();

            }
        }
        return isPortOnDatabase;
    }


    @Override
    public Port getPort(DatabaseConnection databaseConnection, String port){

        try {
            return isPortOnDatabaseS(databaseConnection, port);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    protected Port isPortOnDatabaseS(DatabaseConnection databaseConnection, String port) throws SQLException {
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3= null;

        Port isPortOnDatabase= null;
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select id, name, PLACELOCATIONLATITUDE, PLACELOCATIONLONGITUDE from port where id = ?")) {


            preparedStatement.setInt(1, Integer.parseInt(port));


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                resultSet.next();

                preparedStatement2 = databaseConnection.getConnection().prepareStatement("select countryName from PlaceLocation where longitude = ? and latitude=?");

                preparedStatement2.setString(1, resultSet.getString("PlaceLocationLongitude"));
                preparedStatement2.setString(2, resultSet.getString("PlaceLocationLatitude"));

                try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
                    resultSet2.next();

                    preparedStatement3 = databaseConnection.getConnection().prepareStatement("select continent from country where name=?");
                    preparedStatement3.setString(1, resultSet2.getString("countryName"));

                    try (ResultSet resultSet3 = preparedStatement3.executeQuery()) {
                        resultSet3.next();

                        Country country = new Country(resultSet2.getString("countryName"), resultSet3.getString("continent"));

                        PlaceLocation placeLocation = new PlaceLocation(resultSet.getString("PlaceLocationLatitude"), resultSet.getString("PlaceLocationLongitude"), country);


                        isPortOnDatabase = new Port(resultSet.getString("id"), resultSet.getString("name"), placeLocation);

                    }

                }
            }finally {

                preparedStatement3.close();
            }
        } finally {

            preparedStatement2.close();

        }
        return isPortOnDatabase;
    }

    /**
     * Updates an existing port.
     *
     * @param databaseConnection to the database
     * @param port that is related to the database
     * @throws SQLException with the database
     */
    protected void updatePortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        String sqlCommand = "update port set name = ?, placeLocationLatitude = ?, placeLocationLongitude = ?, capacityship="+100+"  where id = " + port.getId();

        executePortStatementOnDatabase(databaseConnection, port, sqlCommand);
    }

    /**
     * Adds a new port to the database.
     *
     * @param databaseConnection to the database
     * @param port that is related to the database
     * @throws SQLException for errors provoked by the database
     */
    protected void insertPortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        String command = "insert into port(id, name, placeLocationLatitude, placeLocationLongitude, capacity, capacityship) values (" + port.getId() + ", ?, ?, ?,"+5000+","+100+")";

        executePortStatementOnDatabase(databaseConnection, port, command);
    }

    /**
     * Executes the save Port Statement query.
     *
     * @param databaseConnection to the database
     * @param port that is related to the database
     * @throws SQLException related to the database
     */
    private void executePortStatementOnDatabase(DatabaseConnection databaseConnection, Port port, String command) throws SQLException {

        try (PreparedStatement savePortPreparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            savePortPreparedStatement.setString(1, port.getName());
            savePortPreparedStatement.setString(2, port.getPlaceLocation().getLatitude());
            savePortPreparedStatement.setString(3, port.getPlaceLocation().getLongitude());

            try {
                savePortPreparedStatement.executeUpdate();
            } catch (SQLException e) {
                databaseConnection.registerError(e);
            }
        }
    }



    /**
     * Verifies if a port is on the database.
     *
     * @param databaseConnection to the database
     * @param port that contains the Place Location Data
     * @return true if the Place Location is on the database or not
     *
     * @throws SQLException error related to the database
     */
    protected boolean isPLOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException{

        boolean isPlaceLocationOnDatabase;

        String command = "select * from PlaceLocation where countryName = ? and latitude = ? and longitude = ?";

        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            preparedStatement.setString(1, port.getPlaceLocation().getCountry().getName());
            preparedStatement.setString(2, port.getPlaceLocation().getLatitude());
            preparedStatement.setString(3, port.getPlaceLocation().getLongitude());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isPlaceLocationOnDatabase = resultSet.next();
                resultSet.close();

            }
        }
        return isPlaceLocationOnDatabase;
    }

    /**
     * Imports a place location to the database.
     *
     * @param databaseConnection to the database
     * @param port that contains the Place Location Data
     * @throws SQLException error that occurs on the database.
     */
    protected void insertPLOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        String command = "insert into PlaceLocation(countryName, latitude, longitude) values (?, ?, ?)";

        executePLStatement(databaseConnection, port, command);
    }

    /**
     * Executes the PlaceLocation Prepared Statement.
     *
     * @param databaseConnection to the database
     * @param port that contains the Place Location Data
     * @param command error related to the database
     */
    private void executePLStatement(DatabaseConnection databaseConnection, Port port, String command) throws SQLException {

        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            preparedStatement.setString(1, port.getPlaceLocation().getCountry().getName());
            preparedStatement.setString(2, port.getPlaceLocation().getLatitude());
            preparedStatement.setString(3, port.getPlaceLocation().getLongitude());

            try {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                databaseConnection.registerError(e);
            }
        }
    }




    /**
     * Verification if a certain country is on the database.
     *
     * @param databaseConnection to the database
     * @param port that contains the Place Location Data
     * @return true if the country is on the database and false if not
     * @throws SQLException error related to the database
     */
    protected boolean isCountryOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException{

        String command = "select * from Country where Name = ?";
        boolean countryExists;
        try (PreparedStatement getCountryPreparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            getCountryPreparedStatement.setString(1, port.getPlaceLocation().getCountry().getName());



            try (ResultSet resultSet = getCountryPreparedStatement.executeQuery()) {

                countryExists = resultSet.next();
                resultSet.close();

            }
        }
        return countryExists;
    }

    /**
     * Inserts a Country on the database.
     *
     * @param databaseConnection to the database
     * @param port that contains the Place Location Data
     * @throws SQLException error with the database
     */
    protected void insertCountryOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException{

        String command = "insert into Country (name, continent) values (?, ?)";

        try (PreparedStatement saveCountry = databaseConnection.getConnection().prepareStatement(command)) {
            saveCountry.setString(1, port.getPlaceLocation().getCountry().getName());
            saveCountry.setString(2, port.getPlaceLocation().getCountry().getContinent());

            try {
                saveCountry.executeUpdate();
            } catch (SQLException e) {
                databaseConnection.registerError(e);
            }
        }
    }


    public void sendSeaDistToDatabase(Company company) {
        if (connectionDatabase != null) {
            for(SeaDist seaDist : company.getSeaDistStore().getSeaDistList()) {
                saveSeaDist(connectionDatabase, seaDist);
            }
        }

    }

    @Override
    public void saveSeaDist(DatabaseConnection databaseConnection, SeaDist seaDist){

        try {
            saveSeaDistToDatabase(databaseConnection, seaDist);

        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
        }
    }

    private void saveSeaDistToDatabase(DatabaseConnection databaseConnection, SeaDist seaDist) throws SQLException {

        if(!isPortOnDatabase(databaseConnection, seaDist.getPortFrom())) {
            if (!isCountryOnDatabase(databaseConnection, seaDist.getPortFrom()))
                insertCountryOnDatabase(databaseConnection, seaDist.getPortFrom());
            if (!isPLOnDatabase(databaseConnection, seaDist.getPortFrom()))
                insertPLOnDatabase(databaseConnection, seaDist.getPortFrom());
            insertPortOnDatabase(databaseConnection, seaDist.getPortFrom());
        }

        if (isSDOnDatabase(databaseConnection, seaDist)) {

            updateSDOnDatabase(databaseConnection, seaDist);

        }
        else {

            insertSDOnDatabase(databaseConnection, seaDist);

        }
    }


    protected boolean isSDOnDatabase(DatabaseConnection databaseConnection, SeaDist seaDist) throws SQLException {
        boolean isPortOnDatabase;
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from seadist where portidfrom = ? and portidto=?");
        try {
            preparedStatement.setInt(1, Integer.parseInt(seaDist.getPortFrom().getId()));
            preparedStatement.setInt(2, Integer.parseInt(seaDist.getPortTo().getId()));




            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isPortOnDatabase = resultSet.next();

                resultSet.close();


            }
        }
         finally {
            preparedStatement.close();

        }
        return isPortOnDatabase;
    }

    protected void updateSDOnDatabase(DatabaseConnection databaseConnection, SeaDist seaDist) throws SQLException {
        String sqlCommand = "update SeaDist set seadistance = ? where portidfrom = " + seaDist.getPortFrom().getId() + "and portidto = "+ seaDist.getPortTo().getId();

        executeSDStatementOnDatabase(databaseConnection, seaDist, sqlCommand);
    }


    protected void insertSDOnDatabase(DatabaseConnection databaseConnection, SeaDist seaDist) throws SQLException {
        String command = "insert into SeaDist(portidFrom, portidTo, seaDistance) values (" + seaDist.getPortFrom().getId() + ", "+seaDist.getPortTo().getId()+", ?)";

        executeSDStatementOnDatabase(databaseConnection, seaDist, command);
    }

    private void executeSDStatementOnDatabase(DatabaseConnection databaseConnection, SeaDist seaDist, String command) throws SQLException {

        try (PreparedStatement saveSDPreparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            saveSDPreparedStatement.setFloat(1, seaDist.getSeaDistance());


            try {
                saveSDPreparedStatement.executeUpdate();
            } catch (SQLException e) {
                databaseConnection.registerError(e);
            }
        }
    }


    public List<SeaDist> getSDOnDatabase(Company company) throws SQLException {
        String command = "select * from SeaDist";

        List<SeaDist> seaDists = new ArrayList<>();


        try (PreparedStatement saveSDPreparedStatement = connectionDatabase.getConnection().prepareStatement(command); ResultSet resultSet = saveSDPreparedStatement.executeQuery()) {

            while (resultSet.next()) {


                Port portF = company.getPortStore().verify(resultSet.getString("portIdFrom"));


                Port portT = company.getPortStore().verify(resultSet.getString("portIdTo"));

                float seaDistance = resultSet.getFloat("SeaDistance");

                seaDists.add(new SeaDist(portF, portT, String.valueOf(seaDistance)));
            }

            resultSet.close();

        }

        return seaDists;
    }



    public void sendCountryToDatabase(Company company) {
        if (connectionDatabase != null) {
            for(Country country : company.getCountryList().getCountryList()) {
                saveCountry(connectionDatabase, country);
            }
        }
    }


    @Override
    public void saveCountry(DatabaseConnection databaseConnection, Country country){

        try {
            saveCountryToDatabase(databaseConnection, country);

        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
        }
    }

    private void saveCountryToDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {

        if (isCountryOnDatabase(databaseConnection, country)) {
            updateCountryOnDatabase(databaseConnection, country);

            if(!isPLOnDatabase(databaseConnection, country))
                insertPLOnDatabase(databaseConnection, country);
            if(isCapitalOnDatabase(databaseConnection, country))
                updateCapitalOnDatabase(databaseConnection, country);
            else {
                insertCapitalOnDatabase(databaseConnection, country);
            }

        }
        else {
            insertCountryOnDatabase(databaseConnection, country);
            if(!isPLOnDatabase(databaseConnection, country))
                insertPLOnDatabase(databaseConnection, country);
            if(isCapitalOnDatabase(databaseConnection, country))
                updateCapitalOnDatabase(databaseConnection, country);
            else {
                insertCapitalOnDatabase(databaseConnection, country);
            }
        }
    }


    protected boolean isPLOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        boolean isPortOnDatabase;
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from PlaceLocation where latitude = ? AND longitude =?");
        try {
            preparedStatement.setString(1, country.getCapital().getLatitude());
            preparedStatement.setString(2, country.getCapital().getLongitude());





            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isPortOnDatabase = resultSet.next();

                resultSet.close();


            }
        }
         finally {
            preparedStatement.close();

        }
        return isPortOnDatabase;
    }

    protected void insertPLOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        String command = "insert into PlaceLocation(latitude, longitude, countryName) values ('" + country.getCapital().getLatitude() + "', '" + country.getCapital().getLongitude() + "', '"+ country.getName() +"')";

        try (PreparedStatement saveCountryPreparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            saveCountryPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnection.registerError(e);
        }
    }

    protected void insertCapitalOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        String command = "insert into Capital(name, placelocationlatitude , placelocationlongitude, countryName) values ('" + country.getCapital().getName() + "', ?, ?,?)";

        try (PreparedStatement saveCountryPreparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            saveCountryPreparedStatement.setString(1, country.getCapital().getLatitude());
            saveCountryPreparedStatement.setString(2, country.getCapital().getLongitude());
            saveCountryPreparedStatement.setString(3, country.getName());

            try {
                saveCountryPreparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                databaseConnection.registerError(e);
            }
        }
    }

    protected void updateCapitalOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        String sqlCommand = "update Capital set placelocationlatitude = ?, placelocationlongitude = ?, countryName=?  where name = '" + country.getCapital().getName()+"'";

        try (PreparedStatement saveCapitalPreparedStatement = databaseConnection.getConnection().prepareStatement(sqlCommand)) {
            saveCapitalPreparedStatement.setString(1, country.getCapital().getLatitude());
            saveCapitalPreparedStatement.setString(2, country.getCapital().getLongitude());
            saveCapitalPreparedStatement.setString(3, country.getName());

            try {
                saveCapitalPreparedStatement.executeUpdate();
            } catch (SQLException e) {
                databaseConnection.registerError(e);
            }
        }
    }


    protected boolean isCountryOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        boolean isPortOnDatabase;
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from Country where name = ? ")) {
            preparedStatement.setString(1, country.getName());


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isPortOnDatabase = resultSet.next();

                resultSet.close();


            }
        }
        return isPortOnDatabase;
    }


    protected boolean isCapitalOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        boolean isPortOnDatabase;
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from Capital where name = ? ")) {
            preparedStatement.setString(1, country.getCapital().getName());





            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isPortOnDatabase = resultSet.next();

                resultSet.close();


            }
        }
        return isPortOnDatabase;
    }

    protected void updateCountryOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        String sqlCommand = "update Country set continent = ?, population = ?, alpha2_code=?, alpha3_code=?  where name = '" + country.getName()+"'";

        executeCountryStatementOnDatabase(databaseConnection, country, sqlCommand);
    }


    protected void insertCountryOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        String command = "insert into Country(name, continent, population,alpha2_code,alpha3_code) values ('" + country.getName() + "', ?, ?, ?, ?)";

        executeCountryStatementOnDatabase(databaseConnection, country, command);
    }

    private void executeCountryStatementOnDatabase(DatabaseConnection databaseConnection, Country country, String command) throws SQLException {

        try (PreparedStatement saveCountryPreparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            saveCountryPreparedStatement.setString(1, country.getContinent());
            saveCountryPreparedStatement.setFloat(2, country.getPopulation());
            saveCountryPreparedStatement.setString(3, country.getAlpha2code());
            saveCountryPreparedStatement.setString(4, country.getAlpha3code());


            try {
                saveCountryPreparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                databaseConnection.registerError(e);
            }
        }
    }


    public List<Country> getCountryOnDatabase(Company company) throws SQLException {
        String command = "select * from Country";

        List<Country> countries = new ArrayList<>();


        try (PreparedStatement saveSDPreparedStatement = connectionDatabase.getConnection().prepareStatement(command); ResultSet resultSet = saveSDPreparedStatement.executeQuery()) {

            while (resultSet.next()) {

                String name = resultSet.getString("name");

                String continent = resultSet.getString("continent");

                float population = resultSet.getFloat("population");

                String alpha2 = resultSet.getString("alpha2_code");

                String alpha3 = resultSet.getString("alpha3_code");

                Capital capital = company.getCapitalStore().searchByCountry(name);

                countries.add(new Country(name, continent, alpha2, alpha3, String.valueOf(population), capital));
            }

            resultSet.close();

        }

        return countries;
    }

    public List<Capital> getCapitalOnDatabase() throws SQLException {
        String command = "select * from Capital";

        List<Capital> capitals = new ArrayList<>();


        try (PreparedStatement saveSDPreparedStatement = connectionDatabase.getConnection().prepareStatement(command); ResultSet resultSet = saveSDPreparedStatement.executeQuery()) {

            while (resultSet.next()) {

                String latitude = resultSet.getString("placelocationlatitude");

                String longitude = resultSet.getString("placelocationlongitude");

                String name = resultSet.getString("name");

                String cName = resultSet.getString("countryName");

                capitals.add(new Capital(name, latitude, longitude, cName));
            }

            resultSet.close();

        }

        return capitals;
    }


    public void sendBorderToDatabase(Company company) {
        if (connectionDatabase != null) {
            for(Border border : company.getBorderStore().getBorderList()) {
                saveBorder(connectionDatabase, border);
            }
        }
    }


    @Override
    public void saveBorder(DatabaseConnection databaseConnection, Border border){

        try {
            saveBorderToDatabase(databaseConnection, border);

        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
        }
    }

    private void saveBorderToDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {

        if (!isBorderOnDatabase(databaseConnection, border)){

            insertBorderOnDatabase(databaseConnection, border);

        }

    }

    protected boolean isBorderOnDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {
        boolean isPortOnDatabase;
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from Border where Countryname = ? and countryname2=?")) {
            preparedStatement.setString(1, border.getCountry1().getName());

            preparedStatement.setString(2, border.getCountry2().getName());


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                isPortOnDatabase = resultSet.next();

                resultSet.close();


            }
        }
        return isPortOnDatabase;
    }

    protected void insertBorderOnDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {
        String command = "insert into Border(countryname, countryname2) values ('" + border.getCountry1().getName()+ "', '"+ border.getCountry2().getName()+"')";


        try (PreparedStatement saveCountryPreparedStatement = databaseConnection.getConnection().prepareStatement(command)) {
            saveCountryPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnection.registerError(e);
        }
    }





    public List<Border> getBorderOnDatabase(Company company) throws SQLException {
        String command = "select * from Border";

        List<Border> borders = new ArrayList<>();


        try (PreparedStatement saveSDPreparedStatement = connectionDatabase.getConnection().prepareStatement(command); ResultSet resultSet = saveSDPreparedStatement.executeQuery()) {

            while (resultSet.next()) {

                String scountry1 = resultSet.getString("countryname");

                String scountry2 = resultSet.getString("countryname2");

                Country country1 = company.getCountryList().verify(scountry1);

                Country country2 = company.getCountryList().verify(scountry2);


                borders.add(new Border(country1, country2));
            }

            resultSet.close();

        }

        return borders;
    }


    public List<Port> getPortsOnDatabase(Company company) throws SQLException {
        String command = "select * from Port";

        List<Port> ports = new ArrayList<>();


        try (PreparedStatement saveSDPreparedStatement = connectionDatabase.getConnection().prepareStatement(command); ResultSet resultSet = saveSDPreparedStatement.executeQuery()) {



            while (resultSet.next()) {

                String id = resultSet.getString("id");

                String name = resultSet.getString("name");

                String command2 = "select countryName from PlaceLocation where latitude=? AND longitude = ? ";


                try (PreparedStatement getPLandCountryPreparedStatement = connectionDatabase.getConnection().prepareStatement(command2)) {
                    getPLandCountryPreparedStatement.setString(1, resultSet.getString("placelocationlatitude"));

                    getPLandCountryPreparedStatement.setString(2, resultSet.getString("placelocationlongitude"));

                    try (ResultSet resultSet2 = getPLandCountryPreparedStatement.executeQuery()) {
                        resultSet2.next();

                        Country country1 = company.getCountryList().verify(resultSet2.getString("countryName"));


                        PlaceLocation pl = new PlaceLocation(resultSet.getString("placelocationlatitude"), resultSet.getString("placelocationlongitude"), country1);


                        ports.add(new Port(id, name, pl));
                    }





                }


            }

            resultSet.close();


        }

        return ports;
    }

    public List<String> getCargoManifest(int cargomanifest) throws SQLException {
        String command = "select * from CargoManifest_Container inner join cargomanifestload on(cargomanifest_container.cargomanifestloadid= cargomanifestload.id) inner join ship on(cargomanifestload.shipmmsi = ship.mmsi) where cargomanifestloadid="+cargomanifest+"";

        List<String> positions= new ArrayList<>();

        try (PreparedStatement saveSDPreparedStatement = connectionDatabase.getConnection().prepareStatement(command); ResultSet resultSet = saveSDPreparedStatement.executeQuery()) {

            resultSet.next();

            String length = resultSet.getString("LENGHT");
            String width = resultSet.getString("WIDTH");

            positions.add(length);
            positions.add(width);


            do {

                String id = resultSet.getString("containerid");

                String x = resultSet.getString("xcontainer");

                String y = resultSet.getString("ycontainer");

                String z = resultSet.getString("zcontainer");

                positions.add(id);
                positions.add(x);
                positions.add(y);
                positions.add(z);

            } while (resultSet.next());

            resultSet.close();


        }

        return positions;
    }


}