import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test {
    private static StringBuilder sb = new StringBuilder();
    private static String line;
    private static List<String> listCells;

    public static void main(String[] args) throws FileNotFoundException {
        readDataFromFile();
        getCells();

        for (String s : listCells) {
            System.out.println(s);
        }
    }

    private static void readDataFromFile() throws FileNotFoundException {
        int symbol;
        FileInputStream fin = new FileInputStream("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Test_Files\\First_File_CSV.csv");

        try {
            do {
                symbol = fin.read();
                if (symbol != -1) {
                    sb.append((char) symbol);
                }
            } while (symbol != -1);
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла.");
        }

        for (String s : splitIntoLines(String.valueOf(sb))) {
            line = s;
        }
    }

    private static String[] splitIntoLines(String s) {
        return s.split("\n");
    }

    private static String[] splitToCells(String s) {
        return s.split(";");
    }

    public static int getCountOfColumns() {
        return splitToCells(line).length;
    }

    public static int getCountOfLines() {
        return splitIntoLines(String.valueOf(sb)).length;
    }

    private static String[] setIndexesOfCells(int cellsCount) {
        String[] lettersIndexOfCells = new String[cellsCount];
        String appender = "";
        int count = 0;
        char letter;
        char additionalLetter;
        int indexLetter = 0;
        int additionalLetterCount = 0;

        for (int i = 0; i < cellsCount; i++) {
            letter = (char) (indexLetter + 65);
            lettersIndexOfCells[count] = appender + letter;
            indexLetter++;
            count++;
            if ((i != 0) && (i % 25 == 0)) {
                additionalLetter = (char) (additionalLetterCount + 65);
                additionalLetterCount++;
                appender = String.valueOf(additionalLetter);
                indexLetter = 0;
            }
        }
        return lettersIndexOfCells;
    }

    private static List<String> getCells() {
        listCells = new LinkedList<>();
        String s;
        String[] lines = splitIntoLines(String.valueOf(sb));
        for (int i = 0; i < getCountOfLines(); i++) {
            String[] cells = splitToCells(lines[i]);
            for (int j = 0; j < getCountOfColumns(); j++) {
                String[] indexLetters = setIndexesOfCells(getCountOfColumns());
                s = i + "|" + indexLetters[j] + "|" + cells[j];
                listCells.add(s);
            }
        }
        return listCells;
    }
}
