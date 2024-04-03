package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeaDistStoreTest {

    @Test
    void getSeaDistList() {
        Company company= new Company();

        List<SeaDist> seaDistList = new ArrayList<>();

        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);

        SeaDist seaDist = new SeaDist(port,port1,"10");

        List<SeaDist> seaDists= company.getSeaDistStore().getSeaDistList();
        seaDists.add(seaDist);

        seaDistList.add(seaDist);

        assertEquals(seaDistList,seaDists);
    }
    @Test
    void getSeaDistList1() {
        Company company= new Company();

        List<SeaDist> seaDistList = new ArrayList<>();

        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);

        SeaDist seaDist = new SeaDist(port,port1,"10");

        List<SeaDist> seaDists= company.getSeaDistStore().getSeaDistList();


        assertEquals(seaDistList,seaDists);
    }

    @Test
    void newList() {


            Company company= new Company();

            List<SeaDist> seaDistList = new ArrayList<>();

            Country country= new Country("pt","Europa");

            PlaceLocation placeLocation = new PlaceLocation("1","1",country);

            Port port= new Port("1","port1",placeLocation);
            Port port1= new Port("2","port2",placeLocation);

            SeaDist seaDist = new SeaDist(port,port1,"10");




            seaDistList.add(seaDist);
            assertTrue(company.getSeaDistStore().newList(seaDistList));




    }

    @Test
    void getSeadistance() {
        Company company= new Company();

        List<SeaDist> seaDistList = new ArrayList<>();

        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);

        SeaDist seaDist = new SeaDist(port,port1,"10");

        seaDistList.add(seaDist);

        company.getSeaDistStore().newList(seaDistList);

        double seaDists= company.getSeaDistStore().getSeadistance(port.getName(),port1.getName());

        assertEquals(10,seaDists);
    }

    @Test
    void getSeadistance1() {
        Company company= new Company();


        double seaDists= company.getSeaDistStore().getSeadistance("a","b");

        assertEquals(0,seaDists);
    }
}