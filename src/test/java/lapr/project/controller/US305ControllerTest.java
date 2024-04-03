package lapr.project.controller;

import lapr.project.data.US305Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

class US305ControllerTest {

    @Test
    void callUs305() throws IOException, SQLException {
        Company company= new Company();
        US305Controller uS305Controller = new US305Controller(company);

        uS305Controller.CallUs305("Client",223364868);
    }
}