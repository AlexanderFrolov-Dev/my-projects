import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;

public class AppFrame extends JFrame{
    JPanel mainPain = new JPanel();
    JLabel addFirstTable = new JLabel("Введите путь к первой таблице");
    JLabel addSecondTable = new JLabel("Введите путь ко второй таблице");
    JTextField firstPath = new JTextField();
    JTextField secondPath = new JTextField();
    JButton compareTables = new JButton("Сравнить таблицы");
    JLabel messages = new JLabel();
    HSSFWorkbook firstHSSFWorkbook;
    HSSFWorkbook secondHSSFWorkbook;
    ArrayList<String> firstBookList;
    ArrayList<String> secondBookList;

    public AppFrame() {
        setTitle("Сравнение таблиц Excel");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addFirstTable.setAlignmentX(Component.CENTER_ALIGNMENT);  // Установить центральное расположение элементов.
        firstPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSecondTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondPath.setAlignmentX(Component.CENTER_ALIGNMENT);
        compareTables.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstPath.setMaximumSize(new Dimension(500,20));  // Ограничить размеры отдельных элементов.
        secondPath.setMaximumSize(new Dimension(500,20));

        mainPain.add(Box.createRigidArea(new Dimension(mainPain.getWidth(), 50)));  // Установить отступы.
        mainPain.add(addFirstTable);
        mainPain.add(firstPath);
        mainPain.add(Box.createRigidArea(new Dimension(mainPain.getWidth(), 50)));
        mainPain.add(addSecondTable);
        mainPain.add(secondPath);
        mainPain.add(Box.createRigidArea(new Dimension(mainPain.getWidth(), 25)));
        mainPain.add(compareTables);
        mainPain.add(Box.createRigidArea(new Dimension(mainPain.getWidth(), 25)));
        mainPain.add(messages);

        firstPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstHSSFWorkbook = readWorkbook(firstPath.getText());

            }
        });

        secondPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondHSSFWorkbook = readWorkbook(secondPath.getText());
            }
        });

        compareTables.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (compareBooks(firstHSSFWorkbook, secondHSSFWorkbook)) {
                    messages.setText("Таблицы идентичны"
                            + " Хэш первой таблицы: "
                            + firstHSSFWorkbook.hashCode()
                            + " Хэш второй таблицы: "
                            + secondHSSFWorkbook.hashCode());
                } else {
                    messages.setText("Таблицы не идентичны"
                            + " Хэш первой таблицы: "
                            + firstHSSFWorkbook.hashCode()
                            + " Хэш второй таблицы: "
                            + secondHSSFWorkbook.hashCode()
                            + " "
                            + firstHSSFWorkbook.toString());
                    System.out.println(firstHSSFWorkbook.getNumberOfNames());
                }
            }
        });

        mainPain.setLayout(new BoxLayout(mainPain,BoxLayout.PAGE_AXIS));  // Установить менеджер компоновки для
                                                                          // вертикального расположения элементов на
                                                                          // панели.

        add(mainPain);

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.

        setVisible(true);
    }

    public static HSSFWorkbook readWorkbook(String filename) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            return wb;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static boolean compareBooks(HSSFWorkbook firstBook, HSSFWorkbook secondBook) {
        return firstBook.equals(secondBook);
    }
}

// C:\Users\Andrey Pakhomenkov\Desktop\Тест.xls
