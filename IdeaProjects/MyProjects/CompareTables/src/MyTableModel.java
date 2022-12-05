import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class MyTableModel extends AbstractTableModel {
    String path;
    String row;
    String[] cellsArray;
    String[] rows = readFromFile(new File(path)).split("\n");
    String[] header = rows[0].split(";");
    String[] rowsWithoutHeader = Arrays.stream(rows).skip(1).toArray(String[]::new);
    String[][] cells = new String[rowsWithoutHeader.length][header.length];

    public MyTableModel(String path) throws IOException {
        this.path = path;

        for (int i = 0; i < rowsWithoutHeader.length; i++) {
            row = rowsWithoutHeader[i];
            cellsArray = row.split(";");
            for (int j = 0; j < cellsArray.length; j++) {
                cells[i][j] = cellsArray[j];
            }
        }
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    private String readFromFile(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file);
        StringBuilder lineBuilder = new StringBuilder();
        String fullText = "";
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

        return fullText;
    }
}
