package lapr.project.model;

import lapr.project.controller.ImportCSVtoBST;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTShipPositionTest {

    private Ships ships;
    private BSTShipPosition bstShipPosition;
    private BSTShip bstShip;

    public BSTShipPositionTest() throws IOException, ParseException {
        Company company= new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");
        String mmsi="636092932";
        bstShip= company.getBstShip();
        BSTShip.Node<Ships> node= bstShip.search(mmsi);

        this.ships= node.getElement();

        this.bstShipPosition = ships.getBstMessage();
    }

    @Test
    void calculateDistance() {

        BSTShipPosition bstShipPosition = new AVLShipPosition();

        long real= (long) bstShipPosition.calculateDistance(10.0f,20.0f,30.0f,40.0f);

        long expected = 3040602;

        assertEquals(real,expected);
    }

    @Test
    void toRadian() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();

        Double real= bstShipPosition.toRadian(180);

        Double expected = Math.PI;

        assertEquals(real,expected);

    }

    @Test
    void inOrderIterable() {
        BSTShipPosition bstShipPosition = new AVLShipPosition();

        Iterable<ShipPosition> messages= bstShipPosition.inOrderIterable();

        List<ShipPosition> messages1= new ArrayList<>();

        assertEquals(messages,messages1);

    }

    @Test
    void inorderCalculateDistance() {
        int real= (int) bstShipPosition.inorderCalculateDistance();
        int expected = 19;

        assertEquals(real,expected);
    }

    @Test
    void inorderCalculateDistance1() {
        int real= (int) bstShipPosition.inorderCalculateDistance();
        int expected = 19;

        assertEquals(real,expected);
    }

    @Test
    void calculateDistanceWrongLat() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("02/01/2021 13:50", "50", "90", "30.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);

        int real= (int) instance.inorderCalculateDistance();

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceWrongLat2() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "91", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("02/01/2021 13:50", "-91", "90", "30.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);

        int real= (int) instance.inorderCalculateDistance();

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceWrongLat3() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("02/01/2021 13:50", "-91", "90", "30.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);

        int real= (int) instance.inorderCalculateDistance();

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceWrongLon() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "185", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("02/01/2021 13:50", "50", "90", "30.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);

        int real= (int) instance.inorderCalculateDistance();

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceWrongLon1() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "91", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("02/01/2021 13:50", "50", "-182", "30.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);

        int real= (int) instance.inorderCalculateDistance();

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceWrongLon2() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "181", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("02/01/2021 13:50", "50", "-182", "30.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);

        int real= (int) instance.inorderCalculateDistance();

        int expected = 0;

        assertEquals(expected,real);

    }

    @Test
    void calculateDistanceBD() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 2428;

        assertEquals(expected,real);

    }

    @Test
    void calculateDistanceBDWL() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","91","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 607;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceBDWL2() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","-91","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 2428;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceBDWL3() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","91","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 1821;

        assertEquals(expected,real);

    }

    @Test
    void calculateDistanceBDWL4() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","91","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","-91","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 0;

        assertEquals(expected,real);

    }

    @Test
    void calculateDistanceBDWL5() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","91","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","91","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","-91","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceBDWL6() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","91","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","91","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","50","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 0;

        assertEquals(expected,real);

    }

    @Test
    void calculateDistanceBDLon() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-181","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 607;

        assertEquals(expected,real);

    }

    @Test
    void calculateDistanceBDLon1() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","181","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 2428;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceBDLon2() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-181","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 1821;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceBDLon3() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","182","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-181","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceBDLon4() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-190","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","182","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-181","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 0;

        assertEquals(expected,real);

    }
    @Test
    void calculateDistanceBDLon5() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-60","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","182","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-181","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(shipPosition);
        instance.insert(shipPosition1);
        instance.insert(shipPosition2);
        String sDate1= "31/12/2020 17:00";
        String sDate2= "31/12/2020 17:10";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);

        int real= (int) instance.distanceBetweenDate(date1,date2);

        int expected = 0;

        assertEquals(expected,real);

    }

    @Test
    void inorderMaxSOG() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "50", "50.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("01/01/2021 13:52", "50", "50", "100.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd3 = new ShipPosition("01/01/2021 13:53", "50", "50", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd4 = new ShipPosition("01/01/2021 13:53", "50", "50", "100.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);
        instance.insert(sdd3);
        instance.insert(sdd4);

       Double real= instance.inorderMaxSOG();
       Double expected = 100.0;

       assertEquals(real,expected);

    }

    @Test
    void inorderMinSOG() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "50", "50.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("01/01/2021 13:52", "50", "50", "100.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd3 = new ShipPosition("01/01/2021 13:53", "50", "50", "150.0", "50.0", "50", "40", "B","123456789");
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);
        instance.insert(sdd3);

        Double real= instance.inorderMinSOG();
        Double expected = 100.0;

        assertEquals(real,expected);
    }

    @Test
    void inorderMaxCOG() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "50", "50.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("01/01/2021 13:52", "50", "50", "100.0", "40.0", "50", "40", "B","123456789");
        ShipPosition sdd3 = new ShipPosition("01/01/2021 13:53", "50", "50", "150.0", "70.0", "50", "40", "B","123456789");
        ShipPosition sdd4 = new ShipPosition("01/01/2021 13:53", "50", "50", "100.0", "70.0", "50", "40", "B","123456789");

        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();
        instance.insert(sdd1);
        instance.insert(sdd2);
        instance.insert(sdd3);
        instance.insert(sdd4);
        int real= (int) instance.inorderMaxCOG();
        int expected = 70;

        assertEquals(real,expected);
    }

    @Test
    void inorderMinCOG() {
        int real=(int)bstShipPosition.inorderMinCOG();
        int expected = 169;

        assertEquals(real,expected);
    }

    @Test
    void dateDiff() throws ParseException {
        BSTShipPosition bstShipPosition = new AVLShipPosition();

        String sdate1 = "20:00:00";
        String sdate2 = "24:00:00";
        Date date1= new SimpleDateFormat("hh:mm:ss").parse(sdate1);
        Date date2= new SimpleDateFormat("hh:mm:ss").parse(sdate2);
        String real= bstShipPosition.dateDiff(date2,date1);
        String expected= "4h 0m 0s";

        assertEquals(real,expected);



    }

    @Test
    void getSize() {
        int real= bstShipPosition.getSize();
        int expected = 5;

        assertEquals(real,expected);
    }

    @Test
    void minDate() {
        ShipPosition firstdate = bstShipPosition.minDate();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String real = dateFormat.format(firstdate.getAisMessage());

        String expected= "31/12/2020 12:06:00";

        assertEquals(real,expected);
    }

    @Test
    void maxDate() {
        ShipPosition lastdate = bstShipPosition.maxDate();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");



        String real = dateFormat.format(lastdate.getAisMessage());

        String expected= "31/12/2020 21:20:00";

        assertEquals(real,expected);
    }

    @Test
    void insert() throws ParseException {
        ShipPosition sdd1 = new ShipPosition("01/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd2 = new ShipPosition("02/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd3 = new ShipPosition("03/01/2021 13:50", "50", "50", "30.0", "50.0", "50", "40", "B","123456789");
        ShipPosition sdd4 = new ShipPosition("04/01/2020 13:50", "50", "50", "30.0", "50.0", "50", "40", "B","123456789");


        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();

        assertEquals(0, instance.getSize());
        instance.insert(sdd1);
        assertEquals(1, instance.getSize());
        instance.insert(sdd2);
        assertEquals(2, instance.getSize());
        instance.insert(sdd3);
        assertEquals(3, instance.getSize());
        instance.insert(sdd4);
        assertEquals(4, instance.getSize());
        instance.insert(sdd4);
        assertEquals(4, instance.getSize());
        instance.insert(sdd3);
        assertEquals(4, instance.getSize());

    }

    @Test
    void searchSpecificDatePeriodcall() throws ParseException {

        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 17:03");

        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("31/12/2020 17:09");

        BSTShip.Node<Ships> node= bstShip.search("210950000");

        Ships ships= node.getElement();

        BSTShipPosition bstShipPosition = ships.getBstMessage();

        List<ShipPosition> shipPositions = bstShipPosition.searchSpecificDatePeriodcall(date,date1);

        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");


        List<ShipPosition> expecteds= new ArrayList<>();

        expecteds.add(shipPosition);
        expecteds.add(shipPosition1);
        expecteds.add(shipPosition2);

        assertEquals(expecteds, shipPositions);




    }

    @Test
    void search() throws ParseException {

        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");

        BSTShipPosition bstShipPosition = new AVLShipPosition();

        bstShipPosition.insert(shipPosition);
        bstShipPosition.insert(shipPosition1);
        bstShipPosition.insert(shipPosition2);

        String sdata= "31/12/2020 17:07";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date=dateFormat.parse(sdata);

        BSTShipPosition.Node node= bstShipPosition.search(date);

        assertEquals(node.getElement(), shipPosition1);

    }

    @Test
    void searchNull() throws ParseException {

        BSTShipPosition bstShipPosition = new AVLShipPosition();



        String sdata= "31/12/2020 17:07";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date=dateFormat.parse(sdata);

        BSTShipPosition.Node node= bstShipPosition.search(date);

        assertEquals(node,null);

    }

    @Test
    void searchNotFind() throws ParseException {

        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");

        BSTShipPosition bstShipPosition = new AVLShipPosition();

        bstShipPosition.insert(shipPosition);
        bstShipPosition.insert(shipPosition1);
        bstShipPosition.insert(shipPosition2);

        String sdata= "31/12/2020 17:08";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date=dateFormat.parse(sdata);

        BSTShipPosition.Node node= bstShipPosition.search(date);

        assertEquals(node,null);

    }

    @Test
    void testToString() throws ParseException {

        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");

        BSTShipPosition bstShipPosition = new AVLShipPosition();
        bstShipPosition.insert(shipPosition);
        bstShipPosition.insert(shipPosition1);
        bstShipPosition.insert(shipPosition2);

        String expected="BSTMessage{3root=2.6";

        assertEquals(expected,bstShipPosition.toString());

    }

    @Test
    void height() throws ParseException {


        BSTShipPosition bstShipPosition = new AVLShipPosition();


        int real= bstShipPosition.height(null);
        int expected= -1;

        assertEquals(expected,real);

    }
}