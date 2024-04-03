package lapr.project.controller;

import lapr.project.data.US205Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class US205ControllerTest {

    @Test
    void callUs205() throws IOException, SQLException {
        Company company= new Company();
        US205Controller uS205Controller = new US205Controller(company);

        assertTrue(uS205Controller.CallUs205("210950000"));

    }

}