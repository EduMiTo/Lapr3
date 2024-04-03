package lapr.project.controller;

import lapr.project.data.fsiap.ContainerStore;
import lapr.project.model.Company;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class US413Controller {
    private final ContainerStore containerStore;



    public US413Controller(Company company) {
        this.containerStore= company.getContainerStore();


    }



    public double calculateEnergySpecificTrip(int xR, int yR, int zR, int xN, int yN, int zN ){
        return containerStore.calculateEnergySpecificTrip(xR,yR,zR, xN, yN, zN);
    }

    public void write(double energy) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();

        toFile.append("The ship ").append("needed ").append(energy).append(" J to maintain the temperature in those circumstances");

        toFile.append("\n");


        new WriteTxt(toFile,"calculateEnergySpecificTrip.csv");
    }

}
