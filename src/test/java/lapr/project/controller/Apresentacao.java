package lapr.project.controller;
import lapr.project.data.*;
import lapr.project.model.Company;
import lapr.project.model.ShipPosition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Apresentacao {
    @Test
    void global() throws IOException, ParseException, SQLException {
        Company company= new Company();
        //US1
        ImportCSVtoBST importCSVtoBST= new ImportCSVtoBST(company);
        importCSVtoBST.importCSV("sships.csv");
      //  importCSVtoBST.importCSV("bships.csv");
        //US2
        SearchShipController searchShipController = new SearchShipController(company);
        searchShipController.getShipsByMMSI("228339600");
        searchShipController.write();

        //US3
        Date initialDate=  new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 17:03");
        Date finalDate= new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 18:31");
        String mmsi="210950000";
        PositionalMessagesController positionalMessagesController=new PositionalMessagesController(company);
        List<ShipPosition> shipPositions=positionalMessagesController.showMessages(mmsi,initialDate,finalDate);
        positionalMessagesController.writeToFile(shipPositions,initialDate,finalDate);

        ShipPosition shipPosition=positionalMessagesController.showOneMessage("210950000",initialDate);
        positionalMessagesController.writeToFileOne(shipPosition, initialDate);


        //US4
        ShipInfoController shipInfoController = new ShipInfoController(company);
        shipInfoController.getInfoByMMSI("636092932");

        //US5
        ListSmallSummaryController listSmallSummaryController= new ListSmallSummaryController(company);
        listSmallSummaryController.getShips();

        //US6
        TopNShipsController topNShipsController = new TopNShipsController(company);
        topNShipsController.inOrderShips("31/12/2020 17:00:00","31/12/2020 23:00:00");
        topNShipsController.write(3);


        //US7
        PairShipController pairShipController= new PairShipController(company);
        pairShipController.showInfo();



        US204Controller uS204Controller = new US204Controller(company);
        uS204Controller.CallUs204(748163149);

        US205Controller uS205Controller = new US205Controller(company);
        uS205Controller.CallUs205("210950000");

        US206Controller uS206Controller = new US206Controller(company);
        uS206Controller.CallUs206("636092932");

        US207Controller uS207Controller = new US207Controller(company);
        uS207Controller.CallUs207(2004 ,"212180000");

        US208Controller uS208Controller = new US208Controller(company);
        uS208Controller.CallUs208(3,"258692000");

        String date ="14.10.21 18:44:33";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
        Date parsedDate = dateFormat.parse(date);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        US209Controller uS209Controller = new US209Controller(company);
        uS209Controller.CallUs209("210950000", timestamp);

        US210Controller uS210Controller = new US210Controller(company);
        uS210Controller.CallUs210();



    }
}
