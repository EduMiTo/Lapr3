package lapr.project.model;

public class Country {
    private final String name;
    private final String continent;
    private String alpha2code;
    private String alpha3code;
    private float population;
    private Capital capital;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public Country(String name, String continent, String alpha2code, String alpha3code, String population, Capital capital) {
        this.name = name;
        this.continent = continent;
        this.alpha2code= alpha2code;
        this.alpha3code=alpha3code;
        this.population=Float.parseFloat(population);
        this.capital=capital;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getAlpha2code() {
        return alpha2code;
    }

    public String getAlpha3code() {
        return alpha3code;
    }

    public float getPopulation() {
        return population;
    }

    public Capital getCapital() {
        return capital;
    }


    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", alpha2code='" + alpha2code + '\'' +
                ", alpha3code='" + alpha3code + '\'' +
                ", population=" + population +
                ", capital=" + capital +
                '}';
    }
}

