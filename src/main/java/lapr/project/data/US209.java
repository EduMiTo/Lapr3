package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;


import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class US209 {
    private final Connection databaseConnection;

    public US209() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(String mmsi, Timestamp date) throws IOException {

        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US209(?, ?, ?)}")) {





            statement.setString(1, mmsi);

            statement.setTimestamp(2, date);

            statement.registerOutParameter(3, Types.FLOAT);


            statement.executeUpdate();

            float ratio = statement.getFloat(3);


            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(date);

            StringBuilder toFile = new StringBuilder();
            toFile.append("occupancy rate(Total number of Containers/ Ship capacity) at the given moment(").append(timeStamp).append("): ").append(ratio).append("%");
            new WriteTxt(toFile, "US209_" + mmsi + "_" + timeStamp);

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(date);
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US209_" + mmsi + "_" + timeStamp);
            return false;
        }

    }
}
