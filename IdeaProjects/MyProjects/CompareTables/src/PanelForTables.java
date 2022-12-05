import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class PanelForTables extends JTable {
    JScrollPane scrollPaneForFirstTable;
    JScrollPane scrollPaneForSecondTable;
    JTable firstTable;
    JTable secondTable;
    String firstPath;
    String secondPath;

    public PanelForTables(String firstPath, String secondPath) throws IOException {
        this.firstPath = firstPath;
        this.secondPath = secondPath;

        setMaximumSize(new Dimension(1000, 485));
        setBackground(Color.RED);

        firstTable = createTable(firstPath);
        secondTable = createTable(secondPath);

        scrollPaneForFirstTable = new JScrollPane(firstTable);
        scrollPaneForSecondTable = new JScrollPane(secondTable);
        add(scrollPaneForFirstTable);
        add(scrollPaneForFirstTable);
    }

    private JTable createTable(String path) throws IOException {
        String row;
        String[] header;
        String[] cellsArray;
        String[] rows = readFromFile(new File(path)).split("\n");
        String[] firstRow = rows[0].split(";");
        String[] rowsWithoutHeader = Stream.of(rows).skip(1).toArray(String[]::new);
        String[][] cells = new String[rowsWithoutHeader.length][firstRow.length];
        header = rows[0].split(";");

        for (int i = 0; i < rowsWithoutHeader.length; i++) {
            row = rowsWithoutHeader[i];
            cellsArray = row.split(";");
            for (int j = 0; j < cellsArray.length; j++) {
                cells[i][j] = cellsArray[j];
            }
        }

        return new JTable(cells, header);
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
