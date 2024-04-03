package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;

import java.sql.*;

public class US312 {
    private final Connection databaseConnection;

    public US312() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(int cont, String regist) throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{?=call US312(?,?)}")) {




            statement.setInt(2, cont);

            statement.setString(3, regist);


            statement.registerOutParameter(1, Types.LONGVARCHAR);

            statement.executeUpdate();

            String listOfShips = statement.getString(1);



            StringBuilder toFile = new StringBuilder();
            toFile.append(listOfShips);
            new WriteTxt(toFile, "US312");

            statement.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US312");
            return false;

        }




    }
}
