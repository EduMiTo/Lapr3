package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Types;

public class US210 {
    private final Connection databaseConnection;

    public US210() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize() throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US210(?)}")) {





            statement.registerOutParameter(1, Types.LONGVARCHAR);


            statement.executeUpdate();

            String listOfShips = statement.getString(1);


            StringBuilder toFile = new StringBuilder();
            toFile.append(listOfShips);
            new WriteTxt(toFile, "US210");

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US210");
            return false;

        }

    }
}
