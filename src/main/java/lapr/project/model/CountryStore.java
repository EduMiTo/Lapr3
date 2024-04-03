package lapr.project.model;

import lapr.project.model.graph.GenericElement;

import java.util.ArrayList;
import java.util.List;

public class CountryStore {



    private List<Country> countryList;


    public CountryStore(){
        countryList=new ArrayList<>();
    }

    public Country verify(String country){
        for(Country c : countryList){
            if (c.getName().equals(country)){
                return c;
            }
        }
        return null;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public List<GenericElement> nGenericList(){
        List<GenericElement> genericElements=new ArrayList<>();

        for (Country country: countryList){
            genericElements.add(new GenericElement(country));
        }
        return genericElements;
    }

    public void save(Country country){
        countryList.add(country);
    }

    public boolean newList(List<Country> countries){
            countryList=countries;
            return true;
    }

    public Country searchByCapital(String capital){
        for(Country c : countryList){
            if (c.getCapital().getName().equals(capital)){
                return c;
            }
        }
        return null;
    }
    public Country getCountry(String country){
        for(Country c : countryList){
            if (c.getName().equals(country)){
                return c;
            }
        }
        return null;
    }

    public Boolean verifyifCountryinContinent(String country, String continent){

        for(Country c : countryList){
            if (c.getName().equals(country)){
                if (c.getContinent().equals(continent)){
                    return true;
                }
            }
        }
        return false;

    }

}

