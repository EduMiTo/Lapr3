package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PositionalMessagesController {

    private final BSTShip bstShip;
    private Ships ship;
    private BSTShipPosition bstShipPosition;
    private final Company company;

    public PositionalMessagesController(){
        this.company=App.getInstance().getCompany();
        bstShip= company.getBstShip();
    }
    public PositionalMessagesController(Company company){
        this.company=company;
        bstShip= company.getBstShip();
    }


    public List<ShipPosition> showMessages(String mmsi, Date date1, Date date2){

        BSTShip.Node<Ships> node= bstShip.search(mmsi);

        ship = node.getElement();

        bstShipPosition = ship.getBstMessage();

        return (List<ShipPosition>) bstShipPosition.searchSpecificDatePeriodcall(date1, date2);
    }

    public ShipPosition showOneMessage(String mmsi, Date date){
        BSTShip.Node<Ships> node= bstShip.search(mmsi);

        ship = node.getElement();

        bstShipPosition = ship.getBstMessage();

        return (ShipPosition) bstShipPosition.search(date).getElement();
    }

    public void writeToFile(List<ShipPosition> shipPositions, Date date1, Date date2) throws FileNotFoundException {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
        StringBuilder toFile=new StringBuilder();
        toFile.append("MMSI\t\t BaseDateTime\t\t Latitude\t Longitude\t Sog\t Cog\t Heading\n");
        for(ShipPosition mes: shipPositions){
            toFile.append(ship.getMmsi()).append("\t ").append(dateFormat.format(mes.getAisMessage())).append("\t ").append(mes.getLatitude()).append("\t ").append(mes.getLongitude()).append("\t ").append(mes.getSog()).append("\t ").append(mes.getCog()).append("\t ").append(mes.getHeading()).append("\n");
        }
        String sdate1 = dateFormat.format(date1);
        String sdate2 = dateFormat.format(date2);

        new WriteTxt(toFile,"PositionalMessages_"+sdate1+"_"+sdate2+".txt");

    }

    public void writeToFileOne(ShipPosition mes,Date date1) throws FileNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
        StringBuilder toFile=new StringBuilder();
        toFile.append("MMSI: ").append(ship.getMmsi()).append("\tBaseDateTime: ").append(dateFormat.format(mes.getAisMessage())).append("\tLatitude: ").append(mes.getLatitude()).append("\tLongitude: ").append(mes.getLongitude()).append("\tSOG: ").append(mes.getSog()).append("\tCOG: ").append(mes.getCog()).append("\tHeading: ").append(mes.getHeading()).append("\n");

        String sdate1 = dateFormat.format(date1);

        new WriteTxt(toFile,"PositionalMessages_One"+sdate1+".txt");

    }



}
