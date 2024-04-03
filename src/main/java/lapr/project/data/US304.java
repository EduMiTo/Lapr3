package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Types;

public class US304 {
    private final Connection databaseConnection;

    public US304() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(int cm, int cont) throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{CALL US304(?,?,?)}")) {




            statement.setInt(1, cm);

            statement.setInt(2, cont);

            statement.registerOutParameter(3, Types.LONGVARCHAR);


            statement.executeUpdate();

            String listOfShips = statement.getString(3);

            StringBuilder toFile = new StringBuilder();
            toFile.append(listOfShips);
            new WriteTxt(toFile, "US304");

            statement.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US304");
            return false;

        }

    }
}
