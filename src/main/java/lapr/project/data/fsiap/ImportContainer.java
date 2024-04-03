package lapr.project.data.fsiap;

import lapr.project.controller.App;
import lapr.project.model.Container;
import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportContainer {
    private final Company company;

    private final ContainerStore containerStore;



    public ImportContainer() {
        this.company = App.getInstance().getCompany();
        this.containerStore= company.getContainerStore();
    }

    public ImportContainer(Company company) {
        this.company = company;
        this.containerStore= company.getContainerStore();
    }



    public int importContainers(String file) throws IOException {
        String line = "";
        int cont=0;

        String splitBy = " ";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(splitBy);

                try {

                    Container container = new Container(lines[0], lines[1], lines[2], lines[3], lines[4], lines[5], lines[6], lines[7], lines[8], lines[9], lines[10], lines[11], lines[12], lines[13], lines[14], lines[15], lines[16], lines[17]);

                    containerStore.insert(container);

                } catch (Exception e) {
                    cont++;
                }
            }


        }
        return cont;
    }
}
