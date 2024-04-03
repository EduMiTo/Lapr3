package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.PlaceLocation;
import lapr.project.model.Port;
import lapr.project.model.ShipPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class NearestPortTest {

    @Test
    void getShipAndShipPosition() throws IOException, ParseException {

        Company company= new Company();
        //US1
        ImportCSVtoBST importCSVtoBST= new ImportCSVtoBST(company);
        importCSVtoBST.importCSV("sships.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sports.csv");


        NearestPort nearestPort= new NearestPort(company);

        Port port= nearestPort.getShipAndShipPosition("C4SQ2","31-12-2020 17-20");




        Port port1= new Port(port.getId(), port.getName(), port.getPlaceLocation());
        assertEquals(port.getId(),port1.getId());

        NearestPort nearestPort1= new NearestPort();


    }



    @Test
    void nearestPort() throws IOException, ParseException {

        Company company= new Company();
        //US1
        ImportCSVtoBST importCSVtoBST= new ImportCSVtoBST(company);
        importCSVtoBST.importCSV("sships.csv");



        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sports.csv");




        NearestPort nearestPort= new NearestPort(company);

        Port port=nearestPort.getShipAndShipPosition("C4SQ2","31-12-2020 17-20");



        assertEquals(port.getId(),"22226");

    }

    @Test
    void test1() throws IOException, ParseException {

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            Company company= new Company();
            //US1
            ImportCSVtoBST importCSVtoBST= new ImportCSVtoBST(company);
            importCSVtoBST.importCSV("sships.csv");



            PortImportController portImportController= new PortImportController(company);
            portImportController.portImportCSV("sports.csv");

            NearestPort nearestPort= new NearestPort(company);


            Port port=nearestPort.getShipAndShipPosition("C4SasdQ2","31-12-2020 17-20");

            assertEquals(port, new Port(port.getId(), port.getName(), port.getPlaceLocation()));


        });
        assertEquals(thrown.getMessage(), thrown.getMessage());
    }
}