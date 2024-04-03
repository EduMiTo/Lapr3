package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ships implements Comparable<Ships> {
    /**
     * String that contains the mmsi
     */
    private final String mmsi;
    /**
     * String that contains the ship name
     */
    private final String shipName;
    /**
     * String that contains the imo
     */
    private final String imo;
    /**
     * int that contains the number of generators
     */
    private int generators;
    /**
     * int that contains the generator output power
     */
    private String genertorPowerOutput;
    /**
     * String that contains the Call sign
     */
    private final String callSign;
    /**
     * String that contains the vessel type
     */
    private final String vesselType;
    /**
     * int that contains the lenght of a ship
     */
    private final float lenght;
    /**
     * int that contains the width of a ship
     */
    private final float width;
    /**
     * int that contains the capacity of a ship
     */
    private final int capacity;


    /**
     * float that contains the draft
     */
    private final float draft;

    /**
     * Object that contains the different positions of the ship
     */
    private final BSTShipPosition bstShipPosition;

    /**
     * Creates a Ship, receiving by parameter the attributes.
     *
     * @param mmsi The mmsi code
     * @param imo The imo code
     * @param callSign The call sign
     * @param vesselType The vessel type
     * @param lenght The lenght
     * @param width The width
     * @param draft The draft
     * @param tree The tree of ship positions
     */
    public Ships(String mmsi, String shipName, String imo, String callSign, String vesselType, String lenght, String width, String draft, BSTShipPosition tree) {
        if (mmsi.length() != 9)
            throw new IllegalArgumentException("MMSI needs to be 9 characters");
        this.mmsi = mmsi;
        this.shipName = shipName;
        if (imo.length() != 10)
            throw new IllegalArgumentException("IMO needs to be 'IMO' plus 7 characters");
        this.imo = imo;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.lenght = Integer.parseInt(lenght);
        this.width = Integer.parseInt(width);
        this.draft = Float.parseFloat(draft);

        this.bstShipPosition =tree;
        //Container dimensions 3c 1l 2a
        this.capacity= (int)((this.width*this.lenght*6)/ 6);

    }
    /**
     *
     * @return the imo code.
     */
    public String getImo() {
        return imo;
    }

    /**
     *
     * @return the mmsi code.
     */
    public String getMmsi() {
        return mmsi;
    }

    /**
     *
     * @return the ship name.
     */
    public String getShipName() {
        return shipName;
    }

    /**
     *
     * @return the call sign.
     */
    public String getCallSign() {return callSign; }

    /**
     *
     * @return the vessel type.
     */
    public String getVesselType() {
        return vesselType;
    }
    /**
     *
     * @return the number relative to the comparison of the mmsi.
     */
    @Override
    public int compareTo(Ships o) {
        return this.mmsi.compareTo(o.getMmsi());
    }

    /**
     *
     * @return the ship to string.
     */
    @Override
    public String toString() {
        return "Ships:" +
                "mmsi='" + mmsi + '\'' +
                "imo='" +imo+ '\'' +
                "callSign='"+callSign+'\''+
                "\n\n";
    }

    /**
     *
     * @return the bst of ship positions.
     */
    public BSTShipPosition getBstMessage() {
        return bstShipPosition;
    }



    public ShipPosition getSpecificShipPosition(String sDate) throws ParseException {

        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH-mm");
        Date date=formatter.parse(sDate);

        return (ShipPosition) bstShipPosition.search(date).getElement();
    }

    public int getGenerators() {
        return generators;
    }

    public String getGenertorPowerOutput() {
        return genertorPowerOutput;
    }

    public float getLenght() {
        return lenght;
    }

    public float getWidth() {
        return width;
    }

    public int getCapacity() {
        return capacity;
    }

    public float getDraft() {
        return draft;
    }
}
