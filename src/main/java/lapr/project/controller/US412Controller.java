package lapr.project.controller;

import lapr.project.data.fsiap.ContainerStore;
import lapr.project.model.Company;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class US412Controller {
    private final ContainerStore containerStore;



    public US412Controller(Company company) {
        this.containerStore= company.getContainerStore();


    }



    public double calculateEnergySpecificRestrictions(int x, int y, int z){
        return containerStore.calculateEnergySpecificRestrictions(x,y,z);
    }

    public void write(double energy, int x, int y, int z) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();

        toFile.append("The container in the position ").append(x).append(", ").append(y).append(", ").append(z).append(", needed ").append(energy).append(" J to maintain the temperature in those circumstances");

        toFile.append("\n");


        new WriteTxt(toFile,"EnergyNeededForSpecificRestrictions.csv");
    }
}
