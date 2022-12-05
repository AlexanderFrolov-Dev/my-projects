import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AnonymousInnerClassDemo extends Frame {
    String msg = "";

    public AnonymousInnerClassDemo() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                msg = "Mouse pressed";
                repaint();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg,20,80);
    }

    public static void main(String[] args) {
        AnonymousInnerClassDemo aicd = new AnonymousInnerClassDemo();

        aicd.setSize(new Dimension(200,150));
        aicd.setTitle("AnonymousInnerClassDemo");
        aicd.setVisible(true);
    }
}
