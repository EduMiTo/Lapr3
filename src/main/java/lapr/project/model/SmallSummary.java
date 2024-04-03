package lapr.project.model;

public class SmallSummary {
    private final String mmsi;

    private int numberOfMovements;

    private double deltaDistance;

    private final double realDistance;

    private String vesselType;

    private float sog;
    public SmallSummary(String mmsi, int numberOfMovements, double deltaDistance, double realDistance) {
        this.mmsi = mmsi;
        this.numberOfMovements = numberOfMovements;
        this.deltaDistance = deltaDistance;
        this.realDistance = realDistance;
    }

    public SmallSummary(String mmsi, double realDistance, String vesselType, float sog){
        this.mmsi = mmsi;
        this.realDistance = realDistance;
        this.vesselType=vesselType;
        this.sog=sog;
    }


    public int getNumberOfMovements() {
        return numberOfMovements;
    }

    public double getRealDistance() {
        return realDistance;
    }

    public String getVesselType() {
        return vesselType;
    }

    public String getMmsi() {
        return mmsi;
    }

    public float getSog() {
        return sog;
    }

    public double getDeltaDistance(){
        return deltaDistance;
    }

    @Override
    public String toString() {
        return "SmallSummary:" +
                "mmsi='" + mmsi + '\'' +
                " numberOfMovements=" + numberOfMovements +
                " deltaDistance=" + deltaDistance +
                " realDistance=" + realDistance + "\n";
    }
}
