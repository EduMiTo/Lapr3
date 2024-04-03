package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US206Controller {
    private final Company company;

    public US206Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US206Controller(Company company){
        this.company=company;

    }


    public boolean CallUs206(String mmsi) throws IOException {

        US206 us206= new US206();

        return us206.initialize(mmsi);


    }
}
