package lapr.project.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Eduardo Silva <1201371@isep.ipp.pt>
 */
public class MainMenuUI {

    public MainMenuUI()
    {
    }

    public void run() throws IOException
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );

    }


}
