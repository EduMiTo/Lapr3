package lapr.project.controller;

import lapr.project.data.BorderStore;
import lapr.project.data.SeaDistStore;
import lapr.project.model.*;
import lapr.project.model.graph.GenericElement;
import lapr.project.model.graph.MatrixGraph;
import lapr.project.utils.Distance;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.List;

public class GraphBuilderController {

    private final Company company;

    private final BorderStore borderStore;

    private final CountryStore countryStore;

    private final SeaDistStore seaDistStore;

    private final PortStore portStore;





    public GraphBuilderController(Company company) {
        this.company = company;
        this.borderStore= company.getBorderStore();
        this.countryStore= company.getCountryList();
        this.seaDistStore= company.getSeaDistStore();
        this.portStore= company.getPortStore();
    }


    public void buildGraph(int n){

        MatrixGraph graph = company.getGraph();

        List<GenericElement> genericElementCountry = countryStore.nGenericList();

        List<GenericElement> genericElementPort = portStore.nGenericList();


        for (GenericElement c : genericElementCountry){
            for (GenericElement c1: borderStore.getCountriesThatHaveBorder(c.getCountry())){



                graph.addEdge(c, c1, Distance.calculateDistance(c.getLatitude(),c.getLongitude(),c1.getLatitude(),c1.getLongitude()));
                graph.addEdge(c1, c,  Distance.calculateDistance(c1.getLatitude(),c1.getLongitude(),c.getLatitude(),c.getLongitude()));
            }

            GenericElement closestPort = portStore.closestPortToCapital(c);

            graph.addEdge(c,closestPort, Distance.calculateDistance(c.getLatitude(),c.getLongitude(),closestPort.getLatitude(),closestPort.getLongitude()));
            graph.addEdge(closestPort,c, Distance.calculateDistance(closestPort.getLatitude(),closestPort.getLongitude(),c.getLatitude(),c.getLongitude()));
        }


        for( GenericElement p : genericElementPort){

            for (GenericElement p1 : portStore.portsOfsameCountry(p)){




               graph.addEdge(p, p1, seaDistStore.getSeadistance(p.getName(), p1.getName()));
              //  graph.addEdge(p, p1, Distance.calculateDistance(p.getLatitude(),p.getLongitude(),p1.getLatitude(),p1.getLongitude()));
            }

            List<GenericElement> nClosestPorts= portStore.nClosestPortsOutOfCountry(p,n);

            for (GenericElement genericPort : nClosestPorts){

                 graph.addEdge(p, genericPort, seaDistStore.getSeadistance(p.getName(), genericPort.getName()));
                //graph.addEdge(p, genericPort, Distance.calculateDistance(p.getLatitude(),p.getLongitude(),genericPort.getLatitude(),genericPort.getLongitude()));


            }

        }


        company.setGraph(graph);
    }


    public void writeToFile() throws FileNotFoundException {
        StringBuilder toFile = new StringBuilder();
        toFile.append(company.getGraph());
        new WriteTxt(toFile, "Graph");
    }

}

