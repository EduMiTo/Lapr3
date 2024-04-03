package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.Types;

public class US406 {
    private final Connection databaseConnection;

    public US406() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(String mmsi, Timestamp datei, Timestamp datef) throws IOException {

        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US406(?,?,?,?)}")) {

            statement.setString(1, mmsi);

            statement.setTimestamp(2, datei);

            statement.setTimestamp(3, datef);

            statement.registerOutParameter(4, Types.LONGVARCHAR);

            statement.executeUpdate();

            String listOfShips = statement.getString(4);


            StringBuilder toFile = new StringBuilder();
            toFile.append(listOfShips);
            new WriteTxt(toFile, "US406_"+mmsi+".csv");

            statement.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US406"+mmsi+".csv");
            return false;

        }

    }
}
