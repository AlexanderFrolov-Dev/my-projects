import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class TestAppFrame10 extends JFrame {
    private final JPanel mainPane;
    private JPanel panelForSourceTables;
    private JPanel paneForFinalTable;
    private JScrollPane firstTableScrollPane;
    private JScrollPane secondTableScrollPane;
    private final static JLabel ADD_FIRST_TABLE = new JLabel("Введите путь к первому файлу и нажмите <Enter>");
    private final static JLabel ADD_SECOND_TABLE = new JLabel("Введите путь ко второму файлу и нажмите <Enter>");
    private final JTextField firstPath;
    private final JTextField secondPath;
    private JLabel showText;
    private final static JButton COMPARE_TABLES = new JButton("Сравнить файлы");
    private final JTextArea messages;
    private JScrollPane scrollPane;
    private final static String FIRST_FILE_IS_SELECTED = "Первый файл выбран. Введите путь ко второму файлу.";
    private JTable firstTable;
    private JTable secondTable;
    private String[] rowsOfFirstTable;
    private String[] rowsOfSecondTable;
    private String[] headerOfFirstTable;
    private String[][] cellsOfFirstTable;
    private String[] headerOfSecondTable;
    private String[][] cellsOfSecondTable;

    // TODO: 28.07.2022 Написать методы для проверки .csv и наличия разделителя ";".

    public TestAppFrame10() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPane = new JPanel();
        firstPath = new JTextField();
        secondPath = new JTextField();
        showText = new JLabel();
        messages = new JTextArea();

        ADD_FIRST_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        showText.setAlignmentX(Component.CENTER_ALIGNMENT);
        ADD_SECOND_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        COMPARE_TABLES.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500, 20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500, 20));

        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 10)));  // Установить отступы.
        mainPane.add(ADD_FIRST_TABLE);
        mainPane.add(firstPath);
        mainPane.add(showText);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 10)));
        mainPane.add(ADD_SECOND_TABLE);
        mainPane.add(secondPath);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        mainPane.add(COMPARE_TABLES);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMinimumSize(new Dimension(500, 100));
        scrollPane.setMaximumSize(new Dimension(500, 100));
        scrollPane.setBackground(Color.MAGENTA);
        mainPane.add(scrollPane);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    headerOfFirstTable = createColumnHeadersOfSourceTable(readFromFile(new File(firstPath.getText())));
                    cellsOfFirstTable = createArrayOfCellsOfSourceTable(readFromFile(new File(firstPath.getText())));
                    firstTable = new JTable(cellsOfFirstTable, headerOfFirstTable);
                } catch (IOException ex) {
                    messages.setText("Не удалось создать первую таблицу");
                }

                mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
                panelForSourceTables = new JPanel();
                firstTable.setPreferredScrollableViewportSize(new Dimension(450, 300));
                panelForSourceTables.setBackground(Color.MAGENTA);
                firstTableScrollPane = new JScrollPane(firstTable);
                panelForSourceTables.setMaximumSize(new Dimension(mainPane.getWidth(), 450));
                panelForSourceTables.add(firstTableScrollPane);

                mainPane.add(panelForSourceTables);

                revalidate();
                repaint();
            }
        });

        secondPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    secondTable = createSourceTable(secondPath.getText());
//                    headerOfSecondTable = createColumnHeadersOfSourceTable(readFromFile(new File(firstPath.getText())));
//                    cellsOfSecondTable = createArrayOfCellsOfSourceTable(readFromFile(new File(secondPath.getText())));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                secondTable.setPreferredScrollableViewportSize(new Dimension(450, 300));
                secondTableScrollPane = new JScrollPane(secondTable);
                panelForSourceTables.add(secondTableScrollPane);

                mainPane.add(panelForSourceTables);

                revalidate();
                repaint();
            }
        });

        COMPARE_TABLES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    rowsOfFirstTable = readFromFile(new File(firstPath.getText())).split(";");
                    rowsOfSecondTable = readFromFile(new File(secondPath.getText())).split(";");
                } catch (IOException ex) {
                    messages.setText("Не удалось создать первую таблицу");
                }

                List<String> listFirstTableRows;
                List<String> listSecondTableRows;

                try {
                    listFirstTableRows = List.of(readFromFile(new File(firstPath.getText())).split("\n"));
                    listSecondTableRows = List.of(readFromFile(new File(secondPath.getText())).split("\n"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                int count = 0;
                List<String> notMatches = new ArrayList<>();

                for (String s : listFirstTableRows) {
                    if (listSecondTableRows.stream().noneMatch(s::equals)) {
                        count++;
                        notMatches.add(s);
                    }
                }

                System.out.println("Count of notMatches: " + count);
                for (String s : notMatches) {
                    System.out.println(s);
                }

                String[] finalTableHeader = headerOfFirstTable;
                StringBuilder stringBuilder = new StringBuilder();

                for (String s : notMatches) {
                    stringBuilder.append(s);
                }
                System.out.println();
                System.out.println("notMatches: " + stringBuilder);

                for (String s : finalTableHeader) {
                    System.out.println(s);
                }

                String[] rowsOfFinalTable = notMatches.toArray(String[]::new);
                String[][] finalTableCells = new String[rowsOfFinalTable.length][rowsOfFinalTable[0].split(";").length];

                for (int i = 0; i < rowsOfFinalTable.length; i++) {
                    String row = rowsOfFinalTable[i];
                    String[] cells = row.split(";");
                    for (int j = 0; j < cells.length; j++) {
                        finalTableCells[i][j] = cells[j];
                    }
                }

                JTable finalTable = new JTable(finalTableCells, finalTableHeader);
                finalTable.setPreferredScrollableViewportSize(finalTable.getPreferredSize());
                JScrollPane scrollPaneForFinalTable = new JScrollPane(finalTable);

                mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 5)));
                paneForFinalTable = new JPanel();
                paneForFinalTable.setMaximumSize(new Dimension(600, 200));
                paneForFinalTable.setBackground(Color.MAGENTA);
                paneForFinalTable.add(scrollPaneForFinalTable);
                scrollPaneForFinalTable.setMaximumSize(paneForFinalTable.getSize());
                mainPane.add(paneForFinalTable);

                mainPane.repaint();

                revalidate();
                repaint();
            }
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
            for (int j = 0; j < cellsArray.length; j++) {
                cells[i][j] = cellsArray[j];
            }
        }

        return cells;
    }

    private String[] splitTextIntoLines(String text) {
        return text.split("\n");
    }
}