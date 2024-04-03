package lapr.project.controller;

import lapr.project.data.fsiap.ContainerStore;
import lapr.project.model.Company;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class US414Controller {
    private final ContainerStore containerStore;



    public US414Controller(Company company) {
        this.containerStore= company.getContainerStore();


    }



    public double neededEnergyToSupplyContainer( ){
        return containerStore.neededEnergyToSupplyContainer();
    }

    public void write(double energy) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();

        toFile.append("The ship ").append("needed ").append(energy).append(" J to maintain the temperature keeping in mind about the sides exposed to the sun");

        toFile.append("\n");


        new WriteTxt(toFile,"neededEnergyToSupplyContainerExposingSides.csv");
    }
}
