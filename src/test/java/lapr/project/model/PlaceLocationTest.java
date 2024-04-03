package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceLocationTest {

    @Test
    void getLatitude() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","12.4",country);

        assertEquals(placeLocation.getLatitude(),"12.5");
    }

    @Test
    void getLatitude2() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("91","12.4",country);
        assertEquals(placeLocation.getLatitude(),"Not available");
    }
    @Test
    void getLatitude3() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("-91","12.4",country);
        assertEquals(placeLocation.getLatitude(),"Not available");
    }
    @Test
    void getLatitude4() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("90","12.4",country);
        assertEquals(placeLocation.getLatitude(),"90");
    }
    @Test
    void getLatitude5() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("-90","12.4",country);
        assertEquals(placeLocation.getLatitude(),"-90");
    }
    @Test
    void getLongitude() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.4","12.4",country);
        assertEquals(placeLocation.getLongitude(),"12.4");
    }
    @Test
    void getLongitude1() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","181",country);
        assertEquals(placeLocation.getLongitude(),"Not available");
    }
    @Test
    void getLongitude2() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","-181",country);
        assertEquals(placeLocation.getLongitude(),"Not available");
    }
    @Test
    void getLongitude3() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","180",country);
        assertEquals(placeLocation.getLongitude(),"180");
    }
    @Test
    void getLongitude4() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","-180",country);
        assertEquals(placeLocation.getLongitude(),"-180");
    }

    @Test
    void getCountry() {
        Country country= new Country("Portugal", "Europa");
        PlaceLocation placeLocation= new PlaceLocation("12.5","12.4",country);
        assertEquals(placeLocation.getCountry().getName(),country.getName());
    }


}