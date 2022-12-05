import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/*
1. Добавил невозможность введения текста в поле для пути, и запрет на повторное формирование таблицы после того, как
она уже успешно создана.
2. Теперь если не сформирована первая таблица нельзя создать вторую. В случае неверного порядка создания таблиц
выводится соответствующее предупреждение.
3. Также теперь при попытке ввести пустую строку в поле для ввода пути к файлу, выводится соответствующее предупреждение.
4. Добавил кнопку сброса таблиц и прописал логику для неё.
5. Прописал много других проверок, чтобы таблицы заполнялись и удалялись в необходимом порядке.
 */

public class TestAppFrame14 extends JFrame {
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
    private JTable firstTable;
    private JTable secondTable;
    private JTable comparisonTable;
    Color lime = new Color(77, 255, 77);
    private String fullText;
    private String fullTextOfFirstTable;
    private String fullTextOfSecondTable;
    private boolean hasFirstTable;
    private boolean hasSecondTable;
    private boolean hasComparisonTable;
    private int numberOfFirstTables = 0;
    private int numberOfSecondTables = 0;
    private int numberOfComparisonTables = 0;

    // TODO: 28.07.2022 Написать методы для проверки .csv и наличия разделителя ";".
    // TODO: 04.08.2022 Написать проверку на то, в какой таблице больше строк.

    public TestAppFrame14() throws HeadlessException {
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
        final JButton reset = new JButton("Сбросить таблицы");

        ADD_FIRST_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        stateOfFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        ADD_SECOND_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        stateOfSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        COMPARE_TABLES.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 15)));
        mainPane.add(COMPARE_TABLES);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMinimumSize(new Dimension(500, 100));
        scrollPane.setMaximumSize(new Dimension(500, 100));
        mainPane.add(scrollPane);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 15)));
        mainPane.add(reset);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));

        firstPath.addActionListener(e -> {
            if (firstPath.getText().equals("")) {
                stateOfFirstTable.setText("Вы пытаетесь вести пустую строку");
                return;
            }

            if (numberOfFirstTables == 0) {
                try {
                    firstTable = createSourceTable(firstPath.getText());
                    fullTextOfFirstTable = fullText;
                } catch (IOException ex) {
                    stateOfFirstTable.setText("Не удалось создать первую таблицу");
                }

                panelForSourceTables = new JPanel();
                panelForSourceTables.setMaximumSize(new Dimension(mainPane.getWidth(), 300));
                panelForSourceTables.setBackground(Color.CYAN);
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

            if (hasFirstTable) {
                firstPath.setEditable(false);
                numberOfFirstTables++;
            }
        });

        secondPath.addActionListener(e -> {
            if (secondPath.getText().equals("")) {
                stateOfSecondTable.setText("Вы пытаетесь вести пустую строку");
                return;
            }

            if (!hasFirstTable) {
                stateOfSecondTable.setText("Ещё не создана первая таблица. Сначала создайте первую таблицу");
                return;
            }

            if (numberOfSecondTables == 0) {
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
            }

            if (numberOfComparisonTables == 0 && tablesExist()) {
                comparisonTable = createComparisonTable();
                comparisonTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
                JScrollPane scrollPaneForComparisonTable = new JScrollPane(comparisonTable);

                paneForComparisonTable = new JPanel();
                paneForComparisonTable.setMaximumSize(new Dimension(mainPane.getWidth(), 250));
                paneForComparisonTable.setBackground(Color.CYAN);
                paneForComparisonTable.add(scrollPaneForComparisonTable);
                mainPane.add(paneForComparisonTable);

                if (isNotEmpty(comparisonTable)) {
                    hasComparisonTable = true;
                } else {
                    messages.setText("Не удалось создать сводную таблицу");
                }

                revalidate();
                repaint();
            } else if (hasComparisonTable) {
                messages.setText("Сводная таблица уже сформирована");
            }

            if (hasComparisonTable) {
                numberOfComparisonTables++;
            }
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

            revalidate();
            repaint();
        });

        mainPane.setBackground(lime);
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

    private boolean isNotEmpty(JTable table) {
        return table.getRowCount() > 0 && table.getColumnCount() > 0;
    }

    private boolean tablesExist() {
        return hasFirstTable && hasSecondTable;
    }
}