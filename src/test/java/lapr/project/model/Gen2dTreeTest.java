package lapr.project.model;

import lapr.project.controller.PortImportController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Gen2dTreeTest {

    @Test
    void isEmpty() {
        Gen2dTree gen2dTree= new Gen2dTree();
        assertTrue(gen2dTree.isEmpty());
    }
    @Test
    void isEmpty2() {
        Gen2dTree gen2dTree= new Gen2dTree();
        List<Gen2dTree.Node<Port>> n=new ArrayList<>();

        n.add(new Port2dTree.Node("object",12.0,123.8));


        gen2dTree.buildTree(n);
        assertFalse(gen2dTree.isEmpty());
    }
    @Test
    void insert() {

    }

    @Test
    void findNearestNeighbour() throws IOException, ParseException {
        Company company= new Company();
        //US1
        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sports.csv");

        Port2dTree<Port> port2dTree=company.getPort2dTree();

        Port port= port2dTree.findNearestNeighbour(49.28333333,-123.1166667);

        assertEquals(port.getId(),"25350");
    }

    @Test
    void findNearestNeighbour2() throws IOException, ParseException {
        Company company= new Company();
        //US1
        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sports.csv");

        Port2dTree<Port> port2dTree=company.getPort2dTree();

        Port port= port2dTree.findNearestNeighbour(-13.05,-75.16666667);

        assertEquals(port.getId(),"30045");
    }
    @Test
    void findNearestNeighbour3() throws IOException, ParseException {
        Company company= new Company();
        //US1
        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sports.csv");

        Port2dTree<Port> port2dTree=company.getPort2dTree();

        Port port= port2dTree.findNearestNeighbour(41.33333333,2.166666667);

        assertEquals(port.getId(),"17386");
    }

    @Test
    void findNearestNeighbour4() throws IOException, ParseException {
        Company company= new Company();
        //US1
        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sports.csv");

        Port2dTree<Port> port2dTree=company.getPort2dTree();

        Port port= port2dTree.findNearestNeighbour(53.46666667,-3.033333333);

        assertEquals(port.getId(),"29002");
    }
}