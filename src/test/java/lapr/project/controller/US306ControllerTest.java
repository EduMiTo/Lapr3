package lapr.project.controller;

import lapr.project.data.US306Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

class US306ControllerTest {

    @Test
    void callUs306() throws IOException, SQLException {
        Company company= new Company();
        US306Controller uS306Controller = new US306Controller(company);

        uS306Controller.CallUs306();
    }
}