package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ShipInfoControllerTest {

    @Test
    void getInfoByMMSI() throws IOException, ParseException {

        Company company = new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");


        ShipInfoController shipInfoController = new ShipInfoController(company);

        shipInfoController.getInfoByMMSI("636092932");

        ShipInfoController shipInfoController1= new ShipInfoController();


    }
}