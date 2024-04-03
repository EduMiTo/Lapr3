package lapr.project.model;

import lapr.project.model.graph.GenericElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryStoreTest {

    @Test
    void verify() {

        CountryStore countryStore= new CountryStore();

        Country country=countryStore.verify("asd");

        assertNull(country);
    }
    @Test
    void verify2() {

        CountryStore countryStore= new CountryStore();

        Country country = new Country("Portugal","Europa");
        countryStore.save(country);


        Country c= countryStore.verify("Portugal");

        assertEquals(c.getName(),"Portugal");
    }
    @Test
    void verify3() {

        CountryStore countryStore= new CountryStore();

        Country country = new Country("Portugal","Europa");
        Country country2 = new Country("Portugal2","Europa2");
        Country country3 = new Country("Portugal3","Europa3");
        countryStore.save(country);
        countryStore.save(country2);
        countryStore.save(country3);

        Country c= countryStore.verify("Portugal3");

        assertEquals(c.getName(),"Portugal3");
    }

    @Test
    void nGenericList() {
        Company company= new Company();

        List<Country> countries = new ArrayList<>();

        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);


        countries.add(country);


        company.getCountryList().newList(countries);

        List<GenericElement> genericElements2= company.getCountryList().nGenericList();

        List<GenericElement> genericElements= new ArrayList<>();

        genericElements.add(new GenericElement(country));


        assertEquals(genericElements,genericElements2);
    }

    @Test
    void newList() {
        Company company= new Company();

        List<Country> countries = new ArrayList<>();

        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        countries.add(country);

        assertTrue(company.getCountryList().newList(countries));
    }

    @Test
    void searchByCapital() {
        Company company= new Company();

        List<Country> countries = new ArrayList<>();

        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        countries.add(country);

        company.getCountryList().newList(countries);

        Country country1= company.getCountryList().searchByCapital("Lisbon");

        assertEquals(country,country1);



    }

    @Test
    void verifyifCountryinContinent() {
        Company company= new Company();

        List<Country> countries = new ArrayList<>();

        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        countries.add(country);

        company.getCountryList().newList(countries);

        assertTrue(company.getCountryList().verifyifCountryinContinent("pt","Europa"));

    }

    @Test
    void verifyifCountryinContinent1() {
        Company company= new Company();

        List<Country> countries = new ArrayList<>();

        Capital capital= new Capital("Lisbon","1","2","pt");

        Country country= new Country("pt","Europa","a","b","1",capital);

        countries.add(country);

        company.getCountryList().newList(countries);

        assertFalse(company.getCountryList().verifyifCountryinContinent("pt","America"));

    }
}