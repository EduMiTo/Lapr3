package lapr.project.controller;

import lapr.project.data.US312Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

class US312ControllerTest {

    @Test
    void callUs312() throws IOException, SQLException {
        Company company= new Company();
        US312Controller uS312Controller = new US312Controller(company);

        uS312Controller.CallUs312(205492538,"Clientt");
    }
}