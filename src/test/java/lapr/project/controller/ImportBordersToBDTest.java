package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ImportBordersToBDTest {

    @Test
    void borderImportCSV() throws IOException {
        Company company= new Company();




        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);


        int a =importBordersToBD.BorderImportCSV("countries.csv");

        assertEquals(a,68);


    }
    @Test
    void test() {

        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class, () -> {
            Company company= new Company();
            ImportBordersToBD portImportController= new ImportBordersToBD(company);

            portImportController.BorderImportCSV("errado.csv");
        });
        assertEquals(thrown.getMessage(), thrown.getMessage());
    }

    @Test
    void imp() throws IOException {
        Company company= new Company();
        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);

        importCountryToBD.CountryImportCSV("countries.csv");


        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);


        importBordersToBD.BorderImportCSV("borders.csv");
    }
}