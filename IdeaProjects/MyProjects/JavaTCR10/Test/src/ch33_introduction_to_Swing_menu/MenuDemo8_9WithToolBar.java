package ch33_introduction_to_Swing_menu;

// Продемонстрировать простое главное меню.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MenuDemo8_9WithToolBar implements ActionListener {

    JLabel jlab;

    JPopupMenu jpu;

    MenuDemo8_9WithToolBar() {
        // Создать новый контейнер типа JFrame.
        JFrame jfrm = new JFrame("Menu Demo");

        // Задать исходные размеры фрейма.
        jfrm.setSize(220, 200);

        // Завершить прикладную программу, как только
        // пользователь закроет ее окно.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создать метку для отображения результатов выбора из меню.
        jlab = new JLabel();

        // Создать всплывающее меню Edit.
        jpu = new JPopupMenu();

        // Создать пункты всплывающего меню.
        JMenuItem jmiCut = new JMenuItem("Cut");
        JMenuItem jmiCopy = new JMenuItem("Copy");
        JMenuItem jmiPaste = new JMenuItem("Paste");

        // Ввести пункты во всплывающее меню.
        jpu.add(jmiCut);
        jpu.add(jmiCopy);
        jpu.add(jmiPaste);

        // Ввести приемник событий запуска всплывающего меню.
        jfrm.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if(me.isPopupTrigger())
                    jpu.show(me.getComponent(), me.getX(), me.getY());
            }
            public void mouseReleased(MouseEvent me) {
                if(me.isPopupTrigger())
                    jpu.show(me.getComponent(), me.getX(), me.getY());
            }
        });

        jmiCut.addActionListener(this);
        jmiCopy.addActionListener(this);
        jmiPaste.addActionListener(this);

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

        // Использовать флажки, чтобы пользователь мог выбрать
        // сразу несколько цветов.
        JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");

        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);

        // Создать подменю Priority.
        JMenu jmPriority = new JMenu("Priority");

        // Использовать кнопки-переключатели для установки
        // приоритета. Благодаря этому в меню не только
        // отображается установленный приоритет, но и
        // гарантируется установка одного и только одного
        // приоритета. Исходно выбирается кнопка-переключатель
        // в пункте меню High.
        JRadioButtonMenuItem jmiHigh = new JRadioButtonMenuItem("High", true);
        JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");

        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);

        // Создать группу кнопок-переключателей в
        // пунктах подменю Priority.
        ButtonGroup bg = new ButtonGroup();
        bg.add(jmiHigh);
        bg.add(jmiLow);

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
        jmiAbout.setToolTipText("Info about the MenuDemo program.");
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
        jfrm.add(jlab,BorderLayout.CENTER);

        // Ввести строку меню во фрейм.
        jfrm.setJMenuBar(jmb);

        // Создать панель инструментов Debug.
        JToolBar jtb = new JToolBar("Debug");

        // Загрузить изображения значков экранных кнопок.
        ImageIcon set = new ImageIcon("setBP.png");
        ImageIcon clear = new ImageIcon("clearBP.png");
        ImageIcon resume = new ImageIcon("resume.png");

        // Создать кнопки для панели инструментов.
        JButton jbtnSet = new JButton(set);
        jbtnSet.setActionCommand("Set Breakpoint");
        jbtnSet.setToolTipText("Set Breakpoint");

        JButton jbtnClear = new JButton(clear);
        jbtnClear.setActionCommand("Clear Breakpoint");
        jbtnClear.setToolTipText("Clear Breakpoint");

        JButton jbtnResume = new JButton(resume);
        jbtnResume.setActionCommand("Resume");
        jbtnResume.setToolTipText("Resume");

        // Ввести экранные кнопки на панели инструментов.
        jtb.add(jbtnSet);
        jtb.add(jbtnClear);
        jtb.add(jbtnResume);

        // Ввести панель инструментов в северном расположении
        // на панели содержимого.
        jfrm.add(jtb, BorderLayout.NORTH);

        // Ввести приемники действий для панели инструментов.
        jbtnSet.addActionListener(this);
        jbtnClear.addActionListener(this);
        jbtnResume.addActionListener(this);

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
                new MenuDemo8_9WithToolBar();
            }
        });
    }
}