package lapr.project.data;

import lapr.project.model.SeaDist;

import java.util.ArrayList;
import java.util.List;

public class SeaDistStore {
    private List<SeaDist> seaDistList;

    public SeaDistStore(){
        seaDistList=new ArrayList<>();
    }

    public List<SeaDist> getSeaDistList(){
        return seaDistList;
    }


    public boolean newList(List<SeaDist> seaDists){
            seaDistList=seaDists;
            return true;

    }

    public double getSeadistance(String port1, String port2){

        for (SeaDist s: seaDistList){

            if (( s.getPortTo().getName().equals(port1) && s.getPortFrom().getName().equals(port2) ) ||( s.getPortFrom().getName().equals(port1) && s.getPortTo().getName().equals(port2) )  ){
                return s.getSeaDistance();
            }

        }

        return 0;

    }
}
