package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US210Controller {
    private final Company company;

    public US210Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US210Controller(Company company){
        this.company=company;

    }


    public boolean CallUs210() throws IOException {

           US210 us210=  new US210();
            return us210.initialize();

    }
}
