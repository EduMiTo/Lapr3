package lapr.project.controller;

import lapr.project.data.SendToDBController;
import lapr.project.model.Capital;
import lapr.project.model.Company;
import lapr.project.model.Country;
import lapr.project.model.graph.GenericElement;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColoringGraphControllerTest {
    @Test
    void colorGraph() throws SQLException, FileNotFoundException {

        Company company = new Company();

        SendToDBController sendToDBController = new SendToDBController(company);






        sendToDBController.capitals();
        sendToDBController.countries();

        sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.seaDist();


        GraphBuilderController graphBuilderController= new GraphBuilderController(company);

        graphBuilderController.buildGraph(3);

         ColoringGraphController coloringGraphController= new ColoringGraphController(company);

         coloringGraphController.color();

    }

    @Test
    void getElementsOrderByDegree() {

        Company company= new Company();

        List<GenericElement> elements= new ArrayList<>();
        List<Integer> degrees=new ArrayList<>();

        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        Capital capital1= new Capital("Madrid","1","2","es");

        Country country1= new Country("es","Europa","a","b","1",capital1);

        Capital capital2= new Capital("Rome","1","2","it");

        Country country2= new Country("it","Europa","a","b","1",capital2);

        elements.add(new GenericElement(country));
        elements.add(new GenericElement(country1));
        elements.add(new GenericElement(country2));

        degrees.add(1);
        degrees.add(3);
        degrees.add(2);

        ColoringGraphController coloringGraphController=new ColoringGraphController(company);
        elements=coloringGraphController.getElementsOrderByDegree(elements,degrees);

        List<GenericElement> genericElements= new ArrayList<>();

        genericElements.add(new GenericElement(country1));
        genericElements.add(new GenericElement(country2));
        genericElements.add(new GenericElement(country));


        assertEquals(elements,genericElements);


    }
}