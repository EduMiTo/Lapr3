package lapr.project.utils;

import lapr.project.model.AVLShipPosition;
import lapr.project.model.BSTShipPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {

    @Test
    void calculateDistance() {



        long real= (long) Distance.calculateDistance("10.0","20.0","30.0","40.0");

        long expected = 3040602;

        assertEquals(real,expected);
    }

    @Test
    void toRadian() {


        Double real= Distance.toRadian(180);

        Double expected = Math.PI;

        assertEquals(real,expected);

    }

}