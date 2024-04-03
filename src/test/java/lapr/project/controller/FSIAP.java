package lapr.project.controller;

import lapr.project.data.fsiap.ContainerStore;
import lapr.project.data.fsiap.ImportContainer;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FSIAP {

    @Test
    void fsiapApresentacao() throws IOException {
        Company company= new Company();


        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");

        US412Controller us412Controller = new US412Controller(company);

        double res= us412Controller.calculateEnergySpecificRestrictions(3,0,3);

        us412Controller.write(res,3,0,3);

        US413Controller us413Controller = new US413Controller(company);

        double res2 = us413Controller.calculateEnergySpecificTrip(3,0,3,1,0,0);

        us413Controller.write(res2);

        US414Controller us414Controller = new US414Controller(company);

        double res3= us414Controller.neededEnergyToSupplyContainer();
        us414Controller.write(res3);

        US415Controller us415Controller = new US415Controller(company);

        int res4= us415Controller.neededGenerators();
        us415Controller.write(res4);
    }


    @Test
    void calculateEnergySpecificRestrictions() throws IOException {
        Company company= new Company();


        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");

        US412Controller us412Controller = new US412Controller(company);

        double result= us412Controller.calculateEnergySpecificRestrictions(3,0,3);

        assertEquals(result, 105265.359375);
    }
    @Test
    void calculateEnergySpecificTrip() throws IOException {
        Company company= new Company();


        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");
        US413Controller us413Controller = new US413Controller(company);

        double result= us413Controller.calculateEnergySpecificTrip(3,0,3,1,0,0);

        assertEquals(result, 1311768.2956297703);
    }

    @Test
    void neededEnergyToSupplyContainer() throws IOException {
        Company company= new Company();


        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");

        US414Controller us414Controller = new US414Controller(company);

        double result= us414Controller.neededEnergyToSupplyContainer();

        assertEquals(result, 1480192.7817909005);
    }

    @Test
    void neededGenerators() throws IOException {
        Company company= new Company();


        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");
        US415Controller us415Controller = new US415Controller(company);

        int result= us415Controller.neededGenerators();

        assertEquals(result, 1);
    }
}