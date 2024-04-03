package lapr.project.controller;

import lapr.project.data.fsiap.ContainerStore;
import lapr.project.model.Company;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class US415Controller {
    private final ContainerStore containerStore;



    public US415Controller(Company company) {
        this.containerStore= company.getContainerStore();


    }



    public int neededGenerators( ){
        return containerStore.neededGenerators();
    }

    public void write(int generators) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();

        toFile.append("The ship ").append("needed ").append(generators).append(" generators to have sufficient energy for the trip");

        toFile.append("\n");


        new WriteTxt(toFile,"neededGenerators.csv");
    }
}
