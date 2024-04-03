package lapr.project.data;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

class GetCargoManifestTest {

    @Test
    void getCM() throws SQLException, FileNotFoundException {


        GetCargoManifest getCargoManifest= new GetCargoManifest();

        getCargoManifest.getCM(3);
    }
}