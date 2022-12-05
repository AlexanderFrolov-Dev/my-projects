package ch33_introduction_to_Swing_menu;

// Окончательный вариант прикладной программы MenuDemo.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MenuDemo16 implements ActionListener {

    JLabel jlab;

    JMenuBar jmb;

    JToolBar jtb;

    JPopupMenu jpu;

    DebugAction setAct;
    DebugAction clearAct;
    DebugAction resumeAct;

    MenuDemo16() {
        // Создать новый контейнер типа JFrame.
        JFrame jfrm = new JFrame("Complete Menu Demo");

        // Использовать граничную компоновку,
        // выбираемую по умолчанию.

        // Задать исходные размеры фрейма.
        jfrm.setSize(360, 200);

        // Завершить прикладную программу, как только
        // пользователь закроет ее окно.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создать метку для отображения результатов выбора из меню.
        jlab = new JLabel();

        // Создать строку меню.
        jmb = new JMenuBar();

        // Создать меню File.
        makeFileMenu();

        // Создать действия отладки.
        makeActions();

        // Создать панель инструментов.
        makeToolBar();

        // Создать меню Options.
        makeOptionsMenu();

        // Создать меню Help.
        makeHelpMenu();

        // Создать меню Edit.
        makeEditPUMenu();

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

        // Ввести метку в центре панели содержимого.
        jfrm.add(jlab, SwingConstants.CENTER);

        // Ввести панель инструментов в северном положении
        // панели содержимого.
        jfrm.add(jtb, BorderLayout.NORTH);

        // Ввести строку меню во фрейм.
        jfrm.setJMenuBar(jmb);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    // Обработать события действия от пунктов меню.
    // Здесь НЕ обрабатываются события, генерируемые
    // при выборе режимов отладки в подменю или на панели
    // инструментов Debug.
    public void actionPerformed(ActionEvent ae) {
        // Получить команду действия из выбранного меню.
        String comStr = ae.getActionCommand();

        // выйти из программы, если пользователь выберет пункт меню Exit.
        if(comStr.equals("Exit")) System.exit(0);

        // Отобразить в противном случае результат выбора из меню.
        jlab.setText(comStr + " Selected");
    }

    // Класс действий для подменю и панели инструментов Debug.
    class DebugAction extends AbstractAction {
        public DebugAction(String name, Icon image, int mnem,
                           int accel, String tTip) {
            super(name, image);
            putValue(ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(accel,
                            InputEvent.CTRL_DOWN_MASK));
            putValue(MNEMONIC_KEY, mnem);
            putValue(SHORT_DESCRIPTION, tTip);
        }

        // Обработать события как на панели инструментов,
        // так и в подменю Debug.
        public void actionPerformed(ActionEvent ae) {
            String comStr = ae.getActionCommand();

            jlab.setText(comStr + " Selected");

            // Изменить разрешенное состояние вариантов выбора
            // режимов установки и очистки точек прерывания.
            if(comStr.equals("Set Breakpoint")) {
                clearAct.setEnabled(true);
                setAct.setEnabled(false);
            } else if(comStr.equals("Clear Breakpoint")) {
                clearAct.setEnabled(false);
                setAct.setEnabled(true);
            }
        }
    }

    // Создать меню File с мнемоникой и
    // оперативными клавишами.
    void makeFileMenu() {
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

        // Ввести приемники действий для пунктов меню File.
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
    }

    // Создать меню Options.
    void makeOptionsMenu() {
        JMenu jmOptions = new JMenu("Options");

        // Создать подменю Colors.
        JMenu jmColors = new JMenu("Colors");

        // Использовать флажки, чтобы пользователь мог
        // выбрать сразу несколько цветов.
        JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");

        // Ввести пункты в подменю Colors.
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
        JRadioButtonMenuItem jmiHigh =
                new JRadioButtonMenuItem("High", true);
        JRadioButtonMenuItem jmiLow =
                new JRadioButtonMenuItem("Low");

        // Ввести пункты в подменю Priority.
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);

        // Создать группу кнопок-переключателей
        // в пунктах подменю Priority.
        ButtonGroup bg = new ButtonGroup();
        bg.add(jmiHigh);
        bg.add(jmiLow);

        // Создать подменю Debug, входящее
        // в меню Options, используя действия
        // для создания пунктов этого подменю.
        JMenu jmDebug = new JMenu("Debug");
        JMenuItem jmiSetBP = new JMenuItem(setAct);
        JMenuItem jmiClearBP = new JMenuItem(clearAct);
        JMenuItem jmiResume = new JMenuItem(resumeAct);

        // Ввести пункты в подменю Debug.
        jmDebug.add(jmiSetBP);
        jmDebug.add(jmiClearBP);
        jmDebug.add(jmiResume);
        jmOptions.add(jmDebug);

        // Создать пункт меню Reset.
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        // И наконец, ввести все выбираемые меню в строку меню.
        jmb.add(jmOptions);

        // Ввести приемники действий для пунктов меню Options,
        // кроме тех, что поддерживаются в подменю Debug.
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
    }

    // Создать меню Help.
    void makeHelpMenu() {
        JMenu jmHelp = new JMenu("Help");

        // Ввести значок для пункта меню About.
        ImageIcon icon = new ImageIcon("AboutIcon.png");

        JMenuItem jmiAbout = new JMenuItem("About", icon);
        jmiAbout.setToolTipText("Info about the MenuDemo16 program.");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        // Ввести приемник действий для пункта меню About.
        jmiAbout.addActionListener(this);
    }

    // Создать действия для управления подменю и
    // панелью инструментов Debug.
    void makeActions() {
        // Load the images for the actions. 
        ImageIcon setIcon = new ImageIcon("setBP.png");
        ImageIcon clearIcon = new ImageIcon("clearBP.png");
        ImageIcon resumeIcon = new ImageIcon("resume.png");

        // Создать действия.
        setAct =
                new DebugAction("Set Breakpoint",
                        setIcon,
                        KeyEvent.VK_S,
                        KeyEvent.VK_B,
                        "Set a break point.");

        clearAct =
                new DebugAction("Clear Breakpoint",
                        clearIcon,
                        KeyEvent.VK_C,
                        KeyEvent.VK_L,
                        "Clear a break point.");

        resumeAct =
                new DebugAction("Resume",
                        resumeIcon,
                        KeyEvent.VK_R,
                        KeyEvent.VK_R,
                        "Resume execution after breakpoint.");

        // Сделать первоначально недоступным вариант
        // выбора Clear Breakpoint.
        clearAct.setEnabled(false);
    }

    // Создать панель инструментов Debug.
    void makeToolBar() {
        // Создать кнопки для панели инструментов,
        // используя соответствующие действия.
        JButton jbtnSet = new JButton(setAct);
        JButton jbtnClear = new JButton(clearAct);
        JButton jbtnResume = new JButton(resumeAct);

        // Создать панель инструментов Debug.
        jtb = new JToolBar("Breakpoints");

        // Ввести кнопки на панели инструментов.
        jtb.add(jbtnSet);
        jtb.add(jbtnClear);
        jtb.add(jbtnResume);
    }

    // Создать всплывающее меню Edit.
    void makeEditPUMenu() {
        jpu = new JPopupMenu();

        // Создать пункты всплывающего меню Edit.
        JMenuItem jmiCut = new JMenuItem("Cut");
        JMenuItem jmiCopy = new JMenuItem("Copy");
        JMenuItem jmiPaste = new JMenuItem("Paste");

        // Ввести пункты во всплывающее меню Edit.
        jpu.add(jmiCut);
        jpu.add(jmiCopy);
        jpu.add(jmiPaste);

        // Ввести приемники действий для
        // всплывающего меню Edit.
        jmiCut.addActionListener(this);
        jmiCopy.addActionListener(this);
        jmiPaste.addActionListener(this);
    }

    public static void main(String args[]) {
        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuDemo16();
            }
        });
    }
}
