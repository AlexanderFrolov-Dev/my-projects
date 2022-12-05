import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppFrame extends JFrame {
    JPanel mainPane = new JPanel();
    JPanel panelForTables = new JPanel();
    JPanel headerPanel = new JPanel();
    JScrollPane scrollForFirstTable;
    JScrollPane scrollForSecondTable;
    JTable firstTable;
    JTable secondTable;
    private final static JLabel TABLE_HEADER = new JLabel("Создать таблицу:");
    private final static JRadioButton WITH_HEADER = new JRadioButton("с заголовком");
    private final static JRadioButton WITHOUT_HEADER = new JRadioButton("без заголовка");
    ButtonGroup bg = new ButtonGroup();
    JLabel addFirstTable = new JLabel("Введите путь к первому файлу и нажмите <Enter>");
    JLabel addSecondTable = new JLabel("Введите путь ко второму файлу и нажмите <Enter>");
    JLabel showText = new JLabel();
    JLabel firstFileFound = new JLabel("Первый файл найден");
    JLabel secondFileFound = new JLabel("Второй файл найден");
    JTextField firstPath = new JTextField();
    JTextField secondPath = new JTextField();
    JButton compareTables = new JButton("Сравнить файлы");
    JTextArea messages = new JTextArea();
    JScrollPane scrollPane;
    File firstFile;
    File secondFile = new File(secondPath.getText());
    int numberOfRows = 0;
    int symbol;
    FileInputStream fin;
    StringBuilder lineBuilder = new StringBuilder();
    String fullText;
    String fullFirstText;
    String fullSecondText;
    StringBuilder sb = new StringBuilder();
    String line;
    JCheckBox presenceOfHeader;
    boolean headerIsNeeded = true;

    // TODO: 19.07.2022 Ввести компонент для выбора необходимости заголовка при создании таблицы

    public AppFrame() {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TABLE_HEADER.setAlignmentX(Component.CENTER_ALIGNMENT);
        addFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        showText.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        compareTables.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500,20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500,20));
        panelForTables.setMaximumSize(new Dimension(getWidth(), 485));

        bg.add(WITH_HEADER);
        bg.add(WITHOUT_HEADER);
        WITH_HEADER.setSelected(true);
        WITH_HEADER.setBackground(Color.ORANGE);
        WITHOUT_HEADER.setBackground(Color.ORANGE);
        headerPanel.setMaximumSize(new Dimension(250,30));
        headerPanel.setBackground(Color.ORANGE);
        headerPanel.add(WITH_HEADER);
        headerPanel.add(WITHOUT_HEADER);

        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));  // Установить отступы.
        mainPane.add(TABLE_HEADER);
        mainPane.add(headerPanel);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 30)));
        mainPane.add(addFirstTable);
        mainPane.add(firstPath);
        mainPane.add(showText);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));
        mainPane.add(addSecondTable);
        mainPane.add(secondPath);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
        mainPane.add(compareTables);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
//        mainPane.add(messages);
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMaximumSize(new Dimension(500,100));
        mainPane.add(scrollPane);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
        firstTable = createTable();
        scrollForFirstTable = new JScrollPane(firstTable);
        secondTable = createTable();
        scrollForSecondTable = new JScrollPane(secondTable);
        panelForTables.add(scrollForFirstTable);
        panelForTables.add(scrollForSecondTable);
        mainPane.add(panelForTables);

        mainPane.setBackground(Color.ORANGE);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstFile = new File(firstPath.getText());
                try {
                    if (headerIsNeeded) {
                        createTableWithHeading(readFromFile(firstFile));
                    } else {
                        createTableWithoutHeading();
                    }
                    messages.setText(readFromFile(firstFile));
                    showText.setText(String.valueOf(numberOfRows));
                } catch (IOException ex) {
                    System.out.println("Ошибка чтения файла");
                    showText.setText("Ошибка чтения файла");
                    throw new RuntimeException(ex);
                }
            }
        });

        secondPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    messages.setText(readFromFile(secondFile));
                    showText.setText(String.valueOf(numberOfRows));
                } catch (IOException ex) {
                    System.out.println("Ошибка чтения файла");
                    showText.setText("Ошибка чтения файла");
                    throw new RuntimeException(ex);
                }
            }
        });

        compareTables.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        WITH_HEADER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (WITH_HEADER.isSelected()) {
                    headerIsNeeded = true;
                    showText.setText("true");
                }
            }
        });

        WITHOUT_HEADER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (WITHOUT_HEADER.isSelected()) {
                    headerIsNeeded = false;
                    showText.setText("false");
                }
            }
        });

        // Установить менеджер компоновки для вертикального расположения элементов на панели.
        mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.PAGE_AXIS));

        panelForTables.setBackground(Color.RED);

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.

        add(mainPane);
        setVisible(true);
    }

    private String readFromFile(File file) throws IOException {
        fin = new FileInputStream(file);

        try {
            do {
                symbol = fin.read();
                if (symbol != -1) {
                    lineBuilder.append((char) symbol);
//                line = splitIntoLines(lineBuilder);
//                String[] rowOfCells = splitIntoCells(line);
                    fullText = String.valueOf(lineBuilder);
                }
            } while (symbol != -1);
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла.");
        }

//        for (String s : splitIntoLines(String.valueOf(sb))) {
//            line = s;
//        }
        return fullText;
    }

    private static String[] splitIntoLines(String s) {
        return s.split("\n");
    }

    private String[] splitIntoCells(String row) {
        return row.split(";");
    }

    public int getCountOfColumns() {
        return splitIntoCells(line).length;
    }

    public int getCountOfLines() {
        return splitIntoLines(String.valueOf(sb)).length;
    }

    // У этих двух методов тоже есть дубликаты из Test2
//    private String splitIntoLines(StringBuilder sb) {
//        String s = "";
//        if ((((char) symbol) == '\n') || (((char) symbol) == '\t')) {
//            numberOfRows++;
//            s = String.valueOf(sb);
//        }
//        return  s;
//    }

    private JTable createTableWithHeading(String text) {
        String[] header;
        String[] rows;
        String[][] cells;

        rows = splitIntoLines(text);
        header = splitIntoCells(rows[0]);
        cells = fixCreateTable(rows);

        return new JTable(cells, header);
    }

    private JTable createTableWithoutHeading() {
        if (!headerIsNeeded) {

        }
        return null;
    }

    private String[][] fixCreateTable(String[] rows) {
        String[][] table = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < splitIntoCells(rows[i]).length; j++) {
                table[i][j] = splitIntoCells(rows[i])[j];
            }
        }
        return table;
    }

    private JTable createTable() {
        String[] heading = new String[5];
        String[][] table = new String[10][5];
        int addOne = 1;

        for (int i = 0; i < heading.length; i++) {
            heading[i] = String.valueOf((char) ('A' + i));
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (addOne + i) + " " + ((char) ('A' + j));
            }
        }

        return new JTable(table, heading);
    }

    private void getCellsList() {
        List<String[]> rowsList = Stream.of(fullText)
                .map(s -> s.split("\n"))
                .toList();
//        List<String> cellsList = Stream.of(fullText)
//                .map(s -> Arrays.stream(s.split("\n")))
//                .toList()
//                .stream().toList()
//                .stream().map(stringStream -> stringStream.map(s -> s.split(";")))
//                .flatMap(List::add);
//        List<String> strings = (List<String>) Stream.of(fullText.split("\n"))
//                .toList()
//                .stream().map(s -> s.split(";"))
//                .toList();

//                .map(strings -> Arrays.stream(strings).map(s -> s.split(";")))
//                .flatMap(Collection::add)
    }
}