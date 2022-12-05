import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class TestAppFrame5 extends JFrame {
    private JPanel mainPane = new JPanel();
    private final static JLabel ADD_FIRST_TABLE = new JLabel("Введите путь к первому файлу и нажмите <Enter>");
    private final static JLabel ADD_SECOND_TABLE = new JLabel("Введите путь ко второму файлу и нажмите <Enter>");
    private JTextField firstPath = new JTextField();
    private JTextField secondPath = new JTextField();
    private JLabel showText = new JLabel();
    private final static JButton COMPARE_TABLES = new JButton("Сравнить файлы");
    private JTextArea messages = new JTextArea();
    private JScrollPane scrollPane;
    private final static String FIRST_FILE_IS_SELECTED = "Первый файл выбран. Введите путь ко второму файлу.";
    private JTable firstTable;
    private JTable secondTable;
    private boolean firstTableIsFormed = false;
    private boolean secondTableIsFormed = false;
    private List<String[]> listOfRowsOfFirstTable;
    private List<String[]> listOfRowsOfSecondTable;

    private String[] rowsOfFirstTable;
    private String[] rowsOfSecondTable;

    private String[] headerOfFirstTable;
    private String[][] cellsOfFirstTable;
    private String[] headerOfSecondTable;
    private String[][] cellsOfSecondTable;

    // TODO: 28.07.2022 Написать методы для проверки .csv и наличия разделителя ";".

    public TestAppFrame5() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        setContentPane(mainPane);

        ADD_FIRST_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        showText.setAlignmentX(Component.CENTER_ALIGNMENT);
        ADD_SECOND_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        COMPARE_TABLES.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500, 20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500, 20));

        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));  // Установить отступы.
        mainPane.add(ADD_FIRST_TABLE);
        mainPane.add(firstPath);
        mainPane.add(showText);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));
        mainPane.add(ADD_SECOND_TABLE);
        mainPane.add(secondPath);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
        mainPane.add(COMPARE_TABLES);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
//        mainPane.add(messages);
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMaximumSize(new Dimension(500, 100));
        scrollPane.setForeground(Color.CYAN);
        mainPane.add(scrollPane);
//        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
//        firstTable = createTable();
//        scrollForFirstTable = new JScrollPane(firstTable);
//        secondTable = createTable();
//        scrollForSecondTable = new JScrollPane(secondTable);
//        panelForTables.add(scrollForFirstTable);
//        panelForTables.add(scrollForSecondTable);
//        mainPane.add(panelForTables);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
//                    firstTable = createTable(firstPath.getText());
                    headerOfFirstTable = createColumnHeaders(readFromFile(new File(firstPath.getText())));
                    cellsOfFirstTable = createArrayOfCells(readFromFile(new File(firstPath.getText())));
                    firstTable = new JTable(cellsOfFirstTable, headerOfFirstTable);
                } catch (IOException ex) {
                    messages.setText("Не удалось создать первую таблицу");
                    System.out.println("Не удалось создать первую таблицу");
                }

                StringBuilder sb = new StringBuilder();
                int count = 0;
                for (int i = 0; i < cellsOfFirstTable.length; i++) {
                    System.out.println("Длина строки: " + cellsOfFirstTable.length);
                    for (int j = 0; j < cellsOfFirstTable[i].length; j++) {
//                        System.out.println(cellsOfFirstTable[i][j]);
                        sb.append(cellsOfFirstTable[i][j]).append("\n").append(count);
                        count++;
                    }
                }

                System.out.println(sb);

                System.out.println("Количество строк - " + firstTable.getRowCount() + "; Количество столбцов - " + firstTable.getColumnCount());
//                if (true) {
//                    showText.setText(FIRST_FILE_IS_SELECTED);
//                } else {
//                    System.out.println("Не удалось сформировать первую таблицу");
//                }
            }
        });

        secondPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    secondTable = createTable(secondPath.getText());
                    cellsOfSecondTable = createArrayOfCells(readFromFile(new File(secondPath.getText())));
                    List<String[]> listRows = getListOfRowsOfFirstTable(createArrayOfCells(readFromFile(new File(secondPath.getText()))));
//                    for (int i = 0; i < listRows.size(); i++) {
//                        for (int j = 0; j < listRows.get(i).length; j++) {
//                            String cell = listRows.get(i)[j];
//                            System.out.println(cell);
//                        }
//                    }

//                    headerOfSecondTable = createColumnHeaders(readFromFile(new File(secondPath.getText())));
//                    cellsOfSecondTable = createArrayOfCells(readFromFile(new File(secondPath.getText())));
//                    secondTable = new JTable(cellsOfSecondTable, headerOfSecondTable);
//
//                    StringBuilder sb = new StringBuilder();
//                    int count = 0;
//                    for (int i = 0; i < cellsOfSecondTable.length; i++) {
//                        for (int j = 0; j < cellsOfSecondTable[i].length; j++) {
////                        System.out.println(cellsOfFirstTable[i][j]);
//                            sb.append(cellsOfSecondTable[i][j]).append("\n").append(count);
//                            count++;
//                        }
//                    }
//
//                    System.out.println(sb);

                    messages.setText(getListOfRowsOfFirstTable(createArrayOfCells(readFromFile(new File(secondPath.getText())))).toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
                JPanel panelForTables = new JPanel();
                panelForTables.setMaximumSize(new Dimension(getWidth(), 500));
                JScrollPane firstTableScrollPane = new JScrollPane(firstTable);
                JScrollPane secondTableScrollPane = new JScrollPane(secondTable);
                firstTableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                firstTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                secondTableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                secondTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                panelForTables.add(firstTableScrollPane);
                panelForTables.add(secondTableScrollPane);

                mainPane.add(panelForTables);

                revalidate();
                repaint();
            }
        });

        COMPARE_TABLES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listOfRowsOfFirstTable = getListOfRowsOfFirstTable(createArrayOfCells(readFromFile(new File(firstPath.getText()))));
                    listOfRowsOfSecondTable = getListOfRowsOfFirstTable(createArrayOfCells(readFromFile(new File(secondPath.getText()))));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                try {
                    rowsOfFirstTable = readFromFile(new File(firstPath.getText())).split(";");
                    rowsOfSecondTable = readFromFile(new File(secondPath.getText())).split(";");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // TODO: 31.07.2022 Попробовать использовать AnyMuch

                boolean contain;

                Set<String> setSecondTableRows = new TreeSet<>(Arrays.asList(rowsOfSecondTable));

                for (int i = 0; i < rowsOfFirstTable.length; i++) {
                    if (!setSecondTableRows.contains(rowsOfFirstTable[i])) {
                        System.out.println(rowsOfFirstTable[i]);
                    }
                }
//                Set<String[]> setSecondTableRows;
//                try {
//                    setSecondTableRows = Collections.singleton(readFromFile(new File(firstPath.getText())).split("\n"));
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//                for (int i = 0; i < rowsOfFirstTable.length; i++) {
//                    if (setSecondTableRows.contains(rowsOfFirstTable[i])) {
//                        System.out.println(rowsOfFirstTable[i]);
//                    }
//                }

//                for (int i = 0; i < rowsOfSecondTable.length; i++) {
//                    for (int j = 0; j < rowsOfFirstTable.length; j++) {
//                        contain = rowsOfSecondTable[i].contains(rowsOfFirstTable[j]);
//                    }
//                }

//                for (int i = 0; i < rowsOfFirstTable.length; i++) {
//                    Arrays.stream(rowsOfSecondTable).anyMatch(rowsOfFirstTable[i]);
//                }


//                for (int i = 0; i < rowsOfSecondTable.length; i++) {
//                    for (int j = 0; j < rowsOfFirstTable.length; j++) {
//                        int comp = rowsOfSecondTable[i].compareTo(rowsOfFirstTable[j]);
//                        if (comp != 0) {
//                            System.out.println(rowsOfFirstTable[i]);
//                        }
//                    }
//                }
            }
        });

        mainPane.setBackground(Color.ORANGE);

        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

        add(mainPane);

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.

        setVisible(true);
    }

    private JTable createTable(String path) throws IOException {
        String[] header = createColumnHeaders(readFromFile(new File(path)));
        String[][] cells = createArrayOfCells(readFromFile(new File(path)));
//        String row;
//        String[] cellsArray;
//        String[] rows = readFromFile(new File(path)).split("\n");
//        String[] header = rows[0].split(";");
//        String[] rowsWithoutHeader = Arrays.stream(rows).skip(1).toArray(String[]::new);
//        String[][] cells = new String[rowsWithoutHeader.length][header.length];
//
//
//        for (int i = 0; i < rowsWithoutHeader.length; i++) {
//            row = rowsWithoutHeader[i];
//            cellsArray = row.split(";");
//            for (int j = 0; j < cellsArray.length; j++) {
//                cells[i][j] = cellsArray[j];
//            }
//        }

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

    private String[] createColumnHeaders(String text) {
        return splitTextIntoLines(text)[0].split(";");
    }

    private String[][] createArrayOfCells(String text) {
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

    public List<String[]> getListOfRowsOfFirstTable(String[][] cells) {
        return listOfRowsOfFirstTable = Arrays.stream(cells).toList();
    }

    public List<String[]> getListOfRowsOfSecondTable(String text) {
        return listOfRowsOfSecondTable;
    }

    private boolean tableIsFormed(String[] header, String[][] cells) {
        return headerIsFormed(header) && dataCellsIsFormed(cells) && header.length == cells[0].length;
    }

    private boolean headerIsFormed(String[] header) {
        return header.length > 0 && Arrays.stream(header).noneMatch(null);
    }

    private boolean dataCellsIsFormed(String[][] cells) {
        return cells.length > 0 && !Arrays.stream(cells).allMatch(null);
    }
}
