package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US205Controller {



    private final Company company;
    public US205Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US205Controller(Company company){
        this.company=company;

    }


    public boolean CallUs205(String mmsi) throws IOException {

            US205 us205= new US205();

            return us205.initialize(mmsi);





    }


}
