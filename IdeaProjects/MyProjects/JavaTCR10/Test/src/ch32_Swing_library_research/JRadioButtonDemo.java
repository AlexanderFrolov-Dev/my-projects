package ch32_Swing_library_research;

// Продемонстрировать применение компонента
// типа JRadioButton.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JRadioButtonDemo implements ActionListener {
    JLabel jlab;

    public JRadioButtonDemo() {

        // Установить фрейм средствами класса JFrame.
        JFrame jfrm = new JFrame("JRadioButtonDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(250, 100);

        // Создать кнопки-переключатели и ввести
        // их на панели содержимого.
        JRadioButton b1 = new JRadioButton("A");
        b1.addActionListener(this);
        jfrm.add(b1);

        JRadioButton b2 = new JRadioButton("B");
        b2.addActionListener(this);
        jfrm.add(b2);

        JRadioButton b3 = new JRadioButton("C");
        b3.addActionListener(this);
        jfrm.add(b3);

        // Определить группу кнопок.
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);

        // Создать метку и ввести ее на панели содержимого.
        jlab = new JLabel("Select One");
        jfrm.add(jlab);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    // Обработать событие выбора кнопки-переключателя.
    public void actionPerformed(ActionEvent ae) {
        jlab.setText("You selected " + ae.getActionCommand());
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(() -> new JRadioButtonDemo());
    }
}
