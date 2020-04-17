package FileWriterReader.WriteRead.txt;

import Scraper.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

public class FileWriter {
    final String path = "C:\\Users\\hp\\Desktop\\Результати\\dataBaseURL.txt";

    private Model modelUrl;

    public FileWriter(Model modelURL) {
        this.modelUrl = modelURL;
    }



    public void WriteDB() throws IOException {
        java.io.FileWriter writeFileDB;
        File file = new File(path);
        file.createNewFile();

        FileReader read= new FileReader();

            String data = "URL - " + modelUrl.getUrl() +
                    " Tag name - " + modelUrl.getTagName() +
                    " Tag value - " + modelUrl.getTagValue() +
                    " Next page URL - " + modelUrl.getNextPage() +
                    " Number of pages - " + modelUrl.getPages()+
                    " Tag price name - "+modelUrl.getTagNamePrice()+
                    " Tag value name - "+ modelUrl.getTagValuePrice()+"\n";
            if(!checkExistance(data)){
                writeFileDB = new java.io.FileWriter(path,true);
                writeFileDB.write(data);
                writeFileDB.close();
            }
    }


    private boolean checkExistance(String checked) throws FileNotFoundException {
        FileReader reader = new FileReader();
        ArrayList<String> data = reader.ReadFile();
        for (String datum : data) {
            if (datum.equals(checked)) {
                return true;
            }
        }
        return false;
    }

}
