package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;

import java.sql.Timestamp;

public class US209Controller {
    private final Company company;
    public US209Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US209Controller(Company company){
        this.company=company;

    }


    public boolean CallUs209(String mmsi, Timestamp date) throws IOException {

         US209 us209=new US209();
            return us209.initialize(mmsi, date);



    }
}
