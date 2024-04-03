package lapr.project.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ShipsTest {

    @Test
    public void wrongmmsil() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BSTShipPosition bstShipPosition = new AVLShipPosition();
            Ships ship = new Ships("12345678", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        });
        assertEquals("MMSI needs to be 9 characters", thrown.getMessage());
    }

    @Test
    public void wrongmmsiu() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BSTShipPosition bstShipPosition = new AVLShipPosition();
            Ships ship = new Ships("1234567890", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        });
        assertEquals("MMSI needs to be 9 characters", thrown.getMessage());
    }

    @Test
    public void wrongimol() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BSTShipPosition bstShipPosition = new AVLShipPosition();
            Ships ships = new Ships("123456789", "95", "IMO000000", "10", "50", "50", "10","9.5", bstShipPosition);
        });
        assertEquals("IMO needs to be 'IMO' plus 7 characters", thrown.getMessage());
    }

    @Test
    public void wrongimou() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BSTShipPosition bstShipPosition = new AVLShipPosition();
            Ships ships = new Ships("123456789", "95", "IMO00000000", "10", "50", "50", "10","9.5", bstShipPosition);
        });
        assertEquals("IMO needs to be 'IMO' plus 7 characters", thrown.getMessage());
    }
    @Test
    public void shipname(){
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ships = new Ships("123456789", "95", "IMO0000000", "10", "50", "50", "10","9.5", bstShipPosition);
        String expected = "95";
        assertEquals(ships.getShipName(),expected);
    }

    @Test
    public void mmsicode(){
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ships = new Ships("123456789", "95", "IMO0000000", "10", "50", "50", "10","9.5", bstShipPosition);
        String expected = "123456789";
        assertEquals(ships.getMmsi(),expected);
    }
    @Test
    public void imocode(){
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ships = new Ships("123456789", "95", "IMO0000000", "10", "50", "50", "10","9.5", bstShipPosition);
        String expected = "IMO0000000";
        assertEquals(ships.getImo(),expected);
    }



    @Test
    public void wrongMMSI() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BSTShipPosition bstShipPosition = new AVLShipPosition();
            Ships ship = new Ships("2109500000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        });
        assertEquals("MMSI needs to be 9 characters", thrown.getMessage());
    }

    @Test
    public void wrongMMSID() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BSTShipPosition bstShipPosition = new AVLShipPosition();
            Ships ship = new Ships("21095000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        });
        assertEquals("MMSI needs to be 9 characters", thrown.getMessage());
    }

    @Test
    void getVessel(){
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);

        String real= ship.getVesselType();

        String expected="70";

        assertEquals(real,expected);
    }

    @Test
    void getSpecificShipPosition() throws ParseException {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");

        bstShipPosition.insert(sdd1);
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);

        ShipPosition ships= ship.getSpecificShipPosition("01-01-2021 13-50");


        assertEquals(sdd1,ships);



    }


    @Test
    void testToString() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);

        String str= "Ships:mmsi='210950000'imo='IMO9395044'callSign='C4SQ2'\n\n";

        assertEquals(str, ship.toString());

    }

    @Test
    void getGenerators() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        int real=ship.getGenerators();
        int expected=0;

        assertEquals(real,expected);
    }

    @Test
    void getGenertorPowerOutput() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        String real=ship.getGenertorPowerOutput();
        String expected=null;

        assertEquals(real,expected);
    }

    @Test
    void getLenght() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        float real=ship.getLenght();
        float expected=166f;

        assertEquals(real,expected);
    }

    @Test
    void getWidth() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        float real=ship.getWidth();
        float expected=25f;

        assertEquals(real,expected);
    }

    @Test
    void getCapacity() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        int real=ship.getCapacity();
        int expected=4150;

        assertEquals(real,expected);
    }

    @Test
    void getDraft() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();
        Ships ship = new Ships("210950000", "VARAMO", "IMO9395044", "C4SQ2", "70", "166", "25", "9.5", bstShipPosition);
        float real=ship.getDraft();
        float expected=9.5f;

        assertEquals(real,expected);
    }
}