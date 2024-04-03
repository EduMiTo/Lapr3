package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.SmallSummary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListSmallSummaryControllerTest {

    ListSmallSummaryController listSmallSummaryController;

    public ListSmallSummaryControllerTest() throws IOException, ParseException {
        Company company= new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");

       listSmallSummaryController= new ListSmallSummaryController(company);
        listSmallSummaryController.getShips();

        ListSmallSummaryController listSmallSummaryController= new ListSmallSummaryController();
    }

   /* @Test
    void getShips() throws IOException, ParseException {

        Company company= new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");

        ListSmallSummaryController listSmallSummaryController= new ListSmallSummaryController();
        listSmallSummaryController.getShips();

    }*/


    @Test
    void organizeAsc() {
        List<SmallSummary> smallSummaries;
        smallSummaries=listSmallSummaryController.orderAs();
        boolean flag= true;
        SmallSummary anterior= smallSummaries.get(0);
        for (int i=1;i<smallSummaries.size()-1;i++){
            SmallSummary atual= smallSummaries.get(i);
            if (anterior.getNumberOfMovements()>atual.getNumberOfMovements()){
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    void organizeDesc() {
        List<SmallSummary> smallSummaries;
        smallSummaries=listSmallSummaryController.orderDes();

        boolean flag= true;
        SmallSummary anterior= smallSummaries.get(0);
        for (int i=1;i<smallSummaries.size()-1;i++){
            SmallSummary atual= smallSummaries.get(i);
            if (anterior.getRealDistance()<atual.getRealDistance()){
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }
}