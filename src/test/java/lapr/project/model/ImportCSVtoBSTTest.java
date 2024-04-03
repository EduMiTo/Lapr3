package lapr.project.model;


import lapr.project.controller.ImportCSVtoBST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportCSVtoBSTTest {
/*
    @Test
    void importCSVtest() {
        try {
            File myObj = new File("shiptest.csv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write("asdasda\n");
            myWriter.write("210950000,31/12/2020 17:19,42.97875,-66.97001,12.9,13.1,355,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
            myWriter.write("210950000,31/12/2020 17:03,42.92236,-66.97243,12.5,2.4,358,VARAMO,IMO9395044,C4SQ2,70,166,25,9.5,NA,B\n");
            myWriter.write("212180000,31/12/2020 19:37,24.34573,-85.12394,11.7,119.9,117,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,A\n");
            myWriter.write("212180000,31/12/2020 21:49,24.14301,-84.72268,11.7,116.6,114,SAITA I,IMO9643544,5BBA4,70,228,32,14.4,NA,B\n");

            myWriter.close();

            ImportCSVtoBST asd = new ImportCSVtoBST();
            asd.importCSV("sships.csv");


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
    */

    @Test
    void test1() throws IOException, ParseException {

        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class, () -> {
            ImportCSVtoBST asd = new ImportCSVtoBST();
            asd.importCSV("errado.csv");
        });
        assertEquals(thrown.getMessage(), thrown.getMessage());
    }

    @Test
    void test2() throws IOException, ParseException {
        Company company= new Company();
        ImportCSVtoBST asd = new ImportCSVtoBST(company);
        int real= asd.importCSV("sshipsFail.csv");

        assertEquals(2, real);
    }

}