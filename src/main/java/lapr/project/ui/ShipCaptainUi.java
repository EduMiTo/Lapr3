package lapr.project.ui;

import java.util.ArrayList;
import java.util.List;

public class ShipCaptainUi implements Runnable{

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("know how many cargo manifests I have transported \n" +
                "during a given year and the average number of containers per manifest", new US207Ui()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nShip Captain Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        } while (option != -1 );
    }
}
