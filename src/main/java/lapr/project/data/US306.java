package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Types;

public class US306 {
    private final Connection databaseConnection;

    public US306() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize() throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US306(?)}")) {





            statement.registerOutParameter(1, Types.LONGVARCHAR);


            statement.executeUpdate();

            String listOfShips = statement.getString(1);



            StringBuilder toFile = new StringBuilder();
            toFile.append(listOfShips);
            new WriteTxt(toFile, "US306");

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US306");
            return false;

        }

    }
}
