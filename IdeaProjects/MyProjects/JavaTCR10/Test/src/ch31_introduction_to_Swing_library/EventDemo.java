package ch31_introduction_to_Swing_library;

// Обработка события в Swing-приложении.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EventDemo {

    JLabel jlab;

    EventDemo() {

        // Создать новый контейнер типа JFrame.
        JFrame jfrm = new JFrame("An Event Example");
        // "Пример обработки событий"

        // Определить диспетчер поточной
        // компоновки типа FlowLayout.
        jfrm.setLayout(new FlowLayout());

        // Установить исходные размеры фрейма.
        jfrm.setSize(220, 90);

        // Завершить работу приложения, если
        // пользователь закрывает его окно.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создать две кнопки.
        JButton jbtnAlpha = new JButton("Alpha");
        JButton jbtnBeta = new JButton("Beta");

        // Ввести приемник действий от кнопки Alpha.
        jbtnAlpha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                jlab.setText("Alpha was pressed.");
            }
        });
        // jbtnAlpha.addActionListener(actionEvent -> jlab.setText("Alpha was pressed."));

        // Ввести приемник действий от кнопки Beta.
        jbtnBeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                jlab.setText("Beta was pressed.");
            }
        });
        // jbtnBeta.addActionListener(ae -> jlab.setText("Beta was pressed."));

        // Ввести кнопки на панели содержимого.
        jfrm.add(jbtnAlpha);
        jfrm.add(jbtnBeta);

        // Создать текстовую метку.
        jlab = new JLabel("Press a button.");

        // Ввести метку на панели содержимого.
        jfrm.add(jlab);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String args[]) {
        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventDemo();
            }
        });
    }
}
