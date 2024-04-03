package lapr.project.controller;

import lapr.project.data.US209Controller;
import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class US209ControllerTest {

    @Test
    void callUs209() {
        Company company= new Company();
        US209Controller uS209Controller = new US209Controller(company);

        String date ="14.10.21 18:44:33";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            assertTrue(uS209Controller.CallUs209("210950000", timestamp));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}