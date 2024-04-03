package lapr.project.controller;

import lapr.project.data.US208Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class US208ControllerTest {

    @Test
    void callUs208() throws IOException, SQLException {
        Company company= new Company();
        US208Controller uS208Controller = new US208Controller(company);

        assertTrue(uS208Controller.CallUs208(3,"258692000"));
    }

}