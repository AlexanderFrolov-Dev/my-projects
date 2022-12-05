import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class AppFrame extends JFrame {
    private JPanel mainPane = new JPanel();
    private JPanel panelForTables = new JPanel();
    private JScrollPane scrollForFirstTable;
    private JScrollPane scrollForSecondTable;
    private JTable firstTable;
    private JTable secondTable;
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
//    File firstFile = new File(firstPath.getText());
    File firstFile;
    File secondFile = new File(secondPath.getText());
    int numberOfRows = 0;
    int symbol;
    FileInputStream fin;
    StringBuilder lineBuilder = new StringBuilder();
    String fullText = "";
    private StringBuilder sbColumnLetters = new StringBuilder();

    private StringBuilder sb = new StringBuilder();
    private String line;
    private List<String> listCells;

    public AppFrame() {
        setTitle("Сравнение CSV файлы");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        showText.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        compareTables.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500,20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500,20));

        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));  // Установить отступы.
        mainPane.add(addFirstTable);
        mainPane.add(firstPath);
        mainPane.add(showText);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));
        mainPane.add(addSecondTable);
        mainPane.add(secondPath);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
        mainPane.add(compareTables);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
        mainPane.add(messages);
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMaximumSize(new Dimension(500,100));
        mainPane.add(scrollPane);

        mainPane.setBackground(Color.ORANGE);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                showText.setText(firstPath.getText());
//                firstFile = new File(firstPath.getText());
                firstFile = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Test_Files\\First_File_CSV.csv");
                try {
                    readFromFile(firstFile);
                    messages.setText(fullText);
                    showText.setText(String.valueOf(numberOfRows));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        secondPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        compareTables.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.PAGE_AXIS));  // Установить менеджер компоновки для
                                                                          // вертикального расположения элементов на
                                                                          // панели.

        add(mainPane);

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.

        setVisible(true);
    }

    // Выбрать какой метод оставить, этот
    private void readFromFile(File file) throws IOException {
        fin = new FileInputStream(file);
        do {
            symbol = fin.read();
            if (symbol != -1) {
                lineBuilder.append((char) symbol);
//                splitIntoLines(lineBuilder);
                String line = splitIntoLines(lineBuilder);
                String[] rowOfCells = splitIntoCells(line);
                fullText = String.valueOf(lineBuilder);
            }
        } while (symbol != -1);
    }

    // Или этот.
    private void readDataFromFile() throws FileNotFoundException {
        int symbol;
        FileInputStream fin = new FileInputStream("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Test_Files\\First_File_CSV.csv");

        try {
            do {
                symbol = fin.read();
                if (symbol != -1) {
                    sb.append((char) symbol);
                }
            } while (symbol != -1);
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла.");
        }

        for (String s : splitIntoLines(String.valueOf(sb))) {
            line = s;
        }
    }

    private static String[] splitIntoLines(String s) {
        return s.split("\n");
    }

    private static String[] splitToCells(String s) {
        return s.split(";");
    }

    public int getCountOfColumns() {
        return splitToCells(line).length;
    }

    public int getCountOfLines() {
        return splitIntoLines(String.valueOf(sb)).length;
    }

    private String[] setIndexesOfCells(int cellsCount) {
        String[] lettersIndexOfCells = new String[cellsCount];
        String appender = "";
        int count = 0;
        char letter;
        char additionalLetter;
        int indexLetter = 0;
        int additionalLetterCount = 0;

        for (int i = 0; i < cellsCount; i++) {
            letter = (char) (indexLetter + 65);
            lettersIndexOfCells[count] = appender + letter;
            indexLetter++;
            count++;
            if ((i != 0) && (i % 25 == 0)) {
                additionalLetter = (char) (additionalLetterCount + 65);
                additionalLetterCount++;
                appender = String.valueOf(additionalLetter);
                indexLetter = 0;
            }
        }
        return lettersIndexOfCells;
    }

    private List<String> getCells() {
        listCells = new LinkedList<>();
        String s;
        String[] lines = splitIntoLines(String.valueOf(sb));
        for (int i = 0; i < getCountOfLines(); i++) {
            String[] cells = splitToCells(lines[i]);
            for (int j = 0; j < getCountOfColumns(); j++) {
                String[] indexLetters = setIndexesOfCells(getCountOfColumns());
                s = i + "|" + indexLetters[j] + "|" + cells[j];
                listCells.add(s);
            }
        }
        return listCells;
    }

    // У этих двух методов тоже есть дубликаты из Test2
    private String splitIntoLines(StringBuilder sb) {
        String s = "";
        if ((((char) symbol) == '\n') || (((char) symbol) == '\t')) {
            numberOfRows++;
            s = String.valueOf(sb);
        }
        return  s;
    }

    private String[] splitIntoCells(String row) {
        return row.split(";");
    }

    private JTable createTable() {
        new JTable(getCountOfLines() + 1, getCountOfColumns() + 1);
        // Чтобы можно было вывести ячейки с индексами цифр слева и индексами букв вверху.
        // Крайняя верхняя ячейка слева при этом будет пустая.
        return null;
    }
}