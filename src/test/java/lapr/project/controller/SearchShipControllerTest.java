package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ships;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class SearchShipControllerTest {

    private Company company;

    public SearchShipControllerTest() throws IOException, ParseException {
        company=new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");
        ShipInfoController shipInfoController=new ShipInfoController();
    }

    @Test
    void getbyMMSITest() throws IOException, ParseException {

        SearchShipController searchShipController = new SearchShipController(company);
        Ships shipsByMMSI = searchShipController.getShipsByMMSI("228339600");
        assertEquals("228339600", shipsByMMSI.getMmsi());
    }

    @Test
    void getbyMMSINotFoundTest() throws IOException, ParseException {

        SearchShipController searchShipController = new SearchShipController(company);
        Ships shipsByMMSI = searchShipController.getShipsByMMSI("628339601");
        assertNull(shipsByMMSI);
    }

    @Test
    void getbyIMOTest() throws IOException, ParseException {

        SearchShipController searchShipController = new SearchShipController(company);
        Ships shipsByIMO = searchShipController.getShipsByIMO("IMO9395044");
        assertEquals("IMO9395044", shipsByIMO.getImo());
    }

    @Test
    void getbyIMONotFoundTest() throws IOException, ParseException {

        SearchShipController searchShipController = new SearchShipController(company);
        Ships shipsByIMO = searchShipController.getShipsByIMO("IMO9395048");
        assertNull(shipsByIMO);
    }

    @Test
    void getbyCallSignTest() throws IOException, ParseException {

        SearchShipController searchShipController = new SearchShipController(company);
        Ships shipsByCallSign = searchShipController.getShipsByCallSign("C4SQ2");
        assertEquals("C4SQ2", shipsByCallSign.getCallSign());
    }

    @Test
    void getbyCallSignNotFoundTest() throws IOException, ParseException {

        SearchShipController searchShipController = new SearchShipController(company);
        Ships shipsByCallSign = searchShipController.getShipsByCallSign("C4SQ8");
        assertNull(shipsByCallSign);
    }

    @Test
    void writeFile() throws FileNotFoundException {
        SearchShipController searchShipController = new SearchShipController(company);
        searchShipController.getShipsByMMSI("228339600");
        searchShipController.write();

    }

}
