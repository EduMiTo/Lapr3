package lapr.project.model;

public class SeaDist {

    private final Port portFrom;
    private final Port portTo;
    private final float seaDistance;


    public SeaDist(Port portFrom, Port portTo, String seaDistance) {

        this.portFrom = portFrom;

        this.portTo = portTo;


        this.seaDistance = Float.parseFloat(seaDistance);

    }

    public Port getPortFrom() {
        return portFrom;
    }

    public Port getPortTo() {
        return portTo;
    }

    public float getSeaDistance() {
        return seaDistance;
    }
}
