package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class NearestPort {

    private final BSTShip bstShip;
    private final Company company;
    private final Port2dTree port2dTree;
    private ShipPosition shipPosition;

    public NearestPort(Company company) {
        this.company = company;
        bstShip= company.getBstShip();
        port2dTree= company.getPort2dTree();
    }

    public NearestPort() {
        this.company = App.getInstance().getCompany();
        bstShip= company.getBstShip();
        port2dTree= company.getPort2dTree();
    }

    public Port getShipAndShipPosition(String callSign, String sDate) throws FileNotFoundException {

        try {
            Ships ship = bstShip.getShipsByCallSign(callSign);

            this.shipPosition= ship.getSpecificShipPosition(sDate);

            return nearestPort(callSign, sDate);
        }catch (Exception e) {
            StringBuilder toFile = new StringBuilder();
            toFile.append("CallSign or Data are invalid ");
            new WriteTxt(toFile,"NearestPort_"+callSign+"_"+sDate);
            return null;
        }
    }

    private Port nearestPort(String callSign, String sDate) throws FileNotFoundException {

        double latitude = Double.parseDouble(shipPosition.getLatitude());
        double longitude = Double.parseDouble(shipPosition.getLongitude());
        Port port = (Port) port2dTree.findNearestNeighbour(latitude, longitude);
        StringBuilder toFile = new StringBuilder();
        toFile.append("Code: ").append(port.getId()).append(" PortName: ").append(port.getName()).append(" Country: ").append(port.getPlaceLocation().getCountry().getName()).append(" Continent: ").append(port.getPlaceLocation().getCountry().getContinent());
        new WriteTxt(toFile,"NearestPort_"+callSign+"_"+sDate);;
        return port;

    }



}
