package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class US207 {
    private final Connection databaseConnection;

    public US207() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public boolean initialize(int givenYear, String mmsi ) throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US207(?, ?,?,?)}")) {





            statement.setInt(1, givenYear);
            statement.setString(2, mmsi);

            statement.registerOutParameter(3, Types.INTEGER);
            statement.registerOutParameter(4, Types.FLOAT);


            statement.executeUpdate();

            int numberOfCargos = statement.getInt(3);
            float media = statement.getFloat(4);


            StringBuilder toFile = new StringBuilder();
            toFile.append("Number of Cargo manifests: ").append(numberOfCargos).append("\n");
            toFile.append("Average number of containers per Cargo manifest : ").append(media);
            new WriteTxt(toFile, "US207_" + mmsi + "_" + givenYear);

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US207_" + mmsi + "_" + givenYear);
            return false;
        }
    }
}
