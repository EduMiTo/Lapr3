package lapr.project.controller;

import lapr.project.data.SendToDBController;
import lapr.project.model.Company;
import lapr.project.model.graph.Edge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LargestCircuitControllerTest {

    @Test
    void coloredDFS() throws IOException, SQLException {

        Company company = new Company();

        SendToDBController sendToDBController = new SendToDBController(company);

        sendToDBController.capitals();
        sendToDBController.countries();

        sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.seaDist();


        GraphBuilderController graphBuilderController= new GraphBuilderController(company);

        graphBuilderController.buildGraph(3);
/*
        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");


        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);*/

        LargestCircuitController LargestCircuitController= new LargestCircuitController(company);
        List<Edge> largestCircuit = LargestCircuitController.getLargestCircuit("United Kingdom");
        LargestCircuitController.writeToFile(largestCircuit);

    }
}
