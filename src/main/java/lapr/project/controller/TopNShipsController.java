package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TopNShipsController {

    private final BSTShip bstShip;

    private final Company company;
    private Map<String, List<SmallSummary>> map;

    public TopNShipsController() {
        this.company=App.getInstance().getCompany();
        map=new TreeMap<>();
        this.bstShip = company.getBstShip();
    }

    public TopNShipsController(Company company) {
        this.company=company;
        map=new TreeMap<>();
        this.bstShip = company.getBstShip();
    }

    public void inOrderShips(String sDate1,String sDate2 ) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        List<SmallSummary> smallSummaries= smallSummariesDate(date1,date2);

        map=getTopMap(smallSummaries);

    }

    public Map<String, List<SmallSummary>> getTopMap(List<SmallSummary> smallSummaries){
        return bstShip.sameVessel(smallSummaries);
    }

    public List<SmallSummary> smallSummariesDate(Date date1, Date date2){
        return bstShip.smallSummariesBeetwenDate(date1,date2);
    }

    public void write(int n) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();


        for (String s : map.keySet()){
            toFile.append("vessel type: ").append(s).append("\n");
            toFile.append("MMSI\tTravelled distance\tMean Sog\n");
            for (int i=0;i<map.get(s).size();i++){
                if(i<n) {
                    toFile.append(map.get(s).get(i).getMmsi()).append("\t");
                    toFile.append(String.format("%.2f",map.get(s).get(i).getRealDistance())).append("\t");
                    toFile.append(String.format("%.2f",map.get(s).get(i).getSog())).append("\n");
                }
            }
            toFile.append("\n");
        }

        new WriteTxt(toFile,"Top"+n+"Ships.txt");
    }

}
