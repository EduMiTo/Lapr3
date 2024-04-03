package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US305 {
    private final Connection databaseConnection;

    public US305() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(String regist, int cont) throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{?= call US305(?,?)}")) {





            statement.registerOutParameter(1, Types.LONGVARCHAR);
            statement.setString(2, regist);
            statement.setInt(3, cont);

            statement.executeUpdate();

            String listOfShips = statement.getString(1);



            StringBuilder toFile = new StringBuilder();
            toFile.append(listOfShips);
            new WriteTxt(toFile, "US305");

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US305");
            return false;

        }

    }
}
