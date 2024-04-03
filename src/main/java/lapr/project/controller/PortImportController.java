package lapr.project.controller;

import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PortImportController {
    private final Company company;

    private final PortStore portStore;

    public PortImportController() {
        this.company = App.getInstance().getCompany();
        this.portStore= company.getPortStore();
    }

    public PortImportController(Company company) {
        this.company = company;
        this.portStore= company.getPortStore();
    }

    public int portImportCSV(String file) throws IOException {
        String line = "";
        int cont=0;
        List<Port> lst = portStore.getPortList();
        List<Gen2dTree.Node<Port>> nodeList = new ArrayList<>();
        CountryStore countryStore= company.getCountryList();
        Port2dTree port2dTree= company.getPort2dTree();
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                try {


                    String[] lines = line.split(splitBy);
                    Country country = countryStore.verify(lines[1]);
                    //verificar se country existe
                    if (country == null) {
                        country = new Country(lines[1], lines[0]);
                        countryStore.save(country);
                    }

                    PlaceLocation placeLocation = new PlaceLocation(lines[4], lines[5], country);

                    Port port = new Port(lines[2], lines[3], placeLocation);
                    lst.add(port);
                } catch (Exception e) {
                    System.out.println("Linha nao inserida");
                    cont++;
                }
            }
            for (Port port : lst) {
                nodeList.add(new Port2dTree.Node(port, Double.parseDouble(port.getPlaceLocation().getLatitude()), Double.parseDouble(port.getPlaceLocation().getLongitude())));
            }
            port2dTree.buildTree(nodeList);


        }
        return cont;
    }
}
