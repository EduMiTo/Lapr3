package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;

import java.io.IOException;

public class US207Controller {
    private final Company company;
    public US207Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US207Controller(Company company){
        this.company=company;

    }


    public boolean CallUs207(int givenYear, String mmsi) throws IOException {

        US207 us207= new US207();
        return us207.initialize(givenYear, mmsi);


    }
}
