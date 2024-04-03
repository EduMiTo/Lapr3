package lapr.project.controller;

import lapr.project.data.SendToDBController;
import lapr.project.model.Company;
import lapr.project.model.graph.Edge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ESINF {

    @Test
    void criticalPortApresentacao() throws IOException, SQLException {

        Company company= new Company();


        SendToDBController sendToDBController = new SendToDBController(company);


        sendToDBController.capitals();
        sendToDBController.countries();

        sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.seaDist();

        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        CriticalPortController criticalPortController= new CriticalPortController(company);

        criticalPortController.criticalPort(5,5);

    }


    @Test
    void callDijkstra() throws IOException, SQLException {
        Company company= new Company();


        SendToDBController sendToDBController = new SendToDBController(company);


        sendToDBController.capitals();
        sendToDBController.countries();

        sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.seaDist();


        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        ConstrainedShortestPath constrainedShortestPath= new ConstrainedShortestPath(company);

        List<String> strings= new ArrayList<>();

        strings.add("Nicosia");
        strings.add("Athens");
        strings.add("Lisbon");
        strings.add("Tirana");
        strings.add("London");



        constrainedShortestPath.selectTypeForDijkstra(1,strings,5);


    }

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


        LargestCircuitController LargestCircuitController= new LargestCircuitController(company);
        List<Edge> largestCircuit = LargestCircuitController.getLargestCircuit("United Kingdom");
        LargestCircuitController.writeToFile(largestCircuit);

    }


}
