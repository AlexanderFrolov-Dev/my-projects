import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private static final String SOURCE_FILE_PATH = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\89. НОВЫЙ ВОРОНЕЖ 05.09.2022.csv";
    private static String[] arrayLines;
    private static String[][] arrayCellsOfLine;
    private static String[][] arrayCellsOfLine2;

    public static void main(String[] args) throws IOException {
//        arrayLines = getArrayLines();
//        for (String s : linesList) {
//            System.out.println(s);
//        }
        arrayCellsOfLine = getArrayCells(getArrayLines());
//        for (int i = 0; i < arrayCellsOfLine.length; i++) {
//            for (int j = 0; j < arrayCellsOfLine[i].length; j++) {
//                System.out.println(arrayCellsOfLine[i][j]);
//            }
//        }

        arrayCellsOfLine2 = getPersonArrayCells(arrayCellsOfLine);
        for (int i = 0; i < arrayCellsOfLine2.length; i++) {
            for (int j = 0; j < arrayCellsOfLine2[i].length; j++) {
                System.out.println(arrayCellsOfLine2[i][j]);
            }
        }
    }

    private static String getStringFromFile(File file) throws IOException {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedInputStream bis = new BufferedInputStream(fis);
        return IOUtils.toString(bis, "cp1251");
    }

    private static String[] getArrayLines() throws IOException {
        return getStringFromFile(new File(SOURCE_FILE_PATH)).split("\n");
    }

    private static String[][] getArrayCells(String[] arrayCells) {
        return Stream.of(arrayCells).map(s -> s.split(";")).toArray(String[][]::new);
    }

    private static String[][] getPersonArrayCells(String[][] sourceArray) {
        return Stream.of(sourceArray).skip(8).toArray(String[][]::new);
    }
}
