package lapr.project.data;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class US406ControllerTest {

    @Test
    void callUs406() {
        Company company= new Company();
        US406Controller uS406Controller = new US406Controller(company);
        String date ="30.12.21 18:44:33";
        String date1 ="30.01.22 18:44:33";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            Date parsedDate1 = dateFormat.parse(date1);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            Timestamp timestamp1 = new java.sql.Timestamp(parsedDate1.getTime());
            uS406Controller.CallUs406("123456789", timestamp, timestamp1);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}