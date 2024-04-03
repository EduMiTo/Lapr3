package lapr.project.model;

import lapr.project.data.CapitalStore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CapitalStoreTest {

    @Test
    void verify() {
        CapitalStore capitalStore= new CapitalStore();

        Capital capital= new Capital("Lisbon","1","2","pt");
        Capital capital1= new Capital("Madrid","1","2","pt");
        Capital capital2= new Capital("Rome","1","2","pt");
        capitalStore.save(capital);
        capitalStore.save(capital1);
        capitalStore.save(capital2);

        Capital c= capitalStore.verify("Lisbon");

        assertEquals(c.getName(),"Lisbon");
    }

    @Test
    void searchByCountry() {
        CapitalStore capitalStore= new CapitalStore();

        Capital capital= new Capital("Lisbon","1","2","pt");
        Capital capital1= new Capital("Madrid","1","2","pt");
        Capital capital2= new Capital("Rome","1","2","pt");
        capitalStore.save(capital);
        capitalStore.save(capital1);
        capitalStore.save(capital2);

        Capital c= capitalStore.searchByCountry("pt");

        assertEquals(c.getName(),"Lisbon");
    }

    @Test
    void newList() {

        Company company=new Company();

        List<Capital> capitals = new ArrayList<>();

        Capital capital= new Capital("Lisbon","1","2","pt");
        Capital capital1= new Capital("Madrid","1","2","pt");
        Capital capital2= new Capital("Rome","1","2","pt");
        capitals.add(capital);
        capitals.add(capital1);
        capitals.add(capital2);

        assertTrue(company.getCapitalStore().newList(capitals));


    }
}