package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class AVLShipTest {

    @Test
    void testEqualsObject() throws ParseException {

        BSTShip<Ships> shipBST = new AVLShip();
        BSTShip<Ships> shipBST2;

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);



        shipBST.insert(ship);
        shipBST2 = shipBST;

        assertTrue(shipBST.equals(shipBST2));
    }

    @Test
    void testEqualsNull() throws ParseException {
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST.insert(ship);

        assertFalse(ship.equals(null));
    }

    @Test
    void testEqualsNull2() throws ParseException {
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST.insert(ship);
        BSTShip<Ships> shipBST2 = new AVLShip();

        assertFalse(shipBST.equals(shipBST2));
    }
    @Test
    void testEqualsNull3() throws ParseException {
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST.insert(ship);
        BSTShip<Ships> shipBST2 = new AVLShip();

        assertNotEquals(shipBST,shipBST2);
    }
    @Test
    void testEqualsNull4() throws ParseException {
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShip<Ships> shipBST2 = new AVLShip();

        assertEquals(shipBST2, shipBST);
    }

    @Test
    void testEquals5() throws ParseException {
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship2 = new Ships("555555556", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship3 = new Ships("555555554", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST.insert(ship);
        shipBST.insert(ship2);
        shipBST.insert(ship3);
        BSTShip<Ships> shipBST2 = new AVLShip();

        Ships ship4 = new Ships("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship5 = new Ships("555555554", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship6 = new Ships("555555556", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST2.insert(ship4);
        shipBST2.insert(ship5);
        shipBST2.insert(ship6);

        assertEquals(shipBST2, shipBST);
    }
    @Test
    void testEquals6() throws ParseException {
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship2 = new Ships("555555557", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship3 = new Ships("555555554", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST.insert(ship);
        shipBST.insert(ship2);
        shipBST.insert(ship3);
        BSTShip<Ships> shipBST2 = new AVLShip();
        Ships ship4 = new Ships("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship5 = new Ships("555555554", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship6 = new Ships("555555556", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST2.insert(ship4);
        shipBST2.insert(ship5);
        shipBST2.insert(ship6);

        assertNotEquals(shipBST2, shipBST);
    }

    @Test
    void height(){
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship2 = new Ships("555555557", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship3 = new Ships("555555559", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship4 = new Ships("555555554", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship5 = new Ships("555555553", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        shipBST.insert(ship);
        shipBST.insert(ship2);
        shipBST.insert(ship3);
        shipBST.insert(ship4);
        shipBST.insert(ship5);


        assertEquals(shipBST.height(), 2);

    }

    @Test
    void height2(){
        BSTShip<Ships> shipBST = new AVLShip();

        assertEquals(shipBST.height(), -1);

    }

    @Test
    void insert(){
        BSTShip<Ships> shipBST = new AVLShip();

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        Ships ship = new Ships("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship2 = new Ships("555555557", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship3 = new Ships("555555555", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship4 = new Ships("555555556", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship5 = new Ships("555555557", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship6 = new Ships("555555558", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship7 = new Ships("555555559", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship8 = new Ships("555555553", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship9 = new Ships("555555554", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);

        shipBST.insert(ship);
        shipBST.insert(ship2);
        shipBST.insert(ship3);
        shipBST.insert(ship4);
        shipBST.insert(ship5);
        shipBST.insert(ship6);
        shipBST.insert(ship7);
        shipBST.insert(ship8);
        shipBST.insert(ship9);

        assertEquals(shipBST.getSize(),7);
    }

}