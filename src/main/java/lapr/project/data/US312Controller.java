package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US312Controller {
    private final Company company;

    public US312Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US312Controller(Company company){
        this.company=company;

    }


    public boolean CallUs312(int cont, String regist) throws IOException {

        US312 us312=  new US312();
        return us312.initialize(cont,regist);

    }
}
