package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class US208 {
    private final Connection databaseConnection;

    public US208() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(int cmID, String mmsi) throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US208(?, ?, ?)}")) {



            statement.setInt(1, cmID);

            statement.setString(2, mmsi);

            statement.registerOutParameter(3, Types.FLOAT);


            statement.executeUpdate();

            float ratio = statement.getFloat(3);


            StringBuilder toFile = new StringBuilder();
            toFile.append("occupancy rate(Total number of Containers/ Ship capacity): ").append(ratio).append("%");
            new WriteTxt(toFile, "US208_" + mmsi + "_CM_" + cmID);

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US208_" + mmsi + "_CM_" + cmID);
            return false;
        }
    }
}
