package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PairShipControllerTest {

    @Test
    void getShips() throws IOException, ParseException {
        Company company= new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");

        PairShipController pairShipController= new PairShipController(company);

        pairShipController.getMap();

        pairShipController.showInfo();

        PairShipController pairShipController1=new PairShipController();

    }
}