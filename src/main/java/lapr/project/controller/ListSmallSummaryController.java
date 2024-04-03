package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.*;

public class ListSmallSummaryController {

    private final BSTShip bstShip;
    private List<SmallSummary> smallSummaries;
    private final Company company;

    public ListSmallSummaryController() {
        this.company=App.getInstance().getCompany();
        bstShip = company.getBstShip();
    }
    public ListSmallSummaryController(Company company) {
        this.company=company;
        bstShip = company.getBstShip();
    }

    public void getShips() throws FileNotFoundException {
        smallSummaries=new ArrayList<>();

        organizeInformation();

        List<SmallSummary> smallSummariesA=orderAs();
        StringBuilder toFile=new StringBuilder();
        toFile.append("MMSI\tNumber of movements\t Delta distance\t Travelled distance\n");
        for (SmallSummary s : smallSummariesA){
            toFile.append(s.getMmsi()).append("\t\t");
            toFile.append(s.getNumberOfMovements()).append("\t\t\t\t");
            toFile.append(String.format("%.2f",s.getDeltaDistance())).append("\t\t\t");
            toFile.append(String.format("%.2f",s.getRealDistance())).append("\n");
        }
        new WriteTxt(toFile,"SmallSummaryAscendente.txt");


        List<SmallSummary> smallSummariesD=orderDes();
        StringBuilder toFile1=new StringBuilder();
        toFile1.append("MMSI\tNumber of movements\t Delta distance\t Travelled distance\n");
        for (SmallSummary s : smallSummariesD){
            toFile1.append(s.getMmsi()).append("\t\t");
            toFile1.append(s.getNumberOfMovements()).append("\t\t\t");
            toFile1.append(String.format("%.2f",s.getDeltaDistance())).append("\t\t");
            toFile1.append(String.format("%.2f",s.getRealDistance())).append("\n");
        }
        new WriteTxt(toFile1,"SmallSummaryDescendente.txt");

    }


    public List<SmallSummary> orderAs(){
        return organizeAsc(smallSummaries);
    }
    public List<SmallSummary> orderDes(){
        return bstShip.orderVesselDes(smallSummaries);
    }

    public void organizeInformation(){

       smallSummaries= bstShip.organizeSmallSummaryInfo();


    }

    public List<SmallSummary> organizeAsc(List<SmallSummary> smallSummaries){
        Collections.sort(smallSummaries, Comparator.comparingInt(SmallSummary::getNumberOfMovements));
        return smallSummaries;
    }

}
