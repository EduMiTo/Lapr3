package lapr.project.controller;

import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class ImportCSVtoBST{

    private final Company company;
    public ImportCSVtoBST() {
        this.company=App.getInstance().getCompany();
    }
    public ImportCSVtoBST(Company company) {
        this.company=company;
    }

    public int importCSV(String file) throws IOException, ParseException {
        String line = "";
        int cont=0;

        BSTShip tree = company.getBstShip();
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] ship = line.split(splitBy);

                try {


                    if (tree.search(ship[0]) == null) {

                        BSTShipPosition insideTree = new AVLShipPosition();

                        ShipPosition nMsg = new ShipPosition(ship[1], ship[2], ship[3], ship[4], ship[5], ship[6], ship[14], ship[15], ship[0]);
                        insideTree.insert(nMsg);


                        Ships nShip = new Ships(ship[0], ship[7], ship[8], ship[9], ship[10], ship[11], ship[12], ship[13], insideTree);

                        tree.insert(nShip);

                    } else {

                        BSTShip.Node<Ships> node = tree.search(ship[0]);

                        Ships ships = node.getElement();

                        BSTShipPosition insideTree = ships.getBstMessage();


                        ShipPosition nMsg = new ShipPosition(ship[1], ship[2], ship[3], ship[4], ship[5], ship[6], ship[14], ship[15], ship[0]);

                        insideTree.insert(nMsg);

                    }
                } catch (Exception e) {
                    System.out.println("Linha nao inserida");
                    cont++;
                }
            }

        }

        return cont;
    }



}
