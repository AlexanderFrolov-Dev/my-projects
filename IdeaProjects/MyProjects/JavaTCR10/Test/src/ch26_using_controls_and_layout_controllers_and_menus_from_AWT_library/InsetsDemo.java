package ch26_using_controls_and_layout_controllers_and_menus_from_AWT_library;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsetsDemo extends Frame {

    public InsetsDemo() {

        setBackground(Color.cyan);

        setLayout(new BorderLayout());
        add(new Button("This is across the top."),
                BorderLayout.NORTH);
        add(new Label("The footer message might go here."),
                BorderLayout.SOUTH);
        add(new Button("Right"), BorderLayout.EAST);
        add(new Button("Left"), BorderLayout.WEST);

        String msg = "The reasonable man adapts " +
                "himself to the world;\n" +
                "the unreasonable one persists in " +
                "trying to adapt the world to himself.\n" +
                "Therefore all progress depends " +
                "on the unreasonable man.\n\n" +
                "        - George Bernard Shaw\n\n";

        add(new TextArea(msg), BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public Insets getInsets() {
        return new Insets(40,20,10,20);
    }

    public static void main(String[] args) {
        InsetsDemo insetsDemo = new InsetsDemo();

        insetsDemo.setSize(new Dimension(300,220));
        insetsDemo.setTitle("InsetsDemo");
        insetsDemo.setVisible(true);
    }
}
