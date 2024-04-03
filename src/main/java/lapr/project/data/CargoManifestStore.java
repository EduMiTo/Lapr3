package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteTxt;

import java.io.IOException;
import java.sql.*;

public class CargoManifestStore {
    private final Connection databaseConnection;


    public CargoManifestStore() {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();

    }

    public boolean createCMwFirstTrip(int cm, int port, String mmsi , String origin, String dest, Timestamp eDepdate,  Timestamp eArrdate) throws IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL CreateCMwFirstTrip(?,?,?,?,?,?,?)}")){



            statement.setInt(1, cm);

            statement.setInt(2, port);
            statement.setString(3, mmsi);

            statement.setString(4, origin);
            statement.setString(5, dest);

            statement.setTimestamp(6, eDepdate);

            statement.setTimestamp(7, eArrdate);



            statement.executeUpdate();



            StringBuilder toFile = new StringBuilder();
            toFile.append("Created with success");
            new WriteTxt(toFile, "CreateCMwFirstTrip"+cm+"_"+mmsi);

            statement.close();
            return true;
        }catch (Exception e){

            StringBuilder toFile = new StringBuilder();
            toFile.append("Ship is occupied\n");
            toFile.append(e.getMessage());
            new WriteTxt(toFile, "CreateCMwFirstTrip"+cm+"_"+mmsi);
            return false;

        }
    }

    public boolean insertCargoContainer(int cm, int cont, int trip, int x, int y, int z, float gross, String staff ) throws IOException, SQLException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL insertCargoContainer(?,?,?,?,?,?,?,?)}")) {


            statement.setInt(1, cm);

            statement.setInt(2, cont);
            statement.setInt(3, trip);

            statement.setInt(4, x);
            statement.setInt(5, y);

            statement.setInt(6, z);


            statement.setFloat(7, gross);
            statement.setString(8, staff);


            statement.executeUpdate();


            StringBuilder toFile = new StringBuilder();
            toFile.append("Created with success\n");
            new WriteTxt(toFile, "insertCargoContainer" + cm + "_" + cont);


            return true;
        } catch (SQLException e) {
            StringBuilder toFile = new StringBuilder();
            toFile.append("Creation error\n");
            toFile.append(e.getMessage());
            new WriteTxt(toFile, "insertCargoContainer" + cm + "_" + cont);
            return false;

        }
    }

    public boolean updateCargoContainer(int cm, int cont, float gross, String staff ) throws IOException {
        try(CallableStatement statement = databaseConnection.prepareCall("{CALL UpdateCargoContainer(?,?,?,?)}")) {




            statement.setInt(1, cm);

            statement.setInt(2, cont);

            statement.setFloat(3, gross);
            statement.setString(4, staff);


            statement.executeUpdate();



            StringBuilder toFile = new StringBuilder();
            toFile.append("Updated with success");
            new WriteTxt(toFile, "UpdateCargoContainer"+cm+"_"+cont);

            statement.close();
            return true;
        }catch (Exception e){
            StringBuilder toFile = new StringBuilder();
            toFile.append("Update error\n");
            toFile.append(e.getMessage());
            new WriteTxt(toFile, "UpdateCargoContainer"+cm+"_"+cont);
            return false;

        }
    }





}
