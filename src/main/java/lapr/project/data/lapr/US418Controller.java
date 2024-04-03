package lapr.project.data.lapr;

import lapr.project.model.CenterOfMassVessel;
import lapr.project.model.Company;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class US418Controller {

    private CenterOfMassVessel centerOfMassVessel;


    public US418Controller(Company company) {

    }
    public String centerOfMass(String vessel) throws FileNotFoundException {
        try {
            centerOfMassVessel = new CenterOfMassVessel(vessel);


        }catch (Exception e){
            StringBuilder toFile=new StringBuilder();
            toFile.append("Invalid vessel");
            new WriteTxt(toFile,"CenterOfMassOfVessel_"+vessel+".csv");

        }
        return vessel;
    }

    public void write(String vessel) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();

        toFile.append("The vessel ").append(vessel).append(" has center of mass X of: ").append(String.format("%.4f",centerOfMassVessel.getCenterOfMassX())).append("; and center of mass Y of: ").append(String.format("%.4f",centerOfMassVessel.getCenterOfMassY()));

        toFile.append("\n");

        toFile.append("The vessel has a total mass of: ").append(String.format("%.4f",centerOfMassVessel.getMassOfShip())).append("; and width: ").append(centerOfMassVessel.width).append("; length ").append(centerOfMassVessel.length).append("; height").append(centerOfMassVessel.height);

        toFile.append("\n");

        toFile.append("The vessel has the Positions X1 to X4 in: ").append(centerOfMassVessel.getx1()).append(";  ").append(centerOfMassVessel.getx2()).append("; ").append(centerOfMassVessel.getx3()).append("; ").append(centerOfMassVessel.x4);

        toFile.append("\n");

        new WriteTxt(toFile,"CenterOfMassOfVessel_"+vessel+".csv");
    }
}
