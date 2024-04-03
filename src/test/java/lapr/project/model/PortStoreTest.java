package lapr.project.model;

import lapr.project.model.graph.GenericElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortStoreTest {

    @Test
    void getPortList() {
        Company company= new Company();

        List<Port> ports = new ArrayList<>();

        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);


        List<Port> portList= company.getPortStore().getPortList();

        portList.add(port);
        portList.add(port1);


        ports.add(port);
        ports.add(port1);

        assertEquals(ports,portList);
    }

    @Test
    void verify() {
        Company company= new Company();

        List<Port> ports = new ArrayList<>();

        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);

        ports.add(port);
        ports.add(port1);

        company.getPortStore().newList(ports);

        Port port2=company.getPortStore().verify(port.getId());

        assertEquals(port.getId(),port2.getId());

    }

    @Test
    void nGenericList() {
        Company company= new Company();

        List<Port> ports = new ArrayList<>();

        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);

        ports.add(port);
        ports.add(port1);

        company.getPortStore().newList(ports);

        List<GenericElement> genericElements2= company.getPortStore().nGenericList();

        List<GenericElement> genericElements= new ArrayList<>();

        genericElements.add(new GenericElement(port));
        genericElements.add(new GenericElement(port1));

        assertEquals(genericElements,genericElements2);
    }

    @Test
    void portsOfsameCountry() {

        Company company= new Company();

        List<Port> ports = new ArrayList<>();

        Country country= new Country("pt","Europa");
        Country country2= new Country("es","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);
        PlaceLocation placeLocation2 = new PlaceLocation("1","1",country2);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);
        Port port2= new Port("3","port3",placeLocation2);

        ports.add(port);
        ports.add(port1);
        ports.add(port2);

        company.getPortStore().newList(ports);

        List<GenericElement> genericElements= new ArrayList<>();



        genericElements.add(new GenericElement(port1));

        GenericElement genericElement= new GenericElement(port);

        List<GenericElement> genericElements1 = company.getPortStore().portsOfsameCountry(genericElement);

        assertEquals(genericElements,genericElements1);

    }

    @Test
    void closestPortToCapital() {
        Company company= new Company();

        List<Port> ports = new ArrayList<>();

        Country country= new Country("pt","Europa");
        Country country2= new Country("es","Europa");


        PlaceLocation placeLocation = new PlaceLocation("1","1",country);
        PlaceLocation placeLocation2 = new PlaceLocation("2","2",country2);
        PlaceLocation placeLocation3 = new PlaceLocation("50","50",country2);

        Port port= new Port("1","port1",placeLocation);
        Port port2= new Port("3","port3",placeLocation2);
        Port port3= new Port("4","port4",placeLocation3);

        ports.add(port);

        ports.add(port3);

        company.getPortStore().newList(ports);


        GenericElement genericElement=new GenericElement(port2);
        GenericElement genericElement2=new GenericElement(port);


        GenericElement genericElements1 = company.getPortStore().closestPortToCapital(genericElement);

        assertEquals(genericElement2,genericElements1);
    }

    @Test
    void nClosestPortsOutOfCountry() {
        Company company= new Company();

        List<Port> ports = new ArrayList<>();

        Country country= new Country("pt","Europa");
        Country country2= new Country("es","Europa");


        PlaceLocation placeLocation = new PlaceLocation("1","1",country);
        PlaceLocation placeLocation2 = new PlaceLocation("2","2",country2);
        PlaceLocation placeLocation3 = new PlaceLocation("50","50",country2);

        Port port= new Port("1","port1",placeLocation);
        Port port2= new Port("3","port3",placeLocation2);
        Port port3= new Port("4","port4",placeLocation3);

        ports.add(port);

        ports.add(port3);

        company.getPortStore().newList(ports);


        GenericElement genericElement=new GenericElement(port2);
        List<GenericElement> genericElements1= new ArrayList<>();

        genericElements1.add(new GenericElement(port));




        List<GenericElement> genericElements2 = company.getPortStore().nClosestPortsOutOfCountry(genericElement,1);

        assertEquals(genericElements2,genericElements1);
    }

    @Test
    void newList() {
        Company company= new Company();

        List<Port> ports = new ArrayList<>();

        Country country= new Country("pt","Europa");

        PlaceLocation placeLocation = new PlaceLocation("1","1",country);

        Port port= new Port("1","port1",placeLocation);
        Port port1= new Port("2","port2",placeLocation);

        ports.add(port);
        ports.add(port1);

        assertTrue(company.getPortStore().newList(ports));
    }
}