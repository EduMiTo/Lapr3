package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class AVLShipPositionTest {

    @Test
    void testEqualsObject() throws ParseException {

        BSTShipPosition<ShipPosition> shipLocationBST = new AVLShipPosition();
        BSTShipPosition<ShipPosition> shipLocationBST2;
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");



        shipLocationBST.insert(sdd1);
        shipLocationBST2 = shipLocationBST;

        assertTrue(shipLocationBST.equals(shipLocationBST2));
    }

    @Test
    void testEqualsNull() throws ParseException {
        BSTShipPosition<ShipPosition> shipLocationBST = new AVLShipPosition();

        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");

        shipLocationBST.insert(sdd1);

        assertFalse(sdd1.equals(null));
    }
    @Test
    void testEqualsNull2() throws ParseException {

        BSTShipPosition<ShipPosition> shipLocationBST = new AVLShipPosition();
        BSTShipPosition<ShipPosition> shipLocationBST2= new AVLShipPosition();
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");



        shipLocationBST.insert(sdd1);


        assertNotEquals(shipLocationBST,shipLocationBST2);
    }
    @Test
    void testEqualsNull3() throws ParseException {

        BSTShipPosition<ShipPosition> shipLocationBST = new AVLShipPosition();
        BSTShipPosition<ShipPosition> shipLocationBST2= new AVLShipPosition();





        assertEquals(shipLocationBST,shipLocationBST2);
    }
    @Test
    void testEqualsNull4() throws ParseException {

        BSTShipPosition<ShipPosition> shipLocationBST = new AVLShipPosition();
        BSTShipPosition<ShipPosition> shipLocationBST2= new AVLShipPosition();
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");



        shipLocationBST.insert(sdd1);


        assertNotEquals(shipLocationBST2,shipLocationBST);
    }

    @Test
    void testEquals5() throws ParseException {

        BSTShipPosition<ShipPosition> shipLocationBST = new AVLShipPosition();
        BSTShipPosition<ShipPosition> shipLocationBST2= new AVLShipPosition();
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("01/01/2021 13:55", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd3 = new ShipPosition("01/01/2021 13:56", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");

        shipLocationBST.insert(sdd1);
        shipLocationBST.insert(sdd2);
        shipLocationBST.insert(sdd3);


        ShipPosition sdd4 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd5 = new ShipPosition("01/01/2021 13:56", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd6 = new ShipPosition("01/01/2021 13:55", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        shipLocationBST2.insert(sdd4);
        shipLocationBST2.insert(sdd5);
        shipLocationBST2.insert(sdd6);

        assertEquals(shipLocationBST,shipLocationBST2);
    }

    @Test
    void testEquals6() throws ParseException {

        BSTShipPosition<ShipPosition> shipLocationBST = new AVLShipPosition();
        BSTShipPosition<ShipPosition> shipLocationBST2= new AVLShipPosition();
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("01/01/2021 13:55", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd3 = new ShipPosition("01/01/2021 13:56", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");

        shipLocationBST.insert(sdd1);
        shipLocationBST.insert(sdd2);
        shipLocationBST.insert(sdd3);

        ShipPosition sdd4 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd5 = new ShipPosition("01/01/2021 13:56", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd6 = new ShipPosition("01/01/2021 13:54", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd7 = new ShipPosition("01/01/2021 13:54", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        shipLocationBST2.insert(sdd4);
        shipLocationBST2.insert(sdd5);
        shipLocationBST2.insert(sdd6);
        shipLocationBST2.insert(sdd7);

        assertNotEquals(shipLocationBST,shipLocationBST2);
    }
}