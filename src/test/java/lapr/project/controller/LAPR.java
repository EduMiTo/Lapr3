package lapr.project.controller;

import lapr.project.data.lapr.US418Controller;
import lapr.project.data.lapr.US419Controller;
import lapr.project.data.lapr.US420Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class LAPR {

    @Test
    void centerOfMass() throws FileNotFoundException {
        Company company= new Company();
        US418Controller us418Controller = new US418Controller(company);
        us418Controller.centerOfMass("Graneleiro");
        us418Controller.write("Graneleiro");
    }
    @Test
    void centerOfMassPortaContentores() throws FileNotFoundException {
        Company company= new Company();
        US418Controller us418Controller = new US418Controller(company);
        us418Controller.centerOfMass("Porta-Contentores");
        us418Controller.write("Porta-Contentores");
    }
    @Test
    void centerOfMassTanque() throws FileNotFoundException {
        Company company= new Company();
        US418Controller us418Controller = new US418Controller(company);
        us418Controller.centerOfMass("Tanque");
        us418Controller.write("Tanque");
    }

    @Test
    void centerOfMassWContainers() throws FileNotFoundException {
        Company company= new Company();
        US419Controller us418Controller = new US419Controller(company);
        us418Controller.centerOfMass("Porta-Contentores", 100);
        us418Controller.write("Porta-Contentores", 100);
    }
    @Test
    void sink() throws FileNotFoundException {
        Company company= new Company();
        US420Controller us418Controller = new US420Controller(company);
        us418Controller.sink("Porta-Contentores", 20000);
        us418Controller.write("Porta-Contentores", 20000);
    }
}