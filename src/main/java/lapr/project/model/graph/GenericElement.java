package lapr.project.model.graph;

import lapr.project.model.Country;
import lapr.project.model.Port;

import java.util.Objects;

public class GenericElement {

    private final String name;

    private final String latitude;

    private final String longitude;

    private final String country;


    public GenericElement(Country country) {
        this.name = country.getCapital().getName();
        this.latitude = country.getCapital().getLatitude();
        this.longitude = country.getCapital().getLongitude();
        this.country = country.getName();
    }

    public GenericElement(Port port){
        this.name = port.getName();
        this.latitude = port.getPlaceLocation().getLatitude();
        this.longitude = port.getPlaceLocation().getLongitude();
        this.country = port.getPlaceLocation().getCountry().getName();

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

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericElement that = (GenericElement) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public String toString() {
        return "GenericElement{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
