package ch26_using_controls_and_layout_controllers_and_menus_from_AWT_library;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridLayoutDemo extends Frame {
    final static int n = 4;

    public GridLayoutDemo() {

        setLayout(new GridLayout(n, n, 10, 10));

        setFont(new Font("SansSerif", Font.BOLD, 24));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = i * n + j;
                if (k > 0) {
                    add(new Button("" + k));
                }
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        GridLayoutDemo gld = new GridLayoutDemo();

        gld.setSize(new Dimension(300,200));
        gld.setTitle("GridLayoutDemo");
        gld.setVisible(true);
    }
}
