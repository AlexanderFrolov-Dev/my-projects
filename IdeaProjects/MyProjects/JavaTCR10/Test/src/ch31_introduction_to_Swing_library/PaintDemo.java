package ch31_introduction_to_Swing_library;

// Рисовать линии на панели.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// Этот класс расширяет класс JPanel.
// В нем переопределяется метод paintComponent(),
// чтобы произвольно рисовать линии на панели.
class PaintPanel extends JPanel {
    Insets ins; // Служит для хранения размеров границ панели.

    Random rand; // Служит для генерирования случайных чисел.

    // Создать панель.
    PaintPanel() {

        // Разместить рамку вокруг панели,
        // определив ее границы.
        setBorder(BorderFactory.createLineBorder(Color.RED, 5));

        rand = new Random();
    }

    // Переопределить метод paintComponent().
    protected void paintComponent(Graphics g) {
        // Вызывать всегда первым метод из суперкласса.
        super.paintComponent(g);

        int x, y, x2, y2;

        // Получить высоту и ширину компонента.
        int height = getHeight();
        int width = getWidth();

        // Получить размеры границ панели.
        ins = getInsets();

        // Нарисовать десять линий, конечные точки
        // которых формируются произвольно.
        for(int i=0; i < 10; i++) {
            // Получить произвольные координаты,
            // определяющие конечные точки каждой линии.
            x = rand.nextInt(width-ins.left);
            y = rand.nextInt(height-ins.bottom);
            x2 = rand.nextInt(width-ins.left);
            y2 = rand.nextInt(height-ins.bottom);

            // Нарисовать линию.
            g.drawLine(x, y, x2, y2);
        }
    }
}

// Продемонстрировать рисование непосредственно на панели.
class PaintDemo {

    JLabel jlab;
    PaintPanel pp;

    PaintDemo() {

        // Создать новый контейнер типа JFrame.
        JFrame jfrm = new JFrame("Paint Demo");

        // Задать исходные размеры фрейма.
        jfrm.setSize(200, 150);

        // Завершить приложение, если пользователь закроет его окно.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создать панель для рисования.
        pp = new PaintPanel();

        // Ввести эту панель на панели содержимого.
        // В данном случае применяется граничная
        // компоновка, поэтому размеры панели будут
        // автоматически подгоняться таким образом,
        // чтобы она заняла центральную область.
        jfrm.add(pp);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String args[]) {
        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaintDemo();
            }
        });
    }
}
