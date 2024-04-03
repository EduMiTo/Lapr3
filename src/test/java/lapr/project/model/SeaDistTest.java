package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeaDistTest {

    @Test
    void getSeaDistance() {
        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);

        SeaDist seaDist = new SeaDist(port,port1,"10");

        assertEquals(10,seaDist.getSeaDistance());
    }
}