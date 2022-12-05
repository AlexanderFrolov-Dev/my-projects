import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class ExcelCompare {
    public static void main(String[] args) {
        // com.relevantcodes.extentreports.extentreports
        JFrame appFrame = new JFrame("DBFCompare");

        appFrame.setSize(new Dimension(1500,1000));
        appFrame.setVisible(true);

        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        String pathToFirstFile = "";
        String pathToSecondFile = "";

        // FileInputStream fileInputStream = new FileInputStream(pathToFirstFile);

        HSSFWorkbook hssfWorkbook;

    }
}
