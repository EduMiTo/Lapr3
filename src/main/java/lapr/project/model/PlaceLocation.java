package lapr.project.model;

public class PlaceLocation {
    private final String latitude;
    private final String longitude;

    private final Country country;

    public PlaceLocation(String latitude, String longitude, Country country) {
        if (Double.parseDouble(latitude) > 90 || Double.parseDouble(latitude) < -90) {
            this.latitude = "Not available";
        } else {
            this.latitude = latitude;
        }


        if (Double.parseDouble(longitude) > 180 || Double.parseDouble(longitude) < -180) {
            this.longitude = "Not available";
        } else {
            this.longitude = longitude;
        }
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Country getCountry() {
        return country;
    }
}
