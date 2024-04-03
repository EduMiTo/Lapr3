package lapr.project.controller;

import lapr.project.data.BorderStore;
import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;




public class ImportBordersToBD {
    private final Company company;

    private final BorderStore borderStore;





    public ImportBordersToBD(Company company) {
        this.company = company;
        this.borderStore= company.getBorderStore();
    }



    public int BorderImportCSV(String file) throws IOException {
        String line = "";
        int cont=0;
        List<Border> lst = borderStore.getBorderList();


        CountryStore countryStore= company.getCountryList();


        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                try {


                    String[] lines = line.split(splitBy);


                    Country country1 = countryStore.verify(lines[0]);

                    if (country1 == null)
                        throw new Exception();


                    Country country2 = countryStore.verify(lines[1].trim());
                    if (country2 == null)
                        throw new Exception();


                    Border border = new Border(country1, country2);


                    lst.add(border);
                } catch (Exception e) {
                    cont++;
                }
            }


        }
        return cont;
    }
}

