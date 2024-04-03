package lapr.project.controller;

import lapr.project.data.US210Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

class US210ControllerTest {

    @Test
    void callUs210() throws IOException, SQLException {
        Company company= new Company();
        US210Controller uS210Controller = new US210Controller(company);

        uS210Controller.CallUs210();
    }

}