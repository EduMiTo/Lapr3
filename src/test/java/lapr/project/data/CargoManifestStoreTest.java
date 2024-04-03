package lapr.project.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class CargoManifestStoreTest {
//us309
    @Test
    void createCMwFirstTrip() throws IOException, SQLException, ParseException {

        CargoManifestStore cargoManifestStore = new CargoManifestStore();

        String date ="14.11.21 18:44:33";

        String date2 ="14.12.21 18:44:33";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
        Date parsedDate = dateFormat.parse(date);
        Date parsedDate2 = dateFormat.parse(date2);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());

        cargoManifestStore.createCMwFirstTrip(21,29002,"636019825","Larnaca","Marsaxlokk",timestamp,timestamp2);

    }
//Us307
    @Test
    void insertCargoContainer() throws IOException, SQLException {

        CargoManifestStore cargoManifestStore = new CargoManifestStore();

        cargoManifestStore.insertCargoContainer(17,213456782,1,1,1,1,12.6f,"Administrator");
    }
//us308
    @Test
    void insertCargoContainer2() throws IOException, SQLException {

        CargoManifestStore cargoManifestStore = new CargoManifestStore();

        cargoManifestStore.insertCargoContainer(18,213456782,1,1,1,1,12.6f,"Administrator");
    }

    @Test
    void updateCargoContainer() throws IOException, SQLException {

        CargoManifestStore cargoManifestStore = new CargoManifestStore();

        cargoManifestStore.updateCargoContainer(1,456789423,1,"Administrator");

    }
}