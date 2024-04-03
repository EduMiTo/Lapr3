package lapr.project.data;

import lapr.project.model.Capital;

import java.util.ArrayList;
import java.util.List;

public class CapitalStore {

    private List<Capital> capitals;


    public CapitalStore(){
        capitals=new ArrayList<>();
    }

    public Capital verify(String country){
        for(Capital c : capitals){
            if (c.getName().equals(country)){
                return c;
            }
        }
        return null;
    }

    public List<Capital> getCountryList() {
        return capitals;
    }

    public void save(Capital capital){
        capitals.add(capital);
    }


    public Capital searchByCountry(String country){
        for(Capital c : capitals){
            if (c.getcName().equals(country)){
                return c;
            }
        }
        return null;
    }

    public boolean newList(List<Capital> capitals){
        try {
            this.capitals=capitals;
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
