import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class TestAppFrame extends JFrame {
    JPanel mainPane = new JPanel();
//    JPanel panelForTables = new JPanel();
//    JScrollPane scrollForFirstTable;
//    JScrollPane scrollForSecondTable;
//    JTable firstTable;
//    JTable secondTable;
    JLabel addFirstTable = new JLabel("Введите путь к первому файлу и нажмите <Enter>");
    JLabel addSecondTable = new JLabel("Введите путь ко второму файлу и нажмите <Enter>");
    JLabel showText = new JLabel();
    JTextField firstPath = new JTextField();
    JTextField secondPath = new JTextField();
    JTextArea messages = new JTextArea();
    JScrollPane scrollPane;
    int symbol;
    FileInputStream fin;
    StringBuilder lineBuilder = new StringBuilder();
    String fullText;

    public TestAppFrame() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        showText.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));  // Установить отступы.
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 30)));
        mainPane.add(addFirstTable);
        mainPane.add(firstPath);
        mainPane.add(showText);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 50)));
        mainPane.add(addSecondTable);
        mainPane.add(secondPath);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
        mainPane.add(messages);
        messages.setEditable(false);
        scrollPane = new JScrollPane(messages);
        scrollPane.setMaximumSize(new Dimension(500,100));
        mainPane.add(scrollPane);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));
//        firstTable = createTable();
//        scrollForFirstTable = new JScrollPane(firstTable);
//        secondTable = createTable();
//        scrollForSecondTable = new JScrollPane(secondTable);
//        panelForTables.add(scrollForFirstTable);
//        panelForTables.add(scrollForSecondTable);
//        mainPane.add(panelForTables);

        mainPane.setBackground(Color.ORANGE);

        firstPath.setMaximumSize(new Dimension(500,20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500,20));
//        panelForTables.setMaximumSize(new Dimension(getWidth(), 485));

        mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.PAGE_AXIS));

//        panelForTables.setBackground(Color.RED);

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.

        add(mainPane);
        setVisible(true);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = firstPath.getText();
                String[] header;


                try {
                    readFromFile(new File(path));
                    String row;
                    String[] cellsArray;
                    String[] rows = fullText.split("\n");
                    String[] firstRow = rows[0].split(";");
                    String[] rowsWithoutHeader = Stream.of(rows).skip(1).toArray(String[]::new);
                    String[][] cells = new String[rowsWithoutHeader.length][firstRow.length];
                    header = rows[0].split(";");

                    showText.setText(String.valueOf(rowsWithoutHeader));
                    for (int i = 0; i < rowsWithoutHeader.length; i++) {
                        row = rowsWithoutHeader[i];
                        cellsArray = row.split(";");
                        for (int j = 0; j < cellsArray.length; j++) {
                            cells[i][j] = cellsArray[j];
                        }
                    }

                    messages.setText(Arrays.deepToString(cells));

                    JPanel panelForTables = new JPanel();
                    JScrollPane scrollForFirstTable;
                    JScrollPane scrollForSecondTable;
                    JTable firstTable = new JTable(cells, header);
                    JTable secondTable = new JTable(cells, header);

                    scrollForFirstTable = new JScrollPane(firstTable);
                    scrollForSecondTable = new JScrollPane(secondTable);
                    panelForTables.add(scrollForFirstTable);
                    panelForTables.add(scrollForSecondTable);
                    mainPane.add(panelForTables);

                    panelForTables.setMaximumSize(new Dimension(getWidth(), 485));

                    panelForTables.setBackground(Color.RED);

                    revalidate();
                    repaint();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

    private String readFromFile(File file) throws IOException {
        fin = new FileInputStream(file);

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
}
