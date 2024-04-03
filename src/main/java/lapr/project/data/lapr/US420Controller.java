package lapr.project.data.lapr;

import lapr.project.model.CenterOfMassVessel;
import lapr.project.model.Company;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class US420Controller {
    private CenterOfMassVessel centerOfMassVessel;


    public US420Controller(Company company) {

    }

    public double sink(String vessel, int number) throws FileNotFoundException {
        try {
            centerOfMassVessel = new CenterOfMassVessel(vessel, number);

            return centerOfMassVessel.sink(number);
        }catch (Exception e){
            StringBuilder toFile=new StringBuilder();
            toFile.append("Invalid vessel");
            new WriteTxt(toFile,"sinkWith"+number+"Containers_"+vessel+".csv");
        }
        return 0;
    }

    public void write(String vessel, int number) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();

        toFile.append("The ship ").append(vessel).append(" has sinked: ").append(String.format("%.5f", centerOfMassVessel.sink(number))).append("meters.");

        toFile.append("\n");

        toFile.append("The ship had a pressure of ").append(String.format("%.5f",centerOfMassVessel.pression())).append(" before the containers were put in, and ").append(String.format("%.5f",centerOfMassVessel.pressionWithContainers(number))).append("after they were put in.");

        toFile.append("\n");

        toFile.append("The ship has an area of ").append(centerOfMassVessel.getArea()).append(" m^2");

        toFile.append("\n");

        new WriteTxt(toFile,"sinkWith"+number+"Containers_"+vessel+".csv");
    }
}
