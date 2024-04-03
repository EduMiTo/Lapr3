package lapr.project.controller;

import lapr.project.data.US304Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class US304ControllerTest {
    @Test
    void callUs304() throws IOException, SQLException {
        Company company= new Company();
        US304Controller uS304Controller = new US304Controller(company);

        uS304Controller.CallUs304(1,456789423);
    }
}
