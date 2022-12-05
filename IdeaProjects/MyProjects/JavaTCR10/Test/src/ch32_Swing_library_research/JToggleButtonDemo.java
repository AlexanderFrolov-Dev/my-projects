package ch32_Swing_library_research;

// Продемонстрировать применение компонента типа JToggleButton.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JToggleButtonDemo {

    public JToggleButtonDemo() {

        // Установить фрейм средствами класса JFrame.
        JFrame jfrm = new JFrame("JToggleButtonDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(200, 100);

        // Создать метку.
        JLabel jlab = new JLabel("Button is off.");

        // Создать переключатель.
        JToggleButton jtbn =  new JToggleButton("On/Off");

        // Ввести приемник событий от переключателя.
        jtbn.addItemListener(ie -> {
            if(jtbn.isSelected())
                jlab.setText("Button is on.");
            else
                jlab.setText("Button is off.");
        });

        // Ввести переключатель и метку на панель содержимого.
        jfrm.add(jtbn);
        jfrm.add(jlab);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(() -> new JToggleButtonDemo());
    }
}
