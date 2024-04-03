package lapr.project.data;

import lapr.project.model.*;

/**
 * @author Eduardo Silva <1201371@isep.ipp.pt>
 */
public interface DatabaseMethods {
    /**
     * @param databaseConnection the database connection
     * @param ship the object that is going to be saved
     */
    void saveShip(DatabaseConnection databaseConnection, Ships ship);

    /**
     * @param databaseConnection the database connection
     * @param shipPosition the object that is going to be saved
     */
    void savePosition(DatabaseConnection databaseConnection, ShipPosition shipPosition);

    /**
     * @param databaseConnection the database connection
     * @param port the object that is going to be saved
     */
    void savePort(DatabaseConnection databaseConnection, Port port);


    void saveSeaDist(DatabaseConnection databaseConnection, SeaDist seaDist);

    Port getPort(DatabaseConnection databaseConnection, String port);

    void saveCountry(DatabaseConnection databaseConnection, Country country);

    void saveBorder(DatabaseConnection databaseConnection, Border border);
}