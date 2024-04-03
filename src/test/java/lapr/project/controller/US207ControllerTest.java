package lapr.project.controller;

import lapr.project.data.US207Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class US207ControllerTest {

    @Test
    void callUs207() throws IOException, SQLException {
        Company company= new Company();
        US207Controller uS207Controller = new US207Controller(company);

        assertTrue(uS207Controller.CallUs207(2004 ,"212180000"));
    }

}