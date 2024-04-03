package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @Test
    void getContainerId() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getContainerId(),123);
    }

    @Test
    void getX() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getX(),1);
    }

    @Test
    void getY() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getY(),2);
    }

    @Test
    void getZ() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getZ(),3);
    }

    @Test
    void getDimx() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getDimx(),5);
    }

    @Test
    void getDimy() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getDimy(),5);
    }

    @Test
    void getDimz() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getDimz(),5);
    }

    @Test
    void getRefri() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getRefri(),1);
    }

    @Test
    void getExt() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getExt(),"Ext");
    }

    @Test
    void getMid() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getMid(),"Meio");
    }

    @Test
    void getInte() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getInte(),"Int");
    }

    @Test
    void getTamExt() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getTamExt(),1);
    }

    @Test
    void getTamMid() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getTamMid(),1);
    }

    @Test
    void getTamInt() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getTamInt(),1);
    }

    @Test
    void getExtCap() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getExtCap(),1);
    }

    @Test
    void getMidCap() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getMidCap(),1);
    }

    @Test
    void getIntCap() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getIntCap(),1);
    }

    @Test
    void getTempCont() {
        Container container= new Container("123","1","2","3","5","5","5","1","Ext", "Meio","Int","1","1","1","1","1","1","7");
        assertEquals(container.getTempCont(),7);
    }
}