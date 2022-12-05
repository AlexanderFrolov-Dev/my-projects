package ch32_Swing_library_research;

// Продемонстрировать применение компонента
// типа JScrollPane.
import java.awt.*;
import javax.swing.*;

public class JScrollPaneDemo {

    public JScrollPaneDemo() {

        // Установить фрейм средствами класса JFrame;
        // использовать выбираемый по умолчанию диспетчер
        // граничной компоновки типа BorderLayot.
        JFrame jfrm = new JFrame("JScrollPaneDemo");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(400, 400);

        // Создать панель и ввести на ней 400 экранных кнопок.
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(20, 20));

        int b = 0;
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                jp.add(new JButton("Button " + b));
                ++b;
            }
        }

        // Создать панель с полосами прокрутки.
        JScrollPane jsp = new JScrollPane(jp);

        // Ввести панель с полосами прокрутки на панели
        // содержимого. По умолчанию выполняется граничная
        // компоновка, и поэтому вводимая панель с полосами
        // прокрутки располагается по центру.
        jfrm.add(jsp, BorderLayout.CENTER);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(() -> new JScrollPaneDemo());
    }
}
