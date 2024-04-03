package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.data.InsertCargoContainer;

import lapr.project.model.Company;

import java.io.IOException;
import java.sql.SQLException;

public class InsertCargoContainerController {
    private final Company company;

    public InsertCargoContainerController(){
        this.company= App.getInstance().getCompany();

    }

    public InsertCargoContainerController(Company company){
        this.company=company;

    }


    public boolean callInsertCargoContainer(int cargoId, int containerId, int tripdId, int x, int y, int z, float gross, String portStaff) throws IOException, SQLException {

        InsertCargoContainer insertCargoContainer=  new InsertCargoContainer();
        return insertCargoContainer.initialize(cargoId, containerId, tripdId, x, y,  z,  gross,portStaff);

    }
}
