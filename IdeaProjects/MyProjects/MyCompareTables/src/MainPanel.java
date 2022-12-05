import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainPanel extends JPanel {
    PanelForTables panelForTables;
    JLabel addFirstTable = new JLabel("Введите путь к первому файлу и нажмите <Enter>");
    JLabel addSecondTable = new JLabel("Введите путь ко второму файлу и нажмите <Enter>");
    JLabel showText = new JLabel();
    JLabel firstFileFound = new JLabel("Первый файл найден");
    JLabel secondFileFound = new JLabel("Второй файл найден");
    static JTextField firstPath;
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
    StringBuilder sbColumnLetters = new StringBuilder();

    StringBuilder sb = new StringBuilder();
    String line;

    public MainPanel() {
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        firstPath = new JTextField();

        addFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        showText.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        compareTables.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500,20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500,20));

        add(Box.createRigidArea(new Dimension(this.getWidth(), 50)));  // Установить отступы.
        add(addFirstTable);
        add(firstPath);
        add(showText);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 50)));
        add(addSecondTable);
        add(secondPath);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 25)));
        add(compareTables);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 25)));
        add(messages);
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMaximumSize(new Dimension(500,100));
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 25)));

        panelForTables = new PanelForTables();
        add(panelForTables);

        setBackground(Color.ORANGE);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                showText.setText(firstPath.getText());
//                firstFile = new File(firstPath.getText());
                firstFile = new File(firstPath.getText());
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
    }

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
}
