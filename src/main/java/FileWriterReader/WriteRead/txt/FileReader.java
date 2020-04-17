package FileWriterReader.WriteRead.txt;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    final String path = "dataBaseURL.txt";


    private ArrayList<String> ReadDB() throws FileNotFoundException {
        ArrayList<String> dataBaseElements = new ArrayList<String>();

            Scanner scanFile = new Scanner(new java.io.FileReader(path));
            while (scanFile.hasNextLine()) {
                dataBaseElements.add(scanFile.nextLine());
            }

        return  dataBaseElements;
    }

    public ArrayList<String> ReadFile() throws FileNotFoundException {
        return ReadDB();
    }
}
