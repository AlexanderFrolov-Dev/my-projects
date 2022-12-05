import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestAppFrame2 extends JFrame {
    JPanel mainPane = new JPanel();
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
    String firstPathStr;
    String secondPathStr;

    public TestAppFrame2() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000, 1000));
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
        scrollPane.setMaximumSize(new Dimension(500, 100));
        mainPane.add(scrollPane);
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), 25)));

        mainPane.setBackground(Color.ORANGE);

        firstPath.setMaximumSize(new Dimension(500, 20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500, 20));

        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.

        add(mainPane);
        setVisible(true);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPathStr = firstPath.getText();
            }
        });

        secondPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondPathStr = secondPath.getText();
                messages.setText(firstPathStr + " - " + secondPathStr);
                PanelForTables panelForTables;
                try {
                    panelForTables = new PanelForTables(firstPathStr, secondPathStr);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                mainPane.add(panelForTables);

                revalidate();
                repaint();
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
