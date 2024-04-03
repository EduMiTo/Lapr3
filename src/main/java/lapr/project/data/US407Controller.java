package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;

import java.io.IOException;


public class US407Controller {
    private final Company company;

    public US407Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US407Controller(Company company){
        this.company=company;

    }


    public boolean CallUs407( int portid) throws IOException {

        US407 us407=  new US407();
        return us407.initialize(portid);

    }
}
