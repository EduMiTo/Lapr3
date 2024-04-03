package lapr.project.model;

import lapr.project.model.graph.GenericElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorderStoreTest {

    @Test
    void newList() {
        Company company= new Company();


        Country country= new Country("pt","Europa");

        Country country1= new Country("es","Europa");

        Border border= new Border(country,country1);

        List<Border> borders= new ArrayList<>();

        borders.add(border);



        assertTrue(company.getBorderStore().newList(borders));
    }

    @Test
    void getCountriesThatHaveBorder() {
        Company company= new Company();


        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        Capital capital1= new Capital("Madrid","1","2","pt");

        Country country1= new Country("es","Europa","a","b","1",capital1);

        Border border= new Border(country,country1);

        List<Border> borders= new ArrayList<>();

        borders.add(border);

        company.getBorderStore().newList(borders);

        List<GenericElement> genericElement= company.getBorderStore().getCountriesThatHaveBorder("pt");

        List<GenericElement> genericElements= new ArrayList<>();

        genericElements.add(new GenericElement(country1));

        assertEquals(genericElement,genericElements);

    }
}