package lapr.project.controller;

import lapr.project.model.BSTShip;
import lapr.project.model.Company;
import lapr.project.model.SmallSummary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopNShipsControllerTest {

    @Test
    void inOrderShips() throws IOException, ParseException {
        Company company=new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");

        TopNShipsController topNShipsController = new TopNShipsController(company);

        topNShipsController.inOrderShips("31/12/2020 17:00:00","31/12/2020 23:00:00");
        topNShipsController.write(3);
        TopNShipsController topNShipsController1= new TopNShipsController();
    }

    @Test
    void smallSummariesBeetwenDate() throws IOException, ParseException {

        Company company=new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");

        TopNShipsController topNShipsController = new TopNShipsController(company);
        BSTShip bstShip=company.getBstShip();
        String sDate1 = "31-12-2020 17:02:00";
        String sDate2 = "31-12-2020 17:04:00";
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);
        List<SmallSummary> smallSummaries= bstShip.smallSummariesBeetwenDate(date1,date2);

        List<SmallSummary> smallSummaries1= topNShipsController.smallSummariesDate(date1,date2);


        assertEquals(smallSummaries.toString(),smallSummaries1.toString());
    }


}