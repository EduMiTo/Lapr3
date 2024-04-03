package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US208Controller {
    private final Company company;
    public US208Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US208Controller(Company company){
        this.company=company;

    }


    public boolean CallUs208(int cmID, String mmsi) throws IOException {

           US208 us208= new US208();
            return us208.initialize(cmID,mmsi);

    }
}
