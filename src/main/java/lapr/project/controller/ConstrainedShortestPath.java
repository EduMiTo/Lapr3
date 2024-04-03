package lapr.project.controller;

import lapr.project.data.BorderStore;
import lapr.project.model.Company;
import lapr.project.model.CountryStore;
import lapr.project.model.PortStore;
import lapr.project.model.graph.GenericElement;
import lapr.project.model.graph.MatrixGraph;
import lapr.project.utils.Distance;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.*;

public class ConstrainedShortestPath {

    private final CountryStore countryStore;
    private final MatrixGraph matrixGraph;
    private final PortStore portStore;
    private final BorderStore borderStore;
    private int numberAlg;


    public ConstrainedShortestPath(Company company) {

        this.countryStore= company.getCountryList();
        this.matrixGraph= company.getGraph();
        this.borderStore= company.getBorderStore();
        this.portStore=company.getPortStore();


    }





    public void prepareWrite(List<Integer> positions,double value, List<GenericElement> elements, List<Integer> placesOrder) throws FileNotFoundException {
        StringBuilder stringBuilder= new StringBuilder();

        stringBuilder.append("Place of start:");
        stringBuilder.append(elements.get(positions.get(0)).getName());
        stringBuilder.append("\n");
        stringBuilder.append("Places between:");

        for (int i=1; i< positions.size()-1; i++){
            stringBuilder.append(elements.get(positions.get(i)).getName());
            stringBuilder.append("\n");
        }


        stringBuilder.append("Place of End:");
        stringBuilder.append(elements.get(positions.get(positions.size()-1)).getName());
        stringBuilder.append("\n");

        stringBuilder.append("Distance: ");
        stringBuilder.append(value/1000);
        stringBuilder.append("Km");
        stringBuilder.append("\n");
        stringBuilder.append("\n");

        stringBuilder.append("Full Path: ");
        stringBuilder.append("\n");
        for (Integer integer : placesOrder) {
            stringBuilder.append(elements.get(integer).getName());
            stringBuilder.append(", ");
        }

        writeToFile(stringBuilder);
    }

    public void writeToFile(StringBuilder stringBuilder) throws FileNotFoundException {

        new WriteTxt(stringBuilder,"ConstrainedShortestPath_"+numberAlg+".csv");
    }


    public void selectTypeForDijkstra(int n, List<String> placesString, int numberOfPortsConnected) throws FileNotFoundException {
        this.numberAlg=n;


        MatrixGraph graph= null;

        switch (n){
            //grafo completo
            case 1:
                graph=this.matrixGraph;
                break;
                //
            case 2:
                //Land graph
                graph = buildAuxGraphLand();
                break;
            case 3:
                graph = buildAuxGraphMaritime(numberOfPortsConnected);
                break;
            default:
                graph=this.matrixGraph;
                break;
        }



        callDijkstra(placesString,graph);


    }






    public void callDijkstra(List<String> placesString, MatrixGraph matrixGraph) throws FileNotFoundException {


        //System.out.println(matrixGraph);

        List<GenericElement> places=new ArrayList<>();
        for (String string : placesString){
            for(Object oGe: matrixGraph.vertices()){
                GenericElement ge= (GenericElement) oGe;
                if (ge.getName().equals(string)){
                    places.add(ge);
                }
            }
        }



        List<GenericElement> genericElements = matrixGraph.vertices();
        List<Integer> positions = new ArrayList<>();

        for( int i = 0; i< places.size();i++){
            for (int j=0; j< genericElements.size();j++){
                if (places.get(i).equals(genericElements.get(j))){
                    positions.add(j);
                }
            }
        }

        

        List<double[]> distances= new ArrayList<>();
        int[] lastPos= new int[matrixGraph.numVertices()];
        List<int[]> auxPos= new ArrayList<>();


        for (int i=0; i<positions.size()-1;i++) {
            distances.add(matrixGraph.dijkstraNotContinent(positions.get(i),lastPos));
            auxPos.add(lastPos.clone());
        }
       /* for (int[] p: auxPos){
            System.out.println(Arrays.toString(p));
        }*/






        List<double[]> auxi = new ArrayList<>();
        List<Integer> posAux = new ArrayList<>();

        auxi.addAll(distances);
        posAux.addAll(positions);



        List<Integer> path =matrixGraph.getSmallestPath(auxi,posAux);

        //System.out.println(path);
        double total=0;



        for (int i=1; i<= distances.size();i++){

            int posInDist= getPos(positions,path.get(i-1));
            double[] aux = distances.get(posInDist);

            total+= aux[path.get(i)];

        }

        List<Integer> placesPassedNumber= new ArrayList<>();

        for (int i=1; i<positions.size();i++){
            int posInDist= getPos(positions,path.get(i-1));

            int pos= path.get(i);





            List<Integer> auxpositions= new ArrayList<>();
            while (auxPos.get(posInDist)[pos]!=path.get(i-1)){


                auxpositions.add(auxPos.get(posInDist)[pos]);
                pos=auxPos.get(posInDist)[pos];


            }
            Collections.reverse(auxpositions);



            if (auxPos.get(posInDist)[pos]==path.get(i-1)){

                placesPassedNumber.add(auxPos.get(posInDist)[pos]);
            }
            placesPassedNumber.addAll(auxpositions);
            

        }
        placesPassedNumber.add(path.get(positions.size()-1));

      //  System.out.println(placesPassedNumber);









        prepareWrite(positions,total,genericElements, placesPassedNumber);


    }




    public int getPos(List<Integer> pos, int j){

        for (int i=0; i<pos.size();i++){

            if (pos.get(i)==j){
                return i;
            }
        }
        return 0;
    }








    public MatrixGraph buildAuxGraphLand(){

        MatrixGraph graph = new MatrixGraph(true);

        List<GenericElement> genericElementCountry = countryStore.nGenericList();


        for (GenericElement c : genericElementCountry){
            for (GenericElement c1: borderStore.getCountriesThatHaveBorder(c.getCountry())){



                graph.addEdge(c, c1, Distance.calculateDistance(c.getLatitude(),c.getLongitude(),c1.getLatitude(),c1.getLongitude()));
                graph.addEdge(c1, c,  Distance.calculateDistance(c1.getLatitude(),c1.getLongitude(),c.getLatitude(),c.getLongitude()));
            }

            GenericElement closestPort = portStore.closestPortToCapital(c);

            graph.addEdge(c,closestPort, Distance.calculateDistance(c.getLatitude(),c.getLongitude(),closestPort.getLatitude(),closestPort.getLongitude()));
            graph.addEdge(closestPort,c, Distance.calculateDistance(closestPort.getLatitude(),closestPort.getLongitude(),c.getLatitude(),c.getLongitude()));
        }

        return graph;
    }

    public MatrixGraph buildAuxGraphMaritime(int n){

        MatrixGraph graph = new MatrixGraph(true);

        List<GenericElement> genericElementPort = portStore.nGenericList();



        for( GenericElement p : genericElementPort){

            for (GenericElement p1 : portStore.portsOfsameCountry(p)){




                // graph.addEdge(p, p1, seaDistStore.getSeadistance(p.getName(), p1.getName()));
                graph.addEdge(p, p1, Distance.calculateDistance(p.getLatitude(),p.getLongitude(),p1.getLatitude(),p1.getLongitude()));
            }

            List<GenericElement> nClosestPorts= portStore.nClosestPortsOutOfCountry(p,n);

            for (GenericElement genericPort : nClosestPorts){

                //graph.addEdge(p, genericPort, seaDistStore.getSeadistance(p.getName(), genericPort.getName()));
                graph.addEdge(p, genericPort, Distance.calculateDistance(p.getLatitude(),p.getLongitude(),genericPort.getLatitude(),genericPort.getLongitude()));


            }

        }
        return graph;

    }
}
