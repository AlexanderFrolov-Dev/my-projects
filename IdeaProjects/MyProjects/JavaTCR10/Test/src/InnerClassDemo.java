import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InnerClassDemo extends Frame {
    String msg = "";

    public InnerClassDemo() {
        addMouseListener(new MyMouseAdapter());
        addWindowListener(new MyWindowAdapter());
    }

    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            msg = "Mouse pressed.";
            repaint();
        }
    }

    class MyWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg,20,80);
    }

    public static void main(String[] args) {
        InnerClassDemo innerClassDemo = new InnerClassDemo();

        innerClassDemo.setSize(new Dimension(200,150));
        innerClassDemo.setTitle("InnerClassDemo");
        innerClassDemo.setVisible(true);
    }
}
