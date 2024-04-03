package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    void getName() {
        Country country= new Country("Portugal", "Europa");
        assertEquals(country.getName(),"Portugal");
    }

    @Test
    void getContinent() {
        Country country= new Country("Portugal", "Europa");
        assertEquals(country.getContinent(),"Europa");
    }

    @Test
    void getAlpha2code() {
        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        assertEquals("a",country.getAlpha2code());
    }

    @Test
    void getAlpha3code() {
        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        assertEquals("b",country.getAlpha3code());
    }

    @Test
    void getPopulation() {
        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        assertEquals(1,country.getPopulation());
    }
}