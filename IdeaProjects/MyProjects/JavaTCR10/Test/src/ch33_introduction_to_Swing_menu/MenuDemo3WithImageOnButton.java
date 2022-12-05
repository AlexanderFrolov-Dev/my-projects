package ch33_introduction_to_Swing_menu;

// Продемонстрировать простое главное меню.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MenuDemo3WithImageOnButton implements ActionListener {

    JLabel jlab;

    MenuDemo3WithImageOnButton() {
        // Создать новый контейнер типа JFrame.
        JFrame jfrm = new JFrame("Menu Demo");

        // Указать диспетчер поточной компоновки типа FlowLayout.
        jfrm.setLayout(new FlowLayout());

        // Задать исходные размеры фрейма.
        jfrm.setSize(220, 200);

        // Завершить прикладную программу, как только
        // пользователь закроет ее окно.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создать метку для отображения результатов выбора из меню.
        jlab = new JLabel();

        // Создать строку меню.
        JMenuBar jmb = new JMenuBar();

        // Создать меню File мнемоникой и оперативными клавишами.
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);

        JMenuItem jmiOpen = new JMenuItem("Open",
                KeyEvent.VK_O);
        jmiOpen.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O,
                        InputEvent.CTRL_DOWN_MASK));

        JMenuItem jmiClose = new JMenuItem("Close",
                KeyEvent.VK_C);
        jmiClose.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C,
                        InputEvent.CTRL_DOWN_MASK));

        JMenuItem jmiSave = new JMenuItem("Save",
                KeyEvent.VK_S);
        jmiSave.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S,
                        InputEvent.CTRL_DOWN_MASK));

        JMenuItem jmiExit = new JMenuItem("Exit",
                KeyEvent.VK_E);
        jmiExit.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E,
                        InputEvent.CTRL_DOWN_MASK));
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        // Создать меню Options.
        JMenu jmOptions = new JMenu("Options");

        // Создать подменю Colors.
        JMenu jmColors = new JMenu("Colors");
        JMenuItem jmiRed = new JMenuItem("Red");
        JMenuItem jmiGreen = new JMenuItem("Green");
        JMenuItem jmiBlue = new JMenuItem("Blue");
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);

        // Создать подменю Priority.
        JMenu jmPriority = new JMenu("Priority");
        JMenuItem jmiHigh = new JMenuItem("High");
        JMenuItem jmiLow = new JMenuItem("Low");
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);

        // Создать пункт меню Reset.
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        // И наконец, ввести все выбираемые меню в строку меню.
        jmb.add(jmOptions);

        // Создать меню Help.
        JMenu jmHelp = new JMenu("Help");
        ImageIcon icon = new ImageIcon("AboutIcon.png");
        JMenuItem jmiAbout = new JMenuItem("About", icon);
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        // Ввести приемники действий от пунктов меню.
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
        jmiAbout.addActionListener(this);

        // Ввести метку на панели содержимого.
        jfrm.add(jlab);

        // Ввести строку меню во фрейм.
        jfrm.setJMenuBar(jmb);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    // Обработать события действия от пунктов меню.
    public void actionPerformed(ActionEvent ae) {
        // Получить команду действия из выбранного меню.
        String comStr = ae.getActionCommand();

        // Выйти из программы, если пользователь
        // выберет пункт меню Exit.
        if(comStr.equals("Exit")) System.exit(0);

        // В противном случае отобразить результат
        // выбора из меню.
        jlab.setText(comStr + " Selected");
    }

    public static void main(String args[]) {
        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuDemo3WithImageOnButton();
            }
        });
    }
}

