package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class US310 {
    private final Connection databaseConnection;

    public US310() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean initialize(int month, int year) throws IOException {
        try( CallableStatement statement = databaseConnection.prepareCall("{CALL US310f(?,?,?)}")) {




            statement.setInt(1, month);

            statement.setInt(2, year);


            statement.registerOutParameter(3, Types.CLOB);


            statement.executeUpdate();

            Clob listOfShips = statement.getClob(3);

            Reader r= listOfShips.getCharacterStream();

            StringBuffer buffer= new StringBuffer();

            int ch;
            while ((ch=r.read())!=-1){
                buffer.append(""+(char)ch);
            }



            StringBuilder toFile = new StringBuilder();
            toFile.append(buffer.toString());
            new WriteTxt(toFile, "US310");

            statement.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            StringBuilder toFile = new StringBuilder();
            toFile.append("Something went wrong");
            new WriteTxt(toFile, "US310");
            return false;

        }




    }
}
