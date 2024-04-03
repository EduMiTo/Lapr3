package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.graph.GenericElement;
import lapr.project.model.graph.MatrixGraph;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ColoringGraphController {
    private final Company company;

    private final MatrixGraph matrixGraph;

    public ColoringGraphController(Company company) {
        this.company = company;
        matrixGraph= company.getGraph();

    }



    public void color() throws FileNotFoundException {
        print(matrixGraph.cGraph(matrixGraph, getCountryList()));
    }

    public List<GenericElement> getCountryList(){


        List<Integer> degrees = new ArrayList<>();
        List<GenericElement> elements = new ArrayList<>();

        for (Object genericElement: matrixGraph.vertices()) {

            GenericElement ge = (GenericElement) genericElement;

            if (company.getCountryList().searchByCapital(ge.getName()) != null) {

                degrees.add(matrixGraph.adjVertices(ge).size());
                elements.add(ge);

            }
        }

        return getElementsOrderByDegree(elements,degrees);

    }

    public List<GenericElement> getElementsOrderByDegree(List<GenericElement> elements, List<Integer> degrees ){


        for (int i = 0; i < elements.size()-1; i++) {
            for (int j = 0; j < elements.size() - i - 1; j++) {
                if (degrees.get(j) < degrees.get(j + 1)) {
                    int temp = degrees.get(j);
                    degrees.set(j, degrees.get(j + 1));
                    degrees.set(j+1, temp);
                    GenericElement temp2 = elements.get(j);
                    elements.set(j, elements.get(j + 1));
                    elements.set(j+1, temp2);
                }
            }
        }
        return elements;
    }

    public void print(Map<GenericElement, Integer> map) throws FileNotFoundException {
        StringBuilder toFile = new StringBuilder();

        int k = 0;
        for (GenericElement element : map.keySet()){
            toFile.append(k).append(" - ").append(element.getCountry()).append(" - ").append(map.get(element)).append("\n");
            k++;
        }
        new WriteTxt(toFile, "GraphWithColors");
    }

}
