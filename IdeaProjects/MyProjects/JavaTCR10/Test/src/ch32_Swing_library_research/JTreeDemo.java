package ch32_Swing_library_research;

// Продемонстрировать применение компонента типа JTree.
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class JTreeDemo {

    public JTreeDemo() {

        // Установить фрейм средствами класса JFrame;
        // использовать выбираемый по умолчанию диспетчер
        // граничной компоновки типа BorderLayout.
        JFrame jfrm = new JFrame("JTreeDemo");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(200, 250);

        // Создать самый верхний узел дерева.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Options");

        // Создать поддерево "A".
        DefaultMutableTreeNode a = new DefaultMutableTreeNode("A");
        top.add(a);
        DefaultMutableTreeNode a1 = new DefaultMutableTreeNode("A1");
        a.add(a1);
        DefaultMutableTreeNode a2 = new DefaultMutableTreeNode("A2");
        a.add(a2);

        // Создать поддерево "B".
        DefaultMutableTreeNode b = new DefaultMutableTreeNode("B");
        top.add(b);
        DefaultMutableTreeNode b1 = new DefaultMutableTreeNode("B1");
        b.add(b1);
        DefaultMutableTreeNode b2 = new DefaultMutableTreeNode("B2");
        b.add(b2);
        DefaultMutableTreeNode b3 = new DefaultMutableTreeNode("B3");
        b.add(b3);

        // Создать дерево.
        JTree tree = new JTree(top);

        // Ввести дерево на панели прокрутки.
        JScrollPane jsp = new JScrollPane(tree);

        // Ввести панель с полосами прокрутки на панели содержимого.
        jfrm.add(jsp);

        // Ввести метку на панели содержимого.
        JLabel jlab = new JLabel();
        jfrm.add(jlab, BorderLayout.SOUTH);

        // Обработать события выбора узлов дерева.
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent tse) {
                jlab.setText("Selection is " + tse.getPath());
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
                        new JTreeDemo();
                    }
                }
        );
    }
}
