package lapr.project.controller;

import lapr.project.data.DatabaseImporting;
import lapr.project.data.SeaDistStore;
import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ImportSeaDistCSVtoBd {

    private final Company company;

    private final SeaDistStore seaDistStore;



    public ImportSeaDistCSVtoBd(Company company) {
        this.company = company;
        this.seaDistStore= company.getSeaDistStore();
    }

    public int seaDistImportCSV(String file) throws IOException {
        String line = "";
        int cont=0;
        List<SeaDist> lst = seaDistStore.getSeaDistList();



        DatabaseImporting databaseImporting= new DatabaseImporting();


        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                try {


                    String[] lines = line.split(splitBy);

                    Port portfrom = databaseImporting.getPort(App.getInstance().getDatabaseConnection(), lines[1]);
                    if (portfrom == null)
                        throw new Exception();

                    Port portTo = databaseImporting.getPort(App.getInstance().getDatabaseConnection(), lines[4]);
                    if (portTo == null)
                        throw new Exception();

                    SeaDist seaDist;

                    Double sd = Double.parseDouble(lines[6]) * 1000;

                    seaDist = new SeaDist(portfrom, portTo, String.valueOf(sd));


                    lst.add(seaDist);

                } catch (Exception e) {
                    cont++;
                }

            }


        }
        return cont;
    }
}
