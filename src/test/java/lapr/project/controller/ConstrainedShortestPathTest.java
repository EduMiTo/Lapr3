package lapr.project.controller;

import lapr.project.model.Capital;
import lapr.project.model.Company;
import lapr.project.model.Country;
import lapr.project.model.graph.GenericElement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConstrainedShortestPathTest {

    @Test
    void callDijkstra() throws IOException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");


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
    void callDijkstra1() throws IOException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");


        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        ConstrainedShortestPath constrainedShortestPath= new ConstrainedShortestPath(company);

        List<String> strings= new ArrayList<>();

        strings.add("Nicosia");
        strings.add("Athens");
        strings.add("Lisbon");
        strings.add("Tirana");
        strings.add("London");


        constrainedShortestPath.selectTypeForDijkstra(2,strings,5);


    }

    @Test
    void callDijkstra2() throws IOException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");


        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        ConstrainedShortestPath constrainedShortestPath= new ConstrainedShortestPath(company);

        List<String> strings= new ArrayList<>();

        strings.add("Liverpool");


        strings.add("New Jersey");

        strings.add("Rio Grande");

        constrainedShortestPath.selectTypeForDijkstra(3,strings,5);


    }

    @Test
    void callDijkstra3() throws IOException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");


        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        ConstrainedShortestPath constrainedShortestPath= new ConstrainedShortestPath(company);

        List<String> strings= new ArrayList<>();

        strings.add("Washington");
        strings.add("Liverpool");
        strings.add("Rome");
        strings.add("Lisbon");
        strings.add("Los Angeles");
        strings.add("Rio Grande");
        strings.add("Brasilia");
        strings.add("Madrid");



        constrainedShortestPath.selectTypeForDijkstra(1,strings,5);


    }

    @Test
    void getPos() {
        List<Integer> integer= new ArrayList<>();
        integer.add(1);
        integer.add(15);
        integer.add(20);
        integer.add(25);
        integer.add(30);
        integer.add(35);
        integer.add(50);
        integer.add(83);
        integer.add(177);
        integer.add(1900);
        integer.add(30001);
        integer.add(55555);

        Company company= new Company();
        ConstrainedShortestPath constrainedShortestPath= new ConstrainedShortestPath(company);

       int real= constrainedShortestPath.getPos(integer,177);

        assertEquals(8,real);
    }
}