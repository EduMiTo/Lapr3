package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;



import java.sql.SQLException;


public class SendToDBController {

    private final Company company;
    private final DatabaseImporting databaseImporting;

    public SendToDBController(){
        this.company= App.getInstance().getCompany();
        this.databaseImporting= new DatabaseImporting();
    }
    public SendToDBController(Company company){
        this.company= company;
        this.databaseImporting= new DatabaseImporting();
    }

    public void importShips(){

        databaseImporting.sendShipsAndLocationsToDatabase(company);
    }
    public void importPorts(){
        databaseImporting.sendPortsToDatabase(company);
    }

    public void importSeaDist(){
        databaseImporting.sendSeaDistToDatabase(company);
    }

    public Boolean seaDist() throws SQLException {
        return company.getSeaDistStore().newList(databaseImporting.getSDOnDatabase(company));
    }

    public void importCountries(){
        databaseImporting.sendCountryToDatabase(company);
    }

    public Boolean capitals() throws SQLException {
        return company.getCapitalStore().newList(databaseImporting.getCapitalOnDatabase());
    }

    public Boolean countries() throws SQLException {
        return company.getCountryList().newList(databaseImporting.getCountryOnDatabase(company));
    }


    public void importBorders(){
        databaseImporting.sendBorderToDatabase(company);
    }

    public Boolean borders() throws SQLException {
        return company.getBorderStore().newList(databaseImporting.getBorderOnDatabase(company));
    }

    public Boolean ports() throws SQLException {
        return company.getPortStore().newList(databaseImporting.getPortsOnDatabase(company));
    }




}
