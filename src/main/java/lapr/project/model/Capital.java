package lapr.project.model;

public class Capital {

    private final String name;
    private final String latitude;
    private final String longitude;
    private final String cName;

    public Capital(String name, String latitude, String longitude, String cName) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cName= cName;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getcName() {
        return cName;
    }
}
