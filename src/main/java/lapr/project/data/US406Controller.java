package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;

import java.io.IOException;
import java.sql.Timestamp;

public class US406Controller {
    private final Company company;

    public US406Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US406Controller(Company company){
        this.company=company;

    }


    public boolean CallUs406(String mmsi, Timestamp datei, Timestamp datef) throws IOException {

        US406 us406=  new US406();
        return us406.initialize(mmsi, datei, datef);

    }
}
