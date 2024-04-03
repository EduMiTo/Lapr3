package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class US204 {
    private final Connection databaseConnection;

    public US204() {

        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(Integer containerid) throws IOException {

        try(CallableStatement statement = databaseConnection.prepareCall("{? = call US204(?)}")) {



            statement.setInt(2, containerid);

            statement.registerOutParameter(1, Types.LONGVARCHAR);



            statement.executeUpdate();

            String containerPosition = statement.getString(1);


            StringBuilder toFile=new StringBuilder();
            toFile.append(containerPosition);
            new WriteTxt(toFile,"US204_"+containerid);

            statement.close();
            return true;
        } catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile,"US204_"+containerid);
            return false;
        }


    }
}
