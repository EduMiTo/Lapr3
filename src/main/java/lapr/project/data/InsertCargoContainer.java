package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;



import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class InsertCargoContainer {

    private final Connection databaseConnection;


    public InsertCargoContainer() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(int cargoId, int containerId, int tripdId, int x, int y, int z, float gross, String portStaff) throws SQLException, IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{CALL INSERTCARGOCONTAINERS(?,?,?,?,?,?,?,?)}")) {



            statement.setInt(1, cargoId);

            statement.setInt(2, containerId);

            statement.setInt(3, tripdId);

            statement.setInt(4, x);

            statement.setInt(5, y);

            statement.setInt(6, z);

            statement.setFloat(7, gross);

            statement.setString(8, portStaff);


            statement.executeUpdate();


            statement.close();
            return true;
        }catch (Exception e){

            e.printStackTrace();

            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "insertCargoContainer_" + cargoId);
            return false;
        }
    }


}
