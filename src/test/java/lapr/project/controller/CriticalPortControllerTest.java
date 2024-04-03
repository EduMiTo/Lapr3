package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CriticalPortControllerTest {

    @Test
    void criticalPort() throws IOException {

        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");

        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        CriticalPortController criticalPortController= new CriticalPortController(company);

        criticalPortController.criticalPort(5,1);

    }

    /*TROCAR O GRAFO NO CONTROLLER PARA PODER CORRER ESTE TESTE-------------------------
    @Test
    void calculateMostCritical() throws IOException {

        int[] array ={-1,0,1,1,1,1,1,1,1,1,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array1 ={1,-1,1,1,1,1,1,1,1,1,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array2 ={1,2,-1,1,1,1,1,1,1,1,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array3 ={1,3,1,-1,3,1,4,4,1,3,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array4 ={1,4,1,4,-1,1,4,4,1,3,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array5 ={1,5,1,1,1,-1,1,1,5,1,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array6 ={1,6,1,4,6,1,-1,6,1,3,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array7 ={1,7,1,4,7,1,7,-1,1,3,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array8 ={1,8,1,1,1,8,1,1,-1,1,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array9 ={1,9,1,9,3,1,4,4,1,-1,1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array10 ={1,10,1,1,1,1,1,1,1,1,-1,1,13,1,15,20,13,1,1,1,1,19};
        int[] array11 ={1,11,1,1,1,1,1,1,1,1,1,-1,13,1,15,20,13,1,1,1,1,19};
        int[] array12 ={1,13,1,1,1,1,1,1,1,1,1,1,-1,12,15,13,12,13,13,13,13,19};
        int[] array13 ={1,13,1,1,1,1,1,1,1,1,1,1,13,-1,15,13,13,13,13,13,13,19};
        int[] array14 ={1,15,1,1,1,1,1,1,1,1,1,1,13,15,-1,14,13,15,15,15,15,15};
        int[] array15 ={1,15,1,1,1,1,1,1,1,1,1,1,13,15,15,-1,13,15,15,15,15,15};
        int[] array16 ={1,13,1,1,1,1,1,1,1,1,1,1,16,16,15,13,-1,13,13,13,13,19};
        int[] array17 ={1,17,1,1,1,1,1,1,1,1,1,1,13,17,15,17,13,-1,17,17,17,19};
        int[] array18 ={1,18,1,1,1,1,1,1,1,1,1,1,13,18,15,18,13,18,-1,18,18,19};
        int[] array19 ={1,19,1,1,1,1,1,1,1,1,1,1,13,19,15,19,13,19,19,-1,19,19};
        int[] array20 ={1,20,1,1,1,1,1,1,1,1,1,1,13,20,15,20,13,20,20,20,-1,19};
        int[] array21 ={1,21,1,1,1,1,1,1,1,1,1,1,13,21,15,21,13,21,21,21,21,-1};
        List<int[]> list= new ArrayList<>();
        list.add(array);
        list.add(array1);
        list.add(array2);
        list.add(array3);
        list.add(array4);
        list.add(array5);
        list.add(array6);
        list.add(array7);
        list.add(array8);
        list.add(array9);
        list.add(array10);
        list.add(array11);
        list.add(array12);
        list.add(array13);
        list.add(array14);
        list.add(array15);
        list.add(array16);
        list.add(array17);
        list.add(array18);
        list.add(array19);

        list.add(array20);
        list.add(array21);

        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");

        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        CriticalPortController criticalPortController= new CriticalPortController(company);

        criticalPortController.criticalPort(5,1);

        int [] count= new int[22];

        criticalPortController.calculateMostCritical(list,count);

        int [] expected= {43,351,43,49,51,43,43,43,43,43,43,43,43,119,43,83,43,43,43,61,67,43};

        for (int i=0; i<22;i++){
            assertEquals(count[i], expected[i]);
        }

    }
*/
    @Test
    void biggestPos() throws IOException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");

        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        CriticalPortController criticalPortController= new CriticalPortController(company);



        int array[] = {1,2,3,2,1,5,7,6,3};
        Map<Integer, Integer> real=criticalPortController.biggestPos(array,1);

        assertEquals(real.get(6),7);

    }

    @Test
    void biggestPos1() throws IOException {
        Company company= new Company();


        ImportCountryToBD importCountryToBD = new ImportCountryToBD(company);
        importCountryToBD.CountryImportCSV("countriesS.csv");
        //US1

        ImportBordersToBD importBordersToBD= new ImportBordersToBD(company);

        importBordersToBD.BorderImportCSV("borders.csv");

        PortImportController portImportController= new PortImportController(company);
        portImportController.portImportCSV("sportsS.csv");

        GraphBuilderController graphBuilderController = new GraphBuilderController(company);

        graphBuilderController.buildGraph(5);

        CriticalPortController criticalPortController= new CriticalPortController(company);



        int array[] = {1,2,3,2,1,5,7,6,3};
        Map<Integer, Integer> real=criticalPortController.biggestPos(array,1);
        for (Integer s : real.keySet()) {
            assertEquals(s, 6);
        }

    }
}