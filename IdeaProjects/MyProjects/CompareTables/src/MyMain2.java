import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class MyMain2 {
    private static String fullText;
    private static String fullTextOfFirstTable;
    private static String fullTextOfSecondTable;
    private static String[] arrayOfHeaderOfFirstTable;
    private static String[] arrayOfHeaderOfSecondTable;
    private static String[] arrayOfRowsOfFirstTable;
    private static String[] arrayOfRowsOfSecondTable;
    private static String[][] arrayOfCellsOfFirstTable;
    private static String[][] arrayOfCellsOfSecondTable;
    private static List<String[]> listOfCellsOfFirstTable = new LinkedList<>();
    private static List<String[]> listOfCellsOfSecondTable = new LinkedList<>();
    private static List<List<String>> listsOfCellsOfFirstTable;
    private static List<List<String>> listsOfCellsOfSecondTable;
    private static List<String> rowsWithSelectedColumnsOfFirstTable = new LinkedList<>();
    private static List<String> rowsWithSelectedColumnsOfSecondTable = new LinkedList<>();
    private static List<String> listFirstTableRows;
    private static List<String> listSecondTableRows;
    private static int count = 0;
    private static List<String[]> comparisonList = new ArrayList<>();
    private static boolean match = true;
    private static List<Boolean> nonMatch = new ArrayList<>();
    private static List<Integer> notCompareColumns = new ArrayList<>();

/*

 */

    public static void main(String[] args) throws IOException {
        fullTextOfFirstTable = readFromFile(new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\New_Test_Files\\First_File_CSV.csv"));
        fullTextOfSecondTable = readFromFile(new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\New_Test_Files\\Second_File_CSV.csv"));

        notCompareColumns.add(0);
        createComparisonTable();

//        System.out.println(fullTextOfFirstTable);
//        System.out.println();
//        System.out.println(fullTextOfSecondTable);

        arrayOfHeaderOfFirstTable = createColumnHeadersOfSourceTable(fullTextOfFirstTable);
        arrayOfHeaderOfSecondTable = createColumnHeadersOfSourceTable(fullTextOfSecondTable);

//        for (String s : arrayOfHeaderOfFirstTable) {
//            System.out.println(s);
//        }
//        System.out.println();
//        for (String s : arrayOfHeaderOfSecondTable) {
//            System.out.println(s);
//        }

        arrayOfRowsOfFirstTable = Arrays.stream(splitTextIntoLines(fullTextOfFirstTable)).skip(1).toArray(String[]::new);
        arrayOfRowsOfSecondTable = Arrays.stream(splitTextIntoLines(fullTextOfSecondTable)).skip(1).toArray(String[]::new);

//        for (String row : arrayOfRowsOfFirstTable) {
//            System.out.println(row);
//        }
//
//        for (String row : arrayOfRowsOfSecondTable) {
//            System.out.println(row);
//        }

        arrayOfCellsOfFirstTable = createArrayOfCellsOfSourceTable(fullTextOfFirstTable);
        arrayOfCellsOfSecondTable = createArrayOfCellsOfSourceTable(fullTextOfSecondTable);

//        System.out.println(arrayOfCellsOfFirstTable.length);
//        System.out.println(arrayOfCellsOfSecondTable.length);
//        System.out.println(Arrays.toString(arrayOfCellsOfFirstTable[0]));

//        for (int i = 0; i < arrayOfCellsOfFirstTable.length; i++) {
//            String[] row = arrayOfCellsOfFirstTable[i];
//            for (int j = 0; j < row.length; j++) {
//                System.out.println(row[j]);
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------");
//        for (int i = 0; i < arrayOfCellsOfSecondTable.length; i++) {
//            for (int j = 0; j < arrayOfCellsOfSecondTable[i].length; j++) {
//                System.out.println(arrayOfCellsOfSecondTable[i][j]);
//            }
//            System.out.println();
//        }

        for (String[] row : arrayOfCellsOfFirstTable) {
            listOfCellsOfFirstTable.add(row);
        }

        listOfCellsOfSecondTable.addAll(Arrays.asList(arrayOfCellsOfSecondTable));

//        System.out.println(listOfCellsOfFirstTable.size());
//        System.out.println(listOfCellsOfSecondTable.size());
//
//        listOfCellsOfFirstTable.forEach(strings -> {
//            for (String row : strings) {
//                System.out.println(row);
//            }
//        });
//        System.out.println();
//        listOfCellsOfSecondTable.forEach(strings -> {
//            for (String row : strings) {
//                System.out.println(row);
//            }
//        });

        rowsWithSelectedColumnsOfFirstTable = makeListOfRowsWithNecessaryColumns(arrayOfCellsOfFirstTable);
        rowsWithSelectedColumnsOfSecondTable = makeListOfRowsWithNecessaryColumns(arrayOfCellsOfSecondTable);
        System.out.println("rowsWithSelectedColumnsOfFirstTable size: " + rowsWithSelectedColumnsOfFirstTable.size());
        rowsWithSelectedColumnsOfFirstTable.forEach(System.out::println);
        System.out.println();
        System.out.println("rowsWithSelectedColumnsOfSecondTable size: " + rowsWithSelectedColumnsOfSecondTable.size());
        rowsWithSelectedColumnsOfSecondTable.forEach(System.out::println);

//        comparisonList.forEach(strings -> {
//            for (String row : strings) {
//                System.out.println(row);
//            }
//            System.out.println();
//        });

//        compareTables(listOfCellsOfFirstTable);
//        System.out.println(comparisonList.size());
//
//        comparisonList.forEach(strings -> {
//            for (String row : strings) {
//                System.out.println(row);
//            }
//        });
    }

    private static String readFromFile(File file) throws IOException {
        try (FileInputStream fin = new FileInputStream(file)) {
            StringBuilder lineBuilder = new StringBuilder();
            fullText = "";
            int symbol;

            try {
                do {
                    symbol = fin.read();
                    if (symbol != -1) {
                        lineBuilder.append((char) symbol);
                        fullText = String.valueOf(lineBuilder);
                    }
                } while (symbol != -1);
            } catch (IOException e) {
                System.out.println("Ошибка чтения из файла.");
            }
        }

        return fullText;
    }

    private static String[] createColumnHeadersOfSourceTable(String text) {
        return splitTextIntoLines(text)[0].split(";");
    }

    private static String[][] createArrayOfCellsOfSourceTable(String text) {
        String[] rows = splitTextIntoLines(text);
        String[] rowsWithoutHeader = Arrays.stream(rows).skip(1).toArray(String[]::new);
        String[][] cells = new String[rowsWithoutHeader.length][rowsWithoutHeader[0].split(";").length];

        for (int i = 0; i < rowsWithoutHeader.length; i++) {
            String row = rowsWithoutHeader[i];
            String[] cellsArray = row.split(";");
            System.arraycopy(cellsArray, 0, cells[i], 0, cellsArray.length);
        }

        return cells;
    }

    private static String[] splitTextIntoLines(String text) {
        return text.split("\n");
    }

    private static List<String> makeListOfRowsWithNecessaryColumns(String[][] arrayOfCells) {
        List<String> rowsWithNecessaryColumns = new LinkedList<>();
        StringBuilder rowWithNecessaryColumns = new StringBuilder();
        String[] row;

        if (!notCompareColumns.isEmpty()) {
            for (int i = 0; i < arrayOfCells.length; i++) {
                row = arrayOfCells[i];
                for (int j = 0; j < row.length; j++) {
                    int columnIndex = j;
                    if (notCompareColumns.stream().noneMatch(integer -> integer == columnIndex)) {
                        rowWithNecessaryColumns.append(row[j]);
                    }
                }
                rowsWithNecessaryColumns.add(String.valueOf(rowWithNecessaryColumns));
                rowWithNecessaryColumns.setLength(0);
            }
        } else {
            System.out.println("Не выбраны индексы столбцов");
        }
        return rowsWithNecessaryColumns;
    }

    private static void createComparisonTable() {
        listFirstTableRows = List.of(splitTextIntoLines(fullTextOfFirstTable));
        listSecondTableRows = List.of(splitTextIntoLines(fullTextOfSecondTable));

        List<String> notMatches = new ArrayList<>();

        for (String s : listFirstTableRows) {
            if (listSecondTableRows.stream().noneMatch(s::equals)) {
                notMatches.add(s);
            }
        }

        for (String s : listSecondTableRows) {
            if (listFirstTableRows.stream().noneMatch(s::equals)) {
                notMatches.add(s);
            }
        }

        String[] headerOfComparisonTable = createColumnHeadersOfSourceTable(fullText);
        String[] rowsOfComparisonTable = notMatches.toArray(String[]::new);
        String[][] cellsOfComparisonTable = createArrayOfCellsOfComparisonTable(rowsOfComparisonTable);
    }

    private static String[][] createArrayOfCellsOfComparisonTable(String[] rowsOfComparisonTable) {
        String[][] comparisonTableCells = new String[rowsOfComparisonTable.length][rowsOfComparisonTable[0].split(";").length];

        for (int i = 0; i < rowsOfComparisonTable.length; i++) {
            String row = rowsOfComparisonTable[i];
            String[] cells = row.split(";");
            System.arraycopy(cells, 0, comparisonTableCells[i], 0, cells.length);
        }
        return comparisonTableCells;
    }
}

