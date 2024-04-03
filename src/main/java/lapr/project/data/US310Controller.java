package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US310Controller {
    private final Company company;

    public US310Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US310Controller(Company company){
        this.company=company;

    }


    public boolean CallUs310(int month, int year) throws IOException {

        US310 us310=  new US310();
        return us310.initialize(month,year);

    }
}
