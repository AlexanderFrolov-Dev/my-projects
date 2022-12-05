package ch31_introduction_to_Swing_library;

// Пример простого Swing-приложения.

import javax.swing.*;

class SwingDemo {

    SwingDemo() {

        // Создать новый контейнер типа JFrame.
        JFrame jfrm = new JFrame("A Simple Swing Application");
        // "Простое Swing-приложение"

        // Задать исходные размеры фрейма.
        jfrm.setSize(275, 100);

        // Завершить работу, если пользователь
        // закрывает приложение.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создать метку с текстом сообщения.
        JLabel jlab = new JLabel(" Swing means powerful GUIs.");
        // "Swing - это мощные ГПИ"

        // Ввести метку на панели содержимого.
        jfrm.add(jlab);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String args[]) {
        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingDemo();
            }
        });
    }
}
