import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class TestAppFrame3 extends JFrame {
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

    // TODO: 28.07.2022 Написать методы для проверки .csv и наличия разделителя ";".
    public TestAppFrame3() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        setContentPane(mainPane);
        
        ADD_FIRST_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        showText.setAlignmentX(Component.CENTER_ALIGNMENT);
        ADD_SECOND_TABLE.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        COMPARE_TABLES.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500,20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500,20));

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
        scrollPane.setMaximumSize(new Dimension(500,100));
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
                    firstTable = createTable(firstPath.getText());
                } catch (IOException ex) {
                    messages.setText("Не удалось создать первую таблицу");
                    System.out.println("Не удалось создать первую таблицу");
                }


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
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JPanel panelForTables = new JPanel();
                JScrollPane firstTableScrollPane = new JScrollPane(firstTable);
                JScrollPane secondTableScrollPane = new JScrollPane(secondTable);
                panelForTables.add(firstTableScrollPane);
                panelForTables.add(secondTableScrollPane);

                mainPane.add(panelForTables);

                revalidate();
                repaint();
            }
        });

        mainPane.setBackground(Color.ORANGE);

        mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.PAGE_AXIS));

        add(mainPane);

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.

        setVisible(true);
    }

    private JTable createTable(String path) throws IOException {
        String row;
        String[] header;
        String[] cellsArray;
        String[] rows = readFromFile(new File(path)).split("\n");
//        String[] firstRow = rows[0].split(";");
        header = rows[0].split(";");
        String[] rowsWithoutHeader = Arrays.stream(rows).skip(1).toArray(String[]::new);
        String[][] cells = new String[rowsWithoutHeader.length][header.length];


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
