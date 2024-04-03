package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmallSummaryTest {

    SmallSummary smallSummary = new SmallSummary("123456789",5,10.0,15.0);

    @Test
    void getNumberOfMovements() {

       int real= smallSummary.getNumberOfMovements();

       int expected = 5;

       assertEquals(real,expected);

    }

    @Test
    void getRealDistance() {
        Double real= smallSummary.getRealDistance();

        Double expected = 15.0;

        assertEquals(real,expected);
    }

    @Test
    void testToString() {
       String expected= "SmallSummary:" +
                "mmsi='123456789'" +
                " numberOfMovements=5" +
                " deltaDistance=10.0" +
                " realDistance=15.0"+
                '\n';
        String real= smallSummary.toString();

        assertEquals(real,expected);

    }
    @Test
    void getSog(){
        SmallSummary smallSummary = new SmallSummary("123456789",15.0,"70",15.0f);

        float real= smallSummary.getSog();

        float expected = 15.0f;

        assertEquals(real,expected);
    }
    @Test
    void getMmsi(){
        String real= smallSummary.getMmsi();

        String expected ="123456789";

        assertEquals(real,expected);
    }
    @Test
    void getDeltaDistance(){
        double real= smallSummary.getDeltaDistance();

        double expected =10.0;

        assertEquals(real,expected);
    }
}