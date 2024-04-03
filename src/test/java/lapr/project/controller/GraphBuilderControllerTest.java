package lapr.project.controller;

import lapr.project.data.SendToDBController;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.sql.SQLException;



class GraphBuilderControllerTest {

    @Test
    void buildGraph() throws SQLException, FileNotFoundException {

        Company company = new Company();

        SendToDBController sendToDBController = new SendToDBController(company);






        sendToDBController.capitals();
        sendToDBController.countries();

       sendToDBController.ports();
        sendToDBController.borders();

        sendToDBController.seaDist();


        GraphBuilderController graphBuilderController= new GraphBuilderController(company);

        graphBuilderController.buildGraph(3);

        graphBuilderController.writeToFile();


    }
}