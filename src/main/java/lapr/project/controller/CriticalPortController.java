package lapr.project.controller;

import lapr.project.data.SeaDistStore;
import lapr.project.model.Company;
import lapr.project.model.PortStore;
import lapr.project.model.graph.GenericElement;
import lapr.project.model.graph.MatrixGraph;
import lapr.project.utils.Distance;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.*;

public class CriticalPortController {
    private final Company company;

    private MatrixGraph matrixGraph;
    private PortStore portStore;
    private SeaDistStore seaDistStore;

    public CriticalPortController(Company company) {
        this.company = company;

        this.portStore=company.getPortStore();

        this.seaDistStore= company.getSeaDistStore();

    }



    public void criticalPort(int ports, int n) throws FileNotFoundException {

        this.matrixGraph=buildAuxGraphMaritime(ports);
        //this.matrixGraph= company.getGraph();

        int[] lastPos= new int[matrixGraph.numVertices()];
        List<int[]> auxPos= new ArrayList<>();

        for (int i=0; i<matrixGraph.numVertices();i++) {
            matrixGraph.dijkstraNotContinent(i, lastPos);
            auxPos.add(lastPos.clone());

        }

      /*   for (int[] p: auxPos){
            System.out.println(Arrays.toString(p));
        }*/


        int[] critCount= new int[matrixGraph.numVertices()];

         calculateMostCritical(auxPos, critCount);

       // System.out.println(Arrays.toString(critCount));

       // System.out.println(biggestPos(critCount));


        prepareWrite(matrixGraph.vertices(),biggestPos(critCount,n));
    }

    public void calculateMostCritical(List<int[]> auxPos,int[] critCount ){
        for (int i=0; i< matrixGraph.numVertices();i++){
            int pos=0;
            for (int j=0;j< matrixGraph.numVertices();j++) {
                while (auxPos.get(i)[pos] != -1) {
                    critCount[pos]++;
                    pos = auxPos.get(i)[pos];
                }
                critCount[pos]++;
                pos=j+1;
            }
        }
    }

    public Map<Integer,Integer> biggestPos(int[] critCount, int n){
        int max=0;
        int maxPos=0;
        int lastMax= Integer.MAX_VALUE;
        Map<Integer,Integer> map= new LinkedHashMap<>();
        for (int j=0; j< n;j++){

            for (int i=0;i<critCount.length;i++){
                if(critCount[i]>max && critCount[i]<lastMax){
                    max=critCount[i];
                    maxPos=i;
                }
            }
            map.put(maxPos,max);
            lastMax= max;
            max=0;
            maxPos=0;

        }

        return map;
    }

    public MatrixGraph buildAuxGraphMaritime(int n){

        MatrixGraph graph = new MatrixGraph(true);

        List<GenericElement> genericElementPort = portStore.nGenericList();



        for( GenericElement p : genericElementPort){

            for (GenericElement p1 : portStore.portsOfsameCountry(p)){

                 graph.addEdge(p, p1, seaDistStore.getSeadistance(p.getName(), p1.getName()));
                //graph.addEdge(p, p1, Distance.calculateDistance(p.getLatitude(),p.getLongitude(),p1.getLatitude(),p1.getLongitude()));
            }

            List<GenericElement> nClosestPorts= portStore.nClosestPortsOutOfCountry(p,n);

            for (GenericElement genericPort : nClosestPorts){

                graph.addEdge(p, genericPort, seaDistStore.getSeadistance(p.getName(), genericPort.getName()));
                //graph.addEdge(p, genericPort, Distance.calculateDistance(p.getLatitude(),p.getLongitude(),genericPort.getLatitude(),genericPort.getLongitude()));

            }

        }
        return graph;
    }
    public void prepareWrite( List<GenericElement> elements, Map<Integer,Integer> bigPos) throws FileNotFoundException {
        StringBuilder stringBuilder= new StringBuilder();

        for (Integer s : bigPos.keySet()) {
            stringBuilder.append("Most Critical Port: ");
            stringBuilder.append(elements.get(s).getName());

            stringBuilder.append(", ");
            stringBuilder.append("Number of occurrences: ");
            stringBuilder.append(bigPos.get(s));
            stringBuilder.append("\n");

        }



        writeToFile(stringBuilder);
    }

    public void writeToFile(StringBuilder stringBuilder) throws FileNotFoundException {

        new WriteTxt(stringBuilder,"CriticalPort.csv");
    }

}
