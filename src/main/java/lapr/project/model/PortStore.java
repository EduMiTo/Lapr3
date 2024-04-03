package lapr.project.model;


import lapr.project.model.graph.GenericElement;
import lapr.project.utils.Distance;

import java.util.ArrayList;
import java.util.List;

public class PortStore {

    private List<Port> portList;

    public PortStore(){
        portList=new ArrayList<>();
    }

    public List<Port> getPortList(){
        return portList;
    }

    public Port verify(String port){
        for(Port c : portList){
            if (c.getId().equals((port))){
                return c;
            }
        }
        return null;
    }

    public List<GenericElement> nGenericList(){
        List<GenericElement> genericElements=new ArrayList<>();

        for (Port port: portList){
            genericElements.add(new GenericElement(port));
        }
        return genericElements;
    }



    public List<GenericElement> portsOfsameCountry(GenericElement genericElement){

        List<GenericElement> genericElements= new ArrayList<>();

        for (Port p: portList){

            if (p.getPlaceLocation().getCountry().getName().equals(genericElement.getCountry()) && !p.getName().equals(genericElement.getName())){

                genericElements.add(new GenericElement(p));
            }

        }

        return genericElements;

    }


    public GenericElement closestPortToCapital(GenericElement genericElement){
        double min= Double.POSITIVE_INFINITY;
        Port portAux=null;


        for (Port port : portList){

            if(Distance.calculateDistance(genericElement.getLatitude(),genericElement.getLongitude(),port.getPlaceLocation().getLatitude(),port.getPlaceLocation().getLongitude())<min){

                min=Distance.calculateDistance(genericElement.getLatitude(),genericElement.getLongitude(),port.getPlaceLocation().getLatitude(),port.getPlaceLocation().getLongitude());
                portAux= port;

            }

        }

        return new GenericElement(portAux);

    }


    public List<GenericElement> nClosestPortsOutOfCountry (GenericElement genericElement, int n){

        List<GenericElement> genericElements= new ArrayList<>();

        double min=Double.POSITIVE_INFINITY;
        Port portAux=null;
        double lastMin=0;

        for (int i=0; i<n;i++){

            for (Port port : portList) {

                if (!port.getPlaceLocation().getCountry().getName().equals(genericElement.getCountry())) {
                    double dist = Distance.calculateDistance(genericElement.getLatitude(), genericElement.getLongitude(), port.getPlaceLocation().getLatitude(), port.getPlaceLocation().getLongitude());
                    if (dist < min && dist > lastMin) {
                        min = dist;
                        portAux = port;
                    }

                }
            }
            lastMin=min;
            min= Double.POSITIVE_INFINITY;
            genericElements.add(new GenericElement(portAux));
            portAux=null;

        }

        return genericElements;

    }


    public boolean newList(List<Port> ports){
            this.portList=ports;
            return true;
    }



}

