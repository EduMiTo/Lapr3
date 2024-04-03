package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class US407 {
    private final Connection databaseConnection;

    public US407() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(int portid) throws IOException {
        try( CallableStatement statement = databaseConnection.prepareCall("{CALL US407(?,?)}")) {




            statement.setInt(1, portid);

            statement.registerOutParameter(2, Types.CLOB);


            statement.executeUpdate();

            Clob listOfShips = statement.getClob(2);

            Reader r= listOfShips.getCharacterStream();

            StringBuffer buffer= new StringBuffer();

            int ch;
            while ((ch=r.read())!=-1){
                buffer.append(""+(char)ch);
            }



            StringBuilder toFile = new StringBuilder();
            toFile.append(buffer.toString());
            new WriteTxt(toFile, "US407_"+ portid+".csv");

            statement.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US407_"+ portid+".csv");
            return false;

        }




    }
}
