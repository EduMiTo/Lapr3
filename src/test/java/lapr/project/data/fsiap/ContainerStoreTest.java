package lapr.project.data.fsiap;

import lapr.project.model.Company;
import lapr.project.model.Container;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ContainerStoreTest {
//412 - Contentores no ficheiro
    @Test
    void calculateEnergySpecificRestrictions() throws IOException {
        Company company= new Company();
        ContainerStore containerStore= company.getContainerStore();

        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");

        double result= containerStore.calculateEnergySpecificRestrictions(3,0,3);

        assertEquals(result, 105265.359375);
    }
//413 - Contentores no ficheiro
    @Test
    void calculateEnergySpecificTrip() throws IOException {
        Company company= new Company();
        ContainerStore containerStore= company.getContainerStore();

        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");

        double result= containerStore.calculateEnergySpecificTrip(3,0,3,1,0,0);

        assertEquals(result, 1311768.2956297703);
    }

    //414 - Contentores no ficheiro
    @Test
    void neededEnergyToSupplyContainer() throws IOException {
        Company company= new Company();
        ContainerStore containerStore= company.getContainerStore();

        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");

        double result= containerStore.neededEnergyToSupplyContainer();

        assertEquals(result, 1480192.7817909005);
    }

    //415 - Contentores no ficheiro
    @Test
    void neededGenerators() throws IOException {
        Company company= new Company();
        ContainerStore containerStore= company.getContainerStore();

        ImportContainer importContainer= new ImportContainer(company);

        importContainer.importContainers("containers_info.csv");

        int result= containerStore.neededGenerators();

        assertEquals(result, 1);
    }


}