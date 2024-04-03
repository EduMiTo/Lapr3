package lapr.project.controller;

import lapr.project.data.US206Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class US206ControllerTest {

    @Test
    void callUs206() throws IOException, SQLException {
        Company company= new Company();
        US206Controller uS206Controller = new US206Controller(company);

        assertTrue(uS206Controller.CallUs206("636092932"));
    }

}