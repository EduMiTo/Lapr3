package lapr.project.data;

import lapr.project.model.Border;
import lapr.project.model.graph.GenericElement;

import java.util.ArrayList;
import java.util.List;

public class BorderStore {

    private List<Border> borders;


    public BorderStore(){
        borders=new ArrayList<>();
    }


    public List<Border> getBorderList() {
        return borders;
    }

    public void save(Border border){
        borders.add(border);
    }



    public boolean newList(List<Border> borders){
            this.borders=borders;
            return true;
    }

    public List<GenericElement> getCountriesThatHaveBorder(String country){

        List<GenericElement> countries= new ArrayList<>();
        for (Border border : borders){

            if (border.getCountry1().getName().equals(country)){
                countries.add(new GenericElement(border.getCountry2()));
            }

        }
        return countries;

    }



}
