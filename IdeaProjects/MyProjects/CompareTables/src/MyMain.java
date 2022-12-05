import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class MyMain {
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
    private static int count = 0;
    private static List<String[]> comparisonList = new ArrayList<>();
    private static boolean match = true;
    private static List<Boolean> nonMatch = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        fullTextOfFirstTable = readFromFile(new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\New_Test_Files\\First_File_CSV.csv"));
        fullTextOfSecondTable = readFromFile(new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\New_Test_Files\\Second_File_CSV.csv"));

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

        compareTables();
        System.out.println("comparisonList size: " + comparisonList.size());
        System.out.println();

        System.out.println("nonMatch size: " + nonMatch.size());

        for (Boolean aBoolean : nonMatch) {
            count++;
            System.out.println(count + " - " + aBoolean);
        }

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
    
//    private static List<String[]> compareTables() {
//        List<String[]> comparisonTable = new ArrayList<>();
//
//        String[] stringBeingCompared = arrayOfCellsOfFirstTable[0];
//
//        listOfCellsOfFirstTable.addAll(Arrays.asList(arrayOfCellsOfFirstTable));
//        listOfCellsOfSecondTable.addAll(Arrays.asList(arrayOfCellsOfSecondTable));
//
//        for (int i = 0; i < listOfCellsOfSecondTable.size(); i++) {
//            for (int j = 0; j < listOfCellsOfSecondTable.get(i).length; j++) {
//
//            }
//        }
//
//        return comparisonTable;
//    }

//    private static void compareTables() {
//        int arrayLength = arrayOfCellsOfFirstTable.length;
//
//        List<String[]> comparisonTable = new ArrayList<>();
//
//        String[] stringBeingCompared = arrayOfCellsOfFirstTable[0];
//
//        if (count < arrayLength) {
//
//        }
//
//        listOfCellsOfFirstTable.addAll(Arrays.asList(arrayOfCellsOfFirstTable));
//        listOfCellsOfSecondTable.addAll(Arrays.asList(arrayOfCellsOfSecondTable));
//
//        for (int i = 0; i < listOfCellsOfSecondTable.size(); i++) {
//            for (int j = 0; j < listOfCellsOfSecondTable.get(i).length; j++) {
//
//            }
//        }
//        count++;
//        compareTables();
//    }

//    private static void compareTables() {
//        List<String[]> comparisonTable = new ArrayList<>();
//        String[] comparisonRow;
//
//        listOfCellsOfFirstTable.addAll(Arrays.asList(arrayOfCellsOfFirstTable));
//        listOfCellsOfSecondTable.addAll(Arrays.asList(arrayOfCellsOfSecondTable));
//
//        ListIterator<String[]> iterator = listOfCellsOfFirstTable.listIterator();
//
//        for (int i = 0; i < listOfCellsOfSecondTable.size(); i++) {
//            for (int j = 0; j < listOfCellsOfSecondTable.get(i).length; j++) {
//                while (iterator.hasNext()) {
//                    comparisonRow = iterator.next();
//                    if (j != 0 || j != 5 || j != 6) {
//                        listOfCellsOfSecondTable.get(i)[j].equals(comparisonRow[j]);
//                    }
//                }
//
//            }
//        }
//        count++;
//        compareTables();
//    }

//    private static void compareTables(List<String[]> rows) {
//        String[] comparisonRow = rows.get(0);
//        String[] currentRow;
//        String cell;
//
//        for (int i = 0; i < listOfCellsOfFirstTable.size(); i++) {
//            currentRow = listOfCellsOfFirstTable.get(i);
//            for (int j = 0; j < currentRow.length; j++) {
//                cell = currentRow[j];
////                if (j != 0 && j!= 5 && j != 6) {
//                    if (!currentRow[j].equals(comparisonRow[j])) {
//                        comparisonList.add(currentRow);
////                        return;
//                        System.out.println(currentRow[j] + " - " + j);
//                        System.out.println(comparisonRow[j] + " - " + j);
//                        System.out.println(!currentRow[j].equals(comparisonRow[j]));
//                    }
//
////                }
//            }
//        }
//
//        rows.remove(0);
//        if (!rows.isEmpty()) {
//            compareTables(rows);
//        } else {
//            System.out.println("End");
//        }
//    }

    private static void compareTables() {
        String[][] copyArray;
        String[] currentRow;
        String[] comparisonRow;
        int countRow = 0;
        int count2 = 0;
        String cell;
        boolean isFirst;
        ListIterator<String[]> iterator = listOfCellsOfSecondTable.listIterator();
        boolean rowNotMatch = false;

        if (arrayOfCellsOfFirstTable.length - arrayOfCellsOfSecondTable.length >= 0) {
            copyArray = new String[arrayOfCellsOfFirstTable.length][arrayOfCellsOfFirstTable[0].length];
            System.arraycopy(arrayOfCellsOfSecondTable, 0, copyArray, 0, arrayOfCellsOfSecondTable.length);
            isFirst = false;
        } else {
            copyArray = new String[arrayOfCellsOfSecondTable.length][arrayOfCellsOfSecondTable[0].length];
            System.arraycopy(arrayOfCellsOfFirstTable, 0, copyArray, 0, arrayOfCellsOfFirstTable.length);
            isFirst = true;
        }

//        System.out.println("length: " + copyArray.length);

//        for (int i = 0; i < copyArray.length; i++) {
//            for (int j = 0; j < copyArray[i].length; j++) {
//                System.out.println(copyArray[i][j]);
//            }
//            System.out.println();
//        }

        for (int i = 0; i < copyArray.length; i++) {
//            System.out.println("i: " + i);
//            System.out.println("copyArray.length: " + copyArray.length);
            for (int j = 0; j < copyArray.length; j++) {
                currentRow = copyArray[j];
//                System.out.println("j: " + j);
//                for (String s : currentRow) {
//                    System.out.println(s);
//                }

                if (isFirst) {
                    comparisonRow = arrayOfCellsOfSecondTable[i];
                } else {
                    comparisonRow = arrayOfCellsOfFirstTable[i];
                }
//                for (String s : comparisonRow) {
//                    System.out.println(s);
//                }
                for (int k = 0; k < currentRow.length; k++) {
//                    System.out.println("k: " + k);
                    if (currentRow[k] == null) {
                        currentRow[k] = "";
                    }
                    if (!currentRow[k].equals(comparisonRow[k])) {
//                        System.out.println("count: " + countRow);
//                        System.out.println(currentRow[k] + " " + comparisonRow[k]);
                        System.out.println(currentRow[k] + " - " + comparisonRow[k]);
                        countRow++;


                    } else {
                        countRow--;
                    }

                    if (countRow == 8) {
                        count2++;
                        rowNotMatch = true;
                        nonMatch.add(true);
                        countRow = 0;
                    }

                }
                if (rowNotMatch) {

                }
//                System.out.println(count2);
            }
//            System.out.println("i: " + i);
        }
    }

//    private static void compareTables() {
//        String[] currentRow;
//        String[] comparisonRow;
//        String cell;
//        ListIterator<String[]> iterator = listOfCellsOfSecondTable.listIterator();
//
//        for (int i = 0; i < listOfCellsOfFirstTable.size(); i++) {
//            currentRow = listOfCellsOfFirstTable.get(i);
//            for (int j = 0; j < currentRow.length; j++) {
//                while (iterator.hasNext()) {
//                    comparisonRow = iterator.next();
//                    if (Arrays.equals(currentRow, comparisonRow)) {
//                        comparisonList.add(currentRow);
//                    }
//                }
//            }
//        }
//    }

//    private static void compareTables(List<String[]> rows) {
//        String[] comparisonRow = rows.get(0);
//        String[] currentRow;
//        String cell;
//
//        for (int i = 0; i < listOfCellsOfSecondTable.size(); i++) {
//            currentRow = listOfCellsOfSecondTable.get(i);
//            for (int j = 0; j < currentRow.length; j++) {
//                cell = currentRow[j];
//                if (j != 0 && j!= 5 && j != 6) {
//                    if (currentRow[j].equals(comparisonRow[j])) {
//                        comparisonList.add(currentRow);
//                        return;
//                    }
//
//                }
//            }
//        }
//
//        rows.remove(0);
//        if (!rows.isEmpty()) {
//            compareTables(rows);
//        } else {
//            System.out.println("End");
//        }
//    }
}
