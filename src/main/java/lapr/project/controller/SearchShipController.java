package lapr.project.controller;
import lapr.project.model.BSTShip;
import lapr.project.model.Company;
import lapr.project.model.Ships;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class SearchShipController {

    private Ships ship;
    private final BSTShip bstShip;
    private final Company company;
    

    public SearchShipController(Company company) {
        this.company=company;
        this.bstShip = company.getBstShip();
    }

    public Ships getShipsByMMSI(String mmsi) {
        this.ship=bstShip.getShipsByMMSI(mmsi);
        return ship;
    }

    public Ships getShipsByIMO(String imo) {

        this.ship= bstShip.getShipsByIMO(imo);
        return ship;
    }

    public Ships getShipsByCallSign(String callSign) {

        this.ship= bstShip.getShipsByCallSign(callSign);
        return ship;
    }

    public void write() throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();
        toFile.append("MMSI: ").append(ship.getMmsi()).append("\n");
        toFile.append("IMO: ").append(ship.getImo()).append("\n");
        toFile.append("Call Sign: ").append(ship.getCallSign()).append("\n");
        toFile.append("Ship Name: ").append(ship.getShipName()).append("\n");
        toFile.append("Vessel type: ").append(ship.getVesselType()).append("\n");
        new WriteTxt(toFile,"searchShip_"+ ship.getMmsi()+".txt");
    }
}
