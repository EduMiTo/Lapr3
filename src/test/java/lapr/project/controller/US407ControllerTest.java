package lapr.project.controller;

import lapr.project.data.US407Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class US407ControllerTest {

    @Test
    void callUs407() throws IOException, SQLException {
        Company company= new Company();
        US407Controller uS407Controller = new US407Controller(company);

        uS407Controller.CallUs407(29002);
    }
}