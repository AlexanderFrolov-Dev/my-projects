import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestAppFrameX extends JFrame {
    private final JPanel mainPane;
    private JPanel panelForSourceTables;
    private JPanel paneForComparisonTable;
    private JScrollPane firstTableScrollPane;
    private JScrollPane secondTableScrollPane;
    private final static JLabel ADD_FIRST_TABLE = new JLabel("Введите путь к первому файлу и нажмите <Enter>");
    private final static JLabel ADD_SECOND_TABLE = new JLabel("Введите путь ко второму файлу и нажмите <Enter>");
    private final JTextField firstPath;
    private final JTextField secondPath;
    private final JTextArea messages;
    private final JLabel stateOfFirstTable;
    private final JLabel stateOfSecondTable;
    private final static JLabel COMPARE_TABLES_LABEL = new JLabel("Сравнить таблицы");
    private final static JRadioButton ACROSS_ALL_COLUMNS = new JRadioButton("по всем столбцам");
    private final static JRadioButton BY_INDIVIDUAL_COLUMNS = new JRadioButton("по отдельным столбцам");
    private final static JButton CONFIRM_SELECTION_COLUMNS = new JButton("Подтвердить выбор столбцов");
    private final static JLabel ENTER_COLUMN_NUMBERS = new JLabel("Выберите с помощью нажатия CTRL ячейки в" +
            " столбцах, которые требуется сравнить");
    private final static JButton COMPARE_TABLES = new JButton("Сравнить таблицы");
    private final static String EMPTY_STRING = "Вы пытаетесь вести пустую строку";
    private final static String FILE_EXTENSION = "Введенный путь не является путем к файлу с расширением .csv";
    private JTable firstTable;
    private JTable secondTable;
    private JTable comparisonTable;
    private final Color lime = new Color(77, 255, 77);
    private final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    private String fullText;
    private String fullTextOfFirstTable;
    private String fullTextOfSecondTable;
    private boolean hasFirstTable;
    private boolean hasSecondTable;
    private boolean hasComparisonTable;
    private int numberOfFirstTables = 0;
    private int numberOfSecondTables = 0;
    private int numberOfComparisonTables = 0;
    private String[] arrayOfRowsOfFirstTable;
    private String[] arrayOfRowsOfFirstTableWithoutHeader;
    private String[] arrayOfRowsOfSecondTableWithoutHeader;
    private String[] arrayOfRowsOfSecondTable;
    private String[][] arrayOfCellsOfFirstTable;
    private String[][] arrayOfCellsOfSecondTable;
    private int[] arrayOfColumnIndexesForComparison;
    private List<Integer> columnIndexesForComparison;
    private StringBuilder selectedColumns;
    private List<String> listOfRowsOfFirstTableByIndividualColumns;
    private List<String> listOfRowsOfSecondTableByIndividualColumns;
    private Path firstFilePath;
    private Path secondFilePath;

    public TestAppFrameX() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: 10.08.2022 Ввести всплывающие подсказки-описание для элементов экрана.
        // TODO: 16.08.2022 Написать проводник для выбора файлов.
        mainPane = new JPanel();
        firstPath = new JTextField();
        secondPath = new JTextField();
        stateOfFirstTable = new JLabel();
        stateOfSecondTable = new JLabel();
        messages = new JTextArea();
        JScrollPane scrollPane;
        final JButton reset = new JButton("Сбросить таблицы");
        JPanel panelForJRadioButton = new JPanel();
        ButtonGroup bg = new ButtonGroup();

        ADD_FIRST_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        stateOfFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        ADD_SECOND_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        stateOfSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        COMPARE_TABLES_LABEL.setAlignmentX(Component.CENTER_ALIGNMENT);
        ENTER_COLUMN_NUMBERS.setAlignmentX(Component.CENTER_ALIGNMENT);
        CONFIRM_SELECTION_COLUMNS.setAlignmentX(Component.CENTER_ALIGNMENT);
        COMPARE_TABLES.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500, 20));
        secondPath.setMaximumSize(new Dimension(500, 20));

        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 10)));
        mainPane.add(ADD_FIRST_TABLE);
        mainPane.add(firstPath);
        mainPane.add(stateOfFirstTable);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 10)));
        mainPane.add(ADD_SECOND_TABLE);
        mainPane.add(secondPath);
        mainPane.add(stateOfSecondTable);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        mainPane.add(COMPARE_TABLES_LABEL);
        bg.add(ACROSS_ALL_COLUMNS);
        bg.add(BY_INDIVIDUAL_COLUMNS);
        panelForJRadioButton.add(ACROSS_ALL_COLUMNS);
        panelForJRadioButton.add(BY_INDIVIDUAL_COLUMNS);
        panelForJRadioButton.setBackground(lime);
        ACROSS_ALL_COLUMNS.setBackground(lime);
        BY_INDIVIDUAL_COLUMNS.setBackground(lime);
        mainPane.add(panelForJRadioButton);
        panelForJRadioButton.setMaximumSize(new Dimension(320, 30));
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        mainPane.add(ENTER_COLUMN_NUMBERS);
        ENTER_COLUMN_NUMBERS.setVisible(false);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        mainPane.add(CONFIRM_SELECTION_COLUMNS);
        CONFIRM_SELECTION_COLUMNS.setVisible(false);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        mainPane.add(COMPARE_TABLES);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMinimumSize(new Dimension(500, 100));
        scrollPane.setMaximumSize(new Dimension(500, 100));
        mainPane.add(scrollPane);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        mainPane.add(reset);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 20)));

        firstPath.addActionListener(e -> {
            firstFilePath = Path.of(firstPath.getText());

            if (firstPath.getText().equals("")) {
                stateOfFirstTable.setText(EMPTY_STRING);
                return;
            } else if (!firstFilePath.toString().endsWith(".csv")) {
                messages.setText(FILE_EXTENSION);
            }

            if (numberOfFirstTables == 0) {
                try {
                    firstTable = createSourceTable(firstPath.getText().trim());
                } catch (IOException ex) {
                    stateOfFirstTable.setText("Не удалось создать первую таблицу");
                }

                fullTextOfFirstTable = fullText;
                arrayOfRowsOfFirstTable = splitTextIntoLines(fullTextOfFirstTable);
                createColumnHeadersOfSourceTable(fullTextOfFirstTable);
                arrayOfCellsOfFirstTable = createArrayOfCellsOfSourceTable(fullTextOfFirstTable);

                panelForSourceTables = new JPanel();
                panelForSourceTables.setMaximumSize(new Dimension(mainPane.getWidth(), 200));
                panelForSourceTables.setBackground(lime);
                firstTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
                firstTableScrollPane = new JScrollPane(firstTable);
                panelForSourceTables.add(firstTableScrollPane);

                if (isNotEmpty(firstTable)) {
                    stateOfFirstTable.setText("Первый файл выбран. Введите путь ко второму файлу.");
                    hasFirstTable = true;
                }

                mainPane.add(panelForSourceTables);
                revalidate();
                repaint();
            } else {
                stateOfFirstTable.setText("Первая таблица уже сформирована");
            }

            for (String[] strings : arrayOfCellsOfFirstTable) {
                for (String string : strings) {
                    System.out.println(string);
                }
            }

            if (hasFirstTable) {
                firstPath.setEditable(false);
                numberOfFirstTables++;
            }
        });

        secondPath.addActionListener(e -> {
            secondFilePath = Path.of(secondPath.getText());

            if (secondPath.getText().equals("")) {
                stateOfSecondTable.setText(EMPTY_STRING);
                return;
            } else if (!secondFilePath.toString().endsWith(".csv")) {
                messages.setText(FILE_EXTENSION);
            }

            if (!hasFirstTable) {
                stateOfSecondTable.setText("Ещё не создана первая таблица. Сначала создайте первую таблицу");
                return;
            }

            if (numberOfSecondTables == 0) {
                try {
                    secondTable = createSourceTable(secondPath.getText().trim());
                } catch (IOException ex) {
                    stateOfSecondTable.setText("Не удалось создать вторую таблицу");
                }

                fullTextOfSecondTable = fullText;
                arrayOfRowsOfSecondTable = splitTextIntoLines(fullTextOfSecondTable);
                createColumnHeadersOfSourceTable(fullTextOfSecondTable);
                arrayOfCellsOfSecondTable = createArrayOfCellsOfSourceTable(fullTextOfSecondTable);

                secondTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
                secondTableScrollPane = new JScrollPane(secondTable);
                panelForSourceTables.add(secondTableScrollPane);

                if (isNotEmpty(secondTable)) {
                    stateOfSecondTable.setText("Второй файл выбран.");
                    hasSecondTable = true;
                }

                revalidate();
                repaint();
            } else {
                stateOfSecondTable.setText("Вторая таблица уже сформирована");
            }

            if (hasSecondTable) {
                secondPath.setEditable(false);
                numberOfSecondTables++;
            }
        });

        COMPARE_TABLES.addActionListener(e -> {
            if (!tablesExist()) {
                messages.setText("Не все таблицы сформированы");
            } else if (bg.getSelection() == null) {
                messages.setText("Не выбран способ сравнения таблиц");
            }

            if (numberOfComparisonTables == 0 && tablesExist() && !(bg.getSelection() == null)) {
                if (bg.isSelected(ACROSS_ALL_COLUMNS.getModel())) {
                    comparisonTable = createComparisonTableForAllColumns();
                    comparisonTable.setPreferredScrollableViewportSize(new Dimension(500, 250));
                    JScrollPane scrollPaneForComparisonTable = new JScrollPane(comparisonTable);

                    paneForComparisonTable = new JPanel();
                    paneForComparisonTable.setMaximumSize(new Dimension(mainPane.getWidth(), 250));
                    paneForComparisonTable.setBackground(lime);
                    paneForComparisonTable.add(scrollPaneForComparisonTable);
                    mainPane.add(paneForComparisonTable);
                } else if (bg.isSelected(BY_INDIVIDUAL_COLUMNS.getModel())) {
                    listOfRowsOfFirstTableByIndividualColumns = createListOfRowsOfTableByIndividualColumns(arrayOfCellsOfFirstTable);
                    listOfRowsOfSecondTableByIndividualColumns = createListOfRowsOfTableByIndividualColumns(arrayOfCellsOfSecondTable);

                    comparisonTable = createComparisonTableByIndividualColumns();
                    comparisonTable.setPreferredScrollableViewportSize(new Dimension(500, 250));
                    JScrollPane scrollPaneForComparisonTable = new JScrollPane(comparisonTable);

                    paneForComparisonTable = new JPanel();
                    paneForComparisonTable.setMaximumSize(new Dimension(mainPane.getWidth(), 250));
                    paneForComparisonTable.setBackground(lime);
                    paneForComparisonTable.add(scrollPaneForComparisonTable);
                    mainPane.add(paneForComparisonTable);

                    boolean checkComparisonTable = comparisonTable == null;
                    System.out.println("check comparisonTable: " + checkComparisonTable);
                    boolean checkPaneForComparisonTable = paneForComparisonTable == null;
                    System.out.println("check paneForComparisonTable: " + checkPaneForComparisonTable);
                }

                if (isNotEmpty(comparisonTable)) {
                    hasComparisonTable = true;
                    System.out.println("isNotEmpty(comparisonTable) - " + isNotEmpty(comparisonTable));
                } else {
                    messages.setText("Не удалось создать сводную таблицу");
                }

            } else if (hasComparisonTable) {
                numberOfComparisonTables++;
                messages.setText("Сводная таблица уже сформирована");
            }

            revalidate();
            repaint();
        });

        BY_INDIVIDUAL_COLUMNS.addActionListener(e -> {
            System.out.println("BY_INDIVIDUAL_COLUMNS start");

            ENTER_COLUMN_NUMBERS.setVisible(true);
            CONFIRM_SELECTION_COLUMNS.setVisible(true);

            if (firstTable != null) {
                firstTable.setColumnSelectionAllowed(true);
            } else {
                System.out.println("Не сформирована первая таблица");
            }

            revalidate();
            repaint();
        });

        ACROSS_ALL_COLUMNS.addActionListener(e -> {
            System.out.println("ACROSS_ALL_COLUMNS start");

            ENTER_COLUMN_NUMBERS.setVisible(false);
            CONFIRM_SELECTION_COLUMNS.setVisible(false);

            revalidate();
            repaint();
        });

        CONFIRM_SELECTION_COLUMNS.addActionListener(e -> {
            columnIndexesForComparison = new ArrayList<>();
            selectedColumns = new StringBuilder("Выбраны следующие столбцы для сравнения: ");

            selectedColumns.append("\n");

            if (firstTable == null) {
                messages.setText("Не сформирована первая таблица");
            }

            arrayOfColumnIndexesForComparison = firstTable.getSelectedColumns();

            if (arrayOfColumnIndexesForComparison != null) {
                firstTable.getColumnModel().setColumnSelectionAllowed(true);
                for (int i : arrayOfColumnIndexesForComparison) {
                    int numberOfColumn;
                    numberOfColumn = 1 + i;
                    columnIndexesForComparison.add(i);
                    selectedColumns.append(numberOfColumn)
                            .append(" - ")
                            .append(firstTable.getColumnName(i))
                            .append("\n");

                    renderer.setBackground(Color.PINK);
                    firstTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    secondTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    messages.setText(selectedColumns.toString());
                }
            } else {
                messages.setText("Не выбраны столбцы для сравнения");
            }

            System.out.println();
            for (int i : columnIndexesForComparison) {
                System.out.println(i);
            }

            revalidate();
            repaint();
        });

        reset.addActionListener(e -> {
            if (panelForSourceTables != null) {
                mainPane.remove(panelForSourceTables);
                panelForSourceTables = null;
                firstTable = null;
                secondTable = null;
                hasFirstTable = false;
                hasSecondTable = false;
                numberOfFirstTables = 0;
                numberOfSecondTables = 0;
                firstPath.setEditable(true);
                secondPath.setEditable(true);
            }

            if (paneForComparisonTable != null) {
                mainPane.remove(paneForComparisonTable);
                comparisonTable = null;
                hasComparisonTable = false;
                numberOfComparisonTables = 0;
            }

            messages.setText("");
            ENTER_COLUMN_NUMBERS.setVisible(false);
            CONFIRM_SELECTION_COLUMNS.setVisible(false);
            bg.clearSelection();

            revalidate();
            repaint();
        });

        messages.setText("");
        mainPane.setBackground(lime);
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        add(mainPane);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private JTable createSourceTable(String path) throws IOException {
        System.out.println("createSourceTable() start");

        String[] header = createColumnHeadersOfSourceTable(readFromFile(new File(path)));
        String[][] cells = createArrayOfCellsOfSourceTable(readFromFile(new File(path)));

        return new JTable(cells, header);
    }

    private JTable createComparisonTableForAllColumns() {
        List<String> listFirstTableRows = List.of(splitTextIntoLines(fullTextOfFirstTable));
        List<String> listSecondTableRows = List.of(splitTextIntoLines(fullTextOfSecondTable));

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

        return new JTable(cellsOfComparisonTable, headerOfComparisonTable);
    }

    private JTable createComparisonTableByIndividualColumns() {
        arrayOfRowsOfFirstTableWithoutHeader = Arrays.stream(arrayOfRowsOfFirstTable).skip(1).toArray(String[]::new);
        arrayOfRowsOfSecondTableWithoutHeader = Arrays.stream(arrayOfRowsOfSecondTable).skip(1).toArray(String[]::new);
        int countFirstTable = 0;
        int countSecondTable = 0;

        List<String> notMatches = new ArrayList<>();

        for (int i = 0; i < listOfRowsOfFirstTableByIndividualColumns.size(); i++) {
            String currentRow = listOfRowsOfFirstTableByIndividualColumns.get(i);
            System.out.println(countFirstTable + " - currentRow FirstTable: " + currentRow);
            countFirstTable++;
            if (listOfRowsOfSecondTableByIndividualColumns.stream().noneMatch(currentRow::equals)) {
                notMatches.add(arrayOfRowsOfFirstTableWithoutHeader[i]);
                System.out.println(arrayOfRowsOfFirstTableWithoutHeader[i]);
            }
        }

        for (int i = 0; i < listOfRowsOfSecondTableByIndividualColumns.size(); i++) {
            String currentRow = listOfRowsOfSecondTableByIndividualColumns.get(i);
            System.out.println(countSecondTable + " - currentRow SecondTable: " + currentRow);
            countSecondTable++;
            if (listOfRowsOfFirstTableByIndividualColumns.stream().noneMatch(currentRow::equals)) {
                notMatches.add(arrayOfRowsOfSecondTableWithoutHeader[i]);
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

    private boolean isNotEmpty(JTable table) {
        return table.getRowCount() > 0 && table.getColumnCount() > 0;
    }

    private boolean tablesExist() {
        return hasFirstTable && hasSecondTable;
    }

    private List<String> createListOfRowsOfTableByIndividualColumns(String[][] cellsOfTable) {
        StringBuilder resultRow = new StringBuilder();
        List<String> rowsOfTables = new ArrayList<>();
        String[] currentRow;
        String currentCell;

        for (String[] strings : cellsOfTable) {
            currentRow = strings;
            for (int j = 0; j < currentRow.length; j++) {
                currentCell = currentRow[j];
                int columnNumber = j;
                if (columnIndexesForComparison.stream().anyMatch(integer -> integer == columnNumber)) {
                    resultRow.append(currentCell);
                }
            }
            rowsOfTables.add(String.valueOf(resultRow));
            resultRow.setLength(0);
        }

        return rowsOfTables;
    }
}