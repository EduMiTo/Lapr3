package lapr.project.data;


import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US306Controller {
    private final Company company;

    public US306Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US306Controller(Company company){
        this.company=company;

    }


    public boolean CallUs306() throws IOException {

        US306 us306=  new US306();
        return us306.initialize();

    }
}
