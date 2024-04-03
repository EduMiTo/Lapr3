package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Types;

public class US206 {
    private final Connection databaseConnection;

    public US206() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(String mmsi) throws IOException {

        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US206(?, ?)}")) {



            statement.setString(1, mmsi);

            statement.registerOutParameter(2, Types.LONGVARCHAR);


            statement.executeUpdate();

            String listOfContainers = statement.getString(2);



            StringBuilder toFile = new StringBuilder();
            toFile.append(listOfContainers);
            new WriteTxt(toFile, "US206_" + mmsi);

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US206_" + mmsi);
            return false;
        }

    }
}
