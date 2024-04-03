package lapr.project.controller;


import lapr.project.model.Company;
import lapr.project.model.CountryStore;
import lapr.project.model.graph.GenericElement;
import lapr.project.model.graph.MatrixGraph;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.*;

public class ClosestLocalController {

    private final Company company;
    private final CountryStore countryStore;
    private MatrixGraph matrixGraph;


    public ClosestLocalController(Company company) {
        this.company = company;
        this.countryStore= company.getCountryList();
        this.matrixGraph= company.getGraph();

    }




    public void sendContinentToCallDijkstra(int n) throws FileNotFoundException {
        StringBuilder toFile = new StringBuilder();
        List<String> continents = new ArrayList<>();

        continents.add("America");
        continents.add("Europe");
        continents.add("Asia");
        continents.add("Australia");
        continents.add("Antarctica");
        continents.add("Africa");

        for (String s : continents){
            toFile.append(s).append("\n");
            prepareWrite(callDijkstra(s,n),toFile);

        }

        writeToFile(toFile);
    }

    public StringBuilder prepareWrite(List<GenericElement> genericElements, StringBuilder stringBuilder){

        for (GenericElement genericElement : genericElements){
            stringBuilder.append(genericElement.getName());
            stringBuilder.append("-");
            stringBuilder.append(genericElement.getCountry()).append("\n");

        }
        return stringBuilder;
    }

    public void writeToFile(StringBuilder stringBuilder) throws FileNotFoundException {

        new WriteTxt(stringBuilder,"closestPlaces");
    }


        public List<GenericElement> callDijkstra(String continent, int n){
            this.matrixGraph= company.getGraph();


            Map<GenericElement, Double> map;

            double[] nlist= new double [matrixGraph.numVertices()];




            for (int i=0; i<matrixGraph.numVertices();i++) {
                matrixGraph.dijkstra(i, continent, countryStore,nlist);
            }



            map=getnCloserElement(n,nlist);

            return new ArrayList<>(map.keySet());
        }


        public Map<GenericElement, Double> getnCloserElement(int n,double[] nlist){
            double min= Double.MAX_VALUE;
            int minPos=0;
            double lastmin=-1;
            Map<GenericElement, Double> map=new LinkedHashMap<>();


            for (int j=0;j<n;j++) {
                for (int i = 0; i < nlist.length; i++) {
                    if (nlist[i]< Double.MAX_VALUE) {
                        if (nlist[i] < min && nlist[i] > 0 && nlist[i] > lastmin) {
                            min = nlist[i];
                            minPos = i;
                        }
                    }
                }
                List<GenericElement> genericElements = matrixGraph.vertices();

                if(min < Double.MAX_VALUE) {
                    map.put(genericElements.get(minPos), min);
                }

                lastmin=min;
                minPos=0;
                min=Double.MAX_VALUE;


            }



            return map;
        }

    }






