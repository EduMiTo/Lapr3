package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.Date;

public class ShipInfoController {

    private final Company company;

    public ShipInfoController(){
        this.company=App.getInstance().getCompany();
    }
    public ShipInfoController(Company company){
        this.company=company;
    }

    public void getInfoByMMSI(String mmsi) throws FileNotFoundException {


        BSTShip bstShip= company.getBstShip();


        BSTShip.Node<Ships> node= bstShip.search(mmsi);

        Ships ships = node.getElement();

        BSTShipPosition bstShipPosition = ships.getBstMessage();

        ShipPosition firstmove = bstShipPosition.minDate();

        ShipPosition lastmove = bstShipPosition.maxDate();


        String smmsi= ships.getMmsi();

        String shipname= ships.getShipName();

        Date firstDate= firstmove.getAisMessage();

        Date lastDate= lastmove.getAisMessage();

        String timeallmovs=  bstShipPosition.dateDiff(lastmove.getAisMessage(), firstmove.getAisMessage());

        int numofmovs= bstShipPosition.getSize()-1;

        Double maxsog= bstShipPosition.inorderMaxSOG();

        Double minSog= bstShipPosition.inorderMinSOG();

        Double maxCog = bstShipPosition.inorderMaxCOG();

        Double minCog= bstShipPosition.inorderMinCOG();

        Double depLat = Double.parseDouble(firstmove.getLatitude());

        Double depLon = Double.parseDouble(firstmove.getLongitude());

        Double arrLat = Double.parseDouble(lastmove.getLatitude());

        Double arrLon = Double.parseDouble(lastmove.getLongitude());

        Double dist = bstShipPosition.inorderCalculateDistance();

        Double lDist = bstShipPosition.calculateDistance(Float.parseFloat(firstmove.getLatitude()), Float.parseFloat(firstmove.getLongitude()), Float.parseFloat(lastmove.getLatitude()), Float.parseFloat(lastmove.getLongitude()));


        StringBuilder information=new StringBuilder();



        information.append("Ship MMSI number: ").append(smmsi).append(".\n");
        information.append("Ship Vessel Name: ").append(shipname).append(".\n");
        information.append("Start date time: ").append(firstDate).append(".\n");
        information.append("End date time: ").append(lastDate).append(".\n");
        information.append("Time to do all the movements: ").append(timeallmovs).append(".\n");
        information.append("Total number of movements: ").append(numofmovs).append(".\n");
        information.append(String.format("Max sog: %.2f", maxsog)).append(".\n");
        information.append(String.format("Min sog: %.2f", minSog)).append(".\n");
        information.append(String.format("Max Cog: %.2f", maxCog)).append(".\n");
        information.append(String.format("Min Cog: %.2f", minCog)).append(".\n");
        information.append(String.format("Departure latitude: %.2f", depLat)).append(".\n");
        information.append(String.format("Departure longitude: %.2f", depLon)).append(".\n");
        information.append(String.format("Arrive latitude: %.2f", arrLat)).append(".\n");
        information.append(String.format("Arrive longitude: %.2f", arrLon)).append(".\n");
        information.append(String.format("distance: %.2f m", dist)).append(".\n");
        information.append(String.format("linear distance: %.2f m", lDist)).append(".\n");


        new WriteTxt(information,"ShipInformation"+smmsi+".txt");
        
    }





}
