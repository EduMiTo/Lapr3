package lapr.project.data;

import lapr.project.utils.WriteTxt;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class GetCargoManifest {

    private final DatabaseImporting databaseImporting;

    public GetCargoManifest() {
        this.databaseImporting = new DatabaseImporting();
    }

    public void getCM(int cm) throws SQLException, FileNotFoundException {

        writeToFile(databaseImporting.getCargoManifest(cm));

    }

    public void writeToFile(List<String> strings) throws FileNotFoundException {

        StringBuilder stringBuilder= new StringBuilder();

        stringBuilder.append(strings.get(0)).append(" ").append(strings.get(1));

        stringBuilder.append("\n");
        int cont=0;

        for (int i = 2; i< strings.size(); i++){
            cont++;

            stringBuilder.append(strings.get(i));

            if(cont==4){
                stringBuilder.append("\n");
                cont=0;
            }
            else {
                stringBuilder.append(" ");
            }


        }

        new WriteTxt(stringBuilder, "containers.txt");

    }



}
