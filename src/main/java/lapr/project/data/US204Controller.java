package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;

public class US204Controller {
    private final Company company;
    public US204Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US204Controller(Company company){
        this.company=company;

    }


    public boolean CallUs204(Integer containerId) throws IOException {

        US204 us204= new US204();

        return us204.initialize(containerId);




    }
}
