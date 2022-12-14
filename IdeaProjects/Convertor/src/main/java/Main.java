import java.io.FileInputStream;
import java.io.*;

import com.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import java.util.Iterator;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        //First we read the Excel file in binary format into FileInputStream
        FileInputStream input_document = new FileInputStream(new File("F:\\excel_to_csv.xls"));
        // Read workbook into HSSFWorkbook
        HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);
        // Read worksheet into HSSFSheet
        HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
        // To iterate over the rows
        Iterator<Row> rowIterator = my_worksheet.iterator();
        // OpenCSV writer object to create CSV file
        FileWriter my_csv=new FileWriter("convertedCSVFile.csv");
        CSVWriter my_csv_output=new CSVWriter(my_csv);
        //Loop through rows.
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println();
            int i=0;//String array
            //change this depending on the length of your sheet
            String[] csvdata = new String[2];
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); //Fetch CELL
                switch(cell.getCellType()) { //Identify CELL type
                    //you need to add more code here based on
                    //your requirement / transformations
                    case STRING:
                        csvdata[i]= cell.getStringCellValue();
                        break;
//                    case CellType.STRING -> {
//                        break csvdata[i]= cell.getStringCellValue();
//                    }
                }
                i=i+1;
            }
            my_csv_output.writeNext(csvdata);
        }
        my_csv_output.close(); //close the CSV file
        //we created our file..!!
        input_document.close(); //close xls
    }
}
