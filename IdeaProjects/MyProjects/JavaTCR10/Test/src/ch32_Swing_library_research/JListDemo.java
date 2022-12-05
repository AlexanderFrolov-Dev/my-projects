package ch32_Swing_library_research;

// Продемонстрировать применение компонента типа JList.
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class JListDemo {

    // Создать массив из названий городов.
    String Cities[] = { "New York", "Chicago", "Houston",
            "Denver", "Los Angeles", "Seattle",
            "London", "Paris", "New Delhi",
            "Hong Kong", "Tokyo", "Sydney" };

    public JListDemo() {

        // Установить фрейм средствами класса JFrame.
        JFrame jfrm = new JFrame("JListDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(200, 200);

        // Создать список на основе компонента типа JList.
        JList<String> jlst = new JList<String>(Cities);

        // Задать режим выбора единственного элемента из списка.
        jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ввести список на панели с полосами прокрутки.
        JScrollPane jscrlp = new JScrollPane(jlst);

        // Задать предпочтительные размеры панели
        // с полосами прокрутки.
        jscrlp.setPreferredSize(new Dimension(120, 90));

        // Создать метку для отображения выбранного города.
        JLabel jlab = new JLabel("Choose a City");

        // Ввести приемник событий выбора элементов из списка.
        jlst.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent le) {
                // Получить индекс измененного элемента.
                int idx = jlst.getSelectedIndex();

                // Отобразить сделанный выбор, если элемент
                // был выбран из списка.
                if(idx != -1)
                    jlab.setText("Current selection: " + Cities[idx]);
                else // В противном случае еще раз предложить
                     // выбрать город из списка.
                    jlab.setText("Choose a City");
            }
        });

        // Ввести список и метку на панели содержимого.
        jfrm.add(jscrlp);
        jfrm.add(jlab);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new JListDemo();
                    }
                }
        );
    }
}
