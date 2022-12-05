package ch32_Swing_library_research;

// Продемонстрировать применение компонента типа JComboBox.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComboBoxDemo {

    String timepieces[] = { "Hourglass", "Analog", "Digital", "Stopwatch" };

    public JComboBoxDemo() {

        // Установить фрейм средствами класса JFrame.
        JFrame jfrm = new JFrame("JCombBoxDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(400, 250);

        // Получить экземпляр объекта комбинированного
        // списка и ввести его на панели содержимого.
        JComboBox<String> jcb = new JComboBox<String>(timepieces);
        jfrm.add(jcb);

        // Создать метку и ввести ее на панели содержимого.
        JLabel jlab = new JLabel(new ImageIcon("hourglass.png"));
        jfrm.add(jlab);

        // Обработать события выбора элементов из списка.
        jcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String s = (String) jcb.getSelectedItem();
                jlab.setIcon(new ImageIcon(s + ".png"));
            }
        });

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new JComboBoxDemo();
                    }
                }
        );
    }
}