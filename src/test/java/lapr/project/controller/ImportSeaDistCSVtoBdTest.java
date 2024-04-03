package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImportSeaDistCSVtoBdTest {


    @Test
    void seaDistImportCSV() throws IOException {
        Company company= new Company();




        ImportSeaDistCSVtoBd importBordersToBD= new ImportSeaDistCSVtoBd(company);


        int a =importBordersToBD.seaDistImportCSV("countries.csv");

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
        ImportSeaDistCSVtoBd importSeaDistCSVtoBd= new ImportSeaDistCSVtoBd(company);

        importSeaDistCSVtoBd.seaDistImportCSV("seadistsT.csv");
    }
}