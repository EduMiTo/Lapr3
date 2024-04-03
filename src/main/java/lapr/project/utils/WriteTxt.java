package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteTxt {

    public WriteTxt(StringBuilder info, String filename) throws FileNotFoundException {
        File txt = new File("output/" + filename);
        PrintWriter pw = null;
        try  {
            pw = new PrintWriter(txt);
            pw.println(info);
        }
        finally {
            pw.close();
        }
    }
}
