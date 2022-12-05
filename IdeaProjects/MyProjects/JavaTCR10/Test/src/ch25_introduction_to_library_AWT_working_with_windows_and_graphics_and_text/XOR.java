package ch25_introduction_to_library_AWT_working_with_windows_and_graphics_and_text;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class XOR extends Frame {
    int chsX = 100;
    int chsY = 100;

    public XOR() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                chsX = x - 10;
                chsY = y - 10;
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
        g.setColor(Color.green);
        g.fillRect(20,40,60,70);

        g.setColor(Color.blue);
        g.fillRect(110,40,60,70);

        g.setColor(Color.black);
        g.fillRect(200,40,60,70);

        g.setColor(Color.red);
        g.fillRect(60,120,160,110);

        g.setXORMode(Color.black);
        g.drawLine(chsX - 10, chsY, chsX + 10, chsY);
        g.drawLine(chsX, chsY - 10, chsX, chsY + 10);
        g.setPaintMode();
    }

    public static void main(String[] args) {
        XOR xor = new XOR();

        xor.setSize(new Dimension(300, 260));
        xor.setTitle("XOR Demo");
        xor.setVisible(true);
    }
}
