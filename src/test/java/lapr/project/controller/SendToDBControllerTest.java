package lapr.project.controller;

import lapr.project.data.SendToDBController;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class SendToDBControllerTest {

    @Test
    void importShips() throws IOException, ParseException {
        Company company= new Company();

        ImportCSVtoBST importCSVtoBST= new ImportCSVtoBST(company);

        importCSVtoBST.importCSV("sships.csv");

        SendToDBController sendToDBController= new SendToDBController(company);

        sendToDBController.importShips();

    }

    @Test
    void importPorts() throws IOException {
        Company company= new Company();

        PortImportController portImportController= new PortImportController(company);

        portImportController.portImportCSV("bports.csv");

        SendToDBController sendToDBController= new SendToDBController(company);

        sendToDBController.importPorts();
    }

    @Test
    void importSeaDist() throws IOException {
        Company company= new Company();

        ImportSeaDistCSVtoBd importSeaDistCSVtoBd= new ImportSeaDistCSVtoBd(company);

        importSeaDistCSVtoBd.seaDistImportCSV("seadistsT.csv");

        SendToDBController sendToDBController= new SendToDBController(company);

        sendToDBController.importSeaDist();
    }

    @Test
    void seaDist() throws SQLException {
        Company company= new Company();

        SendToDBController sendToDBController= new SendToDBController(company);

        sendToDBController.seaDist();

    }

    @Test
    void importCountries() throws  IOException {
        Company company= new Company();

        ImportCountryToBD importCountryToBD= new ImportCountryToBD(company);

        importCountryToBD.CountryImportCSV("countries.csv");

        SendToDBController sendToDBController= new SendToDBController(company);

        sendToDBController.importCountries();


    }


    @Test
    void Country() throws SQLException {

        Company company= new Company();

        SendToDBController sendToDBController= new SendToDBController(company);

        assertTrue(sendToDBController.capitals());

        assertTrue(sendToDBController.countries());
    }

    @Test
    void importBorder() throws IOException {
        Company company= new Company();

        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);

        importCountryToBD.CountryImportCSV("countries.csv");


        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);


        importBordersToBD.BorderImportCSV("borders.csv");




        SendToDBController sendToDBController= new SendToDBController(company);

        sendToDBController.importBorders();

    }

    @Test
    void borders() throws IOException, SQLException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);

        importCountryToBD.CountryImportCSV("countries.csv");


        SendToDBController sendToDBController= new SendToDBController(company);

        assertTrue(sendToDBController.borders());
    }

    @Test
    void ports() throws SQLException {
        Company company= new Company();



        SendToDBController sendToDBController= new SendToDBController(company);

        assertTrue(sendToDBController.ports());
    }

}