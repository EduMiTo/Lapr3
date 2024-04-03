package lapr.project.controller;


import lapr.project.data.US404Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class US404ControllerTest {

    @Test
    void callUs404() throws SQLException, IOException {
        Company company= new Company();
        US404Controller uS404Controller = new US404Controller(company);

        uS404Controller.CallUs404();
    }
}