package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortTest {

    @Test
    void getId() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","12.4",country);
        Port port=new Port("1","asd",placeLocation);

        assertEquals(port.getId(),"1");
    }

    @Test
    void getName() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","12.4",country);
        Port port=new Port("1","asd",placeLocation);
        assertEquals(port.getName(),"asd");
    }

    @Test
    void getPlaceLocation() {

        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","12.4",country);
        Port port=new Port("1","asd",placeLocation);
        assertEquals(port.getPlaceLocation().getLatitude(),placeLocation.getLatitude());
    }
}