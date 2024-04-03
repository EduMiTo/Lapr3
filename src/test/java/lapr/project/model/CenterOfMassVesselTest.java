package lapr.project.model;

import lapr.project.data.lapr.US420Controller;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CenterOfMassVesselTest {

    @Test
    void getMass() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getMass(centerOfMassVessel.getPopaVolume()),450432.0);
    }

    @Test
    void getProaVolume() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getProaVolume(),23040.0);
    }

    @Test
    void getBaseEstruturalVolume() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getBaseEstruturalVolume(),588000.0);
    }

    @Test
    void getTorreVolume() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getTorreVolume(),45360.0);
    }

    @Test
    void getPopaVolume() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getPopaVolume(),57600.0);
    }

    @Test
    void getCenterOfMassX() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getCenterOfMassX(),195.5344537815126);
    }

    @Test
    void getCenterOfMassY() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getCenterOfMassY(),19.62268907563025);
    }

    @Test
    void getMass1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getMass(centerOfMassVessel.getPopaVolume()),172040.0);
    }

    @Test
    void getProaVolume1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getProaVolume(),7200);
    }

    @Test
    void getBaseEstruturalVolume1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getBaseEstruturalVolume(),240000);
    }

    @Test
    void getTorreVolume1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getTorreVolume(),6080);
    }

    @Test
    void getPopaVolume1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getPopaVolume(),22000);
    }

    @Test
    void getCenterOfMassX1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getCenterOfMassX(),113.46730601569311);
    }

    @Test
    void getCenterOfMassY1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getCenterOfMassY(),14.763586166811972);
    }

    @Test
    void getMass2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getMass(centerOfMassVessel.getPopaVolume()),279174.0);
    }

    @Test
    void getProaVolume2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getProaVolume(),20400);
    }

    @Test
    void getBaseEstruturalVolume2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getBaseEstruturalVolume(),510000);
    }

    @Test
    void getTorreVolume2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getTorreVolume(),6800);
    }

    @Test
    void getPopaVolume2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getPopaVolume(),35700);
    }

    @Test
    void getCenterOfMassX2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getCenterOfMassX(),143.87091988130564);
    }

    @Test
    void getCenterOfMassY2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getCenterOfMassY(),15.170623145400594);
    }

    @Test
    void getx1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getx1(),10.5);
    }

    @Test
    void getx2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getx2(),146);
    }

    @Test
    void getx3() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.getx3(),279);
    }

    @Test
    void getx12() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getx1(),11);
    }

    @Test
    void getx22() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getx2(),122);
    }

    @Test
    void getx32() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.getx3(),228.66666666666666);
    }

    @Test
    void getx13() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getx1(),15);
    }

    @Test
    void getx23() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getx2(),205);
    }

    @Test
    void getx33() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getx3(),386.6666666666667);
    }

    @Test
    void gety1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.gety1(),20);
    }

    @Test
    void gety2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.gety2(),17.5);
    }

    @Test
    void gety3() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.gety3(),16);
    }

    @Test
    void gety4() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.gety4(),48.5);
    }

    @Test
    void gety12() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.gety1(),12.5);
    }

    @Test
    void gety22() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.gety2(),15);
    }

    @Test
    void gety32() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.gety3(),6);
    }

    @Test
    void gety42() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Tanque");
        assertEquals(centerOfMassVessel.gety4(),24);
    }

    @Test
    void gety13() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.gety1(),12.5);
    }

    @Test
    void gety23() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.gety2(),15);
    }

    @Test
    void gety33() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.gety3(),8.333333333333334);
    }

    @Test
    void gety43() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Graneleiro");
        assertEquals(centerOfMassVessel.gety4(),62.5);
    }

    @Test
    void getx5() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getx5(100),130);
    }

    @Test
    void getx6() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getx6(100),280);
    }

    @Test
    void gety5() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.gety5(),5.0);
    }

    @Test
    void gety6() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.gety6(),5.0);
    }

    @Test
    void sink() {

    }

    @Test
    void getMassOfShip() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getMassOfShip(),5583480.0);
    }

    @Test
    void weightOfShip() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.weightOfShip(),5.4773938800000004E7);
    }

    @Test
    void getArea() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.getArea(),19200.0);
    }

    @Test
    void pression() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores");
        assertEquals(centerOfMassVessel.pression(),2852.8093125);
    }

    @Test
    void getMassOfShipWithContainers() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.getMassOfShipWithContainers(100),5633480.0);
    }

    @Test
    void weightOfShipWithContainers() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.weightOfShipWithContainers(100),5.5264438800000004E7);
    }

    @Test
    void pressionWithContainers() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.pressionWithContainers(100),2878.3561875);
    }

    @Test
    void calculateCenterOfMassXWContainer() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores",100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(1,0,3,4,5,6,500),0.4325042259977653);
    }

    @Test
    void calculateCenterOfMassXWContainer1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(15,205,380,205,34,476,500),195.33001461192447);
    }

    @Test
    void calculateCenterOfMassXWContainer2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(0,0,0,0,0,0,500),0.0);
    }

    @Test
    void calculateCenterOfMassXWContainer3() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(0,0,0,0,0,0,0),0.0);
    }

    @Test
    void calculateCenterOfMassXWContainer4() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(15,205,380,205,34,476,0),195.31932773109244);
    }


    @Test
    void calculateCenterOfMassYWContainer() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassYWContainer(1,0,3,4,5,6,500),0.4325042259977653);
    }

    @Test
    void calculateCenterOfMassYWContainer1() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(15,205,380,205,34,476,500),195.33001461192447);
    }

    @Test
    void calculateCenterOfMassYWContainer2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(0,0,0,0,0,0,500),0.0);
    }

    @Test
    void calculateCenterOfMassYWContainer3() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(0,0,0,0,0,0,0),0.0);
    }

    @Test
    void calculateCenterOfMassYWContainer4() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(15,205,380,205,34,476,0),195.31932773109244);
    }

    @Test
    void calculateCenterOfMassYWContainer5() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.calculateCenterOfMassXWContainer(20,17.5,18,48.5,36.5,36.5,500),19.690237515399826);
    }

    @Test
    void calculatesink() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores",100);
        assertEquals(centerOfMassVessel.sink(20),5.056634304207119E-4);
    }
    @Test
    void calculatesink2() {
        CenterOfMassVessel centerOfMassVessel = new CenterOfMassVessel("Porta-Contentores", 100);
        assertEquals(centerOfMassVessel.sink(0),0);
    }

    @Test
    void calculatesink3() throws FileNotFoundException {
        Company company= new Company();
        US420Controller us420Controller= new US420Controller(company);
        double res= us420Controller.sink("Tanque", 100);
        assertEquals(res,0);
    }
    @Test
    void calculatesink4() throws FileNotFoundException {
        Company company= new Company();
        US420Controller us420Controller= new US420Controller(company);
        double res= us420Controller.sink("Graneleiro", 100);
        assertEquals(res,0);
    }
    @Test
    void calculatesink5() throws FileNotFoundException {
        Company company= new Company();
        US420Controller us420Controller= new US420Controller(company);
        double res= us420Controller.sink("asd", 100);
        assertEquals(res,0);
    }
}