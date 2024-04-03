package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US304Controller {
    private final Company company;

    public US304Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US304Controller(Company company){
        this.company=company;

    }


    public boolean CallUs304(int cm, int cont) throws IOException {

        US304 us304=  new US304();
        return us304.initialize(cm,cont);

    }
}
