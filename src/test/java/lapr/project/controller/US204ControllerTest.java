package lapr.project.controller;

import lapr.project.data.US204Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class US204ControllerTest {

    @Test
    void callUs204() throws IOException, SQLException {
        Company company= new Company();
        US204Controller uS204Controller = new US204Controller(company);



        assertTrue(uS204Controller.CallUs204(748163149));
    }
}