package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.ShipPosition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionalMessagesControllerTest {

    @Test
    void getPositionalMessage() throws ParseException, IOException {
        Company company= new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");

        PositionalMessagesController positionalMessagesController1= new PositionalMessagesController();
        PositionalMessagesController positionalMessagesController=new PositionalMessagesController(company);
        Date initialDate=  new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 17:03");
        Date finalDate= new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 18:31");
        String mmsi="210950000";
        List<ShipPosition> shipPositions=positionalMessagesController.showMessages(mmsi,initialDate,finalDate);
        positionalMessagesController.writeToFile(shipPositions,initialDate,finalDate);

    }

    @Test
    void oneMessage() throws ParseException, IOException {
        Date initialDate= new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 17:03");
        Company company= new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");
        PositionalMessagesController positionalMessagesController=new PositionalMessagesController(company);
        ShipPosition shipPosition=positionalMessagesController.showOneMessage("210950000",initialDate);

        positionalMessagesController.writeToFileOne(shipPosition,initialDate);

        assertEquals(shipPosition.getSog(),12.5);

    }
}