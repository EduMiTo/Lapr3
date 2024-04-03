package lapr.project.model;

import lapr.project.controller.ImportCSVtoBST;
import lapr.project.controller.PairShipController;
import lapr.project.controller.TopNShipsController;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BSTShipTest {

    @Test
    void insert() {
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();

        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship1 = new Ships("123456788", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship2 = new Ships("123456786", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship3 = new Ships("123456787", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);


        BSTShip<Ships> bstShip= new AVLShip();

        assertEquals(0, bstShip.getSize());
        bstShip.insert(ship);
        assertEquals(1, bstShip.getSize());
        bstShip.insert(ship1);
        assertEquals(2, bstShip.getSize());
        bstShip.insert(ship2);
        assertEquals(3, bstShip.getSize());
        bstShip.insert(ship3);
        assertEquals(4, bstShip.getSize());
        bstShip.insert(ship3);
        assertEquals(4, bstShip.getSize());
        bstShip.insert(ship2);
        assertEquals(4, bstShip.getSize());

    }

    @Test
    void search() throws ParseException {
        BSTShip bstMessage= new AVLShip();



        String sdata= "31/12/2020 17:07";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date date=dateFormat.parse(sdata);

        BSTShip.Node node=bstMessage.search(sdata);

        assertEquals(node,null);
    }

    @Test
    void inOrderIterable() {
        BSTShip bstShip= new AVLShip();

        Iterable<Ships> ships= bstShip.inOrderIterable();

        List<Ships> ships1= new ArrayList<>();

        assertEquals(ships,ships1);

        }

    @Test
    void organize() throws ParseException {
        Company company= new Company();
        PairShipController pairShipController= new PairShipController(company);

        List<Double> distances= new ArrayList<>();
        List<Ships> ships= new ArrayList<>();

        BSTShipPosition bstShipPosition= new AVLShipPosition();
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition5 = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","42.93874","-66.97208","13.1","2.6","359","NA","B","123456789");

        bstShipPosition.insert(shipPosition);
        bstShipPosition.insert(shipPosition1);
        bstShipPosition.insert(shipPosition5);

        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",bstShipPosition);

        double distance= 1821;
        distances.add(distance);
        ships.add(ship);


        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition3 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        ShipPosition shipPosition4 = new ShipPosition("31/12/2020 17:10","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition bstShipPosition2= new AVLShipPosition();

        bstShipPosition2.insert(shipPosition2);
        bstShipPosition2.insert(shipPosition3);
        bstShipPosition2.insert(shipPosition4);
        Ships ship2 = new Ships("123456788", "ship", "1000000001", "callSign", "A", "100", "500", "3",bstShipPosition2);

        double distance2= 2428;
        distances.add(distance2);
        ships.add(ship2);

        BSTShip bstShip= new AVLShip();

        bstShip.insert(ship);
        bstShip.insert(ship2);


        List<Ships>ships1= bstShip.organize(distances,ships);

        boolean flag= true;
        Ships anterior= ships1.get(0);
        for (int i=1;i<ships1.size()-1;i++){
            Ships atual= ships1.get(i);

            double dist1 = anterior.getBstMessage().inorderCalculateDistance();

            double dist2=  atual.getBstMessage().inorderCalculateDistance();

            if (dist2>dist1){
                flag = false;
                break;
            }
        }
        assertTrue(flag);

    }

    @Test
    void getPair() throws ParseException {
        Company company= new Company();
        PairShipController pairShipController= new PairShipController(company);

        List<Ships> ships= new ArrayList<>();

        BSTShipPosition bstShipPosition= new AVLShipPosition();
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition5 = new ShipPosition("31/12/2020 17:04","80","-150","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","-70","100","13.1","2.6","359","NA","B","123456789");

        bstShipPosition.insert(shipPosition);
        bstShipPosition.insert(shipPosition1);
        bstShipPosition.insert(shipPosition5);

        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",bstShipPosition);

        ships.add(ship);

        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:03","10","-76","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition3 = new ShipPosition("31/12/2020 17:09","-40","-66.97192","13.3","4.2","358","NA","B","123456789");
        ShipPosition shipPosition4 = new ShipPosition("31/12/2020 17:10","86","-160.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition bstShipPosition2= new AVLShipPosition();

        bstShipPosition2.insert(shipPosition2);
        bstShipPosition2.insert(shipPosition3);
        bstShipPosition2.insert(shipPosition4);
        Ships ship2 = new Ships("123456788", "ship", "1000000001", "callSign", "A", "100", "500", "3",bstShipPosition2);

        ships.add(ship2);



        pairShipController.getMap();
    }

    @Test
    void getPair2() throws ParseException {
        Company company= new Company();
        PairShipController pairShipController= new PairShipController(company);

        List<Ships> ships= new ArrayList<>();

        BSTShipPosition bstShipPosition= new AVLShipPosition();
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition5 = new ShipPosition("31/12/2020 17:04","80","-150","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","-70","100","13.1","2.6","359","NA","B","123456789");

        bstShipPosition.insert(shipPosition);
        bstShipPosition.insert(shipPosition1);
        bstShipPosition.insert(shipPosition5);

        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",bstShipPosition);

        ships.add(ship);

        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition3 = new ShipPosition("31/12/2020 17:09","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        ShipPosition shipPosition4 = new ShipPosition("31/12/2020 17:10","42.9442","-66.97192","13.3","4.2","358","NA","B","123456789");
        BSTShipPosition bstShipPosition2= new AVLShipPosition();

        bstShipPosition2.insert(shipPosition2);
        bstShipPosition2.insert(shipPosition3);
        bstShipPosition2.insert(shipPosition4);
        Ships ship2 = new Ships("123456788", "ship", "1000000001", "callSign", "A", "100", "500", "3",bstShipPosition2);


        ships.add(ship2);
        pairShipController.getMap();
    }
    @Test
    void getPair3() throws ParseException {
        Company company= new Company();
        PairShipController pairShipController= new PairShipController(company);

        List<Ships> ships= new ArrayList<>();

        BSTShipPosition bstShipPosition= new AVLShipPosition();
        ShipPosition shipPosition = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition5 = new ShipPosition("31/12/2020 17:04","80","-150","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition1 = new ShipPosition("31/12/2020 17:07","-70","100","13.1","2.6","359","NA","B","123456789");

        bstShipPosition.insert(shipPosition);
        bstShipPosition.insert(shipPosition1);
        bstShipPosition.insert(shipPosition5);

        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",bstShipPosition);

        ships.add(ship);

        BSTShipPosition bstShipPosition2= new AVLShipPosition();
        ShipPosition shipPosition2 = new ShipPosition("31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition3 = new ShipPosition("31/12/2020 17:04","80","-150","12.5","2.4","358","NA","B","123456789");
        ShipPosition shipPosition4 = new ShipPosition("31/12/2020 17:07","-70","100","13.1","2.6","359","NA","B","123456789");

        bstShipPosition2.insert(shipPosition2);
        bstShipPosition2.insert(shipPosition3);
        bstShipPosition2.insert(shipPosition4);
        Ships ship2 = new Ships("123456788", "ship", "1000000001", "callSign", "A", "100", "500", "3",bstShipPosition2);


        ships.add(ship2);

        pairShipController.getMap();
    }

    @Test
    void smallSummariesBeetwenDate() throws IOException, ParseException {

        Company company=new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");

        TopNShipsController topNShipsController = new TopNShipsController(company);
        BSTShip bstShip=company.getBstShip();
        String sDate1 = "31-12-2020 17:02:00";
        String sDate2 = "31-12-2020 17:04:00";
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date date1=formatter.parse(sDate1);
        Date date2=formatter.parse(sDate2);
        List<SmallSummary> smallSummaries= bstShip.smallSummariesBeetwenDate(date1,date2);

        String expected= "[SmallSummary:mmsi='210950000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"
                +", SmallSummary:mmsi='212180000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='212351000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"
                +", SmallSummary:mmsi='228339600' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='229767000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='229857000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='229961000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='235092459' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='249047000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='256304000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='256888000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='257881000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='258692000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='303221000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='303267000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='305176000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='305373000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='305776000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='309416000' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='636019825' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='636091400' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 ", SmallSummary:mmsi='636092932' numberOfMovements=0 deltaDistance=0.0 realDistance=0.0\n"+
                 "]";

        assertEquals(smallSummaries.toString(),expected);




    }

    @Test
    void sameVessel() {
        SmallSummary smallSummary1 = new SmallSummary("123456789",10.0,"1",5);
        SmallSummary smallSummary2 = new SmallSummary("123456788",15.0,"2",6);
        SmallSummary smallSummary3 = new SmallSummary("123456787",5.0,"1",5);
        SmallSummary smallSummary4 = new SmallSummary("123456786",61.0,"3",30);
        SmallSummary smallSummary5 = new SmallSummary("123456785",7.6,"3",7);
        SmallSummary smallSummary6 = new SmallSummary("123456784",12.0,"1",5);
        SmallSummary smallSummary7 = new SmallSummary("123456783",11.0,"5",2);

        List<SmallSummary> smallSummaries= new ArrayList<>();
        smallSummaries.add(smallSummary1);
        smallSummaries.add(smallSummary2);
        smallSummaries.add(smallSummary3);
        smallSummaries.add(smallSummary4);
        smallSummaries.add(smallSummary5);
        smallSummaries.add(smallSummary6);
        smallSummaries.add(smallSummary7);
        BSTShip bstShip= new AVLShip();
        Map<String,List<SmallSummary>> listMap= bstShip.sameVessel(smallSummaries);

        List<SmallSummary> key1= new ArrayList<>();
        key1.add(smallSummary6);
        key1.add(smallSummary1);
        key1.add(smallSummary3);


        List<SmallSummary> key2= new ArrayList<>();
        key2.add(smallSummary2);


        List<SmallSummary> key3= new ArrayList<>();
        key3.add(smallSummary4);
        key3.add(smallSummary5);


        List<SmallSummary> key5= new ArrayList<>();
        key5.add(smallSummary7);


        Map<String, List<SmallSummary>> map= new TreeMap<>();

        map.put("1",key1);
        map.put("2",key2);
        map.put("3",key3);
        map.put("5",key5);


        assertEquals(listMap,map);
    }

    @Test
    void vessel2(){
        BSTShip bstShip=new AVLShip();
        List<SmallSummary> smallSummaries= new ArrayList<>();
        Map<String,List<SmallSummary>> listMap= bstShip.sameVessel(smallSummaries);

        Map<String, List<SmallSummary>> map= new TreeMap<>();

        assertEquals(map,listMap);

    }

    @Test
    void orderVesselDes() throws IOException, ParseException {

        Company company=new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        asd.importCSV("sships.csv");


        BSTShip bstShip=company.getBstShip();

        SmallSummary smallSummary1 = new SmallSummary("123456789",10.0,"1",5);
        SmallSummary smallSummary2 = new SmallSummary("123456788",15.0,"2",6);
        SmallSummary smallSummary3 = new SmallSummary("123456787",5.0,"1",5);
        SmallSummary smallSummary4 = new SmallSummary("123456786",61.0,"3",30);
        SmallSummary smallSummary5 = new SmallSummary("123456785",7.6,"3",7);
        SmallSummary smallSummary6 = new SmallSummary("123456784",12.0,"1",5);
        SmallSummary smallSummary7 = new SmallSummary("123456783",11.0,"5",2);

        List<SmallSummary> smallSummaries2= new ArrayList<>();
        smallSummaries2.add(smallSummary1);
        smallSummaries2.add(smallSummary2);
        smallSummaries2.add(smallSummary3);
        smallSummaries2.add(smallSummary4);
        smallSummaries2.add(smallSummary5);
        smallSummaries2.add(smallSummary6);
        smallSummaries2.add(smallSummary7);

            List<SmallSummary> smallSummaries;
            smallSummaries=bstShip.orderVesselDes(smallSummaries2);
            boolean flag= true;
            SmallSummary anterior= smallSummaries.get(0);
            for (int i=1;i<smallSummaries.size()-1;i++){
                SmallSummary atual= smallSummaries.get(i);
                if (anterior.getRealDistance()<atual.getRealDistance()){
                    flag = false;
                    break;
                }
            }
            assertTrue(flag);
    }

    @Test
    void insertNull(){
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();

        Ships ship = null;


        BSTShip<Ships> bstShip= new AVLShip();

        assertEquals(0, bstShip.getSize());
        bstShip.insert(ship);
        assertEquals(0,bstShip.getSize());

    }

    @Test
    void insert2(){
        BSTShipPosition<ShipPosition> instance = new AVLShipPosition();

        Ships ship = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship1 = new Ships("123456790", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship2 = new Ships("123456786", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship3 = new Ships("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);
        Ships ship4 = new Ships("123456791", "ship", "1000000000", "callSign", "A", "100", "500", "3",instance);


        BSTShip<Ships> bstShip= new AVLShip();

        assertEquals(0, bstShip.getSize());
        bstShip.insert(ship);
        assertEquals(1, bstShip.getSize());
        bstShip.insert(ship1);
        assertEquals(2, bstShip.getSize());
        bstShip.insert(ship2);
        assertEquals(3, bstShip.getSize());
        bstShip.insert(ship3);
        assertEquals(3, bstShip.getSize());
        bstShip.insert(ship3);
        assertEquals(3, bstShip.getSize());
        bstShip.insert(ship2);
        assertEquals(3, bstShip.getSize());
        bstShip.insert(ship4);
        assertEquals(4,bstShip.getSize());
    }

}