package lapr.project.data.lapr;

import lapr.project.model.CenterOfMassVessel;
import lapr.project.model.Company;
import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;

public class US419Controller {
    private CenterOfMassVessel centerOfMassVessel;


    public US419Controller(Company company) {

    }

    public String centerOfMass(String vessel, int number) throws FileNotFoundException {
        try {
            centerOfMassVessel = new CenterOfMassVessel(vessel, number);


        }catch (Exception e){
            StringBuilder toFile=new StringBuilder();
            toFile.append("Invalid vessel");
            new WriteTxt(toFile,"CenterOfMassOfVesselW"+number+"Containers_"+vessel+".csv");
        }
        return vessel;
    }

    public void write(String vessel, int number) throws FileNotFoundException {
        StringBuilder toFile=new StringBuilder();

        toFile.append("The vessel ").append(vessel).append(" has center of mass X of: ").append(String.format("%.4f",centerOfMassVessel.getCenterOfMassX())).append("; and center of mass Y of: ").append(String.format("%.4f",centerOfMassVessel.getCenterOfMassY()));

        toFile.append("\n");

        toFile.append("The vessel has a total mass of: ").append(centerOfMassVessel.getMassOfShip()).append("; and width: ").append(centerOfMassVessel.width).append("; length ").append(centerOfMassVessel.length).append("; height").append(centerOfMassVessel.height);

        toFile.append("\n");

        toFile.append("The vessel has the Positions X1 to X6 in: ").append(String.format("%.4f",centerOfMassVessel.getx1())).append(";  ").append(String.format("%.4f",centerOfMassVessel.getx2())).append("; ").append(String.format("%.4f",centerOfMassVessel.getx3())).append("; ").append(String.format("%.4f",centerOfMassVessel.x4)).append("; ").append(String.format("%.4f",centerOfMassVessel.getx5(number))).append("; ").append(String.format("%.4f",centerOfMassVessel.getx6(number)));

        toFile.append("\n");

        toFile.append("The container had a center of mass X of: ").append(String.format("%.4f",centerOfMassVessel.X_CONTAINER/2.0)).append("; a center of mass Y of: ").append(String.format("%.5f",centerOfMassVessel.Y_CONTAINER/2.0)).append("; a center of mass Z of: ").append(String.format("%.5f",centerOfMassVessel.Z_CONTAINER/2.0));

        toFile.append("\n");

        toFile.append("The vessel has a total mass with the containers was: ").append(String.format("%.4f",centerOfMassVessel.getMassOfShipWithContainers(number)));

        toFile.append("\n");


        new WriteTxt(toFile,"CenterOfMassOfVesselW"+number+"Containers_"+vessel+".csv");
    }
}
