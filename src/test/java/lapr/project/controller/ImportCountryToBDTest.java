package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImportCountryToBDTest {



    @Test
    void countryImportCSVf() throws IOException {
        Company company= new Company();




        ImportCountryToBD importBordersToBD= new ImportCountryToBD(company);


       int a= importBordersToBD.CountryImportCSV("borders.csv");

        assertEquals(a,121);


    }
    @Test
    void countryImportCSV() throws IOException {
        Company company= new Company();




        ImportCountryToBD importBordersToBD= new ImportCountryToBD(company);


        importBordersToBD.CountryImportCSV("countries.csv");


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


        ImportCountryToBD importCountryToBD= new ImportCountryToBD();

        importCountryToBD.CountryImportCSV("countries.csv");
    }
}