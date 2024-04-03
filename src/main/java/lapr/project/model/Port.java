package lapr.project.model;

public class Port {

    private final String id;
    private final String name;

    private final PlaceLocation placeLocation;

    public Port(String id, String name, PlaceLocation placeLocation) {
        this.id = id;
        this.name = name;
        this.placeLocation = placeLocation;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlaceLocation getPlaceLocation() {
        return placeLocation;
    }

}
