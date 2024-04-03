package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;

import java.io.IOException;


public class US404Controller {
    private final Company company;

    public US404Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US404Controller(Company company){
        this.company=company;

    }


    public boolean CallUs404() throws IOException {

        US404 us404=  new US404();
        return us404.initialize();

    }
}
