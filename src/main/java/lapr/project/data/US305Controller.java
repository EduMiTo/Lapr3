package lapr.project.data;

import lapr.project.controller.App;

import lapr.project.model.Company;

import java.io.IOException;


public class US305Controller {
    private final Company company;

    public US305Controller(){
        this.company= App.getInstance().getCompany();

    }

    public US305Controller(Company company){
        this.company=company;

    }


    public boolean CallUs305(String regist, int cont) throws IOException {

        US305 us305=  new US305();
        return us305.initialize(regist,cont);

    }
}
