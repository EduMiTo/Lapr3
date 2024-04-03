package lapr.project.controller;

import lapr.project.data.BorderStore;
import lapr.project.model.*;
import lapr.project.model.graph.Edge;
import lapr.project.model.graph.GenericElement;
import lapr.project.model.graph.MatrixGraph;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.util.List;

public class LargestCircuitController {
    
    private final Company company;
    private final CountryStore countryStore;
    private MatrixGraph graph;
    private PortStore portStore;
    private final BorderStore borderStore;
    private int numberAlg;
    

    public LargestCircuitController(Company company) {
        this.company = company;
        this.countryStore= company.getCountryList();
        this.graph= company.getGraph();
        this.borderStore= company.getBorderStore();
        this.portStore=company.getPortStore();

    }
    

    public List<Edge> getLargestCircuit(String country) {
        Country country1 = countryStore.getCountry(country);
        return graph.coloredDFS(new GenericElement(country1));
    }

    public void writeToFile(List<Edge> circuit) throws FileNotFoundException {
        StringBuilder toFile = new StringBuilder();

        for (int i = 0; i < circuit.size(); i++) {
            GenericElement vOrig = (GenericElement)(circuit.get(i).getVOrig());
            toFile.append(vOrig.getName() +  ", " );
        }
        GenericElement vDest = (GenericElement)(circuit.get(circuit.size() - 1).getVDest());
        toFile.append(vDest.getName());
        new WriteTxt(toFile, "LargestCircuit.csv");
    }
}
