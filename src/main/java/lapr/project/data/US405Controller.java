package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;

import java.io.IOException;
import java.sql.Timestamp;

public class US405Controller {
    private final Company company;

    public US405Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US405Controller(Company company){
        this.company=company;

    }


    public boolean CallUs405(String mmsi, Timestamp datei, Timestamp datef) throws IOException {

        US405 us405=  new US405();
        return us405.initialize(mmsi, datei, datef);

    }
}
