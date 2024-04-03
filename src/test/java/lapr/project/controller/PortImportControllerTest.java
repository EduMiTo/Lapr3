package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Port2dTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PortImportControllerTest {

    @Test
    void portImportCSV() throws IOException, ParseException {
        Company company= new Company();
        //US1
        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sports.csv");
    }
    @Test
    void test() throws IOException, ParseException {

        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class, () -> {
            PortImportController portImportController= new PortImportController();

            portImportController.portImportCSV("errado.csv");
        });
        assertEquals(thrown.getMessage(), thrown.getMessage());
    }

    @Test
    void test2() throws IOException, ParseException {
        Company company= new Company();
        PortImportController asd = new PortImportController(company);
        int real= asd.portImportCSV("sportsFail.csv");

        assertEquals(1, real);
    }
    /*@Test
    void portImportCSVteste() throws IOException, ParseException {
        Company company= new Company();
        //US1
        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("balancetree.csv");

        Port2dTree port2dTree= company.getPort2dTree();

        System.out.println(port2dTree.root().getX());
        System.out.println(port2dTree.root().getLeft().getX());
        System.out.println(port2dTree.root().getLeft().getLeft().getX());
        System.out.println(port2dTree.root().getLeft().getRight().getX());
        System.out.println(port2dTree.root().getRight().getX());
        System.out.println(port2dTree.root().getRight().getLeft().getX());

    }*/
}