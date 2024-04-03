package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ShipPositionTest {

    @Test
    void overLatitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "95", "50", "10", "50", "50", "10","B","123456789");

        String expected = "Not available";
        assertEquals(shipPosition.getLatitude(),expected);
    }

    @Test
    void underLatitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-95", "50", "10", "50", "50", "10","B","123456789");

        String expected = "Not available";
        assertEquals(shipPosition.getLatitude(),expected);
    }

    @Test
    void overLongitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "185", "10", "50", "50", "10","B","123456789");

        String expected = "Not available";
        assertEquals(shipPosition.getLongitude(),expected);
    }

    @Test
    void underLongitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "-185", "10", "50", "50", "10","B","123456789");

        String expected = "Not available";
        assertEquals(shipPosition.getLongitude(),expected);
    }

    @Test
    void undercog() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "170", "10", "-10", "50", "10","B","123456789");

        int expected = 350;
        assertEquals(shipPosition.getCog(),expected);
    }

    @Test
    void underheading() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "170", "10", "50", "-10", "10","B","123456789");

        Double expected = 350.0;
        assertEquals(Double.parseDouble(shipPosition.getHeading()),expected);
    }

    @Test
    void wrongheading() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "170", "10", "50", "511", "10","B","123456789");

        String expected = "Not available";
        assertEquals(shipPosition.getHeading(),expected);
    }
    @Test
    void minheading() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "170", "10", "50", "0", "10","B","123456789");

        Double expected = 0.0;
        assertEquals(Double.parseDouble(shipPosition.getHeading()),expected);
    }

    @Test
    void minCog() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "170", "10", "0", "5", "10","B","123456789");

        Double expected = 0.0;
        assertEquals(shipPosition.getCog(),expected);
    }

    @Test
    void minLongitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "-180", "10", "50", "50", "10","B","123456789");

        String expected = "-180";
        assertEquals(shipPosition.getLongitude(),expected);
    }

    @Test
    void maxLongitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "85", "180", "10", "50", "50", "10","B","123456789");

        String expected = "180";
        assertEquals(shipPosition.getLongitude(),expected);
    }

    @Test
    void maxLatitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "90", "170", "10", "50", "50", "10","B","123456789");

        String expected = "90";
        assertEquals(shipPosition.getLatitude(),expected);
    }

    @Test
    void minLatitude() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        String expected = "-90";
        assertEquals(shipPosition.getLatitude(),expected);
    }

    @Test
    void compareto() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        ShipPosition shipPosition2 = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        int real= shipPosition.compareTo(shipPosition2);

        int expected=0;

        assertEquals(real,expected);
    }
    @Test
    void compareto2() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        ShipPosition shipPosition2 = new ShipPosition("10/10/2010 12:20:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        int real= shipPosition.compareTo(shipPosition2);

        int expected=1;

        assertEquals(real,expected);
    }

    @Test
    void compareto3() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        ShipPosition shipPosition2 = new ShipPosition("10/10/2010 12:40:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        int real= shipPosition.compareTo(shipPosition2);

        int expected=-1;

        assertEquals(real,expected);
    }

    @Test
    void tostring() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");

        String expected="10/10/2010 12:30 sog:10.0";

        assertEquals(expected, shipPosition.toString());

    }

    @Test
    void getSog() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");
        float real= shipPosition.getSog();
        float expected=10.0f;

        assertEquals(expected, real);
    }

    @Test
    void getCog() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");
        float real= shipPosition.getCog();
        float expected=50.0f;

        assertEquals(expected, real);
    }

    @Test
    void getHeading() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");
        String real= shipPosition.getHeading();
        String expected="50";

        assertEquals(expected, real);
    }

    @Test
    void getMmsi() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");
        String real= shipPosition.getMmsi();
        String expected="123456789";

        assertEquals(expected, real);
    }

    @Test
    void getPosition() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");
        int real= shipPosition.getPosition();
        int expected=0;

        assertEquals(expected, real);
    }

    @Test
    void getTransceiver() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");
        String real= shipPosition.getTransceiver();
        String expected="B";

        assertEquals(expected, real);
    }

    @Test
    void getCargo() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("10/10/2010 12:30:00", "-90", "170", "10", "50", "50", "10","B","123456789");
        String real= shipPosition.getCargo();
        String expected="10";

        assertEquals(expected, real);
    }
}