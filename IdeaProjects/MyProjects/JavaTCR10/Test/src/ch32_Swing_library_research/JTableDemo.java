package ch32_Swing_library_research;

// Продемонстрировать применение компонента типа JTable.
import java.awt.*;
import javax.swing.*;

public class JTableDemo {

    // Инициализировать заголовки столбцов.
    String[] colHeads = { "Name", "Extension", "ID#" };

    // Инициализировать данные.
    Object[][] data = {
            { "Gail", "4567", "865" },
            { "Ken", "7566", "555" },
            { "Viviane", "5634", "587" },
            { "Melanie", "7345", "922" },
            { "Anne", "1237", "333" },
            { "John", "5656", "314" },
            { "Matt", "5672", "217" },
            { "Claire", "6741", "444" },
            { "Erwin", "9023", "519" },
            { "Ellen", "1134", "532" },
            { "Jennifer", "5689", "112" },
            { "Ed", "9030", "133" },
            { "Helen", "6751", "145" }
    };

    public JTableDemo() {

        // Установить фрейм средствами класса JFrame;
        // использовать выбираемый по умолчанию диспетчер
        // граничной компоновки типа BorderLayout.
        JFrame jfrm = new JFrame("JTableDemo");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(300, 300);

        // Создать таблицу.
        JTable table = new JTable(data, colHeads);

        // Ввести таблицу на панели с полосами прокрутки.
        JScrollPane jsp = new JScrollPane(table);

        // Ввести панель с полосами прокрутки
        // на панели содержимого.
        jfrm.add(jsp);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new JTableDemo();
                    }
                }
        );
    }
}
