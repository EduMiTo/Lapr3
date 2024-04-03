package lapr.project.controller;

import lapr.project.data.SendToDBController;
import lapr.project.model.Capital;
import lapr.project.model.Company;
import lapr.project.model.Country;
import lapr.project.model.graph.GenericElement;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClosestLocalControllerTest {

    @Test
    void callDijkstraApresentacaoEsinf() throws IOException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");





/*


        sendToDBController.capitals();
        sendToDBController.countries();

        sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.SeaDist();
*/
        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        ClosestLocalController closestLocalController= new ClosestLocalController(company);

        List<GenericElement> elementList=closestLocalController.callDijkstra("America",1);

        //https://graphonline.ru/pt/?graph=aJfunVEsXRtDwZuK
        //Olhar apenas para as ligaçoes entre paises da america
        System.out.println(elementList);

        List<GenericElement> elements= new ArrayList<>();

        Capital capital= new Capital("New Jersey","1","2","United States");

        Country country= new Country("United States","Europa","a","b","1",capital);



        elements.add(new GenericElement(country));

        assertEquals(elementList, elements);



    }
/*
    @Test
    void callDijkstra() throws SQLException {
        Company company= new Company();
        SendToDBController sendToDBController = new SendToDBController(company);




        sendToDBController.capitals();
        sendToDBController.countries();

        sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.SeaDist();

        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(3);

        ClosestLocalController closestLocalController= new ClosestLocalController(company);

        List<GenericElement> elementList=closestLocalController.callDijkstra("America",3);

        System.out.println(elementList);


        List<GenericElement> elements= new ArrayList<>();

        Capital capital= new Capital("Balboa","1","2","Panama");

        Country country= new Country("Panama","America","a","b","1",capital);

        Capital capital1= new Capital("Panama City","1","2","Panama");

        Country country1= new Country("Panama","America","a","b","1",capital1);

        Capital capital2= new Capital("Cristobal","1","2","Panama");

        Country country2= new Country("Panama","America","a","b","1",capital2);

        elements.add(new GenericElement(country2));
        elements.add(new GenericElement(country));
        elements.add(new GenericElement(country1));


        assertEquals(elementList, elements);



    }
*/

    @Test
    void callDijkstra1() throws SQLException, FileNotFoundException {
        Company company= new Company();
        SendToDBController sendToDBController = new SendToDBController(company);




        sendToDBController.capitals();
        sendToDBController.countries();

        sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.seaDist();

        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(3);

        ClosestLocalController closestLocalController= new ClosestLocalController(company);

        closestLocalController.sendContinentToCallDijkstra(3);



    }
}