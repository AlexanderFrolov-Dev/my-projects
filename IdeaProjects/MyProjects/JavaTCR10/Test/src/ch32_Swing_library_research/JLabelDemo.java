package ch32_Swing_library_research;

// Продемонстрировать применение компонентов
// типа JLabel и ImageIcon из библиотеки Swing.
import java.awt.*;
import javax.swing.*;

public class JLabelDemo {

    public JLabelDemo() {

        // Установить фрейм средствами класса JFrame.
        JFrame jfrm = new JFrame("JLabelDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(260, 210);

        // Создать значок.
        ImageIcon ii = new ImageIcon("hourglass.png");

        // Создать метку.
        JLabel jl = new JLabel("Hourglass", ii, JLabel.CENTER);

        // Ввести метку на панели содержимого.
        jfrm.add(jl);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new JLabelDemo();
                    }
                }
                // () -> new JLabelDemo()
        );
    }
}
