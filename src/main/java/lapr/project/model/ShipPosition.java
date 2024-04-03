package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class ShipPosition implements Comparable<ShipPosition> {

    /**
     *
     * Date of the Ship Position.
     */
    private final Date aisMessage;
    /**
     *
     * string of the latitude.
     */
    private final String latitude;
    /**
     *
     * string of the longitude.
     */
    private final String longitude;
    /**
     *
     * float of the sog.
     */
    private final float sog;
    /**
     *
     * float of the cog.
     */
    private final float cog;
    /**
     *
     * string of the heading.
     */
    private final String heading;
    /**
     *
     * int of the position.
     */
    private int position;
    /**
     *
     * string of the cargo.
     */
    private final String cargo;



    /**
     *
     * string of the transceiver.
     */
    private final String transceiver;


    private final String shipMMSI;

    /**
     * Creates a Ship position, receiving by parameter the attributes.
     *
     * @param aisMessage The date
     * @param latitude The latitude
     * @param longitude The longitude
     * @param sog The sog
     * @param cog The cog
     * @param heading The heading
     * @param cargo The cargo
     * @param transceiver The transceiver class
     */
    public ShipPosition(String aisMessage, String latitude, String longitude, String sog, String cog, String heading, String cargo, String transceiver, String shipMMSI) throws ParseException {
        this.aisMessage=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(aisMessage);
        if (Double.parseDouble(latitude)> 90 || Double.parseDouble(latitude)<-90) {
            this.latitude = "Not available";
        }
        else{
            this.latitude = latitude;
        }
        if (Double.parseDouble(longitude)> 180 || Double.parseDouble(longitude)<-180) {
            this.longitude = "Not available";
        }
        else{
            this.longitude = longitude;
        }

        this.sog = Float.parseFloat(sog);

        if (Double.parseDouble(cog)<0 ) {

            this.cog = Float.parseFloat(cog)+360;
        }
        else {
            this.cog = Float.parseFloat(cog);
        }



        if (Double.parseDouble(heading)<0 ) {

            this.heading = String.valueOf(Float.parseFloat(heading)+360);
        }
        else {
            if (Double.parseDouble(heading)==511){
                this.heading="Not available";
            }
            else {
                this.heading = heading;
            }
        }
        this.cargo = cargo;
        this.transceiver = transceiver;
        this.shipMMSI=shipMMSI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipPosition shipPosition = (ShipPosition) o;
        return Float.compare(shipPosition.sog, sog) == 0 &&
                Float.compare(shipPosition.cog, cog) == 0 &&
                position == shipPosition.position &&
                Objects.equals(aisMessage, shipPosition.aisMessage) &&
                Objects.equals(latitude, shipPosition.latitude) &&
                Objects.equals(longitude, shipPosition.longitude) &&
                Objects.equals(heading, shipPosition.heading) &&
                Objects.equals(cargo, shipPosition.cargo) &&
                Objects.equals(transceiver, shipPosition.transceiver);
    }
    @Override
    public int compareTo(ShipPosition o) {
        return this.aisMessage.compareTo(o.getAisMessage());
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.format(this.aisMessage)+" sog:"+String.valueOf(sog);
    }

    public Date getAisMessage() {
        return aisMessage;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public float getSog() {
        return sog;
    }

    public float getCog() {
        return cog;
    }

    public String getHeading() {
        return heading;
    }

    public String getMmsi(){
        return shipMMSI;
    }

    public int getPosition() {
        return position;
    }

    public String getTransceiver() {
        return transceiver;
    }

    public String getCargo() {
        return cargo;
    }
}
