package FileWriterReader.WriteXLS;


import Scraper.Announcement;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class WriteFile {





    public  static int WriteToFile(String fileName, ArrayList<Announcement> data) throws IOException {



        Path path = Paths.get("C:\\Users\\hp\\Desktop\\Результати\\");
        if (new File(((path).toString())).mkdir()){
            System.out.println("Dir created");
        }

        String[] columns ={"Name","Price","Url","Serial number"};

        Workbook workBook = new HSSFWorkbook();
        Sheet sheet = workBook.createSheet(fileName);



        Font headerFont = workBook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeight((short) 12);
        headerFont.setColor(Font.COLOR_NORMAL);

        CellStyle headerCellStyle = workBook.createCellStyle();
        headerCellStyle.setFont(headerFont);


        //HAS CHANGED 06.03.2020 22:29
        Row headerRow = sheet.createRow(0);


        for(int i=1;i<columns.length;i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int ID=1;
        Iterator xlsDataAnnouncementIterator=data.iterator();

        while(xlsDataAnnouncementIterator.hasNext()) {
            Announcement tmp = (Announcement)xlsDataAnnouncementIterator.next();
            Row row = sheet.createRow(ID++);
            row.createCell(0).setCellValue(tmp.getTitle());
            if(tmp.getPrice()!=null){
                row.createCell(1).setCellValue(tmp.getPrice());
            }
          //  System.out.println(tmp.getSerialNumber());
            if(tmp.getSerialNumber()!=null){
                row.createCell(2).setCellValue(tmp.getSerialNumber());
            }
            if(tmp.getDate()!=null){
                row.createCell(3).setCellValue(tmp.getDate());
            }
        }

        for(int i=0;i<columns.length;i++){
            sheet.autoSizeColumn(i);
        }


            FileOutputStream fileXlsOut = new FileOutputStream("C:\\Users\\hp\\Desktop\\Результати\\"+fileName+".xls");
            workBook.write(fileXlsOut);
            fileXlsOut.close();

        return 0;
    }



}
