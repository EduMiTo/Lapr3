package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.*;

public class PairShipController {



    private final BSTShip bstShip;


    private final Company company;

    Map<Ships,List<Ships>> map;

    public PairShipController(){
        this.company=App.getInstance().getCompany();
        this.bstShip= company.getBstShip();

    }

    public PairShipController(Company company){
        this.company=company;
        this.bstShip= company.getBstShip();

    }

    public Map<Ships, List<Ships>> getMap() {
        return bstShip.pairShips();
    }


    public void showInfo() throws FileNotFoundException {
        map=getMap();
        StringBuilder toFile=new StringBuilder();

        for (Map.Entry<Ships, List<Ships>> entry : map.entrySet()) {

            Ships ships= entry.getKey();

            BSTShipPosition bstShipPosition =ships.getBstMessage();



            for (Ships otherShip : entry.getValue()) {

                BSTShipPosition bstShipPosition1 =otherShip.getBstMessage();


                toFile.append("MMSI first ship: ").append(ships.getMmsi()).append(" : ").append("MMSI second ship: ").append(otherShip.getMmsi()).append("\n");


                toFile.append(String.format("traveled distance difference: %.2f",Math.abs(bstShipPosition.inorderCalculateDistance() - bstShipPosition1.inorderCalculateDistance()))).append("\n");

                double depdist= bstShipPosition.calculateDistance(Float.parseFloat(bstShipPosition.minDate().getLatitude()),Float.parseFloat(bstShipPosition.minDate().getLongitude()),Float.parseFloat(bstShipPosition1.minDate().getLatitude()),Float.parseFloat(bstShipPosition1.minDate().getLongitude()));

                double arrdist= bstShipPosition.calculateDistance(Float.parseFloat(bstShipPosition.maxDate().getLatitude()),Float.parseFloat(bstShipPosition.maxDate().getLongitude()),Float.parseFloat(bstShipPosition1.maxDate().getLatitude()),Float.parseFloat(bstShipPosition1.maxDate().getLongitude()));

                toFile.append(String.format("difference in departure: %.2f",depdist)).append(String.format("     : difference in em arrival: %.2f",arrdist)).append("\n\n");
            }

        }

        new WriteTxt(toFile,"pairShip.txt");



    }




}
