package lapr.project.controller;

import lapr.project.data.CapitalStore;
import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ImportCountryToBD {



    private final Company company;

    private final CountryStore countryStore;

    public ImportCountryToBD() {
        this.company = App.getInstance().getCompany();
        this.countryStore= company.getCountryList();
    }

    public ImportCountryToBD(Company company) {
        this.company = company;
        this.countryStore= company.getCountryList();
    }



    public int CountryImportCSV(String file) throws IOException {
        String line = "";
        int cont=0;
        List<Country> lst = countryStore.getCountryList();
        CapitalStore capitalStore= company.getCapitalStore();


        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                try {


                    String[] lines = line.split(splitBy);

                    Capital capital = capitalStore.verify(lines[5]);
                    //verificar se country existe
                    if (capital == null) {

                        capital = new Capital(lines[5], lines[6], lines[7], lines[3]);
                        capitalStore.save(capital);
                    }


                    Country country = new Country(lines[3], lines[0], lines[1], lines[2], lines[4], capital);


                    lst.add(country);
                } catch (Exception e) {
                    cont++;
                }
            }
            countryStore.newList(lst);


        }
        return cont;
    }
}

