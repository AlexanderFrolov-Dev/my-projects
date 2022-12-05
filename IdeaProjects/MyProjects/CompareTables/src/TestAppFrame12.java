import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class TestAppFrame12 extends JFrame {
    private final JPanel mainPane;
    private JPanel panelForSourceTables;
    private JPanel paneForFinalTable;
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

    // TODO: 28.07.2022 Написать методы для проверки .csv и наличия разделителя ";".
    // TODO: 04.08.2022 Написать проверку на то, в какой таблице больше строк.

    public TestAppFrame12() throws HeadlessException {
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

            if (firstTable.getColumnCount() > 0 && firstTable.getRowCount() > 0) {
                stateOfFirstTable.setText(FIRST_FILE_IS_SELECTED);
            }

            mainPane.add(panelForSourceTables);

            revalidate();
            repaint();
        });

        secondPath.addActionListener(e -> {
            try {
                secondTable = createSourceTable(secondPath.getText());
            } catch (IOException ex) {
                stateOfSecondTable.setText("Не удалось создать вторую таблицу");
            }

            secondTable.setPreferredScrollableViewportSize(new Dimension(450, 200));
            secondTableScrollPane = new JScrollPane(secondTable);
            panelForSourceTables.add(secondTableScrollPane);

            if (secondTable.getColumnCount() > 0 && secondTable.getRowCount() > 0) {
                stateOfSecondTable.setText(SECOND_FILE_IS_SELECTED);
            }

            mainPane.add(panelForSourceTables);

            revalidate();
            repaint();
        });

        COMPARE_TABLES.addActionListener(e -> {

            List<String> listFirstTableRows;
            List<String> listSecondTableRows;

            try {
                listFirstTableRows = List.of(readFromFile(new File(firstPath.getText())).split("\n"));
                listSecondTableRows = List.of(readFromFile(new File(secondPath.getText())).split("\n"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            List<String> notMatches = new ArrayList<>();

            for (String s : listFirstTableRows) {
                if (listSecondTableRows.stream().noneMatch(s::equals)) {
                    notMatches.add(s);
                }
            }

            String[] finalTableHeader = createColumnHeadersOfSourceTable(fullText);

            String[] rowsOfFinalTable = notMatches.toArray(String[]::new);
            String[][] finalTableCells = new String[rowsOfFinalTable.length][rowsOfFinalTable[0].split(";").length];

            for (int i = 0; i < rowsOfFinalTable.length; i++) {
                String row = rowsOfFinalTable[i];
                String[] cells = row.split(";");
                System.arraycopy(cells, 0, finalTableCells[i], 0, cells.length);
            }

            JTable finalTable = new JTable(finalTableCells, finalTableHeader);
            finalTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
            JScrollPane scrollPaneForFinalTable = new JScrollPane(finalTable);

            mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
            paneForFinalTable = new JPanel();
            paneForFinalTable.setMaximumSize(new Dimension(mainPane.getWidth(), 200));
            paneForFinalTable.setBackground(Color.MAGENTA);
            paneForFinalTable.add(scrollPaneForFinalTable);
            mainPane.add(paneForFinalTable);

            mainPane.repaint();

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

    private String[] splitTextIntoLines(String text) {
        return text.split("\n");
    }
}