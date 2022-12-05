import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class TestAppFrame13 extends JFrame {
    private final JPanel mainPane;
    private JPanel panelForSourceTables;
    private JPanel paneForComparisonTable;
    private JScrollPane firstTableScrollPane;
    private JScrollPane secondTableScrollPane;
    private final static JLabel ADD_FIRST_TABLE = new JLabel("Введите путь к первому файлу и нажмите <Enter>");
    private final static JLabel ADD_SECOND_TABLE = new JLabel("Введите путь ко второму файлу и нажмите <Enter>");
    private final JTextField firstPath;
    private final JTextField secondPath;
    private final JLabel stateOfFirstTable;
    private final JLabel stateOfSecondTable;
    private final static JButton COMPARE_TABLES = new JButton("Сравнить файлы");
    private final static String FIRST_FILE_IS_SELECTED = "Первый файл выбран. Введите путь ко второму файлу.";
    private final static String SECOND_FILE_IS_SELECTED = "Второй файл выбран.";
    private JTable firstTable;
    private JTable secondTable;
    private String fullText;
    private String fullTextOfFirstTable;
    private String fullTextOfSecondTable;
    private boolean hasFirstTable;
    private boolean hasSecondTable;
    private boolean hasComparisonTable;

    // TODO: 28.07.2022 Написать методы для проверки .csv и наличия разделителя ";".
    // TODO: 04.08.2022 Написать проверку на то, в какой таблице больше строк.

    public TestAppFrame13() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPane = new JPanel();
        firstPath = new JTextField();
        secondPath = new JTextField();
        stateOfFirstTable = new JLabel();
        stateOfSecondTable = new JLabel();
        JTextArea messages = new JTextArea();
        JScrollPane scrollPane;

        ADD_FIRST_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        stateOfFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        ADD_SECOND_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        stateOfSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        COMPARE_TABLES.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500, 20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500, 20));

        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 20)));  // Установить отступы.
        mainPane.add(ADD_FIRST_TABLE);
        mainPane.add(firstPath);
        mainPane.add(stateOfFirstTable);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 20)));
        mainPane.add(ADD_SECOND_TABLE);
        mainPane.add(secondPath);
        mainPane.add(stateOfSecondTable);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        mainPane.add(COMPARE_TABLES);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMinimumSize(new Dimension(500, 100));
        scrollPane.setMaximumSize(new Dimension(500, 100));
        scrollPane.setBackground(Color.MAGENTA);
        mainPane.add(scrollPane);

        firstPath.addActionListener(e -> {
            try {
                firstTable = createSourceTable(firstPath.getText());
                fullTextOfFirstTable = fullText;
            } catch (IOException ex) {
                stateOfFirstTable.setText("Не удалось создать первую таблицу");
            }

            mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));
            panelForSourceTables = new JPanel();
            firstTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
            panelForSourceTables.setBackground(Color.MAGENTA);
            firstTableScrollPane = new JScrollPane(firstTable);
            panelForSourceTables.setMaximumSize(new Dimension(mainPane.getWidth(), 300));
            panelForSourceTables.add(firstTableScrollPane);

            if (isNotEmpty(firstTable)) {
                stateOfFirstTable.setText(FIRST_FILE_IS_SELECTED);
                hasFirstTable = true;
            }

            mainPane.add(panelForSourceTables);

            revalidate();
            repaint();
        });

        secondPath.addActionListener(e -> {
            try {
                secondTable = createSourceTable(secondPath.getText());
                fullTextOfSecondTable = fullText;
            } catch (IOException ex) {
                stateOfSecondTable.setText("Не удалось создать вторую таблицу");
            }

            secondTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
            secondTableScrollPane = new JScrollPane(secondTable);
            panelForSourceTables.add(secondTableScrollPane);

            if (isNotEmpty(secondTable)) {
                stateOfSecondTable.setText(SECOND_FILE_IS_SELECTED);
                hasSecondTable = true;
            }

            mainPane.add(panelForSourceTables);

            revalidate();
            repaint();
        });

        COMPARE_TABLES.addActionListener(e -> {
            JTable comparisonTable = createComparisonTable();
            comparisonTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
            JScrollPane scrollPaneForComparisonTable = new JScrollPane(comparisonTable);

            mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
            paneForComparisonTable = new JPanel();
            paneForComparisonTable.setMaximumSize(new Dimension(mainPane.getWidth(), 200));
            paneForComparisonTable.setBackground(Color.MAGENTA);
            paneForComparisonTable.add(scrollPaneForComparisonTable);
            mainPane.add(paneForComparisonTable);

            if (isNotEmpty(comparisonTable)) {
                hasComparisonTable = true;
            } else {
                messages.setText("Не удалось создать сводную таблицу");
            }

            revalidate();
            repaint();
        });

        mainPane.setBackground(Color.MAGENTA);
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        add(mainPane);
        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.
        setResizable(true);
        setVisible(true);
    }

    private JTable createSourceTable(String path) throws IOException {
        String[] header = createColumnHeadersOfSourceTable(readFromFile(new File(path)));
        String[][] cells = createArrayOfCellsOfSourceTable(readFromFile(new File(path)));

        return new JTable(cells, header);
    }

    private JTable createComparisonTable() {
        List<String> listFirstTableRows = List.of(splitTextIntoLines(fullTextOfFirstTable));
        List<String> listSecondTableRows = List.of(splitTextIntoLines(fullTextOfSecondTable));

        List<String> notMatches = new ArrayList<>();

        for (String s : listFirstTableRows) {
            if (listSecondTableRows.stream().noneMatch(s::equals)) {
                notMatches.add(s);
            }
        }

        String[] headerOfComparisonTable = createColumnHeadersOfSourceTable(fullText);
        String[] rowsOfComparisonTable = notMatches.toArray(String[]::new);
        String[][] cellsOfComparisonTable = createArrayOfCellsOfComparisonTable(rowsOfComparisonTable);

        return new JTable(cellsOfComparisonTable, headerOfComparisonTable);
    }

    private String readFromFile(File file) throws IOException {
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

    private String[] createColumnHeadersOfSourceTable(String text) {
        return splitTextIntoLines(text)[0].split(";");
    }

    private String[][] createArrayOfCellsOfSourceTable(String text) {
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

    private String[][] createArrayOfCellsOfComparisonTable(String[] rowsOfComparisonTable) {
        String[][] comparisonTableCells = new String[rowsOfComparisonTable.length][rowsOfComparisonTable[0].split(";").length];

        for (int i = 0; i < rowsOfComparisonTable.length; i++) {
            String row = rowsOfComparisonTable[i];
            String[] cells = row.split(";");
            System.arraycopy(cells, 0, comparisonTableCells[i], 0, cells.length);
        }
        return comparisonTableCells;
    }

    private String[] splitTextIntoLines(String text) {
        return text.split("\n");
    }

    private String[] splitRowIntoCells(String row) {
        return row.split(";");
    }

    private boolean isNotEmpty(JTable table) {
        return table.getRowCount() > 0 && table.getColumnCount() > 0;
    }
}